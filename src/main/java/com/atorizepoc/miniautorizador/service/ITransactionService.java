package com.atorizepoc.miniautorizador.service;

import com.atorizepoc.miniautorizador.external.dto.TransactionalDTO;
import org.springframework.http.ResponseEntity;

public interface ITransactionService {

    ResponseEntity<Object> executeTransaction(TransactionalDTO transactionalDTO);

}
