package com.acko.htmlgenerator.service;

import com.acko.htmlgenerator.dto.HeaderRequestDTO;
import com.acko.htmlgenerator.entities.Attributes;

import java.util.List;

public interface GeneratorService {

    public String getIconForCoverId(String coverId);

    public List<Attributes> getValuesForLob(String lob);

    public String getHeaderTemplate(HeaderRequestDTO request);
}
