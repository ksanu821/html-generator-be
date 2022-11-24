package com.acko.htmlgenerator.repositories;

import com.acko.htmlgenerator.models.TemplateHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TemplateHistoryRepository extends JpaRepository<TemplateHistory, Long> {

    Optional<TemplateHistory> findByTemplateNameAndLob (final String templateName, final String lob);

}
