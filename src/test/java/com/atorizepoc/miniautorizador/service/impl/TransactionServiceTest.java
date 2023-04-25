package com.atorizepoc.miniautorizador.service.impl;

import com.atorizepoc.miniautorizador.exception.MiniAutorizationErrors;
import com.atorizepoc.miniautorizador.external.dto.CardDTO;
import com.atorizepoc.miniautorizador.external.dto.TransactionalDTO;
import com.atorizepoc.miniautorizador.mapper.TransactionMapper;
import com.atorizepoc.miniautorizador.template.CardTemplate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.when;

class TransactionServiceTest {

    @InjectMocks
    private TransactionService service;

    @Mock
    private ICardService cardService;

    @Mock
    private TransactionMapper mapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should Pass When Throw SALDO_INSUFICIENTE")
    void testShouldPassWhenThrowSALDO_INSUFICIENTE() {
        TransactionalDTO transactionalDTO = CardTemplate.validTransactionalDTO();
        CardDTO existscardDTO = CardTemplate.validCardDTO();
        existscardDTO.setValor(BigDecimal.ZERO);
        when(cardService.findByNumber(anyString())).thenReturn(existscardDTO);

        ResponseEntity<Object> response = service.executeTransaction(transactionalDTO);
        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, response.getStatusCode(),
                "Assertion fail, status invalid");
        assertEquals(MiniAutorizationErrors.INSUFFICIENT_FUNDS.getMessage(),
                response.getBody(), "Assertion fail, message invalid");
    }

    @Test
    @DisplayName("Should Pass When Throw SENHA_INVALIDA")
    void testShouldPassWhenThrowSENHA_INVALIDA() {
        TransactionalDTO transactionalDTO = CardTemplate.validTransactionalDTO();
        CardDTO existscardDTO = CardTemplate.validCardDTO();
        existscardDTO.setSenha("9999");
        when(cardService.findByNumber(anyString())).thenReturn(existscardDTO);

        ResponseEntity<Object> response = service.executeTransaction(transactionalDTO);
        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, response.getStatusCode(),
                "Assertion fail, status invalid");
        assertEquals(MiniAutorizationErrors.INVALID_PASSWORD.getMessage(),
                response.getBody(), "Assertion fail, message invalid");
    }

    @Test
    @DisplayName("Should Pass When ExecuteTransaction OK")
    void testShouldPassWhenExecuteTransactionOK() {
        TransactionalDTO transactionalDTO = CardTemplate.validTransactionalDTO();
        when(cardService.findByNumber(anyString())).thenReturn(CardTemplate.validCardDTO());
        when(mapper.to(any(), any())).thenReturn(CardTemplate.validCardDTO());

        ResponseEntity<Object> response = service.executeTransaction(transactionalDTO);
        assertEquals(HttpStatus.CREATED, response.getStatusCode(),
                "Assertion fail, status invalid");
        assertEquals(HttpStatus.OK, response.getBody(),
                "Assertion fail, status invalid");
    }

}
