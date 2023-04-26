package com.atorizepoc.miniautorizador.mapper;

import com.atorizepoc.miniautorizador.exception.MiniAutorizationErrors;
import com.atorizepoc.miniautorizador.exception.MiniAutorizationException;
import com.atorizepoc.miniautorizador.external.dto.CardDTO;
import com.atorizepoc.miniautorizador.external.dto.TransactionalDTO;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class TransactionMapper {

    @SneakyThrows
    public CardDTO to(TransactionalDTO transactionalDTO, CardDTO existscardDTO) {
        return CardDTO.builder()
                .id(existscardDTO.getId())
                .numeroCartao(isValid(transactionalDTO.getNumeroCartao()))
                .senha(isValid(transactionalDTO.getSenhaCartao()))
                .valor(subtractValue(transactionalDTO.getValor(), existscardDTO.getValor()))
                .build();
    }

    @SneakyThrows
    private static BigDecimal subtractValue(BigDecimal valueTransaction, BigDecimal existsValor) {
        BigDecimal value = existsValor.subtract(valueTransaction);
        if (value.compareTo(valueTransaction) <= 0)
            throw new MiniAutorizationException(MiniAutorizationErrors.INSUFFICIENT_FUNDS);
        return value;
    }

    @SneakyThrows
    private static String isValid(String value) {
        if (value.trim().isEmpty()) {
            throw new MiniAutorizationException(MiniAutorizationErrors.VALUE_INVALID);
        }
        return value;
    }

}
