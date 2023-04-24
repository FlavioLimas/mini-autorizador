package com.atorizepoc.miniautorizador.repository;

import com.atorizepoc.miniautorizador.model.CardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Optional;

@Repository
public interface CardRepository extends JpaRepository<CardEntity, Long> {

    Optional<CardEntity> findByNumber(String cardNumber);

    Optional<BigDecimal> findByValue(String cardNumber);

    void deleteByNumber(String cardNumber);

}
