package com.cloud.online.markdown.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
public class IndexController {

    @RequestMapping(value = {"/", "/index"}, method = GET)
    public String index() {
        return "redirect:/contents/all";
    }

}
