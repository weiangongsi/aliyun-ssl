FROM openjdk:21
VOLUME /tmp
ADD target/aliyun-ssl.jar app.jar
ENV TZ=Asia/Shanghai
RUN sh -c 'touch /app.jar' && ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]
