# Cloud Native OAM Installation Guide

## Abstract

The Cloud Native OAM system consists of several key services that are separately installed. These services work together depending on your needs and include the Auth, Alarm, Config, Log,  Performance and Agent services. You can install any of these projects separately and configure them stand-alone or as connected entities.

This guide documents the installation of 2022.11 Release.

## Prerequisites

* Kubernetes 1.20+ <1.25(PSP issue)
* The Kubernetes cluster API endpoint should be reachable from the machine you are running helm.
* Authenticate the cluster using kubectl and it should have cluster-admin permissions.
* The Kubernetes cluster should has free resources more then:
  * 16 CPUs
  * 32 Gb Memroy
  * 200Gb disk space
* If you want to repackage this services jar:
  * Linux 3.10.0+
  * OpenJDK 1.8+/Oracle JDK 11+
  * Apache Maven 3.0.0+
* If you want to rebuild this services OCI image:
  * Linux 3.10.0+
  * Docker 20.10.0+ or other imagemaker tools


## Compile

You can complete the OAM source code compilation in the local Linux environment.
This guide document uses CentOS7 as an example.


#### Step1: Install build tools:
```
yum install git maven
```

#### Step2: Check build tools version
```
[root@cmcc cloud-native-OAM]# java -version
openjdk version "1.8.0_345"
OpenJDK Runtime Environment (build 1.8.0_345-b01)
OpenJDK 64-Bit Server VM (build 25.345-b01, mixed mode)

[root@cmcc cloud-native-OAM]# mvn --version
Apache Maven 3.0.5 (Red Hat 3.0.5-17)
Maven home: /usr/share/maven
Java version: 1.8.0_345, vendor: Red Hat, Inc.
Java home: /usr/lib/jvm/java-1.8.0-openjdk-1.8.0.345.b01-1.el7_9.x86_64/jre
Default locale: en_US, platform encoding: UTF-8
OS name: "linux", version: "3.10.0-957.el7.x86_64", arch: "amd64", family: "unix"
```

#### Step3: Download OAM source code
```
mkdir ~/oam
cd ~/oam
git clone https://github.com/XGVela/cloud-native-OAM.git
cd ~/oam/cloud-native-OAM
```

#### Step4: Compile use maven
>This command will automatically download the dependent jar package in pom.xml and compile java files and packaged as jar
```
mvn package
```
>Under normal circumstances, you will see the following output indicating that the compilation was successful
```
[INFO] Reactor Summary:
[INFO]
[INFO] oam ............................................... SUCCESS [0.002s]
[INFO] oam-network-common ................................ SUCCESS [9.914s]
[INFO] oam-network-grpc-common ........................... SUCCESS [0.183s]
[INFO] oam-network-agent ................................. SUCCESS [2.263s]
[INFO] oam-network-config ................................ SUCCESS [0.639s]
[INFO] oam-network-nftube ................................ SUCCESS [0.615s]
[INFO] oam-network-subscribe-publish ..................... SUCCESS [0.665s]
[INFO] oam-network-log ................................... SUCCESS [0.514s]
[INFO] oam-network-auth .................................. SUCCESS [0.423s]
[INFO] oam-network-alarm ................................. SUCCESS [0.586s]
[INFO] oam-network-performance ........................... SUCCESS [0.302s]
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time: 17.320s
[INFO] Finished at: Tue Nov 01 15:32:53 CST 2022
[INFO] Final Memory: 112M/1327M
```

>You can find the jar file in ~/oam/cloud-native-OAM/<servicename>/target/

>*Please download oam-network-nfregister-1.0-SNAPSHOT.jar binary from google drive https://drive.google.com/file/d/1H-oH7A-GZ_IRc7D5c1KM1q-UO0V6-oJJ/view?usp=sharing
>md5:e66b39adee8d859eb2dfe7760e56fda9*

## Build OCI images

You can build Cloud Native OAM’s OCI images in the local Linux environment.This guide document uses CentOS7 as an example.

#### Step1: Install build tools
```
yum-config-manager -add-repo https://download.docker.com/linux/centos/docker-ce.repo
yum install docker-ce
systemctl start docker
```

The Cloud Native OAM system’s several key services use the same method to build OCI image.This guide document uses oam-network-log as an example.After compile oam-network-log source code, You can find the jar file in ~/oam/cloud-native-OAM/oam-network-log/target/oam-network-log-1.0-SNAPSHOT.jar

####  Step2: Build OCI image
#when you download oam-network-nfregister-1.0-SNAPSHOT.jar and from google-driver
```
cd ~/oam/cloud-native-OAM/docs
/bin/sh  image.sh
```

####  Step3: Build tmaas/tmaas-gw image
```
cd  ~/oam
git clone https://github.com/XGVela/tmaas.git
cd  ~/oam/tmaas
/bin/sh buildall.sh 1.0

cd  ~/oam
git clone https://github.com/XGVela/tmaas-gw.git
cd  ~/oam/tmaas-gw
/bin/sh buildall.sh 1.0
```
>You can find image file in ~/oam/tmaas/artifacts/images/
>~/oam/tmaas-gw/artifacts/images/

## Installation
#### Step1: Prepare

```
cd ~/oam
git clone https://github.com/XGVela/cloud-native-OAM-builder.git
cd cloud-native-OAM-builder

kubectl -n oam-system create secret generic etcd-client-certs \
--from-file=/etc/ssl/etcd/ssl/ca.pem \
--from-file=/etc/ssl/etcd/ssl/member-b5g-iepnm-vm01.pem \
--from-file=/etc/ssl/etcd/ssl/member-b5g-iepnm-vm01-key.pem

kubectl create ns oam-system

cat <<EOF | kubectl apply -f -
apiVersion: v1
kind: ServiceAccount
metadata:
  name: xgvela-sa
  namespace: oam-system
EOF


cat <<EOF | kubectl apply -f - 
kind: ClusterRole 
apiVersion: rbac.authorization.k8s.io/v1 
metadata: 
  name: xgvela-cr 
  namespace: oam-system
rules:
- apiGroups: ["extensions", "apps"]
  resources: ["deployments","statefulsets", "daemonsets"]
  verbs: ["get", "list", "patch"]
- apiGroups: [""]
  resources: ["pods"]
  verbs: ["get", "list", "watch", "patch", "delete"]
- apiGroups: [""]
  resources: ["services"]
  verbs: ["get", "list"]
- apiGroups: [""]
  resources: ["configmaps"]
  verbs: ["get", "watch", "list", "update", "patch", "delete"]
- apiGroups: [""]
  resources: ["namespaces"]
  verbs: ["get", "watch"]
- apiGroups: ["rbac.authorization.k8s.io"]
  resources: ["clusterroles"]
  verbs: ["get", "list"]
- apiGroups: ["admissionregistration.k8s.io"]
  resources: ["mutatingwebhookconfigurations"]
  verbs: ["create", "update", "get"]
- apiGroups: [""]
  resources: ["nodes"]
  verbs: ["get", "list"]
- apiGroups: ["monitoring.coreos.com"]
  resources: ["prometheusrules"]
  verbs: ["get", "create", "list", "delete", "update", "patch"]
- apiGroups: ["monitoring.coreos.com"]
  resources: ["podmonitors"]
  verbs: ["get", "create", "list", "update"]
- apiGroups: ["monitoring.coreos.com"]
  resources: ["prometheuses"]
  verbs: ["get" ]
EOF

cat <<EOF | kubectl apply -f -
kind: ClusterRoleBinding
apiVersion: rbac.authorization.k8s.io/v1
metadata: 
  name: xgvela-crb 
  namespace: oam-system
subjects:
- kind: ServiceAccount
  name: xgvela-sa
  namespace: oam-system
roleRef:
  kind: ClusterRole
  name: xgvela-cr
  apiGroup: rbac.authorization.k8s.io
EOF
```

#### Step2: Upload OAM's images to image repository

```

```

#### Step3: Install basic component

```
cd ~/oam/cloud-native-OAM-builder/

cd ~/oam/cloud-native-OAM-builder/oam-basic/charts/mysql
docker pull mysql:5.7.28
docker build . -f Dockerfile   -t mysql:5.7.28

cd ~/oam/cloud-native-OAM-builder/oam-basic/charts/postgresql
docker pull docker.io/bitnami/postgresql:15.1.0-debian-11-r0
docker build . -f Dockerfile   -t docker.io/bitnami/postgresql:15.1.0-debian-11-r0

helm install oam ~/oam/cloud-native-OAM-builder/oam-basic -n oam-system

kubectl label <host> mgmt=true

helm install tmaas ~/oam/tmaas/charts/topo-engine
helm install tmaas-gw ~/oam/tmaas-gw/charts/topo-gw
```

Check the installation status

```
kubectl get pod -n oam-system
NAME                                                 READY   STATUS    RESTARTS   AGE
elasticsearch-master-0                               1/1     Running   0          5d2h
oam-grafana-c7fbb87cf-hn22p                          2/2     Running   0          7d8h
oam-kafka-0                                          1/1     Running   0          5d2h
oam-kibana-55cbf9b898-5l59x                          2/2     Running   0          5d2h
oam-kube-prometheus-stack-operator-9d4b89f64-9hc6h   1/1     Running   0          6d6h
oam-kube-state-metrics-9674b86f4-wc9m8               1/1     Running   0          6d7h
oam-logstash-0                                       1/1     Running   0          2d13h
oam-mysql-8687f65cb7-q4v5p                           1/1     Running   0          5d2h
oam-postgresql-0                                     1/1     Running   0          3h30m
oam-nacos-0                                          1/1     Running   0          5d2h
oam-prometheus-adapter-5656f6755d-bprh6              1/1     Running   0          7d8h
oam-prometheus-blackbox-exporter-f945954cc-tgp2r     1/1     Running   0          5d2h
oam-redis-master-0                                   1/1     Running   0          5d2h
topo-engine-7b76d78d49-gx6wf                         1/1     Running   0          3d
topo-gw-6f977cdd74-8z92f                             1/1     Running   0          5d
oam-sftp-agent-b5dd77967-bf68l                       1/1     Running   0          4d9h
oam-sftp-conf-f8f454466-hhf26                        1/1     Running   0          4d8h
oam-zookeeper-0                                      1/1     Running   0          7d8h
prometheus-oam-kube-prometheus-stack-prometheus-0    2/2     Running   1          6d3h
```
#### Step4: Install OAM simulator component

```
cd ~/oam/cloud-native-OAM-builder/
kubectl create ns inspur-xgvela1-infra-upf-upfinstanceid001
kubectl create ns inspur-xgvela1-infra-upf-upfinstanceid002

helm install simulator ~/oam/cloud-native-OAM-builder/simulator -n oam-system
helm install simulator002 ~/oam/cloud-native-OAM-builder/simulator002 -n oam-system
```

Check the installation status in inspur-xgvela1-infra-upf-upfinstanceid001 and inspur-xgvela1-infra-upf-upfinstanceid002 namespace

```
kubectl get pods -n inspur-xgvela1-infra-upf-upfinstanceid001
NAME                         READY   STATUS    RESTARTS   AGE
simulator-5d6446d5df-hfbv2   2/2     Running   2          23h

kubectl get pods -n inspur-xgvela1-infra-upf-upfinstanceid002
NAME                         READY   STATUS    RESTARTS   AGE
simulator-6b4956b9f6-m4xbf   2/2     Running   2          23h
```
#### Step5: Install OAM component

```
cd ~/oam/cloud-native-OAM-builder/
helm install oam-network ~/oam/cloud-native-OAM-builder/oam-network -n oam-system
```

Check the installation status

```
kubectl get pods  -n oam-system |grep network
oam-network-agent-7775bc47c9-454l5                   1/1     Running   0          28m
oam-network-alarm-7ddf68766c-l75bx                   1/1     Running   0          28m
oam-network-auth-6bc4fd4f97-v8xtm                    1/1     Running   0          28m
oam-network-config-66964b4758-5k686                  1/1     Running   0          28m
oam-network-log-7965b97645-2ghgg                     1/1     Running   0          28m
oam-network-nfregister-bdbb55cbc-7k46s               1/1     Running   0          28m
oam-network-nftube-b47c85757-gjmfz                   1/1     Running   0          28m
oam-network-performance-7569f6c7b5-59c4c             1/1     Running   0          28m
oam-network-subscribe-publish-74d7f7999b-29kz5       1/1     Running   0          28m
```