package com.aidoteam.bankaccount.controller;

import com.aidoteam.bankaccount.model.UserEntity;
import com.aidoteam.bankaccount.service.bank.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping("")
    String getUser() {
        return "User!";
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    String getUsersByFirstName(@RequestParam("firstName") String firstName) {
        List<UserEntity> users = userService.findAllByFirstName(firstName);
        return "Users found: "+users.size();
    }
}
