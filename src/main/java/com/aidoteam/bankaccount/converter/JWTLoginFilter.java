package com.aidoteam.bankaccount.converter;

import com.aidoteam.bankaccount.model.errors.ErrorMessage;
import com.aidoteam.bankaccount.model.jwtauth.TokenAuthentication;
import com.aidoteam.bankaccount.service.TokenAuthenticationService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by igorkasyanenko on 04.03.17.
 */


public class JWTLoginFilter extends AbstractAuthenticationProcessingFilter {

    @Autowired
    private TokenAuthenticationService tokenAuthenticationService;

    public JWTLoginFilter() {
        super("/**");

        /// tokenAuthenticationService = new TokenAuthenticationService();

        setAuthenticationSuccessHandler((request, response, authentication) ->
        {
            SecurityContextHolder.getContext().setAuthentication(authentication);
            request.getRequestDispatcher(request.getServletPath()).forward(request, response);
        });

        setAuthenticationFailureHandler((request, response, authenticationException) -> {
            ErrorMessage errorMessage = new ErrorMessage(authenticationException.getMessage());
            response.setStatus(403);
            response.getOutputStream().print(convertObjectToJson(errorMessage));
        });
    }

//    @Override
//    public Authentication attemptAuthentication(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws AuthenticationException, IOException, ServletException {
//        try {
//            AccountCredentials credentials = new ObjectMapper().readValue(httpServletRequest.getInputStream(), AccountCredentials.class);
//            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(credentials.getSsoId(), credentials.getPassword());
//            return getAuthenticationManager().authenticate(token);
//        }
//        catch (UnrecognizedPropertyException ex){
//            httpServletResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
//            httpServletResponse.getWriter().write(convertObjectToJson(new JSONException(0,ex.getMessage())));
//            return null;
//        }
//    }


    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException, IOException, ServletException {

        System.out.println("attempt auth " + request.getHeader("Authorization"));
        String token = request.getHeader("Authorization");
        if (token == null)
            token = request.getParameter("Authorization");
//        if (token == null) {
//            TokenAuthentication authentication = new TokenAuthentication(null);
//            System.out.println("not auth "+authentication.getName());
//            authentication.setAuthenticated(false);
//            return authentication;
//        }
        TokenAuthentication tokenAuthentication = new TokenAuthentication(token);
        Authentication authentication = getAuthenticationManager().authenticate(tokenAuthentication);
        return authentication;
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res,
                         FilterChain chain) throws IOException, ServletException {
        super.doFilter(req, res, chain);
    }


    public String convertObjectToJson(Object object) throws JsonProcessingException {
        if (object == null) {
            return null;
        }
        ObjectMapper mapper = new ObjectMapper();

        return mapper.writeValueAsString(object);
    }

//    @Override
//    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication)
//            throws IOException, ServletException {
//
//
//
//        User user = ((CustomUserDetails)authentication.getPrincipal()).getUser();
//        String name = authentication.getName();
//        tokenAuthenticationService.addAuthentication(response, name);
//        response.setContentType("application/json");
////        response.setCharacterEncoding("UTF-8");
//        response.getWriter().write(convertObjectToJson(user));
//
//    }


}