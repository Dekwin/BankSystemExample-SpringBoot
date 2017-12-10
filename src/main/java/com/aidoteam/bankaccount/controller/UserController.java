package com.aidoteam.bankaccount.controller;

import com.aidoteam.bankaccount.model.UserEntity;
import com.aidoteam.bankaccount.service.bank.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

//    @RequestMapping(value = "", method = RequestMethod.GET)
//    String getUsersByFirstName(@RequestParam("firstName") String firstName) {
//        List<UserEntity> users = userService.findAllByFirstName(firstName);
//        return "Users found: "+users.size();
//    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    ResponseEntity<List<UserEntity>> getAllUsersByNameOrEmail(@RequestParam("firstName") Optional<String> firstName, @RequestParam("email") Optional<String> email) {

        List<UserEntity> users = userService.findByFirstNameOrEmail(firstName.isPresent() ? firstName.get() : null, email.isPresent() ? email.get() : null);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
}
