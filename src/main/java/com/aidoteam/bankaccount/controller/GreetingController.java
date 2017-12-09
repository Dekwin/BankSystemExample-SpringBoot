package com.aidoteam.bankaccount.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
class GreetingController {
    @RequestMapping("/hi")
    String hello() {
        return "Hello!";
    }
}
