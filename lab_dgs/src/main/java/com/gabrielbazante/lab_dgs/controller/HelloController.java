package com.gabrielbazante.lab_dgs.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping(value = "/helloworld")
    public String getMethodName() {
        return "Hello World!";
    }
}
