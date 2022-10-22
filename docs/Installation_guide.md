# Cloud Native OAM Installation Guide

## Abstract

The Cloud Native OAM system consists of several key services that are separately installed. These services work together depending on your needs and include the Auth, Alarm, Config, Log,  Performance and Agent services. You can install any of these projects separately and configure them stand-alone or as connected entities.  

 This guide documents the installation of 2022.11 Release.
 
## Prerequisites

* Kubernetes 1.20+
* The Kubernetes cluster API endpoint should be reachable from the machine you are running helm.
* Authenticate the cluster using kubectl and it should have cluster-admin permissions.
* The Kubernetes cluster should has free resources more then:
    * 16 CPUs
    * 32 Gb Memroy
    * 200Gb disk space
* If you want to repackage this services jar:
    * Linux 3.10.0+
    * JDK 1.8+
    * Apache Maven  3.0.0+
* If you want to rebuild this services OCI image:
    * Linux 3.10.0+
    * Docker 20.10.0+ or other imagemaker tools


## Compile [optional]

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
cd cloud-native-OAM
```

#### Step4: Compile use maven
>This command will automatically download the dependent jar package in pom.xml and compile java files and packaged as jar
```
mvn package
```

>Under normal circumstances, you will see the following output indicating that the compilation was successful
```
[INFO] oam ............................................... SUCCESS [0.002s]
[INFO] oam-network-common ................................ SUCCESS [7.980s]
[INFO] oam-network-grpc-common ........................... SUCCESS [0.675s]
[INFO] oam-network-agent ................................. SUCCESS [7.635s]
[INFO] oam-network-log ................................... SUCCESS [1.291s]
[INFO] oam-network-auth .................................. SUCCESS [1.206s]
[INFO] oam-network-alarm ................................. SUCCESS [2.175s]
[INFO] oam-network-subscribe-publish ..................... SUCCESS [1.653s]
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time: 25.875s[INFO] Finished at: Tue Oct 11 17:58:06 CST 2022
[INFO] Final Memory: 71M/214M
[INFO] ------------------------------------------------------------------------
```

>You can find the jar file in ~/oam/cloud-native-OAM/<servicename>/target/

>*Please download oam-network-nfregister-1.0-SNAPSHOT.jar binary from google drive https://drive.google.com/file/d/1uWPO5jQqh3rSkudZJrCjI3DIRmGkqzEx/view?usp=sharing*


## Build OCI images [optional]

You can build Cloud Native OAM’s OCI images in the local Linux environment.This guide document uses CentOS7 as an example.

#### Step1: Install build tools
```
yum-config-manager -add-repo https://download.docker.com/linux/centos/docker-ce.repo
yum install docker-ce
systemctl start docker
```

The Cloud Native OAM system’s several key services use the same method to build OCI image.This guide document uses oam-network-log as an example.After compile oam-network-log source code, You can find the jar file in ~/oam/cloud-native-OAM/oam-network-log/target/oam-network-log-1.0-SNAPSHOT.jar
 
####  Step2: Build OCI image
 
```
cd ~/oam/cloud-native-OAM/oam-network-log
cp target/oam-network-log-1.0-SNAPSHOT.jar ci/app.jar
cd ci
docker build . -f Dockerfile
```
>Under normal circumstances, you will see the following output
```
Successfully built 3f1103058de1
```
>You can find the OCI image in the local image repo.
```
[root@cmcc oam-network-log]# docker image ls |grep 3f1103058de1<none>                                                       <none>         3f1103058de1   19 minutes ago   271MB
```
#### Step3: Export OCI image to tar
```
docker save 3f1103058de1 > oam-network-log.tar
```

## Installation
#### Step1: Prepare
```
kubectl -n oam-system create secret generic additional-external 
--from-file=/root/oam/data/additional-prometheus.yaml

kubectl -n oam-system create secret generic etcd-client-certs  \
--from-file=/etc/ssl/etcd/ssl/ca.pem \
--from-file=/etc/ssl/etcd/ssl/member-b5g-iepnm-vm01.pem \
--from-file=/etc/ssl/etcd/ssl/member-b5g-iepnm-vm01-key.pem
```

#### Step2: Upload OAM's images to image repository

#### Step3: Install basic component
```
mkdir ~/oam
cd ~/oam
git clone https://github.com/XGVela/cloud-native-OAM-builder.git
cd cloud-native-OAM-builder
kubectl create ns oam-system
helm install oam-basic-component charts -n oam-system
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
oam-nacos-0                                          1/1     Running   0          5d2h
oam-prometheus-adapter-5656f6755d-bprh6              1/1     Running   0          7d8h
oam-prometheus-blackbox-exporter-f945954cc-tgp2r     1/1     Running   0          5d2h
oam-redis-master-0                                   1/1     Running   0          5d2h
oam-sftp-agent-b5dd77967-bf68l                       1/1     Running   0          4d9h
oam-sftp-conf-f8f454466-hhf26                        1/1     Running   0          4d8h
oam-zookeeper-0                                      1/1     Running   0          7d8h
prometheus-oam-kube-prometheus-stack-prometheus-0    2/2     Running   1          6d3h
socat-84b7c89dcb-9bbzv                               1/1     Running   0          7d8h
```

#### Step4: Install OAM component
```
cd ~/oam/cloud-native-OAM-builder/
kubectl create ns oam-system
helm install oam oam-charts -n oam-system
```
Check the installation status
```
kubectl get pods  -n oam-system |grep network
oam-network-agent-c69d89c85-c8wqg                    1/1     Running   0          3d7h
oam-network-alarm-76696f4f99-mqxl4                   1/1     Running   0          3d7h
oam-network-auth-779c9bf599-f7bw6                    1/1     Running   0          3d7h
oam-network-config-66f64fcf6c-87vxj                  1/1     Running   0          3d7h
oam-network-log-7f9c7d6c8f-lws4b                     1/1     Running   0          3d7h
oam-network-nfregister-6457fb79ff-swkqk              1/1     Running   0          3d7h
oam-network-nftube-6f45c6f957-4jqvh                  1/1     Running   0          2d
oam-network-performance-55c59d585c-kp875             1/1     Running   0          3d7h
oam-network-subscribe-publish-854c665f84-ghqgw       1/1     Running   0          3d7h
```

