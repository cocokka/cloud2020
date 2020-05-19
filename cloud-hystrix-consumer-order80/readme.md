当使用通用的feign hystrix时，此时
1. 通过接口实现
2. 通过factory实现
以上2种方式中，都无法像@HystrixCommand一样设置timeout的时间，
故而使用的是默认的1s，当服务运行正常但是稍有延迟时，会进入到fallback中，此时并非预期的结果。
需要添加一下配置来重新设置timeout时长
hystrix:
  command:
    default:  #default全局有效，service id指定应用有效
      execution:
        timeout:
          #是否开启超时熔断
          enabled: true
        isolation:
          thread:
            timeoutInMilliseconds: 3000 #断路器超时时间，默认1000ms