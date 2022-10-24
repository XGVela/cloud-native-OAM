# Cloud Native OAM Designs

## Background

**Cloud Native OAM** is a group of operation and management functions to serve network functions' basic operation and management requirements, which includes but not limited to NF's configuration manegement, NF's application topology management, NF's performance management, NF's alarm management, NF's log management, NF's heartbeat management, NF's signaling trace management, etc.

As the above operation and management functions are independent from network functions' business logics, they can be shared as common and reusable services among multiple network functions, which means they can be treated as a group of XGVela Telco PaaS capabilities. Providing cloud native OAM functions as a PaaS capabilities can relief developers from spending too much effort on non-NF logic design and coding.

## Design Principle

As listed above, cloud native OAM usually has following functions: NF register, NF management enabler, NF heartbeat management, NF configuration management, NF topology management, NF signaling tracing, NF performance management, NF alarm management, and NF log management.

Cloud native OAM interacts with NFs on southbound and upper layer management systems on northbound (such as OSS, MANO, etc.). So, following cloud native design principles, the above cloud native OAM functions have been designed as microservices in three logical layers:

> *	**Agent layer**
> 
> Cloud native OAM agent layer is in charge of southbound interaction with NFs, which include NF’s data collection and operational command delivery. NFs can establish connection with cloud native OAM and use its functions through unified mechanisms defined by agent layer.
> 
> Each cloud native OAM function could have an independent agent-layer microservice to ensure flexibility, while all cloud native OAM functions can have one agent-layer microservices to avoid managing too many microservices. Within Release 11.2022, the latter solution has been selected.
>
>Agent layer is usually deployed together with NFs in similar Kubernetes environment. One agent layer microservice can serve one or multiple NFs.
>
> * **Processing layer**
> 
> Processing layer is the core of cloud native OAM. It processes data and files collected by agent layer, which includes but not limited to data cleaning, data supplement, data/file storage, data update, rules setting and execution. Besides, it processes commands received by data sharing layer, and provides processing outcomes.
> 
> Each cloud native OAM function is recommended to have an independent processing-layer microservice.
> 
> Processing layer can be deployed with agent layer and NF in the same Kubernetes environment, as well as be deployed in different Kubernetes environment. One processing layer microservice can serve one or multiple agent layer microservices.
> 
> * **Data sharing layer**
> 
> Data sharing layer provides a group of northbound unified interfaces for management systems to subscribe data from it and send commands to NF through it. 
> 
> In release 11.2022, data sharing layer microservice and processing layer microservice of each cloud native OAM function are been wrapped together into one microservice to avoid increasing management complexity.

![avatar](https://github.com/QihuiZhao/cloud-native-OAM/blob/main/figure/three_layer_architecture.png)

## Functional design

### Network function register

NF register function is used by NF to register itself to cloud native OAM. After successful registration, NF register function will notify upper layer management system about the registration event.

By register, the following NF data are provided by NF and collected by NF register function: vnf_instance_id, vnf_name, vnf_type, vnf_manage_ip, vnf_manage_port. Besides NF register function will generate a record in database for this new NF.

The layering logical functional description for NF register function is in the following table.

|Logical layer	|Functional description|
|:-------- |:-----|
|Data sharing layer	|	Monitors new NF records. <br> Provide northbound interface for upper layer management systems to subscribe NF register notification.|
|Processing layer	|	Process NF data and update database.|
|Agent layer |	Provide unified southbound interface for NF to register to cloud native OAM. <br> Collect NF data and send to processing layer.|

### Network function management
NF management function is used by upper layer management systems to actually manage or un-manage a NF registered to cloud native OAM. This will actually gives upper layer management system the authorization to operate on a NF. It supports NF data query, NF microservice topology query, NF manage and NF un-manage. 

The layering logical functional description for NF management function is in tabl

|Logical layer	|Functional description|
|:-------- |:-----|
|Data sharing layer	|	Provide northbound interface for management systems to query NF data. <br>	Provide northbound interface for management systems to query NF microservice topology. <br>	Provide northbound interface for management systems to manage a specific NF. <br> Provide northbound interface for management systems to un-manage a managed NF.|
|Processing layer	|	Accept query command from data sharing layer, check database, and respond filtered data. <br>	Accept manage and un-manage command from data sharing layer, update NF management status in database, and record management system information.|
|Agent layer	|	Recollecting the newest NF data and NF microservice topology info after being managed by upper layer management system.|

### Network function heartbeat management

Once NF registered to cloud native OAM, it will report heartbeat to ensure it’s healthy. NF heartbeat management function tracks the heartbeat of NF and report alarms once heartbeat is abnormal.

The layering logical functional description for NF heartbeat management function is in table.

|Logical layer	|Functional description|
|:-------- |:-----|
|Data sharing layer	| N/A |
|Processing layer	|	Track heartbeat collected by agent layer, diagnose the NF status based on pre-defined rules (which defines what types of heartbeat indicates unhealthy NF), and store the NF status in databased with its diagnosing time.<br> When the heartbeat is abnormal and NF is diagnosed as unhealthy, generate alarm.|
|Agent layer |	Collect heartbeat of NF|

### Network function configuration management

NF configuration management function manages the configurations of NF and is often used by upper layer management systems to configure NF. It supports synchronizing the under-use configuration from NF, storing multiple versions of configuration, and support upper layer management systems to deliver, query, and update NF configurations.

The layering logical functional description for NF configuration management function is in table.

|Logical layer	|Functional description|
|:-------- |:-----|
|Data sharing layer|	Provide northbound interface for management systems to query configuration information of selected NF.<br>Provide northbound interface for management system to synchronize configuration details.<br>Provide northbound interface for management system to send configuration to NF and configure NF.<br>Provide northbound interface for management systems to select historical configuration and update NF configuration. (rollback)|
|Processing layer	|	Maintain configuration operations and configuration files.|
|Agent layer	|	Collect configuration from NF.<br>Send configuration to NF and track its execution outcomes.|

### Network function signaling tracing
Under design. To be updated in future release.

### Network function performance management

NF performance management function supports to collect performance data from NF, store the data, and support upper layer management system to subscribe performance data from cloud native OAM. 

As Prometheus is one of the most commonly used metrics collection software, NF performance management function selects Prometheus as the performance metrics backend.

The layering logical functional description for NF performance management function is in table.

|Logical layer	|Functional description|
|:-------- |:-----|
|Data sharing layer	|Provide northbound interface for performance data subscription.|
|Processing layer|	Store performance data collected by exporter into Prometheus.|
|Agent layer	|	Exporter for NF business performance data collection.|

### Network function alarm management
NF alarm management function collects NF alarms, process the collected alarms (e.g. remove duplicated alarms), store alarms and support upper layer management system to subscribe target NF’s alarm.
The layering logical functional description for NF alarm management function is in table.

|Logical layer	|Functional description|
|:-------- |:-----|
|Data sharing layer	|	Provide northbound interface for management system to subscribe target alarms of selected NF.|
|Processing layer	|	Process alarms and store them.|
|Agent layer	|	NF alarm collection.|

### Network function log management
NF log management function collect NF logs, store them, and support upper layer management systems to subscribe selected NF log.

Filebeat, logstash, elastic search and Kikana are integrated to implement this function.








