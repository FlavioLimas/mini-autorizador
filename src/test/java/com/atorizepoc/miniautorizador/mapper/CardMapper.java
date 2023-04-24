package com.atorizepoc.miniautorizador.mapper;

import com.atorizepoc.miniautorizador.exception.MiniAutorizationErrors;
import com.atorizepoc.miniautorizador.exception.MiniAutorizationException;
import com.atorizepoc.miniautorizador.external.dto.CardDTO;
import com.atorizepoc.miniautorizador.external.dto.CardSaveDTO;
import com.atorizepoc.miniautorizador.model.CardEntity;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class CardMapper {

    public List<CardDTO> from(List<CardEntity> cards) {
        List<CardDTO> cardsDTO = new ArrayList<>();
        cards.forEach(card ->
                cardsDTO.add(CardDTO.builder()
                        .numeroCartao(card.getNumber())
                        .senha(card.getPassword())
                        .valor(card.getValue())
                        .build())
        );
        return cardsDTO;
    }

    public CardEntity to(CardDTO cardDTO) {
        return CardEntity.builder()
                .id(cardDTO.getId())
                .number(cardDTO.getNumeroCartao())
                .password(cardDTO.getSenha())
                .value(cardDTO.getValor())
                .build();
    }

    public CardDTO from(CardEntity card) {
        return CardDTO.builder()
                .id(card.getId())
                .numeroCartao(card.getNumber())
                .senha(card.getPassword())
                .valor(card.getValue())
                .build();
    }

    public CardEntity toSave(CardSaveDTO cardSaveDTO) {
        return CardEntity.builder()
                .number(cardSaveDTO.getNumeroCartao())
                .password(cardSaveDTO.getSenha())
                .value(BigDecimal.valueOf(500))
                .build();
    }


    public CardSaveDTO fromSave(CardEntity card) {
        return CardSaveDTO.builder()
                .numeroCartao(card.getNumber())
                .senha(card.getPassword())
                .build();
    }

    @SneakyThrows
    public CardEntity toUpdate(CardEntity oldCard, CardDTO newCard) {
        return CardEntity.builder()
                .id(oldCard.getId())
                .number(isDifferent(newCard.getNumeroCartao(), oldCard.getNumber()))
                .password(isDifferent(newCard.getSenha(), oldCard.getPassword()))
                .value(new BigDecimal(isDifferent(newCard.getValor().toEngineeringString(),
                        oldCard.getValue().toEngineeringString())))
                .build();
    }

    private static String isDifferent(String newValue, String oldValue) {
        if (newValue != null && !newValue.equals(oldValue))
            return isValid(newValue);
        else
            return isValid(oldValue);
    }

    @SneakyThrows
    private static String isValid(String value) {
        if (value.trim().isEmpty()) {
            throw new MiniAutorizationException(MiniAutorizationErrors.VALUE_INVALID);
        }
        return value;
    }
}
