package com.acko.htmlgenerator.models;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.OffsetDateTime;

@Entity
@Table(name = TemplateHistory.TABLE_NAME)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class TemplateHistory {
    public static final String TABLE_NAME = "template_history";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "template_name")
    private String templateName;

    @Column(name = "lob")
    private String lob;

    @Type(type = "text")
    @Column(name = "partner_details_request")
    private String partnerDetailsRequest;

    @Type(type = "text")
    @Column(name = "coverage_details_request")
    private String coverageDetailsRequest;

    @Type(type = "text")
    @Column(name = "insured_details_request")
    private String insuredDetailsRequest;

    @CreationTimestamp
    @Column(name = "created_on")
    private OffsetDateTime createdOn;
}
