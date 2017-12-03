package com.aidoteam.bankaccount.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class GreetingController {

    @RequestMapping("/hi")
    String hello() {
        return "Hello!";
    }
}
