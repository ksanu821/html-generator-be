package com.acko.htmlgenerator.service;

import com.acko.htmlgenerator.dto.HeaderRequestDTO;
import com.acko.htmlgenerator.entities.Attributes;
import com.acko.htmlgenerator.models.LobAttributes;

import java.util.List;

public interface GeneratorService {

    public String getIconForCoverId(String coverId);

    public List<LobAttributes> getValuesForLob(String lob);

    public String createInsuredDetails(HeaderRequestDTO request);

    public String getHeaderTemplate(HeaderRequestDTO request);

    public String getCoverageTemplate(HeaderRequestDTO request);

    public String createTemplate(HeaderRequestDTO request);
}
