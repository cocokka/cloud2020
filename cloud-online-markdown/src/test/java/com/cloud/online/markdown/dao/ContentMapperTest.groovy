package com.cloud.online.markdown.dao


import com.cloud.online.markdown.config.MybatisConfig
import com.cloud.online.markdown.entity.Content
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.ImportAutoConfiguration
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

//@SpringBootTest
@MybatisTest
@ImportAutoConfiguration(MybatisConfig.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ContentMapperTest extends Specification {

    @Autowired
    private ContentMapper contentMapper;

    def "QueryById"() {
        given:
        def id = 1

        when:
        Content content = contentMapper.queryById(id)

        then:
        println(content)
        "第一天文本内容".equals(content.getText())
    }
}
