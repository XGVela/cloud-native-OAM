package org.xgvela.oam.entity.alarm.sys;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * Dict entity
 * </p>
 */
@Data
@TableName("sys_dict")
@ApiModel(value = "Dict entity", description = "Dict entity")
public class Dict implements Serializable {

	private static final long serialVersionUID = 3292061036734358767L;

	@ApiModelProperty(value = "id")
	@TableId(value = "id", type = IdType.AUTO)
	private Long id;

	@ApiModelProperty(value = "label")
	private String label;

	@ApiModelProperty(value = "value")
	private String value;

	@ApiModelProperty(value = "remark")
	private String remark;

	@ApiModelProperty(value = "sort")
	private String sort;

	@ApiModelProperty(value = "pid")
	@TableField("pid")
	private Long pid;

	@ApiModelProperty(value = "createTime", hidden = true)
//	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
//	@JsonSerialize(using = LocalDateTimeSerializer.class)
	@TableField("create_time")
	private Date createTime;

	@ApiModelProperty(value = "children")
	@TableField(exist = false)
	private List<Dict> children;

}
