package com.atorizepoc.miniautorizador.service.impl;

import com.atorizepoc.miniautorizador.exception.MiniAutorizationErrors;
import com.atorizepoc.miniautorizador.exception.MiniAutorizationException;
import com.atorizepoc.miniautorizador.external.dto.CardDTO;
import com.atorizepoc.miniautorizador.external.dto.CardSaveDTO;
import com.atorizepoc.miniautorizador.mapper.CardMapper;
import com.atorizepoc.miniautorizador.model.CardEntity;
import com.atorizepoc.miniautorizador.repository.CardRepository;
import com.atorizepoc.miniautorizador.service.ICardService;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.net.URI;
import java.util.List;
import java.util.Optional;

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
    @SneakyThrows
    public CardDTO findByNumber(String cardNumber) {
        checkValue(cardNumber);
        log.info("Find By Card Number " + cardNumber);
        CardEntity existsCardBalance = repository.findByNumber(cardNumber).orElseThrow(() ->
                new MiniAutorizationException(MiniAutorizationErrors.NON_EXISTENT_CARD));
        return mapper.from(existsCardBalance);
    }

    @Override
    @SneakyThrows
    public ResponseEntity<Object> findByCardBalance(String cardNumber) {
        checkValue(cardNumber);
        log.info("Find By Card Number " + cardNumber);
        Optional<BigDecimal> existsCardBalance = repository.findByNumber(cardNumber)
                .map(CardEntity::getValue);
        if (existsCardBalance.isEmpty()) {
            return ResponseEntity.status(HttpStatusCode.valueOf(404).value()).build();
        }
        return ResponseEntity.ok(existsCardBalance.map(BigDecimal::toEngineeringString));
    }

    @Override
    @SneakyThrows
    @Transactional
    public ResponseEntity<CardSaveDTO> save(CardSaveDTO cardSaveDTO) {
        checkValue(cardSaveDTO.getNumeroCartao());
        Optional<CardEntity> existsCardNumber = repository.findByNumber(cardSaveDTO.getNumeroCartao());
        if (existsCardNumber.isEmpty()) {
            CardEntity card = mapper.toSave(cardSaveDTO);
            log.info("Save Card " + card);
            CardEntity cardSaved = Optional.of(repository.save(card))
                    .orElseThrow(() -> new MiniAutorizationException(MiniAutorizationErrors.CARD_NOT_SAVED));
            return ResponseEntity.created(URI.create("/card/save")).body(mapper.fromSave(cardSaved));
        }
        return ResponseEntity.unprocessableEntity().body(mapper.fromSave(existsCardNumber.get())
        );
    }

    @Override
    @SneakyThrows
    @Transactional
    public CardDTO update(CardDTO cardDTO) {
        CardEntity oldCard = repository.findById(cardDTO.getId())
                .orElseThrow(() -> new MiniAutorizationException(MiniAutorizationErrors.NON_EXISTENT_CARD));
        log.info("Update Card new value " + cardDTO + " old value " + oldCard);
        CardEntity cardEntity = mapper.toUpdate(oldCard, cardDTO);
        CardEntity cardEntitySaved = Optional.of(repository.save(cardEntity))
                .orElseThrow(() -> new MiniAutorizationException(MiniAutorizationErrors.CARD_NOT_UPDATED));
        return mapper.from(cardEntitySaved);
    }

    @Override
    @Transactional
    @SneakyThrows
    public void deleteByCardNumber(String cardNumber) {
        repository.deleteByNumber(cardNumber);
    }

    @SneakyThrows
    private static void checkValue(String value) {
        if (value == null || value.trim().isEmpty()) {
            log.error("Value Invalid " + value);
            throw new MiniAutorizationException(MiniAutorizationErrors.VALUE_INVALID);
        }
    }

}
