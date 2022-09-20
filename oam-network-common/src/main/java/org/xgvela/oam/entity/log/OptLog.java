package org.xgvela.oam.entity.log;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;


@Data
@NoArgsConstructor
public class OptLog implements Serializable {

	@ApiModelProperty(value = "id")
	private String id;

	@ApiModelProperty(value = "username")
	private String username;

	@ApiModelProperty(value = "requestIp")
	private String requestIp;

	@ApiModelProperty(value = "description")
	private String description;

	@ApiModelProperty(value = "method")
	private String method;

	@ApiModelProperty(value = "params")
	private String params;

	@ApiModelProperty(value = "result")
	private String result = "";

	@ApiModelProperty(value = "logType")
	private String logType;

	@ApiModelProperty(value = "serverName")
	private String serverName;

	@ApiModelProperty(value = "level")
	private String level;

	@ApiModelProperty(value = "requestUri")
	private String requestUri;

	@ApiModelProperty(value = "responseTime")
	private Long responseTime;

	@ApiModelProperty(value = "exceptionDetail")
	private String exceptionDetail = "";

	@ApiModelProperty(value = "developerId")
	private String developerId;

	@ApiModelProperty(value = "createTime")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date createTime;

	public OptLog(String serverName, String level, Long time) {
		this.serverName = serverName;
		this.responseTime = time;
		this.level = level;
	}
}
