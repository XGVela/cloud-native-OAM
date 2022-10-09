package org.xgvela.oam.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.xgvela.oam.config.ESConfig;
import org.xgvela.oam.entity.log.EsLog;
import org.xgvela.oam.service.ILogService;
import org.xgvela.oam.utils.EsUtils;
import org.xgvela.oam.utils.ExportUtils;
import org.xgvela.oam.utils.JsonUtils;
import org.xgvela.oam.utils.RamPageUtils;
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

    private static final String INDEX = "*";
    private static final Long MAX = 10000L;
    public static final String PORT_KIBANA = "30016";
    public static final String HTTP = "http://";
    private  final ESConfig esConfig;

    @Override
    public IPage<EsLog.VO> list(Long current, Long size, EsLog.VO.Request entity) {
        if (current * size > MAX) {
            throw new RuntimeException("exceed" + MAX + "data, can not be queried, please provide more accurate query conditions");
        }
        try {
            SearchResponse sr = getClient().search(getSearchRequest(current, size, entity), RequestOptions.DEFAULT);
            return RamPageUtils.getPage(getEntitys(sr).stream().map(EsLog::toVO).collect(Collectors.toList()), current, Math.min(sr.getHits().getTotalHits(), MAX));
        } catch (Exception e) {
            log.warn(e.getMessage(), e);
            return null;
        }
    }

    @Override
    public Boolean del(EsLog.VO entity) {
        try {
            if (StringUtils.isNotEmpty(entity.getStartDate()) || StringUtils.isNotEmpty(entity.getEndDate())) {
                getClient().deleteByQuery(new DeleteByQueryRequest(INDEX).setQuery(
                        new BoolQueryBuilder().must(QueryBuilders.rangeQuery(EsLog.FIELD_TIMESTAMP).to(date2time(entity.getEndDate())))
                ), RequestOptions.DEFAULT);
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
        return true;
    }

    @Override
    public void export(Long current, Long size, EsLog.VO.Request entity,String exportType, HttpServletResponse response) {
        List<EsLog.VO> entitys = new ArrayList<>();
        try {
            SearchResponse sr = getClient().search(getSearchRequest(current, size, entity), RequestOptions.DEFAULT);
            entitys = getEntitys(sr).stream().map(EsLog::toVO).collect(Collectors.toList());
        } catch (Exception e) {
            log.warn(e.getMessage(), e);
        }
        try {
            String fileName = "simulator log -" + System.currentTimeMillis();
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
            log.error("Export failed", e);
        }
    }

    @Override
    public String getKibanaUrl() {
        return HTTP + esConfig.getUrl() + ":" + PORT_KIBANA;
    }

    private RestHighLevelClient getClient() {
        return EsUtils.getRestHighLevelClient(esConfig.getUrl(), esConfig.getPort());
    }

    private SearchRequest getSearchRequest(Long current, Long size, EsLog.VO.Request entity) throws Exception {

        SearchSourceBuilder ssb = new SearchSourceBuilder();
        int _size = ObjectUtils.isNotEmpty(size) ? size.intValue() : MAX.intValue();
        int _from = ObjectUtils.isNotEmpty(current) ? (current.intValue() - 1) * _size : 0;
        ssb.from(_from).size(_size);
        ssb.sort(EsLog.FIELD_TIMESTAMP, SortOrder.DESC);
        BoolQueryBuilder qb = new BoolQueryBuilder();
        if (StringUtils.isNotEmpty(entity.getStartTime())) {
            Date startTime = DateUtils.parseDate(entity.getStartTime(), "yyyy-MM-dd HH:mm:ss");
            entity.setStartTime(date2time(startTime));
        }
        if (StringUtils.isNotEmpty(entity.getEndTime())) {
            Date endTime = DateUtils.parseDate(entity.getEndTime(), "yyyy-MM-dd HH:mm:ss");
            entity.setEndTime(date2time(endTime));
        }
        qb.must(QueryBuilders.rangeQuery(EsLog.FIELD_TIMESTAMP).from(entity.getStartTime()).to(entity.getEndTime()));

        if (StringUtils.isNotEmpty(entity.getNeId())) {
            qb.must(new BoolQueryBuilder().should(QueryBuilders.wildcardQuery(EsLog.FIELD_CONTAINER, String.format("*%s*", entity.getNeId()))));
        }
        if (StringUtils.isNotEmpty(entity.getNeType())) {
            qb.must(new BoolQueryBuilder().should(QueryBuilders.wildcardQuery(EsLog.FIELD_CONTAINER, String.format("*%s*", entity.getNeType()))));
        }
        ssb.query(qb);
        return new SearchRequest(INDEX).source(ssb);
    }

    private static List<EsLog> getEntitys(SearchResponse sr) {
        List<EsLog> entities = new ArrayList<>();
        for (SearchHit item : sr.getHits().getHits()) {
            EsLog entity = JsonUtils.js2o(item.getSourceAsString(), EsLog.class);
            entity.setId(item.getId());
            entities.add(entity);
        }
        return entities;
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
