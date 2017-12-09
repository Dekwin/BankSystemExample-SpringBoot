package com.aidoteam.bankaccount.controller;

import com.aidoteam.bankaccount.model.UserEntity;
import com.aidoteam.bankaccount.model.auth.UserAuthCredentialsEntity;
import com.aidoteam.bankaccount.service.bank.UserService;


import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    UserService userService;

    @ResponseBody
    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public ResponseEntity registerUser(@JsonProperty("firstName") String firstName,
                                       @JsonProperty("lastName") String lastName,
                                       @JsonProperty("email") String email,
                                       @JsonProperty("password") String password,
                                       @JsonProperty("phone") String phone) {
        userService.create(firstName,lastName,email,password,phone);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //mock
    @RequestMapping(value = "/signin",  method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<UserEntity> getUsersByFirstName(UserAuthCredentialsEntity credentials) {
        UserEntity user = userService.findByEmail(credentials.getEmail());
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}

