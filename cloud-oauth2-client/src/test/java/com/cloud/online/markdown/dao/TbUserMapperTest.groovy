package com.cloud.online.markdown.dao

import com.cloud.oauth2.client.config.MybatisConfig
import com.cloud.oauth2.client.dao.TbUserMapper
import com.cloud.oauth2.client.entity.TbUser
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.ImportAutoConfiguration
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import spock.lang.Specification

//@SpringBootTest
@MybatisTest
@ImportAutoConfiguration(MybatisConfig.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class TbUserMapperTest extends Specification {

    @Autowired
    private TbUserMapper tbUserMapper;

    def "QueryById"() {
        given:
        def id = 1

        when:
        TbUser user = tbUserMapper.queryById(id)

        then:
        println(user)
        user.getUsername() == "zhangsan"
    }
}
