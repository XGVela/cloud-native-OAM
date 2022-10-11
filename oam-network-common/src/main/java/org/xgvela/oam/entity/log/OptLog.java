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

	@ApiModelProperty(value = "Operator ")
	private String username;

	@ApiModelProperty(value = "requested IP")
	private String requestIp;

	@ApiModelProperty(value = "details ")
	private String description;

	@ApiModelProperty(value = "request method ")
	private String method;

	@ApiModelProperty(value = "request parameter ")
	private String params;

	@ApiModelProperty(value = "result of request ")
	private String result = "";

	@ApiModelProperty(value = "log type ")
	private String logType;

	@ApiModelProperty(value = "service name ")
	private String serverName;

	@ApiModelProperty(value = "log level ")
	private String level;

	@ApiModelProperty(value = "request path ")
	private String requestUri;

	@ApiModelProperty(value = "Response time (ms) ")
	private Long responseTime;

	@ApiModelProperty(value = "Exception details ")
	private String exceptionDetail = "";

	@ApiModelProperty(value = "Tenant number ")
	private String developerId;

	@ApiModelProperty(value = "created time ")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date createTime;

	public OptLog(String serverName, String level, Long time) {
		this.serverName = serverName;
		this.responseTime = time;
		this.level = level;
	}
}