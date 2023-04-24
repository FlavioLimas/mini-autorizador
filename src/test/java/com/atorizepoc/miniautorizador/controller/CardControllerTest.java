package com.atorizepoc.miniautorizador.controller;

import com.atorizepoc.miniautorizador.external.dto.CardDTO;
import com.atorizepoc.miniautorizador.external.dto.CardSaveDTO;
import com.atorizepoc.miniautorizador.service.impl.CardService;
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
import java.net.URI;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.atMostOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class CardControllerTest {

    @InjectMocks
    private CardController controller;
    @Mock
    private CardService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should Pass When GetAll OK")
    void testShouldPassWhenGetAllOK() {
        ResponseEntity<List<CardDTO>> response = controller.getAll();
        assertEquals(HttpStatus.OK, response.getStatusCode(),
                "Assertion fail, response status invalid");
    }

    @Test
    @DisplayName("Should Pass When FindByCardBalance OK")
    void testShouldPassWhenFindByCardBalanceOK() {
        when(service.findByCardBalance(anyString())).thenReturn(ResponseEntity.ok(BigDecimal.TEN));
        ResponseEntity<Object> response = controller.findByCardBalance("6549873025634501");
        assertEquals(HttpStatus.OK, response.getStatusCode(),
                "Assertion fail, response status invalid");
    }

    @Test
    @DisplayName("Should Pass When Save CREATED")
    void testShouldPassWhenSaveCREATED() {
        CardSaveDTO cardSaveDTO = CardTemplate.validCardSaveDTO();
        when(service.save(any())).thenReturn(ResponseEntity.created(URI.create("/card/save"))
                .body(cardSaveDTO));
        ResponseEntity<CardSaveDTO> response = controller.save(cardSaveDTO);
        assertEquals(HttpStatus.CREATED, response.getStatusCode(),
                "Assertion fail, response status invalid");
    }

    @Test
    @DisplayName("Should Pass When Update OK")
    void testShouldPassWhenUpdateOK() {
        ResponseEntity<CardDTO> response = controller.update(CardTemplate.validCardDTO());
        when(service.update(any())).thenReturn(CardTemplate.validCardDTO());
        assertEquals(HttpStatus.OK, response.getStatusCode(),
                "Assertion fail, response status invalid");
    }

    @Test
    @DisplayName("Should Pass When Delete OK")
    void testShouldPassWhenDeleteOK() {
        ResponseEntity<java.lang.Object> response = controller.deleteByCardNumber("6549873025634501");
        verify(service, atMostOnce()).deleteByCardNumber("6549873025634501");
        assertEquals(HttpStatus.OK, response.getStatusCode(),
                "Assertion fail, response status invalid");
    }

}