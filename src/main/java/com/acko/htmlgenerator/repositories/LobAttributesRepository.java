package com.acko.htmlgenerator.repositories;

import com.acko.htmlgenerator.models.LobAttributes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LobAttributesRepository extends JpaRepository<LobAttributes, Long> {
    List<LobAttributes> findAllByLob(final String lob);
}
