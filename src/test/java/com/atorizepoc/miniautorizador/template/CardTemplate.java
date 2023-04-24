package com.atorizepoc.miniautorizador.template;

import com.atorizepoc.miniautorizador.external.dto.CardDTO;
import com.atorizepoc.miniautorizador.external.dto.CardSaveDTO;

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
                .numeroCartao("6549873025634501")
                .senha("1234")
                .valor(BigDecimal.TEN)
                .build();
    }

}
