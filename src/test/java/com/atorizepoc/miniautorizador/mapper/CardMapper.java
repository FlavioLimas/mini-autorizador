package com.atorizepoc.miniautorizador.mapper;

import com.atorizepoc.miniautorizador.external.dto.CardDTO;
import com.atorizepoc.miniautorizador.model.CardEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

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

}
