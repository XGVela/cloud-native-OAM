package org.xgvela.oam.entity.conf;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;


@Data
@Builder
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "OamVnfConfigFile entity ", description = "OamVnfConfigFile entity ")
public class OamVnfConfigFile extends Model<OamVnfConfigFile> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "primary key ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "NE instance ID")
    private String neId;

    @ApiModelProperty(value = "NE type ")
    private String neType;

    @ApiModelProperty(value = "Configuration version number, OAM maintains ")
    private String cfVersion;

    @ApiModelProperty(value = "configuration file path ")
    private String cfPath;

    @ApiModelProperty(value = "profile name ")
    private String cfName;

    @ApiModelProperty(value = "Configuration file size, in MB ")
    private String cfSize;

    @ApiModelProperty(value = "Configuration update time ")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date cfUpdatetime;

    @ApiModelProperty(value = "Is current version ")
    private Boolean isUse;

    @ApiModelProperty(value = "config file contents ")
    private String fileContent;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class VnfConfigDeliveryRequest implements Serializable {
        String neType;
        String neId;
        String vnfName;
        String callbackUrl;
        public ConfFile file;

        @Data
        @Builder
        @NoArgsConstructor
        @AllArgsConstructor
        public static class ConfFile implements Serializable {
            String cfName;
            String cfPath;
            String version;
        }
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ConfUpdateRequest implements Serializable {
        String taskId;
        String neId;
        String result;
        String message;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Vnf implements Serializable {
        String neType;
        String neId;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class VnfRequest implements Serializable {
        String neType;
        String neId;
        String taskId;
    }
}