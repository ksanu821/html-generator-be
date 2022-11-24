package com.acko.htmlgenerator.models;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.OffsetDateTime;

@Entity
@Table(name = GeneratedCoi.TABLE_NAME)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class GeneratedCoi {
    public static final String TABLE_NAME = "generated_coi";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "lob")
    private String lob;

    @Column(name = "template_name")
    private String templateName;

    @Type(type = "text")
    @Column(name = "partner_details")
    private String partnerDetails;

    @Type(type = "text")
    @Column(name = "coverage_details")
    private String coverageDetails;

    @Type(type = "text")
    @Column(name = "insured_details")
    private String insuredDetails;

    @CreationTimestamp
    @Column(name = "created_on")
    private OffsetDateTime createdOn;
}
