package com.atorizepoc.miniautorizador.mapper;

import com.atorizepoc.miniautorizador.exception.MiniAutorizationErrors;
import com.atorizepoc.miniautorizador.exception.MiniAutorizationException;
import com.atorizepoc.miniautorizador.external.dto.CardDTO;
import com.atorizepoc.miniautorizador.external.dto.TransactionalDTO;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class TransactionMapper {

    @SneakyThrows
    public CardDTO to(TransactionalDTO transactionalDTO, CardDTO existscardDTO) {
        return CardDTO.builder()
                .id(existscardDTO.getId())
                .numeroCartao(isValid(transactionalDTO.getNumeroCartao()))
                .senha(isValid(transactionalDTO.getSenhaCartao()))
                .valor(Optional.of(transactionalDTO.getValor()).orElseThrow(() ->
                        new MiniAutorizationException(MiniAutorizationErrors.VALUE_INVALID)))
                .build();
    }

    @SneakyThrows
    private static String isValid(String value) {
        if (value.trim().isEmpty()) {
            throw new MiniAutorizationException(MiniAutorizationErrors.VALUE_INVALID);
        }
        return value;
    }

}
