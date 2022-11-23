package com.acko.htmlgenerator.service;

import com.acko.htmlgenerator.entities.Attributes;
import com.acko.htmlgenerator.models.Coi;
import com.acko.htmlgenerator.repositories.CoiRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class GeneratorServiceImpl implements GeneratorService {
    private final CoiRepository coiRepository;


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
