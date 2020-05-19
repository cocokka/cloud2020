##nacons github: 
###1. https://github.com/alibaba/spring-cloud-alibaba/blob/master/README-zh.md
###2. https://github.com/alibaba/nacos/wiki
##http://console.nacos.io/nacos/index.html#/login

## doc: https://nacos.io/en-us/docs/what-is-nacos.html

##quick start: https://nacos.io/en-us/docs/quick-start-spring-cloud.html

##config文件形式，即dataID名字（目前只支持yaml，实例：cloud-alibaba-config-dev.yaml，属性名后需要有空格）：${spring.application.name}-${spring.profile.active}.${spring.cloud.nacos.config.file-extension}

##nacos 集群配置，需要1个Nginx，3个nacos，一个mysql：https://nacos.io/en-us/docs/deployment.html
###1）该数据库，从嵌入式数据库改为mysql实现，需要直接导入官方提供的sql
###2）修改application.properties, 增加datasource配置来支持mysql。
###3）nginx代理3台nacos服务，暴露出统一服务接口。
###3) 微服务指向代理服务地址。