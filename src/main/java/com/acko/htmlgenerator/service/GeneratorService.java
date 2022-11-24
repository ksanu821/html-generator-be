package com.acko.htmlgenerator.service;

import com.acko.htmlgenerator.dto.HeaderRequestDTO;
import com.acko.htmlgenerator.entities.Attributes;
import com.acko.htmlgenerator.models.CoverageIcon;
import com.acko.htmlgenerator.models.GeneratedCoi;
import com.acko.htmlgenerator.models.LobAttributes;

import java.util.List;

public interface GeneratorService {

    public List<CoverageIcon> getCovers();

    public List<LobAttributes> getValuesForLob(String lob);

    public String createInsuredDetails(HeaderRequestDTO request);

    public String getHeaderTemplate(HeaderRequestDTO request);

    public String getCoverageTemplate(HeaderRequestDTO request);

    public String createTemplate(HeaderRequestDTO request);

    public String saveGeneratedHtml(HeaderRequestDTO request);

    public String saveNewGeneratedHtml(HeaderRequestDTO request);

    public List<GeneratedCoi> getTemplatesByLob(String lob);
}
