package com.aidoteam.bankaccount.service;

/**
 * Created by igorkasyanenko on 11.12.16.
 */


import com.aidoteam.bankaccount.model.UserEntity;
import com.aidoteam.bankaccount.model.userdetails.CustomUserDetails;
import com.aidoteam.bankaccount.service.bank.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.plugin.util.UserProfile;

import java.util.ArrayList;
import java.util.List;


@Service("customUserDetailsService")
@Transactional
public class CustomUserDetailsService implements UserDetailsService {

    static final Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);

    @Autowired
    private UserService userService;

    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String ssoId)
            throws UsernameNotFoundException {
        UserEntity user = userService.findByEmail(ssoId);
        logger.info("User : {}", user);
        if (user == null) {
            logger.info("User not found");
            throw new UsernameNotFoundException("Username not found");
        }
        CustomUserDetails cud = new CustomUserDetails(user.getEmail(), user.getPassword(),
                true, true, true, true, getGrantedAuthorities(user));

        cud.setUser(user);
        return cud;
    }


    private List<GrantedAuthority> getGrantedAuthorities(UserEntity user) {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();


//        for (UserProfile userProfile : user.getUserProfiles()) {
//            logger.info("UserProfile : {}", userProfile);
//            authorities.add(new SimpleGrantedAuthority("ROLE_" + userProfile.getType()));
//        }
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        logger.info("authorities : {}", authorities);
        return authorities;
    }

}
