package org.xgvela.oam.entity.log;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;
import java.util.Optional;

@Data
@NoArgsConstructor
@ColumnWidth(25)
public class EsLog implements Serializable {
    public static final String FIELD_ID = "_id";
    public static final String FIELD_TIMESTAMP = "@timestamp";
    public static final String FIELD_LEVEL = "level";
    public static final String FIELD_NAMESPACE = "kubernetes.namespace";
    public static final String FIELD_POD = "kubernetes.pod.name";
    public static final String FIELD_CONTAINER = "kubernetes.container.name";
    public static final String FIELD_NODE = "kubernetes.node.name";
    public static final String FIELD_MESSAGE = "message";

    @JsonProperty(value = FIELD_ID)
    private String id;

    @JsonProperty(value = FIELD_TIMESTAMP)
    private String timeStamp;

    private String level;

    private Kubernetes kubernetes;

    @Data
    private static class Kubernetes {
        private String namespace;
        private Pod pod;
        private Container container;
        private Node node;
    }

    @Data
    private static class Pod {
        private String name;
    }

    @Data
    private static class Container {
        private String name;
    }

    @Data
    private static class Node {
        private String name;
    }

    private String message;

    public VO toVO() {
        VO.VOBuilder builder = VO.builder().id(id).timestamp(timeStamp).level(level);
        Optional.ofNullable(kubernetes).ifPresent(k -> builder.namespace(k.namespace)
                .pod(Optional.ofNullable(k.pod).map(o -> o.name).orElse(null))
                .container(Optional.ofNullable(k.container).map(o -> o.name).orElse(null))
                .node(Optional.ofNullable(k.node).map(o -> o.name).orElse(null))
        );
        builder.message(message);
        return builder.build();
    }

    public VO.EsMessageVO toEsMessageVO() {
        return VO.EsMessageVO.builder().timestamp(timeStamp).message(message).id(id).build();
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class VO {

        @ExcelProperty(value = "ID", index = 0)
        private String id;
        @ExcelProperty(value = "时间", index = 1)
        @ColumnWidth(50)
        private String timestamp;
        @ExcelProperty(value = "级别", index = 2)
        @ColumnWidth(15)
        private String level;
        @ExcelProperty(value = "命名空间", index = 3)
        @ColumnWidth(15)
        private String namespace;
        @ExcelProperty(value = "容器组", index = 4)
        @ColumnWidth(15)
        private String pod;
        @ExcelProperty(value = "容器", index = 5)
        @ColumnWidth(15)
        private String container;
        @ExcelProperty(value = "节点", index = 6)
        @ColumnWidth(15)
        private String node;
        @ExcelProperty(value = "信息", index = 7)
        private String message;

        @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
        @ExcelIgnore
        private Long clusterId;

        @Getter(onMethod_ = {@JsonIgnore})
        @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
        @ExcelIgnore
        private String date;

        @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
        @ExcelIgnore
        private String startTime;

        @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
        @ExcelIgnore
        private String endTime;

        @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
        @ExcelIgnore
        private String startDate;

        @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
        @ExcelIgnore
        private String endDate;

        @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
        @ExcelIgnore
        private String keyword;

        @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
        @ExcelIgnore
        private String exportType;

        public enum ExportType {
            XLSX, CSV, ZIP
        }

        @Data
        @Builder
        @NoArgsConstructor
        @AllArgsConstructor
        public static class Request implements Serializable {
            private String neId;
            private String neType;
            private String startTime;
            private String endTime;
        }

        @Data
        @Builder
        @NoArgsConstructor
        @AllArgsConstructor
        public static class EsMessageVO implements Serializable {
            private String timestamp;
            private String message;
            private String id;
        }
    }
}
