package com.aidoteam.bankaccount.controller;

import com.aidoteam.bankaccount.service.bank.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
class IndexController {


    @Autowired
    UserService userService;


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



}
