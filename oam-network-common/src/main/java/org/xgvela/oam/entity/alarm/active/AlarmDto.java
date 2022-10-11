package org.xgvela.oam.entity.alarm.active;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class AlarmDto implements Serializable{

    private static final long serialVersionUID = 1L;
    // Serial number of the alarm message, which identifies the alarm event in the OMC to determine whether the alarm message is lost. 1. The initial number starts with 1. If the number exceeds the maximum positive integer (2^31-1), the number starts with 1 again
    private Long alarmSeq;
    // Indicates the alarm event title
    private String alarmTitle;
    // Alarm status 1: active alarm. 0: indicates that the alarm is cleared.
    private int alarmStatus;
    // Indicates the alarm type
    private String alarmType;
    // Original severity 1: level-1 alarm (Critical). 2: indicates a secondary alarm (Major). 3: indicates a level-3 alarm (Minor). 4: Level 4 Warning
    private int origSeverity;
    // Indicates the event occurrence time, alarm generation time, or alarm clearance time. The 2013-08-08 07:45:43
    private String eventTime;
    // The alarm event uniquely identifies the manufacturer's device alarm serial number, which corresponds to the alarm in the clear alarm.
    private String alarmId;
    // ID of the cause of the alarm problem
    private String specificProblemID;
    // The cause of the alarm problem
    private String specificProblem;
    // Alarm NE UID Specifies the rmUID of the alarm device, such as the rmUID of the MME or ENB. For VNMF, enter VNFMID, which is the same as the VNFMID obtained by NFVO. In particular, when you enter VNFMID, the length is 32 bits, that is, rmUID. For details, see OMC Northbound Interface Data Specification - Public Data Volume.
    private String neUID;
    // The name of the alarm NE must be the same as userLabel in the public field of the resource data file
    private String neName;
    // Alarm NE device type "2G\3G\4G Upgrade NE, high principle. If the SGSN is upgraded to an MME, set Device type to MME. To merge the nes, enter them in a list. (semicolon) : PGW/SGW fusion NE is written as PGW. SGW. For VNFM, enter VNFM. For OMC, enter OMC.
// For private subnet alarms, set the standard primary NE neType and objectUID. The located subnet information is reported in locationInfo. neType is PCRF. objectUID is the rmUID of the active NE PCRF. For locationInfo, see locationInfo Value Range and Description.
    private String neType;
    // Alarm location object UID "Specifies the rmUID of the alarm object. For details, see OMC Northbound Interface Data Specification - Public Data Volume, for example, the rmUID of the port and RRU.
// For private subnet alarms, set the standard primary NE neType and objectUID. The located subnet information is reported in locationInfo. neType is PCRF. objectUID is the rmUID of the active NE PCRF. For locationInfo, see locationInfo Value Range and Description.
    private String objectUID;
    // Alarm Location Object Name Specifies the alarm object name. The name of the wireless or core network major must be the same as the public field userLabel in the resource data file
    private String objectName;
    // Alarm Locating Object Resource Type "Specifies the alarm locating object resource type. For VNFM, set this parameter to VNFM. For OMC, set this parameter to the name of the object managed by OMC.
    private String objectType;
    // If alarm location information has multiple name values, use the name-value pair format. "":"" Split between name and value. Multiple name and value pairs use "";" "Division. Values must not contain "":"" and "";" ".
// Traditional networks: such as frames, slots, and boards.
//NFV network: 1. At least the VMID of the VNF should be included. Named "VMID ", the multiple identifiers in the value are separated by "","". VMID: aaa, BBB. Multiple name-value pairs such as host name :xxx; VMID:aaa,bbb; IPv4:nnn
//2. If the alarm cannot be located to one or more VMS or is unrelated to VMS, enter ALL in VMID, as shown in VMID:ALL
// When the reported alarm NE can locate the subnet of the primary NE and the subnet is a non-standardized private NE, the subnet information must be reported. This applies to both traditional and NFV networks. The value is defined by the manufacturer and must contain at least the name of the subnet. You can add other information to help locate the subnet. Values are separated by "","". subNeInfo:PCRFDB,ID=xxxx,DN=yyyy"
    private String locationInfo;
    // Alarm Auxiliary information "Multiple name and value pairs, used to customize information and backfill the section identifier that may be affected by the alarm. "":"" Split between name and value. Multiple name and value pairs use "";" "Division. Values must not contain "":"" and "";" ".
// If the KEY value field of the alarm standardization is not covered by the common model, fill in this field. For example, the auxiliary alarm interpretation field used in alarm standardization.
//1. Core network and wireless network for which the slicing service is enabled alarms strongly related to slicing are mandatory. Named ""S-NSSAI"", the multiple identifiers in the value are separated by "","".
// The format of S-NSSAI is 3GPP specification, which is 32bit itself. The first 8bit represents SST and the last 24bit represents SD.
// This variable contains 1 to 10 characters and consists of 1-3 characters for SST + "-" + 6 characters for SD
//SST This parameter is mandatory. The 8bit decimal number is directly changed to a string (for example, 1-> "1", 133-> "133").
// The last 6 characters are optional, and the corresponding 24-bit hexadecimal number is directly converted to a string (for example, the number FEFA89-> "FEFA89")"
    private String addInfo;
    // The OMC that manages both PNF and VNF is mandatory. The virtual and real nes for alarm location are PNF and VNF
    private String PVFlag;
    // NE Service Province Indicates the NE service province. For the code, see OMC Northbound Interface Data Specifications - Public Data Volume. If it serves more than one province, the province is abbreviated as ""; "Segmentation. If the NE serves the entire Northeast region, including Heilongjiang, Jilin, and Liaoning, fill in this field: HL; JL; "LN" for VNFM/OMC alarms, enter "VNFM/OMC deployment province".
    private String province;
// Unique to wireless alarms, alarm remote object UID Unique identifier of the alarm remote object, such as RRU\BBU, which is consistent with the unique identifier of the resource. If the alarm is not related to this field, fill in the blank
//    private String rNeUID;
// // Remote object name of the alarm remote object. RRU\BBU, etc. If the alarm is not related to this field, fill in the blank
//    private String rNeName;
// // Remote object type of the remote object of the alarm, such as RRU-GSM, RRU-TD, and RRU-LTE. If the alarm is not related to this field, fill in the blank
//    private String rNeType;
// // Transmission alarm specific Device Board model Board \PTP\CTP alarm This parameter is mandatory
//    private String holderType;
// // Indicates the KEY value in the WDM of the ACAR at which the alarm is detected
//    private String alarmCheck;
// // The transmission alarm is unique, and the layer rate is required for SDH alarm standardization
//    private int layer;
// // Specifies the transmission alarm type
//    private String relateType;
// // Indicates the serial number of root alarms that are unique to transmission alarms
//    private String correlatedSourceAlarmSN;
// // Indicates the project status information
//    private String maintenanceStatus;
// // The transmission alarm is unique and belongs to the local city
//    private String location;
// // IP Specifies the IP address of the NE
//    private String neIp;
// // IP Specifies whether an active alarm has a clear alarm. 1: there is a lawsuit; 0: no complaint.
//    private int pairFlag;
// // IP Alarm specific, frequency Active alarm count Frequency alarm count. For non-frequency alarms, this field is left blank.
//    private int alarmCount;
}