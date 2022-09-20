package org.xgvela.oam.entity.alarm.active;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class AlarmDto implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    private Long alarmSeq;

    private String alarmTitle;

    private int alarmStatus;

    private String alarmType;

    private int origSeverity;

    private String eventTime;

    private String alarmId;

    private String specificProblemID;

    private String specificProblem;

    private String neUID;

    private String neName;

    private String neType;

    private String objectUID;

    private String objectName;

    private String objectType;

    private String locationInfo;

    private String addInfo;

    private String PVFlag;

    private String province;

}
