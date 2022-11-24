package com.acko.htmlgenerator.controller;

import com.acko.htmlgenerator.dto.HeaderRequestDTO;
import com.acko.htmlgenerator.entities.TemplateHistoryWithHtmlContent;
import com.acko.htmlgenerator.models.CoverageIcon;
import com.acko.htmlgenerator.models.GeneratedCoi;
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

    @GetMapping("/getCovers")
    public List<CoverageIcon> getIconLink() {
        return this.generatorService.getCovers();
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

    @PostMapping("/createTemplate")
    public String createTemplate(@RequestBody HeaderRequestDTO request) {
        return this.generatorService.createTemplate(request);
    }

    @PostMapping("/modifyTemplate")
    public String modifyTemplate(@RequestBody HeaderRequestDTO request) {
        return this.generatorService.saveGeneratedHtml(request);
    }

    @PostMapping("/saveNewTemplate")
    public String saveNewTemplate(@RequestBody HeaderRequestDTO request) {
        return this.generatorService.saveNewGeneratedHtml(request);
    }

    @GetMapping("/getTemplatesByLob/{lob}")
    public List<GeneratedCoi> getTemplatesByLob(@PathVariable String lob) {
        return this.generatorService.getTemplatesByLob(lob);
    }

    @GetMapping("/getTemplateDetails/{lob}/{templateName}")
    public TemplateHistoryWithHtmlContent getTemplateDetailsByLob(@PathVariable String lob, @PathVariable String templateNamee) {
        return this.generatorService.getTemplateHistoryByTemplateNameAndLob(templateNamee, lob);
    }
}
