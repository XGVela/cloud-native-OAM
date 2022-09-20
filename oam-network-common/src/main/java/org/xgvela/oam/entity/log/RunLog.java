package org.xgvela.oam.entity.log;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;


@Data
@NoArgsConstructor
public class RunLog implements Serializable {

	@ApiModelProperty(value = "id")
	private String id;

	@ApiModelProperty(value = "ip")
	private String ip;

	@ApiModelProperty(value = "level")
	private String level;

	@ApiModelProperty(value = "serverName")
	private String serverName;

	@ApiModelProperty(value = "content")
	private String content;

	@ApiModelProperty(value = "createTime")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date createTime;
}
