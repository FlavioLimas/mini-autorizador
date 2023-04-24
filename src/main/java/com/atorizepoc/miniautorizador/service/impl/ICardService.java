package com.atorizepoc.miniautorizador.service.impl;

import com.atorizepoc.miniautorizador.external.dto.CardDTO;
import com.atorizepoc.miniautorizador.external.dto.CardSaveDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ICardService {

    List<CardDTO> getAll();

    CardDTO findByTitle(String cardNumber);

    ResponseEntity<CardSaveDTO> save(CardSaveDTO cardSaveDTO);

    CardDTO update(CardDTO cardDTO);

    void deleteByCardNumber(String cardNumber);

}