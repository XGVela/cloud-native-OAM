package org.xgvela.oam.entity.alarm.sys;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;


@Data
@TableName("sys_conf")
public class SysConf implements Serializable {

	private static final long serialVersionUID = 6407287687421847576L;

	@TableId(value = "id", type = IdType.AUTO)
	private Integer id;

	@TableField("code")
	private String code;

	@TableField("value")
	private String value;

	@TableField("name")
	private String name;

	@TableField("sys_code")
	private String sysCode;

	@TableField("remark")
	private String remark;
}
