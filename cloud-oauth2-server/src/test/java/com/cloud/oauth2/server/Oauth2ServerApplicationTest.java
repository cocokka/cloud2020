package com.cloud.oauth2.server;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Oauth2ServerApplicationTest {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Test
    public void encode() {
        System.out.println(passwordEncoder.encode("secret"));
    }
}