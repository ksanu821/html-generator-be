package com.acko.htlmgenerator.repositories;

import com.acko.htlmgenerator.models.Coi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface CoiRepository extends JpaRepository<Coi, Long> {
}
