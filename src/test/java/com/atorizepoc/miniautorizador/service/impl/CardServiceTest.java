package com.atorizepoc.miniautorizador.service.impl;

import com.atorizepoc.miniautorizador.exception.MiniAutorizationErrors;
import com.atorizepoc.miniautorizador.exception.MiniAutorizationException;
import com.atorizepoc.miniautorizador.external.dto.CardDTO;
import com.atorizepoc.miniautorizador.mapper.CardMapper;
import com.atorizepoc.miniautorizador.model.CardEntity;
import com.atorizepoc.miniautorizador.repository.CardRepository;
import com.atorizepoc.miniautorizador.template.CardTemplate;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class CardServiceTest {

    @InjectMocks
    private CardService service;
    @Mock
    private CardRepository repository;
    @Mock
    private CardMapper mapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should Pass When Cards Is Valid")
    void testShouldPassWhenAgendasIsValid() {
        CardDTO cardDTO = CardTemplate.validCardDTO();
        CardEntity agendaEntity = mapper.to(cardDTO);
        when(repository.findAll()).thenReturn(Arrays.asList(agendaEntity));
        when(mapper.from(anyList())).thenReturn(Arrays.asList(cardDTO));
        List<CardDTO> agendas = service.getAll();
        assertTrue(agendas.size() > 0, "Assertion fail, list is empty");
    }

    @Test
    @DisplayName("Should Pass When GetAll Throw New CARDS_NOT_FOUND")
    @SneakyThrows
    void testShouldPassWhenGetAllThrowCardsNotFound() {
        when(repository.findAll()).thenReturn(Collections.EMPTY_LIST);
        MiniAutorizationException error = assertThrows(MiniAutorizationException.class, () ->
                service.getAll(), "Assertion fail, Exception not throws");
        assertEquals(MiniAutorizationErrors.CARDS_NOT_FOUND.getMessage(), error.getMessage(),
                "Assertion fail, message error invalid");
    }

    @Test
    @DisplayName("Should Pass When Throw CARD_NOT_FOUND")
    @SneakyThrows
    void testShouldPassWhenFindByCardNumberNotFound() {
        when(repository.findByNumber(anyString())).thenReturn(Optional.empty());
        MiniAutorizationException error = assertThrows(MiniAutorizationException.class, () ->
                service.findByCardNumber("6549873025634501"), "Assertion fail, Exception not throws");
        assertEquals(MiniAutorizationErrors.CARD_NOT_FOUND.getMessage(), error.getMessage(),
                "Assertion fail, message error invalid");
    }

    @Test
    @DisplayName("Should Pass When FindByCardNumber CardNumber Is Valid")
    void testShouldPassWhenFindByCardNumberIsValid() {
        CardEntity card = CardTemplate.validCardEntity();
        when(repository.findByNumber(anyString())).thenReturn(Optional.of(card));
        when(mapper.from(any(CardEntity.class))).thenReturn(CardTemplate.validCardDTO());
        CardDTO cardDTO = service.findByCardNumber("6549873025634501");
        assertEquals(card.getNumber(), cardDTO.getNumeroCartao(),
                "Assertion fail, value invalid");
    }

}