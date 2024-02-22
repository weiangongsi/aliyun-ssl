#阿里云证书自动更新部署

域名要在阿里云平台，因为申请证书验证的时候需要在域名解析中添加一条记录。

前端没有太多的校验，需要认真填写表单数据。

修改配置文件 \src\main\resources\application.yml

```yml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/alissl?useUnicode=true&characterEncoding=UTF-8&useSSL=false
    username: root
    password: root 
login:
  username: "admin"
  password: "admin"
ali:
  accessKeyId: "accessKeyId"
  accessKeySecret: "accessKeySecret"

```

### 直接运行

打包
```shell
mvn '-Dmaven.test.skip=true' clean package
```

运行
```shell
java -jar aliyun-ssl.jar
```

访问 http://localhost:90


### docker部署

打包
```shell
mvn '-Dmaven.test.skip=true' clean package
```

构建镜像
``` shell
docker build -t  aliyun-ssl:1.0.0 .
```

运行
```shell
docker run  -d -p 90:90  --name aliyun-ssl aliyun-ssl:1.0.0
```