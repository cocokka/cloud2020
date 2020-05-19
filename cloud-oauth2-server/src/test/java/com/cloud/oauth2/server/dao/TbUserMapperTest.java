package com.cloud.oauth2.server.dao;

import com.cloud.oauth2.server.entity.TbUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TbUserMapperTest {

    @Autowired
    private TbUserMapper tbUserMapper;

    @Test
    public void queryByName() {
        TbUser tbUser = tbUserMapper.queryByName("zhangsan");
        assertNotNull(tbUser);
    }
}