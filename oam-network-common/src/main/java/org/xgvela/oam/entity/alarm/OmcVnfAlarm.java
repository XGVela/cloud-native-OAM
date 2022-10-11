package org.xgvela.oam.entity.alarm;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class OmcVnfAlarm implements Serializable{

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "province ")
	private String province;

	@ApiModelProperty (value = "managing state: lock release | | close")
	private String administrativeState;

	@ApiModelProperty(value = "NE name ")
	private String vnfName;

	@ApiModelProperty(value = "unique identifier ")
	private String rmuid;

	@ApiModelProperty (value = "yuan running state: online | main standby offline | | project")
	private String vnfStatus;

	@ApiModelProperty(value = "admin ip")
	private String vnfManageIp;

	@ApiModelProperty (value = "physical | yuan virtualization identification: virtual")
	private String vnfVirtName;
}