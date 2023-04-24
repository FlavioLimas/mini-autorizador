package com.atorizepoc.miniautorizador.repository;

import com.atorizepoc.miniautorizador.model.CardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends JpaRepository<CardEntity, Long> {
}
