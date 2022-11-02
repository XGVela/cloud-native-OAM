#!/bin/sh
# shellcheck disable=SC2164

cd ~/oam/cloud-native-OAM/oam-network-agent/
cp target/oam-network-agent-1.0-SNAPSHOT.jar ci/linux/app.jar
cd ci/linux
docker build . -f Dockerfile   -t registry.local:9001/omc/oam-network-agent:1.0-SNAPSHOT

cd ~/oam/cloud-native-OAM/oam-network-alarm/
cp target/oam-network-alarm-1.0-SNAPSHOT.jar ci/linux/app.jar
cd ci/linux
docker build . -f Dockerfile   -t registry.local:9001/omc/oam-network-alarm:1.0-SNAPSHOT

cd ~/oam/cloud-native-OAM/oam-network-auth/
cp target/oam-network-auth-1.0-SNAPSHOT.jar ci/linux/app.jar
cd ci/linux
docker build . -f Dockerfile   -t registry.local:9001/omc/oam-network-auth:1.0-SNAPSHOT

cd ~/oam/cloud-native-OAM/oam-network-config/
cp target/oam-network-config-1.0-SNAPSHOT.jar ci/linux/app.jar
cd ci/linux
docker build . -f Dockerfile   -t registry.local:9001/omc/oam-network-config:1.0-SNAPSHOT

cd ~/oam/cloud-native-OAM/oam-network-log/
cp target/oam-network-log-1.0-SNAPSHOT.jar ci/linux/app.jar
cd ci/linux
docker build . -f Dockerfile   -t registry.local:9001/omc/oam-network-log:1.0-SNAPSHOT

cd ~/oam/cloud-native-OAM/oam-network-nftube/
cp target/oam-network-nftube-1.0-SNAPSHOT.jar ci/linux/app.jar
cd ci/linux
docker build . -f Dockerfile   -t registry.local:9001/omc/oam-network-nftube:1.0-SNAPSHOT

cd ~/oam/cloud-native-OAM/oam-network-nfregister/ci
cp oam-network-nfregister-1.0-SNAPSHOT.jar linux/app.jar
cd linux
docker build . -f Dockerfile   -t registry.local:9001/omc/oam-network-nfregister:1.0-SNAPSHOT

cd ~/oam/cloud-native-OAM/oam-network-performance/
cp target/oam-network-performance-1.0-SNAPSHOT.jar ci/linux/app.jar
cd ci/linux
docker build . -f Dockerfile   -t registry.local:9001/omc/oam-network-performance:1.0-SNAPSHOT

cd ~/oam/cloud-native-OAM/oam-network-subscribe-publish/
cp target/oam-network-subscribe-publish-1.0-SNAPSHOT.jar ci/linux/app.jar
cd ci/linux
docker build . -f Dockerfile   -t registry.local:9001/omc/oam-network-subscribe-publish:1.0-SNAPSHOT

docker push registry.local:9001/omc/oam-network-agent:1.0-SNAPSHOT
docker push registry.local:9001/omc/oam-network-alarm:1.0-SNAPSHOT
docker push registry.local:9001/omc/oam-network-auth:1.0-SNAPSHOT
docker push registry.local:9001/omc/oam-network-config:1.0-SNAPSHOT
docker push registry.local:9001/omc/oam-network-log:1.0-SNAPSHOT
docker push registry.local:9001/omc/oam-network-nftube:1.0-SNAPSHOT
docker push registry.local:9001/omc/oam-network-nfregister:1.0-SNAPSHOT
docker push registry.local:9001/omc/oam-network-performance:1.0-SNAPSHOT
docker push registry.local:9001/omc/oam-network-nftube:1.0-SNAPSHOT
docker push registry.local:9001/omc/oam-network-subscribe-publish:1.0-SNAPSHOT