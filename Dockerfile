# 基础镜像使用java
FROM openjdk:8
# 作者
MAINTAINER zzq
# VOLUME 指定临时文件目录为/tmp，在主机/var/lib/docker目录下创建了一个临时文件并链接到容器的/tmp
VOLUME /tmp
# 将jar包添加到容器中并更名为zzyy_docker.jar
ADD *.jar /app.jar
# 运行jar包
RUN bash -c 'touch /app.jar'
ENTRYPOINT ["java","-jar","/app.jar","--spring.profiles.active=pro"]
#暴露6001端口作为微服务
EXPOSE 9999

# 构建命令 docker build -t dousheng .
# 运行命令(映射日志文件) docker run -d -p 9999:9999 --name dousheng -v /docker-data/springboot/dousheng/logs:/logs dousheng