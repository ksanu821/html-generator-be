package com.acko.htmlgenerator.dto;

import com.acko.htmlgenerator.pojo.CoverageDetails;
import com.acko.htmlgenerator.pojo.InsuredDetails;
import com.acko.htmlgenerator.pojo.PartnerDetails;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class HeaderRequestDTO {
    private String templateName;
    private String lob;
    private PartnerDetails partnerDetails;
    private List<InsuredDetails> insuredDetailsList;
    private List<CoverageDetails> coverageDetailsList;
}
