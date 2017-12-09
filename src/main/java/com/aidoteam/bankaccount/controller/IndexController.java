package com.aidoteam.bankaccount.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/index")
class IndexController {
    @RequestMapping("")
    String hello() {
        return "Hello!";
    }
}
