package com.aidoteam.bankaccount.service.security;

import com.aidoteam.bankaccount.model.UserEntity;
import org.springframework.security.core.Authentication;

public interface IAuthenticationFacade {
    Authentication getAuthentication();
    UserEntity getCurrentUser();
}