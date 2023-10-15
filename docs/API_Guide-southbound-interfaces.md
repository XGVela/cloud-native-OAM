# API Guide - Cloud native OAM southbound interfaces

## Writing Purpose
Cloud native OAM southbound interfaces are gRPC APIs supporting lower layer network functions to to upload FCAPS data to cloud native OAM.

## Target User
Deveoplers and Operators of network functions.

## Southbound Interfaces
### *Network Function Register*
When network function has been initiated for the first time, it should register itself to cloud native OAM to be able to be managed.

*RPC Interface*

>service NfRegisterService {
>
>  &emsp;&emsp; rpc NfRegister(RegisterReq)returns(RegisterRsp){}
>
>}

*RPC Request Sample*
>message RegisterReq
>
>{
>
>	&emsp;&emsp;string neId   = 1; //NF Instance ID
>
>	&emsp;&emsp;string vnfName = 2; //NF Name
>
>&emsp;&emsp;string neType = 3; //NF Type
>
>&emsp;&emsp;string vnfManageIp = 4; //NF Management IP
>
>&emsp;&emsp;string vnfManagePort = 5; //NF Management Port
>
>&emsp;&emsp;string vnfSignalPort = 6; //NF signal tracing Port
>
>}

*RPC Respond Sample*
>message RegisterRsp
>
>{
>
>	&emsp;&emsp;string result = 1;          //0 succ, other fail
>
>}

### *Network Function Heartbeat Report*
This interface is used by network function to report heartbeat to cloud native OAM. This heartbeat can help to know the connection status between NF and cloud native OAM.

*RPC Interface*

>service HeartBeatReportService {
>
>  &emsp;&emsp; rpc HeartBeatReport(HeartReq)returns(HeartRsp){}
>
>}

*RPC Request Sample*
>message HeartReq{
>
>{
>
>	&emsp;&emsp;string neType = 1;
>
>	&emsp;&emsp;string neId = 2;
>
>}

*RPC Respond Sample*
>message HeartRsp{
>
>{
>
>	&emsp;&emsp;string result = 1; //0 succ, other fail
>
>}

### *Network Function Alarm Report*
This interface is used by network function to report generated alarms to cloud native OAM. 

*RPC Interface*

>service AlarmReportService {
>
>  &emsp;&emsp;  rpc AlarmReport(AlarmReq)returns(AlarmRsp){}
>
>}

*RPC Request Sample*
>message AlarmReq
>
>{
>
>	&emsp;&emsp;string nfType   = 1;
>
>&emsp;&emsp;string neId = 2;
>
> &emsp;&emsp;uint32 alarmId = 3;
>
>&emsp;&emsp;uint64 alarmCurrentTime = 4;
>
>&emsp;&emsp;uint32 alarmLevel = 5;
>
> &emsp;&emsp;uint32 alarmType = 6;
>
> &emsp;&emsp;uint32 alarmStatusType = 7; // 0 represent there is active alarm, 1 represent alarm clear
>
> &emsp;&emsp;string alarmInfo = 8;
>
>}

*RPC Respond Sample*
>message AlarmRsp
>
>{
>
>	&emsp;&emsp;string result = 1; //0 succ, other fail
>
>}

### *Network Function Performance Data Report*
This interface is used by network function to report performance data to cloud native OAM. The metrics are defined by network function developers.

*RPC Interface*

>service PerfStatisticsService {
>
>  &emsp;&emsp;   rpc StatisticsReq(StatsInfoReq)returns(stream StatsInfoRsp){}
>
>}

*RPC Request Sample*
>message StatsInfoReq
>
>{
>
>	&emsp;&emsp;map<string,uint32> statsResultInfo = 1;
>
>}

*RPC Respond Sample*
>message StatsInfoRsp
>
>{
>
>	&emsp;&emsp;string result = 1; //0 succ, other fail
>
>}

### *Network Function Configuration File Get Interface*
This interface is used by cloud native OAM to get configuration files that is in use of network functions.

*RPC Interface*

>service SyncConfigService {
>
>  &emsp;&emsp;   rpc GetConfigFile(GetFileReq)returns(stream GetFileResp){}
>
>}

*RPC Request Sample*
>message GetFileReq
>
>{
>
>	&emsp;&emsp;string nfType = 1;
>
>&emsp;&emsp;string neId = 2;
>
>&emsp;&emsp;string fileName = 3;
>
>}

*RPC Respond Sample*
>message GetFileResp
>
>{
>
>	&emsp;&emsp;string result = 1; //0 succ, other fail
>
>&emsp;&emsp;bytes fileData = 2; //file contents,if success
>
>}

### *Configuration Interface*
This interface is used by Cloud Native OAM to push configuration file to network function.

*RPC Interface*

>service ConfigureManagerService {
>
>  &emsp;&emsp;   rpc UpdateConfigFile(streamUpdateCfgFileReq)returns(UpdateCfgFileRsp){}
>
>}

*RPC Request Sample*
>message UpdateCfgFileReq
>
>{
>
>&emsp;&emsp;string nfType = 1;
>
>&emsp;&emsp;string neId = 2;
>
>&emsp;&emsp;uint32 taskId = 3;
>
>&emsp;&emsp;string fileName = 4;
>
>&emsp;&emsp;bytes  fileData = 5; //file contents
>
>}

*RPC Respond Sample*
>message UpdateCfgFileRsp
>
>{
>
>	&emsp;&emsp;string updateRsp = 1; // 0 succ 1 failed 2 need reboot
>
>}

### *Network Function Configuration Updated Confirmation Interface*
This interface is used by network functions to report outcome of configuration update.

*RPC Interface*

>service ConfigUpdateResultService {
>
>  &emsp;&emsp;   rpc cfgUpdateResult(CfgResultNotifyReq) returns (CfgResultNotifyResp) {}
>
>}

*RPC Request Sample*
>message CfgResultNotifyReq
>
>{
>
>&emsp;&emsp;string NfType = 1;
>
>&emsp;&emsp;string InstanceId = 2;
>
>&emsp;&emsp;uint32 taskId = 3;
>
>&emsp;&emsp;string Result = 4;
>
>}

*RPC Respond Sample*
>message CfgResultNotifyResp
>
>{
>
>	&emsp;&emsp;string Result = 1; // 0 succ 1 failed 2 need reboot
>
>}

