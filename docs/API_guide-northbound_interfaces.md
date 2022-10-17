# API Guide - Cloud native OAM northbound interfaces

## Writing Purpose

Cloud native OAM northbound interfaces are RESTful APIs supporting upper layer management systems to do FCAPS operations on NFs. 

## Target user

Developers and operators of upper layer management systems, cloud native OAM developer

## Northbound interfaces

### *Authentication and Authorization - Get token*

When accessing cloud native OAM, upper layer management systems should provide user name and password to apply for a token, which should be carried in the header for all other API requests.

* `POST` &nbsp;&nbsp;&nbsp;&nbsp; */api/rest/securityManagement/{apiVersion}/oauth/token*

Normal Response Code: 200

Error Response Code: 500

Input:
|Name  |In |Type  |Description  |
| :--- | :--- | :--- | :--- |
|grantType |body  |string  | The type of authentication applied. When applying for Token, "password" is used here. |
|userName | body | string | User name, which consists of letters and numbers. Letters are not case sensitive. |
|value  | body | string | Password |

<u>Request Example</u>
```
POST /api/rest/securityManagement/v1/oauth/token HTTP/1.1
Host: serverIP:port
Content-Type: application/json;charset=UTF-8
Content-Length:…
{
    "grantType": "password", 
    "userName": "test", 
    "value": "XXXXXX"
}
```
<u>Response Example: Normal response</u>
```
{
    "status": 200,
    "message": "Request is successful",
    "data": {
        "accessToken": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2NjQwODU1NjIsInVzZXJJZCI6ImFkbWluIn0.o-CfwXC_uFIOz-m9qeEBMWREMRlVcGODSZwIdXzmR70",
        "expires": 86400000
    }
}
```
<u>Response Example: Error response</u>
```
{
    "timestamp": 1663999198777,
    "status": 500,
    "error": "Internal Server Error",
    "message": "User Name or Password Error",
    "path": "/api/rest/securityManagement/v1/oauth/token"
}
```

* * *

### *Subscribe data of newly registered NF*

Interfaces support upper layer management systems subscribing data of NFs through cloud native OAM. Subscribing data types include NF register notification, NF alarm, NF performance.

* `Post` &nbsp;&nbsp;&nbsp;&nbsp; */system/jobs/register*

Create subscription of new NF register info.

Normal Response Code: 200

Error Response Code: 401, 503

Input:
|Name  |In |Type  |Description  |
| :--- | :--- | :--- | :--- |
| systemId | body | string | ID of upper layer management system |
| callbackurl | body | string | URL for cloud native OAM to send target data to |

<u>Request Example</u>
```
{
    "systemId": "BJ-1",
    "callbackUrl": “http://192.168.112.100:8080/reschange"
}
```

<u>Response Example</u>
```
{
     Status:200,
     Message: Request is successful
}
```
### *Unsubscribe data of newly registered NF*

* `DELETE` &nbsp;&nbsp;&nbsp;&nbsp; */system/jobs/register{systemId}*

Delete existing subscription of new NF register info.

Normal Response Code: 200

Error Response Code: 401, 503

Input:
|Name  |In |Type  |Description  |
| :--- | :--- | :--- | :--- |
| systemId | body | string | ID of upper layer management system |

<u>Response Example</u>
```
{
     Status:204,
     Message: Request is successful
}
```

### *Query "newly registered NF" info of target upper layer manegement system*
* `GET` &nbsp;&nbsp;&nbsp;&nbsp; */system/jobs/register*

Get new NF register info.

Normal Response Code: 200

Error Response Code: 401, 503

Input:
|Name  |In |Type  |Description  |
| :--- | :--- | :--- | :--- |
| systemId | body | string | ID of upper layer management system |

<u>Response Example</u>
```
{
     Status:200,
     Message: Request is successful,
     Data:[
                { 
                    "systemid":"bj-1",
                    "callbackUrl":"https://192.168.100.2:8080/reschange"
                }
         ]
}
```
### *Subscribe business data (alarm & performance) of NF*
* `POST` &nbsp;&nbsp;&nbsp;&nbsp; */system/jobs/business*

Create subscription of NF alarm or performance data.

Normal Response Code: 200

Error Response Code: 401, 503

Input:
|Name  |In |Type  |Description  |
| :--- | :--- | :--- | :--- |
| systemId | body | string | ID of upper layer management system |
| neid | body | string | ID of target NF that upper layer management system wants to subscribe |
| datatype | body | string | Types of data upper layer management syste, want to subscribe. The type can be "alarm" for alarm, "perf" for performance. To subscribe multiple types of data, using commas to seperate. |
| callbackurl | body | string | URL for cloud native OAM to send target data to |

*Request Example*
```
{
    "systemId": "BJ-1",
    "neId": "upf01,upf02",
    "dataType": "alarm,perf",
    "callbackUrl": “http://192.168.112.100:8080/reschange"
}
```
*Response Example*
```
{
     Status:200,
     Message: Request is successful
}
```

### *Unsubscribe business data (alarm & performance) of NF*
* `DELETE` &nbsp;&nbsp;&nbsp;&nbsp; */system/jobs/business{systemId}*

DELETE existing subscription of NF alarm or performance data.

Normal Response Code: 200

Error Response Code: 401, 503

Input:
|Name  |In |Type  |Description  |
| :--- | :--- | :--- | :--- |
| systemId | body | string | ID of upper layer management system |

<u>Response Example</u>
```
{
     Status:204,
     Message: Request is successful
}
```

### *Query subscription data types of target upper layer manegement systemF*
* `GET` &nbsp;&nbsp;&nbsp;&nbsp; *system/jobs/business*

Get NF's subscription info of NF alarms and performance.

Normal Response Code: 200

Error Response Code: 401, 503

Input:
|Name  |In |Type  |Description  |
| :--- | :--- | :--- | :--- |
| systemId | body | string | ID of upper layer management system |

<u>Response Example</u>
```
{
     Status:200,
     Message:Successful,
     Data:[
                { 
                    "systemid":"bj-1",
                    "neId": "upf01,upf02",
                    "dataType": "register,alarm,perf",
                    "callbackUrl":"https://192.168.100.2:8080/reschange"
                }
        ]
}
```

### *Upper layer management system manages a NF through cloud native OAM*

* `POST` &nbsp;&nbsp;&nbsp;&nbsp; */tubeVnf/{neType}/{neId} /{systemId}*

Used by upper layer management systems to establish management relationship with a NF through cloud native OAM.

Normal Response Code: 200

Error Response Code: 401, 503

Input:
|Name  |In |Type  |Description  |
| :--- | :--- | :--- | :--- |
| neType | body | string | Type of target NF that upper layer management system wants to manage, e.g. UPF, AMF. |
| neid | body | string | ID of target NF that upper layer management system wants to manage|
| systemId | body | string | ID of upper layer management system which want to manage the NF. |

<u>Request Example</u>
```
neType=UPF&neId=upfinstanceid001&systemId=OSS-02
```

<u>Response Example</u>
```
{
    "status": 200,
    "message": "Request is successful",
    "data": null
}
```

### *Upper layer management system unmanages a NF through cloud native OAM*
* `POST` &nbsp;&nbsp;&nbsp;&nbsp;*/deTubeVnf/{neType}/{neId} /{systemId}*

Used by upper layer management systems to remove management relationship with a NF through cloud native OAM.

Normal Response Code: 200

Error Response Code: 401, 503

Input:
|Name  |In |Type  |Description  |
| :--- | :--- | :--- | :--- |
| neType | body | string | Type of target NF that upper layer management system wants to un-manage, e.g. UPF, AMF. |
| neid | body | string | ID of target NF that upper layer management system wants to un-manage|
| isClear| body| int | whether or not clear NF data in cloud native OAM when unmanaging the NF. "0" represents not clear the data. "1" represents clear the data. |
| systemId | body | string | ID of upper layer management system which want to un-manage the NF. |

<u>Request Example</u>
```
neType=UPF&neId=upfinstanceid001&systemId=OSS-02&isClear=0
```

<u>Response Example</u>
```
Json：
{
    "status": 200,
    "message": "Request is Succesful",
    "data": null
}

```

### *Upper layer management system get information of managed NFs*
* `GET` &nbsp;&nbsp;&nbsp;&nbsp; */getVnfList*

Used by upper layer management systems to get list of VNFs which are registered to cloud native OAM.

Input:
|Name  |In |Type  |Description  | Note|
| :--- | :--- | :--- | :--- |:---|
| neType | body | string | Type of NF |Optional|
| neid | body | string | ID of NF|Optional|
| vnfName | body | string | Name of NF. |Optional|
| vnfManagIp | body | string | Management IP of NF. |Optional|
| vnfManagPort | body | string | Management port of NF. |Optional|
| vnfStatus | body | string | Whether the NF is connected with cloud native OAM. "0" represents losing connection. "1" represents connected and running well. |Optional|
| vnfManageStatus | body | string | Whether the NF is managed by upper layer management systems. "0" represents managed. "1" represents unmanaged. |Optional|
| heartBeatTime | body | string | The most recent time stamp cloud native OAM receiving NF's heartbeat |Optional|
| systemId | body | string | ID of upper layer management system which manages the NF. |Optional|

<u>Request Example</u>
```
{
   "neType": "UPF",
    "vnfName": null,
    "vnfManageIp": null,
    "vnfManagePort": null,
    "vnfSignalPort": null,
    "vnfStatus": null,
    "vnfManageStatus": null,
    "heartBeatTime": null,
    "systemId": "OSS-02",
}
```
<u>Response Example</u>
```
{
    "status": 200,
    "message": "Request is Succesful",
    "data": [
        {
            "neId": "upfinstanceid001",
            "neType": "UPF",
            "vnfName": "UPF001",
            "vnfManageIp": "111.111.63.203",
            "vnfManagePort": "30101",
            "vnfSignalPort": "20100",
            "vnfStatus": "3",
            "vnfManageStatus": "1",
            "heartBeatTime": 1663988767000,
            "systemId": "OSS-02",
        },
        {
            "neId": "upfinstanceid002",
            "neType": "UPF",
            "vnfName": "UPF002",
            "vnfManageIp": "111.111.63.203",
            "vnfManagePort": "30102",
            "vnfSignalPort": "20100",
            "vnfStatus": null,
            "vnfManageStatus": "0",
            "heartBeatTime": 1663845036000,
            "systemId": null,
        }
    ]
}
```

### *Upper layer management system get logs of managed NFs*
* `GET` &nbsp;&nbsp;&nbsp;&nbsp; */logs/list*

Used by upper layer management systems to get logs of NFs.

|Name  |In |Type  |Description  | 
| :--- | :--- | :--- | :--- |
| neType | body | string | Type of NF |
| neid | body | string | ID of NF|
| start_time | body | string | The start time for log query|
| end_time | body | string | The end time for log query|

<u>Request Example</u>
```
current=1&endTime=2022-09-10 18:00:00&neId=upfinstanceid002&neType=upf&size=10&startTime=2022-09-09 10:50:00
```

<u>Response Example</u>
```
{
     Status:200,
     Message: Request is Succesful,
     "log": [{logs}]
}
```

### *Upper layer management system get topology information of managed NFs*
* `GET` &nbsp;&nbsp;&nbsp;&nbsp; */getNfResources/{neType}/{neId}*

Used by upper layer management systems to get NF's microservice topology and corresponding resource topology.

Input:
|Name  |In |Type  |Description  | 
| :--- | :--- | :--- | :--- |
| neType | body | string | Type of NF |
| neid | body | string | ID of NF|

<u>Response Example</u>
There exist three-layer relationship within NF, which is NF (the NF layer), nf-service (NF microservices), nf-service-instance (Pod).

```
{
    "status": 200,
    "message": "Request is Succesful",
    "data": [
        {
            "userLabel": ",ManagedElement=me-xgvela1,NetworkFunction=upfinstanceid001",
            "usageState": "ACTIVE",
            "administrativeState": "UNLOCKED",
            "nf_services": [
                {
                    "userLabel": ",ManagedElement=me-xgvela1,NetworkFunction=upfinstanceid001,NFService=simulator",
                    "adminstrativeState": "UNLOCKED",
                    "nfServiceSwVersion": "v0.1",
                    "monitoringMode": "",
                    "nf_service_instances": [
                        {
                            "dnPrefix": "",
                            "network-status": [
                                {
                                    "vips": [],
                                    "default": true,
                                    "name": "k8s-pod-network",
                                    "interface": "eth0",
                                    "ips": [
                                        "10.233.113.106"
                                    ]
                                }
                            ],
                            "userLabel": ",ManagedElement=me-xgvela1,NetworkFunction=upfinstanceid001,NFService=simulator,NFServiceInstance=simulator-5dffdc4894-pzsmv",
                            "name": "simulator-5dffdc4894-pzsmv",
                            "haRole": "N/A",
                            "extendedAttrs": {
                                "app": "simulator",
                                "xgvelaId": "xgvela1",
                                "kubernetes.io/arch": "amd64",
                                "beta.kubernetes.io/arch": "amd64",
                                "microSvcName": "simulator",
                                "node.kubernetes.io/exclude-from-external-load-balancers": "",
                                "node-role.kubernetes.io/worker": "",
                                "kubernetes.io/hostname": "b5g-iepnm-vm01",
                                "svcVersion": "v0.1",
                                "kubernetes.io/os": "linux",
                                "pod-template-hash": "5dffdc4894",
                                "beta.kubernetes.io/os": "linux",
                                "node-role.kubernetes.io/master": "",
                                "node-role.kubernetes.io/control-plane": ""
                            },
                            "id": "2b947812-18c5-3c28-b108-78c908eebc7c",
                            "state": "READY",
                            "msuid": "NULL"
                        }
                    ],
                    "haEnabled": false,
                    "numStandby": 0,
                    "mode": "",
                    "usageState": "ACTIVE",
                    "name": "simulator",
                    "nfServiceType": "simulator",
                    "extendedAttrs": {},
                    "id": "0815a901-547d-380c-b7ea-fa7455a6e467",
                    "state": "INSTANTIATED_CONFIGURED_ACTIVE",
                    "operationalState": "ENABLED"
                }
            ],
            "name": "upfinstanceid001",
            "nfType": "upf",
            "extendedAttrs": {},
            "id": "2446c1c4-901e-3514-9bac-0bb0897f5a65",
            "state": "INSTANTIATED_CONFIGURED_ACTIVE",
            "operationalState": "ENABLED",
            "nfSwVersion": "v0"
        }
    ]
}
```

### *Upper layer management system get in-use configuration info of managed NFs*
* `GET`  &nbsp;&nbsp;&nbsp;&nbsp;  */ne/config*

Used by upper layer management systems to get basic configuration info of NFs.

Input:
|Name  |In |Type  |Description  | Note|
| :--- | :--- | :--- | :--- |:---|
| neType | body | string | Type of NF |Optional|
| neid | body | string | ID of NF|Optional|
| vnfName | body | string | Name of NF. |Optional|

<u>Response Example</u>
```
{
     Status:200,
     Message:Request is successful,
     "file": [{
         "ne_id": "Your NF ID",
         "ne_type ": "Your NF type"，
         "cf_version": "Version number of your NF's configuration (in-use)"，
         "cf_path": "Path of NF's configuration file"，
         "cf_name": "Name of NF's configuration file"，
         "cf_size": "Size of NF's configuration file"，
         "cf_updatetime": "Time stamp of updating NF's configuration with this configuration file"
     }]
}
```

### *Upper layer management system post configuration onto cloud native OAM and configure NF*
* `POST`  &nbsp;&nbsp;&nbsp;&nbsp;  */ne/config*

Used by upper layer management systems to configure NF.

Input:
|Name  |In |Type  |Description  | 
| :--- | :--- | :--- | :--- |
| neType | body | string | Type of NF |
| neid | body | string | ID of NF|
| vnfName | body | string | Name of NF. |
| callback_url | body | string | Address of upper layer management systems to get configuration response.|
| file | body | string | Path and name of configuration file.|

<u>Request Example</u>
```
{
 "neType": "Your NF type",
 "neId": "Your NF ID",
 "vnfName": "Your NF name",
 "callback_url": "Callback URL of upper layer management system",
 "file": {
     "cf_name": "Name of NF's configuration file",
     "cf_path": "Path of NF's configuration file"
    }
}
```

<u>Response Example</u>
```
{
     Status:200,
     Message:Request is successful
}
```
Configuration files are stored in cloud native OAM under path: *{sftproot}/conf/write/{nfType}/{neId}/*


### *Upper layer management system change NF's configuration to hostorical version*
* `POST`  &nbsp;&nbsp;&nbsp;&nbsp; */ne/config/swtich*

Used by upper layer management systems to change NF's configuration to a historical version. Cloud native OAM support to store 5 versions of historical configurations.

Input:
|Name  |In |Type  |Description  | 
| :--- | :--- | :--- | :--- |
| neType | body | string | Type of NF |
| neid | body | string | ID of NF|
| vnfName | body | string | Name of NF. |
| callback_url | body | string | Address of upper layer management systems to get configuration response.|
| file | body | string | Path and name of configuration file.|

<u>Request Example</u>
```
{
 "neType": "Your NF type",
 "neId": "Your NF ID",
 "vnfName": "Your NF name",
 "callback_url": "Callback URL of upper layer management system",
 "file": {
     "cf_name": "Name of NF's configuration file",
     "cf_path": "Path of NF's configuration file"
    }
}
```

<u>Response Example</u>
```
{
     Status:200,
     Message:Request is successful
}
```

