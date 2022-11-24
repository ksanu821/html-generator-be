package com.acko.htmlgenerator.repositories;

import com.acko.htmlgenerator.models.GeneratedCoi;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GeneratedCoiRepository extends JpaRepository<GeneratedCoi, Long> {
    Optional<GeneratedCoi> findByTemplateNameAndLob(final String templateName, final String lob);
}
