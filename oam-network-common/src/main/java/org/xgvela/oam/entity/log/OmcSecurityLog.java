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
@ApiModel(value = "OmcSecurityLog", description = "OmcSecurityLog")
public class OmcSecurityLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    @ExcelIgnore
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
	
	@ApiModelProperty(value = "createTime")
	@ExcelProperty(value = "createTime", index = 7)
	@ColumnWidth(20)
	@TableField("create_time")
	private Date createTime;
	
	@ApiModelProperty(value = "description")
	@ExcelProperty(value = "description", index = 1)
	@ColumnWidth(15)
	@TableField("description")
	private String description;
	
	@ApiModelProperty(value = "exceptionDetail")
	@ColumnWidth(30)
    @ExcelProperty(value = "exceptionDetail", index = 9)
	@TableField("exception_detail")
	private String exceptionDetail;
	
	@ApiModelProperty(value = "logType")
	@ColumnWidth(15)
	@ExcelProperty(value = "logType", index = 6)
	@TableField("log_type")
	private String logType;
	
	@ApiModelProperty(value = "method")
	@ExcelIgnore
	@TableField("method")
	private String method;
	
	@ApiModelProperty(value = "params")
	@ColumnWidth(15)
    @ExcelProperty(value = "params", index = 8)
	@TableField("params")
	private String params;
	
	@ApiModelProperty(value = "requestIp")
	@ExcelProperty(value = "requestIp", index = 4)
	@ColumnWidth(15)
	@TableField("request_ip")
	private String requestIp;
	
	@ApiModelProperty(value = "time")
	@ExcelProperty(value = "time", index = 5)
	@ColumnWidth(15)
	@TableField("time")
	private Long time;
	
	@ApiModelProperty(value = "username")
	@ExcelProperty(value = "username", index = 3)
	@ColumnWidth(15)
	@TableField("username")
	private String username;
	
	@ApiModelProperty(value = "status")
	@ExcelProperty(value = "status", index = 2)
	@ColumnWidth(15)
	@TableField("status")
	private String status;
	
	@ApiModelProperty(value = "exceptionName")
	@ExcelIgnore
	@TableField("exception_name")
	private String exceptionName;
	
	@ApiModelProperty(value = "action")
	@ExcelProperty(value = "action", index = 0)
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
            return "failed";
        }else if("SUCCESS".equals(status)){
            return "success";
        }else{
            return "";
        }
    }
}
