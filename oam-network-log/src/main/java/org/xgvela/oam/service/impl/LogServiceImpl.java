package org.xgvela.oam.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.xgvela.oam.config.ESConfig;
import org.xgvela.oam.entity.log.EsLog;
import org.xgvela.oam.service.ILogService;
import org.xgvela.oam.utils.EsUtils;
import org.xgvela.oam.utils.ExportUtils;
import org.xgvela.oam.utils.JsonUtils;
import org.xgvela.oam.utils.RamPageUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.reindex.DeleteByQueryRequest;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortOrder;

import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@Service
@Slf4j
@AllArgsConstructor
public class LogServiceImpl implements ILogService {

    private static final String INDEX = "simulator*";
    private static final Long MAX = 10000L;
    public static final String PORT_KIBANA = "30016";
    public static final String HTTP = "http://";
    private final ESConfig esConfig;

    @Override
    public IPage<EsLog.VO.EsMessageVO> list(Long pageNum, Long pageSize, EsLog.VO.Request entity) {
        if (pageNum * pageSize > MAX) {
            throw new RuntimeException("More than" + MAX + "cannot be queried. Please provide more precise query criteria");
        }
        try {
            SearchResponse sr = getClient().search(getSearchRequest(pageNum, pageSize, entity), RequestOptions.DEFAULT);
            return RamPageUtils.getPage(getEntitys(sr).stream().map(EsLog::toEsMessageVO).collect(Collectors.toList()), pageNum, Math.min(sr.getHits().getTotalHits(), MAX));
        } catch (Exception e) {
            log.warn(e.getMessage(), e);
            return null;
        }
    }

    @Override
    public Boolean del(EsLog.VO.Request entity) {
        try {
            BoolQueryBuilder qb = new BoolQueryBuilder();
            getBoolQueryBuilder(entity, qb);
            getClient().deleteByQuery(new DeleteByQueryRequest(INDEX).setQuery(qb), RequestOptions.DEFAULT);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
        return true;
    }

    private void getBoolQueryBuilder(EsLog.VO.Request entity, BoolQueryBuilder qb) throws Exception {
        if (StringUtils.isNotEmpty(entity.getNeId())) {
            qb.must(new BoolQueryBuilder().should(QueryBuilders.wildcardQuery(EsLog.FIELD_MESSAGE, String.format("*%s*", entity.getNeId()))));
        }
        if (StringUtils.isNotEmpty(entity.getNeType())) {
            qb.must(new BoolQueryBuilder().should(QueryBuilders.wildcardQuery(EsLog.FIELD_MESSAGE, String.format("*%s*", entity.getNeType()))));
        }
        if (StringUtils.isNotEmpty(entity.getStartTime())) {
            Date startTime = DateUtils.parseDate(entity.getStartTime(), "yyyy-MM-dd HH:mm:ss");
            startTime = DateUtils.addHours(startTime, -8);
            entity.setStartTime(date2time(startTime));
        }
        if (StringUtils.isNotEmpty(entity.getEndTime())) {
            Date endTime = DateUtils.parseDate(entity.getEndTime(), "yyyy-MM-dd HH:mm:ss");
            endTime = DateUtils.addHours(endTime, -8);
            entity.setEndTime(date2time(endTime));
        }
        if (StringUtils.isNotEmpty(entity.getStartTime()) && StringUtils.isNotEmpty(entity.getEndTime())) {
            qb.must(QueryBuilders.rangeQuery(EsLog.FIELD_TIMESTAMP).from(entity.getStartTime()).to(entity.getEndTime()));
        } else {
            qb.must(QueryBuilders.rangeQuery(EsLog.FIELD_TIMESTAMP));
        }
    }

    @Override
    public void export(Long pageNum, Long pageSize, EsLog.VO.Request entity, String exportType, HttpServletResponse response) {
        List<EsLog.VO> entitys = new ArrayList<>();
        try {
            SearchResponse sr = getClient().search(getSearchRequest(pageNum, pageSize, entity), RequestOptions.DEFAULT);
            entitys = getEntitys(sr).stream().map(EsLog::toVO).collect(Collectors.toList());
        } catch (Exception e) {
            log.warn(e.getMessage(), e);
        }
        try {
            String fileName = "【 simulator 】- log" + System.currentTimeMillis();
            switch (EsLog.VO.ExportType.valueOf(exportType)) {
                case XLSX:
                    ExportUtils.exportXlsx(response, fileName, entitys, EsLog.VO.class);
                    break;
                case CSV:
                    ExportUtils.exportCsv(response, fileName, entitys, EsLog.VO.class);
                    break;
                case ZIP:
                    ExportUtils.exportCsvZip(response, fileName, entitys, EsLog.VO.class);
                    break;
            }
        } catch (Exception e) {
            log.error("export failed", e);
        }
    }

    @Override
    public String getKibanaUrl() {
        return HTTP + esConfig.getUrl() + ":" + PORT_KIBANA;
    }

    private RestHighLevelClient getClient() {
        return EsUtils.getRestHighLevelClient(esConfig.getUrl(), esConfig.getPort());
    }

    private SearchRequest getSearchRequest(Long pageNum, Long pageSize, EsLog.VO.Request entity) throws Exception {
        SearchSourceBuilder ssb = new SearchSourceBuilder();
        int _size = ObjectUtils.isNotEmpty(pageSize) ? pageSize.intValue() : MAX.intValue();
        int _from = ObjectUtils.isNotEmpty(pageNum) ? (pageNum.intValue() - 1) * _size : 0;
        ssb.from(_from).size(_size);
        ssb.sort(EsLog.FIELD_TIMESTAMP, SortOrder.ASC);
        BoolQueryBuilder qb = new BoolQueryBuilder();
        getBoolQueryBuilder(entity, qb);
        ssb.query(qb);
        return new SearchRequest(INDEX).source(ssb);
    }

    private static List<EsLog> getEntitys(SearchResponse sr) {
        List<EsLog> entitys = new ArrayList<>();
        for (SearchHit item : sr.getHits().getHits()) {
            EsLog entity = JsonUtils.js2o(item.getSourceAsString(), EsLog.class);
            entity.setId(item.getId());
            entitys.add(entity);
        }
        return entitys;
    }

    private String date2time(Object o) throws Exception {
        Date date;
        if (o instanceof Date) {
            date = (Date) o;
        } else {
            date = DateUtils.parseDate((String) o, "yyyy.MM.dd");
        }
        return DateFormatUtils.format(date, "yyyy-MM-dd'T'HH:mm:ss'.000Z'");
    }
}
