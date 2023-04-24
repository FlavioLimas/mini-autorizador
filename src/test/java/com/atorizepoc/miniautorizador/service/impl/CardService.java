package com.atorizepoc.miniautorizador.service.impl;

import com.atorizepoc.miniautorizador.exception.MiniAutorizationErrors;
import com.atorizepoc.miniautorizador.exception.MiniAutorizationException;
import com.atorizepoc.miniautorizador.external.dto.CardDTO;
import com.atorizepoc.miniautorizador.external.dto.CardSaveDTO;
import com.atorizepoc.miniautorizador.mapper.CardMapper;
import com.atorizepoc.miniautorizador.model.CardEntity;
import com.atorizepoc.miniautorizador.repository.CardRepository;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class CardService implements ICardService {

    private CardRepository repository;

    private CardMapper mapper;

    @Override
    @SneakyThrows
    public List<CardDTO> getAll() {
        List<CardEntity> cards = repository.findAll();
        if (cards.isEmpty()) {
            log.error("Cards " + cards);
            throw new MiniAutorizationException(MiniAutorizationErrors.CARDS_NOT_FOUND);
        }
        return mapper.from(cards);
    }

    @Override
    public CardDTO findByTitle(String cardNumber) {
        return null;
    }

    @Override
    public ResponseEntity<CardSaveDTO> save(CardSaveDTO cardSaveDTO) {
        return null;
    }

    @Override
    public CardDTO update(CardDTO cardDTO) {
        return null;
    }

    @Override
    public void deleteByCardNumber(String cardNumber) {

    }

}
