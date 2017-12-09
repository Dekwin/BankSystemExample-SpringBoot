package com.aidoteam.bankaccount.service.auth;



import com.aidoteam.bankaccount.model.UserEntity;
import com.aidoteam.bankaccount.model.userdetails.CustomUserDetails;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by igorkasyanenko on 21.03.17.
 */
public interface JWTService {
    String getToken(CustomUserDetails user)  throws Exception;
    UserEntity addAuthentication(HttpServletResponse response, String ssoId, String password) throws Exception;
}
