package com.cloud.oauth2.server.config.impl;

import com.cloud.oauth2.server.entity.TbUser;
import com.cloud.oauth2.server.service.TbPermissionService;
import com.cloud.oauth2.server.service.TbUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE;

@Service("userDetailsServiceImpl")
@Scope(SCOPE_PROTOTYPE)
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private TbUserService tbUserService;

    @Autowired
    private TbPermissionService tbPermissionService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        TbUser tbUser = tbUserService.queryByName(username);
        if (Objects.nonNull(tbUser)) {
            List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
            tbPermissionService.queryByUserId(tbUser.getId()).forEach(p -> {
                GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(p.getEnname());
                grantedAuthorities.add(grantedAuthority);
            });
            return new User(tbUser.getUsername(), tbUser.getPassword(), grantedAuthorities);
        }
        return null;
    }
}
