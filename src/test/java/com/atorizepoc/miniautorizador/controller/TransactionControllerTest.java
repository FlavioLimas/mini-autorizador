package com.atorizepoc.miniautorizador.controller;

import com.atorizepoc.miniautorizador.external.dto.TransactionalDTO;
import com.atorizepoc.miniautorizador.service.impl.TransactionService;
import com.atorizepoc.miniautorizador.template.CardTemplate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TransactionControllerTest {

    @InjectMocks
    private TransactionController controller;
    @Mock
    private TransactionService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should Pass When ExecuteTransaction OK")
    void testShouldPassWhenExecuteTransactionOK() {
        TransactionalDTO transactionalDTO = CardTemplate.validTransactionalDTO();
        when(service.executeTransaction(any()))
                .thenReturn(ResponseEntity.ok(CardTemplate.validTransactionalDTO()));
        ResponseEntity<Object> response = controller.executeTransaction(transactionalDTO);
        assertEquals(HttpStatus.OK, response.getStatusCode(),
                "Assertion fail, value invalid");
    }

}
