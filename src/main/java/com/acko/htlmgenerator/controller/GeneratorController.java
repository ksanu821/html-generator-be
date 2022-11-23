package com.acko.htlmgenerator.controller;

import com.acko.htlmgenerator.service.GeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GeneratorController {
    @Autowired
    private GeneratorService generatorService;

    @GetMapping("/home")
    public String getHome() {
        return "This is home page";
    }

    @GetMapping("/getIconLink/{coverId}")
    public String getIconLink(@PathVariable String coverId) {
        return this.generatorService.getIconForCoverId(coverId);
    }
}
