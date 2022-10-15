FROM openjdk:8-jdk-alpine

RUN sed -i 's/dl-cdn.alpinelinux.org/mirrors.ustc.edu.cn/g' /etc/apk/repositories
RUN apk update && \
    apk add --no-cache curl bash tzdata busybox-extras fontconfig ttf-dejavu dmidecode  && \
    cp /usr/share/zoneinfo/Asia/Shanghai /etc/localtime && \
    echo "Asia/Shanghai" > /etc/timezone && \
    rm -rf /var/cache/apk/* \
    apk del tzdata
ENV CNET_DIR /inspur/cnet
ARG JAR_FILE
ADD ${JAR_FILE}  app.jar
ENTRYPOINT ["java","-jar","app.jar"]