package com.acko.htlmgenerator.service;

import com.acko.htlmgenerator.entities.Attributes;
import com.acko.htlmgenerator.models.Coi;
import com.acko.htlmgenerator.repositories.CoiRepository;
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
