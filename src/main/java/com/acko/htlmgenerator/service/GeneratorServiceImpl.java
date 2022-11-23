package com.acko.htlmgenerator.service;

import com.acko.htlmgenerator.entities.Attributes;
import com.acko.htlmgenerator.models.Coi;
import com.acko.htlmgenerator.repositories.CoiRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class GeneratorServiceImpl implements GeneratorService {

//    private Map<String, String> iconsMap;
    private final CoiRepository coiRepository;

//    public GeneratorServiceImpl() {
//        iconsMap = new HashMap<>();
//        iconsMap.put("hospital_daily_allowance", "https://www.acko.com/static/images/rapido/hospitalization_daily_allowance_1x.png");
//        iconsMap.put("personal_accident", "https://www.acko.com/static/images/abhibus/personal_accident_1x.png");
//        iconsMap.put("emi_protection", "https://www.acko.com/static/images/aubank/emi_protection_1x.png");
//        iconsMap.put("critical_illness", "https://www.acko.com/static/images/aubank/critical_illness_1x.png");
//    }

    @Override
    public String getIconForCoverId(String coverId) {
        this.coiRepository.save(Coi.builder()
                .lob("internet")
                .template("<></>")
                .createdOn(OffsetDateTime.now())
                .build());

//        return this.iconsMap.get(coverId);
        return "true";
    }

    @Override
    public List<Attributes> getValuesForLob(String lob) {
        return null;
    }

}
