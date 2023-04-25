package com.atorizepoc.miniautorizador.service.impl;

import com.atorizepoc.miniautorizador.exception.MiniAutorizationErrors;
import com.atorizepoc.miniautorizador.external.dto.CardDTO;
import com.atorizepoc.miniautorizador.external.dto.TransactionalDTO;
import com.atorizepoc.miniautorizador.mapper.TransactionMapper;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.URI;

@Service
@AllArgsConstructor
@Slf4j
public class TransactionService implements ITransactionService {

    private ICardService cardService;

    private TransactionMapper mapper;

    @Override
    @SneakyThrows
    public ResponseEntity<Object> executeTransaction(TransactionalDTO transactionalDTO) {
        CardDTO existscardDTO = cardService.findByNumber(transactionalDTO.getNumeroCartao());
        ResponseEntity<Object> response = checkTransaction(transactionalDTO, existscardDTO);
        cardService.update(mapper.to(transactionalDTO, existscardDTO));
        return response;
    }

    @SneakyThrows
    private ResponseEntity<Object> checkTransaction(TransactionalDTO transactionalDTO, CardDTO existscardDTO) {

        if (0 == existscardDTO.getValor().intValue()) {
            return ResponseEntity.unprocessableEntity()
                    .body(MiniAutorizationErrors.INSUFFICIENT_FUNDS.getMessage());
        } else if (!transactionalDTO.getSenhaCartao().equals(existscardDTO.getSenha())) {
            return ResponseEntity.unprocessableEntity()
                    .body(MiniAutorizationErrors.INVALID_PASSWORD.getMessage());
        }
        return ResponseEntity.created(URI.create("/transacoes")).body(HttpStatus.OK);
    }

}
