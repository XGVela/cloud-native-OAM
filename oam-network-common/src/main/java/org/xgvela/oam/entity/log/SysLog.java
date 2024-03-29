package org.xgvela.oam.entity.log;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

@Builder
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class SysLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String username;

    private String description;

    private String method;

    private String params;

    private String type;

    private String ip;

    private Long time;

    private String exception;

    private Date createTime;

    private Integer lx;

    private String neId;

    private String action;

    private String status;

    public enum ExportType {
        XLSX, TXT
    }

    @AllArgsConstructor
    @ToString
    public enum lxType {
        SYSTEM(1, "system "), OMC_OPREATE(2, "omc operation log "), NET_OPREATE(3," NE operation log "), ALARM_DATA(4, "Alarm data log ");
        @Getter
        private final int id;
        @Getter
        private final String name;
    }

    @AllArgsConstructor
    @ToString
    public enum actionType {
        CONFIG_MANAGE, VNF_OPERATION, PM_MANAGE,
        ALARM_MANAGE, MAINTAIN_MANAGE, LOG_MANAGE,
        SYS_LOG, OTHER;
    }

    public String getLogStatus(String status) {
        if ("ERROR".equals(status)) {
            return "failed ";
        } else if ("SUCCESS".equals(status)) {
            return "success ";
        } else {
            return "";
        }
    }
}