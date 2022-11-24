package com.acko.htmlgenerator.models;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.OffsetDateTime;

@Entity
@Table(name = LobAttributes.TABLE_NAME)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class LobAttributes {

    public static final String TABLE_NAME = "lob_attributes";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "lob")
    private String lob;

    @Column(name = "attribute_id")
    private String attribute_id;

    @Column(name = "display_name")
    private String displayName;

    @Column(name = "value")
    private String value;

    @CreationTimestamp
    @Column(name = "created_on")
    private OffsetDateTime createdOn;
}
