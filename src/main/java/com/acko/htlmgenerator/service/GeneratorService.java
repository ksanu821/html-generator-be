package com.acko.htlmgenerator.service;

import com.acko.htlmgenerator.entities.Attributes;

import java.util.List;

public interface GeneratorService {

    public String getIconForCoverId(String coverId);

    public List<Attributes> getValuesForLob(String lob);
}
