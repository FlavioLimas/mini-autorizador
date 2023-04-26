package com.atorizepoc.miniautorizador.mapper;

import com.atorizepoc.miniautorizador.external.dto.CardDTO;
import com.atorizepoc.miniautorizador.external.dto.CardSaveDTO;
import com.atorizepoc.miniautorizador.model.CardEntity;
import com.atorizepoc.miniautorizador.template.CardTemplate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CardMapperTest {

    @InjectMocks
    private CardMapper mapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should Pass When from Return List<CardDTO> Is Valid")
    void testShouldPassWhenCardsDTOIdValid() {
        CardEntity cardEntityMock = CardTemplate.validCardEntity();
        List<CardDTO> cards = mapper.from(List.of(cardEntityMock));
        CardDTO cardDTO = cards.get(0);
        assertEquals(cardEntityMock.getNumber(), cardDTO.getNumeroCartao(),
                "Assertion fail, value invalid");
        assertEquals(cardEntityMock.getPassword(), cardDTO.getSenha(),
                "Assertion fail, value invalid");
        assertEquals(cardEntityMock.getValue(), cardDTO.getValor(),
                "Assertion fail, value invalid");
    }

    @Test
    @DisplayName("Should Pass When to Return CardEntity Is Valid")
    void testShouldPassWhenToReturnCardEntityIsValid() {
        CardDTO cardDTO = CardTemplate.validCardDTO();
        CardEntity cardEntity = mapper.to(cardDTO);
        assertEquals(cardDTO.getId(), cardEntity.getId(),
                "Assertion fail, value invalid");
        assertEquals(cardDTO.getNumeroCartao(), cardEntity.getNumber(),
                "Assertion fail, value invalid");
        assertEquals(cardDTO.getSenha(), cardEntity.getPassword(),
                "Assertion fail, value invalid");
        assertEquals(cardDTO.getValor(), cardEntity.getValue(),
                "Assertion fail, value invalid");
    }

    @Test
    @DisplayName("Should Pass When from Return CardDTO Is Valid")
    void testShouldPassWhenFromReturnCardDTOIsValid() {
        CardEntity card = CardTemplate.validCardEntity();
        CardDTO cardDTO = mapper.from(card);
        assertEquals(card.getId(), cardDTO.getId(),
                "Assertion fail, value invalid");
        assertEquals(card.getNumber(), cardDTO.getNumeroCartao(),
                "Assertion fail, value invalid");
        assertEquals(card.getPassword(), cardDTO.getSenha(),
                "Assertion fail, value invalid");
        assertEquals(card.getValue(), cardDTO.getValor(),
                "Assertion fail, value invalid");
    }

    @Test
    @DisplayName("Should Pass When toSave Return CardEntity Is Valid")
    void testShouldPassWhenToSaveReturnCardEntityIsValid() {
        CardSaveDTO cardSaveDTO = CardTemplate.validCardSaveDTO();
        CardEntity card = mapper.toSave(cardSaveDTO);
        assertEquals(cardSaveDTO.getNumeroCartao(), card.getNumber(),
                "Assertion fail, value invalid");
        assertEquals(cardSaveDTO.getSenha(), card.getPassword(),
                "Assertion fail, value invalid");
    }

    @Test
    @DisplayName("Should Pass When fromSave Return CardSaveDTO Is Valid")
    void testShouldPassWhenfromSaveReturnCardSaveDTOIsValid() {
        CardEntity card = CardTemplate.validCardEntity();
        CardSaveDTO cardSaveDTO = mapper.fromSave(card);
        assertEquals(card.getId(), card.getId(),
                "Assertion fail, value invalid");
        assertEquals(card.getNumber(), card.getNumber(),
                "Assertion fail, value invalid");
        assertEquals(card.getPassword(), card.getPassword(),
                "Assertion fail, value invalid");
        assertEquals(card.getValue(), card.getValue(),
                "Assertion fail, value invalid");
    }

    @Test
    @DisplayName("Should Pass When toUpdate Return CardEntity Is Valid")
    void testShouldPassWhenToUpdateReturnCardEntityIsValid() {
        CardDTO newCard = CardTemplate.validCardDTOUpdate();
        CardEntity oldCard = CardTemplate.validCardEntity();
        CardEntity card = mapper.toUpdate(oldCard, newCard);
        assertEquals(oldCard.getId(), card.getId(),
                "Assertion fail, value invalid");
        assertEquals(oldCard.getId(), card.getId(),
                "Assertion fail, value invalid");
        assertEquals(newCard.getNumeroCartao(), card.getNumber(),
                "Assertion fail, value invalid");
        assertEquals(newCard.getSenha(), card.getPassword(),
                "Assertion fail, value invalid");
        assertEquals(newCard.getValor(), card.getValue(),
                "Assertion fail, value invalid");
    }

}