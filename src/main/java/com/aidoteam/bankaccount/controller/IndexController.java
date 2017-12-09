package com.aidoteam.bankaccount.controller;

import com.aidoteam.bankaccount.model.UserEntity;
import com.aidoteam.bankaccount.model.jwtauth.AccountCredentials;
import com.aidoteam.bankaccount.model.jwtauth.AuthenticatedUser;
import com.aidoteam.bankaccount.service.auth.JWTService;
import com.aidoteam.bankaccount.service.bank.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequestMapping("/")
class IndexController {


    @Autowired
    UserService userService;
    @Autowired
    JWTService jwtService;

//    @ResponseBody
//    @RequestMapping(value = "/signup", method = RequestMethod.POST)
//    public ResponseEntity registerUser(@JsonProperty("firstName") String firstName,
//                                       @JsonProperty("lastName") String lastName,
//                                       @JsonProperty("email") String email,
//                                       @JsonProperty("password") String password,
//                                       @JsonProperty("phone") String phone) {
//        userService.create(firstName,lastName,email,password,phone);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
//
//    //mock
//    @RequestMapping(value = "/signin",  method = RequestMethod.POST)
//    @ResponseBody
//    public ResponseEntity<UserEntity> getUsersByFirstName(UserAuthCredentialsEntity credentials) {
//        UserEntity user = userService.findByEmail(credentials.getEmail());
//        return new ResponseEntity<>(user, HttpStatus.OK);
//    }


    @RequestMapping(value = { "/signup" }, method = RequestMethod.POST)
    public ResponseEntity<UserEntity> signup(
            @Valid @RequestBody UserEntity user, BindingResult bindingResult, HttpServletResponse response) throws Exception {

        if(userService.findByEmail(user.getEmail()) == null){
            //FieldError ssoError = new FieldError("user","ssoId","non.unique.ssoId", new String[]{user.getEmail()}, Locale.getDefault()));
            //bindingResult.addError(ssoError);
            IllegalArgumentException error = new IllegalArgumentException("err");
            // new  MethodArgumentNotValidException().getBindingResult().addError(ssoError);
            throw error;
            //return new ResponseEntity<User>(user, HttpStatus.FORBIDDEN);
        }

        userService.save(user);

        String name = user.getEmail();

        jwtService.addAuthentication(response,user.getEmail(),user.getPassword());

        SecurityContextHolder.getContext().setAuthentication(new AuthenticatedUser(name));

        return new ResponseEntity<UserEntity>(user, HttpStatus.CREATED);
    }

    @RequestMapping(value = { "/signin" }, method = RequestMethod.POST)
    public ResponseEntity<UserEntity> signin(
            @RequestBody AccountCredentials credentials, HttpServletResponse response) throws Exception {


        UserEntity user = jwtService.addAuthentication(response,credentials.getEmail(),credentials.getPassword());


        return new ResponseEntity<UserEntity>(user, HttpStatus.OK);
    }
}
