package com.clousimmodeler.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
public class HomeController {
    @GetMapping("/")
    public String start(){
        return "index";
    }

    @RequestMapping("/sendData")
    public String sendData(@RequestParam Map<String, String> formData){
        System.out.println(formData);
        return "index";
    }

}
