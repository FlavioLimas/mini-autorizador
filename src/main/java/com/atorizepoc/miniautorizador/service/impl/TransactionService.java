package com.atorizepoc.miniautorizador.service.impl;

import com.atorizepoc.miniautorizador.exception.MiniAutorizationErrors;
import com.atorizepoc.miniautorizador.external.dto.CardDTO;
import com.atorizepoc.miniautorizador.external.dto.TransactionalDTO;
import com.atorizepoc.miniautorizador.mapper.TransactionMapper;
import com.atorizepoc.miniautorizador.service.ICardService;
import com.atorizepoc.miniautorizador.service.ITransactionService;
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
        CardDTO existsCardDTO = cardService.findByNumber(transactionalDTO.getNumeroCartao());
        ResponseEntity<Object> response = checkTransaction(transactionalDTO, existsCardDTO);
        cardService.update(mapper.to(transactionalDTO, existsCardDTO));
        return response;
    }

    @SneakyThrows
    private ResponseEntity<Object> checkTransaction(TransactionalDTO transactionalDTO, CardDTO existsCardDTO) {
        if (0 == existsCardDTO.getValor().intValue()) {
            log.info("Saldo existente " + existsCardDTO.getValor() + " Valor da transação " +
                    transactionalDTO.getValor());
            return ResponseEntity.unprocessableEntity()
                    .body(MiniAutorizationErrors.INSUFFICIENT_FUNDS.getMessage());
        } else if (!existsCardDTO.getSenha().equals(transactionalDTO.getSenhaCartao())) {
            log.info("Senha existente " + existsCardDTO.getSenha() + "Senha da transação " +
                    transactionalDTO.getSenhaCartao());
            return ResponseEntity.unprocessableEntity()
                    .body(MiniAutorizationErrors.INVALID_PASSWORD.getMessage());
        }
        return ResponseEntity.created(URI.create("/transacoes")).body(HttpStatus.OK);
    }
}
