package com.acko.htlmgenerator.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class GeneratorServiceImpl implements GeneratorService {

    private Map<String, String> iconsMap;

    public GeneratorServiceImpl() {
        iconsMap = new HashMap<>();
        iconsMap.put("hospital_daily_allowance", "https://www.acko.com/static/images/rapido/hospitalization_daily_allowance_1x.png");
        iconsMap.put("personal_accident", "https://www.acko.com/static/images/abhibus/personal_accident_1x.png");
        iconsMap.put("emi_protection", "https://www.acko.com/static/images/aubank/emi_protection_1x.png");
        iconsMap.put("critical_illness", "https://www.acko.com/static/images/aubank/critical_illness_1x.png");
    }

    @Override
    public String getIconForCoverId(String coverId) {
        return this.iconsMap.get(coverId);
    }
}
