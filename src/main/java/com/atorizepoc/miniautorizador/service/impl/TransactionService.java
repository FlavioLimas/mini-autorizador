package com.atorizepoc.miniautorizador.service.impl;

import com.atorizepoc.miniautorizador.external.dto.TransactionalDTO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class TransactionService implements ITransactionService {

    @Override
    public ResponseEntity<Object> executeTransaction(TransactionalDTO transactionalDTO) {
        return null;
    }

}
