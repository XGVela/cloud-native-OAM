package org.xgvela.oam.entity.alarm;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class OmcVnfAlarm implements Serializable{

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "province")
	private String province;
	
	@ApiModelProperty(value = "Administrative Status: Locked | Released | Closed")
	private String administrativeState;
		
	@ApiModelProperty(value = "vnfName")
	private String vnfName;
		
	@ApiModelProperty(value = "rmuid")
	private String rmuid;
		
	@ApiModelProperty(value = "Network element operation status: online | main standby | offline | project")
	private String vnfStatus;

	@ApiModelProperty(value = "Manage ip")
    private String vnfManageIp;

	@ApiModelProperty(value = "VirtName：physical | virtual")
	private String vnfVirtName;
}
