package com.acko.htmlgenerator.controller;

import com.acko.htmlgenerator.dto.HeaderRequestDTO;
import com.acko.htmlgenerator.entities.Attributes;
import com.acko.htmlgenerator.service.GeneratorService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @PostMapping("/generateHeader")
    public ResponseEntity<String> getHeaderTemplate(@RequestBody HeaderRequestDTO request) {
        return ResponseEntity.ok(this.generatorService.getHeaderTemplate(request));
    }

}
