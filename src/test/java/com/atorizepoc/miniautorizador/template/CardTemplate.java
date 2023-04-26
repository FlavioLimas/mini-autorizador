package com.atorizepoc.miniautorizador.template;

import com.atorizepoc.miniautorizador.external.dto.CardDTO;
import com.atorizepoc.miniautorizador.external.dto.CardSaveDTO;
import com.atorizepoc.miniautorizador.external.dto.TransactionalDTO;
import com.atorizepoc.miniautorizador.model.CardEntity;

import java.math.BigDecimal;

public class CardTemplate {

    public static CardSaveDTO validCardSaveDTO() {
        return CardSaveDTO.builder()
                .numeroCartao("6549873025634501")
                .senha("1234")
                .build();
    }

    public static CardDTO validCardDTO() {
        return CardDTO.builder()
                .id(1L)
                .numeroCartao("6549873025634501")
                .senha("1234")
                .valor(BigDecimal.valueOf(10.50))
                .build();
    }

    public static CardEntity validCardEntity() {
        return CardEntity.builder()
                .id(1L)
                .number("6549873025634501")
                .password("1234")
                .value(BigDecimal.valueOf(500))
                .build();
    }

    public static CardDTO validCardDTOUpdate() {
        return CardDTO.builder()
                .id(1L)
                .numeroCartao("6549873025634850")
                .senha("4321")
                .valor(BigDecimal.valueOf(450.90))
                .build();
    }

    public static TransactionalDTO validTransactionalDTO() {
        return TransactionalDTO.builder()
                .numeroCartao("6549873025634850")
                .senhaCartao("1234")
                .valor(BigDecimal.valueOf(56.40))
                .build();
    }

}
