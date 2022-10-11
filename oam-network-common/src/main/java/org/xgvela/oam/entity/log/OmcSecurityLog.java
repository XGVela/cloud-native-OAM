package org.xgvela.oam.entity.log;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "omc_security_log")
@ApiModel(value = "OmcSecurityLog", description = "Log Management - security log ")
public class OmcSecurityLog implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "primary key ID")
	@ExcelIgnore
	@TableId(value = "id", type = IdType.AUTO)
	private Integer id;

	@ApiModelProperty(value = "created time ")
	@ExcelProperty(value = "time ", index = 7)
	@ColumnWidth(20)
	@TableField("create_time")
	private Date createTime;

	@ApiModelProperty(value = "description ")
	@ExcelProperty(value = "action ", index = 1)
	@ColumnWidth(15)
	@TableField("description")
	private String description;

	@ApiModelProperty(value = "Exception details ")
	@ColumnWidth(30)
	@ExcelProperty(value = "exception ", index = 9)
	@TableField("exception_detail")
	private String exceptionDetail;

	@ApiModelProperty (value = "type: the INFO | ERROR")
	@ColumnWidth(15)
	@ExcelProperty(value = "log level ", index = 6)
	@TableField("log_type")
	private String logType;

	@ApiModelProperty(value = "method ")
	@ExcelIgnore
	@TableField("method")
	private String method;

	@ApiModelProperty(value = "parameter ")
	@ColumnWidth(15)
	@ExcelProperty(value = "parameter ", index = 8)
	@TableField("params")
	private String params;

	@ApiModelProperty(value = "ip address ")
	@ExcelProperty(value = "source IP address ", index = 4)
	@ColumnWidth(15)
	@TableField("request_ip")
	private String requestIp;

	@ApiModelProperty(value = "time consuming ")
	@ExcelProperty(value = "time (ms)", index = 5)
	@ColumnWidth(15)
	@TableField("time")
	private Long time;

	@ApiModelProperty(value = "user name ")
	@ExcelProperty(value = "user ", index = 3)
	@ColumnWidth(15)
	@TableField("username")
	private String username;

	@ApiModelProperty (value = "ERROR: | SUCCESS")
	@ExcelProperty(value = "result ", index = 2)
	@ColumnWidth(15)
	@TableField("status")
	private String status;

	@ApiModelProperty(value = "Exception name ")
	@ExcelIgnore
	@TableField("exception_name")
	private String exceptionName;

	@ApiModelProperty(value = "action ")
	@ExcelProperty(value = "operation name ", index = 0)
	@ColumnWidth(15)
	@TableField("action")
	private String action;

	public OmcSecurityLog(String logType, Long time) {
		this.logType = logType;
		this.time = time;
	}

	public enum ExportType {
		XLSX, TXT
	}

	public String getLogStatus(String status){
		if("ERROR".equals(status)){
			return "failed ";
		}else if("SUCCESS".equals(status)){
			return "success ";
		}else{
			return "";
		}
	}
}