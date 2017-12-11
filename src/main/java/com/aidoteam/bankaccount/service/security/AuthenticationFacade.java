package com.aidoteam.bankaccount.service.security;

import com.aidoteam.bankaccount.model.UserEntity;
import com.aidoteam.bankaccount.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationFacade implements IAuthenticationFacade {

    @Autowired
    private UserRepository userRepository;
    @Override
    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    @Override
    public UserEntity getCurrentUser(){
        String email = (String)getAuthentication().getPrincipal();
        UserEntity owner = userRepository.findByEmail(email);
        return  owner;
    }
}
