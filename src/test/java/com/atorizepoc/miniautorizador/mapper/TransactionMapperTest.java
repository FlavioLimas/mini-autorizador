package com.atorizepoc.miniautorizador.mapper;

import com.atorizepoc.miniautorizador.external.dto.CardDTO;
import com.atorizepoc.miniautorizador.external.dto.TransactionalDTO;
import com.atorizepoc.miniautorizador.template.CardTemplate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TransactionMapperTest {

    @InjectMocks
    private TransactionMapper mapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should Pass When To Return CardDTO Is Valid")
    void testShouldPassWhenToReturnCardDTOIsValid() {
        TransactionalDTO transactionalDTO = CardTemplate.validTransactionalDTO();
        CardDTO cardDTOMock = CardTemplate.validCardDTO();
        cardDTOMock.setNumeroCartao("6549873025634850");
        cardDTOMock.setValor(BigDecimal.valueOf(56.40));
        CardDTO cardDTO = mapper.to(transactionalDTO, cardDTOMock);
        assertEquals(cardDTOMock.getId(), cardDTO.getId(),
                "Assertion fail, value invalid");
        assertEquals(cardDTOMock.getNumeroCartao(), cardDTO.getNumeroCartao(),
                "Assertion fail, value invalid");
        assertEquals(cardDTOMock.getSenha(), cardDTO.getSenha(),
                "Assertion fail, value invalid");
        assertEquals(cardDTOMock.getValor(), cardDTO.getValor(),
                "Assertion fail, value invalid");
    }

}
