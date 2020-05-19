# [Oauth2](https://www.funtl.com/zh/spring-security-oauth2/)
## 密码模式
- [测试地址](http://localhost:8888/oauth/authorize?client_id=client&response_type=code)
- 用户名密码登录
- 授权
- 页面跳转后，获取到code， https://www.baidu.com/?code=rVWRWT
- [发起post请求](http://client:secret@localhost:8888/oauth/token)，并添加request body
    1. grant_type:authorization_code
    2. code:rVWRWT
- 获取到access_token
    ```json
    {
         "access_token": "4424c2d2-b6a5-495e-919e-d3ad7e8c1da5",
         "token_type": "bearer",
         "expires_in": 43199,
         "scope": "app"
     }
    ```

## JDBC存储TOKEN
- 准备数据库表，导入schema.sql
- 准备数据
  ```sql
  INSERT INTO `oauth2`.`oauth_client_details` (`client_id`, `resource_ids`, `client_secret`, `scope`, `authorized_grant_types`, `web_server_redirect_uri`, `authorities`, `access_token_validity`, `refresh_token_validity`, `additional_information`, `autoapprove`) VALUES ('client', NULL, '$2a$10$34N/5L02Px5CZdCVSQcM1.SUTZTw1FYLYnuODhtRGrpl1Li3d1yIC', 'app', 'authorization_code', 'https://www.baidu.com', NULL, NULL, NULL, NULL, NULL);
  INSERT INTO `oauth2`.`oauth_client_details` (`client_id`, `resource_ids`, `client_secret`, `scope`, `authorized_grant_types`, `web_server_redirect_uri`, `authorities`, `access_token_validity`, `refresh_token_validity`, `additional_information`, `autoapprove`) VALUES ('client', NULL, '$2a$10$TL2ruNy9nhRtk4Q7uDfc5u3EWIjPmuUSZyLr5ZXidRh2cGlKrJJhS', 'app', 'authorization_code,client_credentials,password,refresh_token', 'http://localhost:8008/webjars/springfox-swagger-ui/oauth2-redirect.html', 'ROLE_USER', '1800', '86400', NULL, 'false');  
  ```
- 验证方式同密码模式

## Swagger2
- 参考：https://www.jianshu.com/p/5041629e238e

## [Spock Demo](https://gitee.com/yawensilence/demo-spock)