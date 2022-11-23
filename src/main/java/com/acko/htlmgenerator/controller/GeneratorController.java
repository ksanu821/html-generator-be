package com.acko.htlmgenerator.controller;

import com.acko.htlmgenerator.entities.Attributes;
import com.acko.htlmgenerator.service.GeneratorService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class GeneratorController {
    private final GeneratorService generatorService;

    @GetMapping("/home")
    public ResponseEntity<String> getHome() {
        return ResponseEntity.ok("This is home page");
    }

    @GetMapping("/getIconLink/{coverId}")
    public String getIconLink(@PathVariable String coverId) {
        return this.generatorService.getIconForCoverId(coverId);
    }

    @GetMapping("/getAttributesForLob/{lob}")
    public List<Attributes> getAttributesForLob(@PathVariable String lob) {
        System.out.println("getAtrributes");
        return this.generatorService.getValuesForLob(lob);
    }


}
