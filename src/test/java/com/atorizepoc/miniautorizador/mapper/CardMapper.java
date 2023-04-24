package com.atorizepoc.miniautorizador.mapper;

import com.atorizepoc.miniautorizador.external.dto.CardDTO;
import com.atorizepoc.miniautorizador.external.dto.CardSaveDTO;
import com.atorizepoc.miniautorizador.model.CardEntity;
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

}
