package com.acko.htmlgenerator.controller;

import com.acko.htmlgenerator.dto.HeaderRequestDTO;
import com.acko.htmlgenerator.models.LobAttributes;
import com.acko.htmlgenerator.service.GeneratorService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public List<LobAttributes> getAttributesForLob(@PathVariable String lob) {
        System.out.println("getAtrributesForLob");
        return this.generatorService.getValuesForLob(lob);
    }

    @PostMapping("/generateHeader")
    public ResponseEntity<String> getHeaderTemplate(@RequestBody HeaderRequestDTO request) {
        return ResponseEntity.ok(this.generatorService.getHeaderTemplate(request));
    }
    @PostMapping("/createInsuredDetails")
    public String createInsuredDetails(@RequestBody HeaderRequestDTO request) {
        return this.generatorService.createInsuredDetails(request);
    }

    @PostMapping("/createCoverageDetails")
    public String createCoverageDetails(@RequestBody HeaderRequestDTO request) {
        return this.generatorService.getCoverageTemplate(request);
    }
}
