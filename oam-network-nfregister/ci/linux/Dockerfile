FROM openjdk:8-jdk-alpine

RUN sed -i 's/dl-cdn.alpinelinux.org/mirrors.ustc.edu.cn/g' /etc/apk/repositories
RUN apk update && \
    apk add --no-cache curl bash tzdata busybox-extras fontconfig ttf-dejavu dmidecode  && \
    cp /usr/share/zoneinfo/Asia/Shanghai /etc/localtime && \
    echo "Asia/Shanghai" > /etc/timezone && \
    rm -rf /var/cache/apk/* \
    apk del tzdata
ADD app.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]