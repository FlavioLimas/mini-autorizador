package com.atorizepoc.miniautorizador.controller;

import com.atorizepoc.miniautorizador.external.dto.CardDTO;
import com.atorizepoc.miniautorizador.external.dto.TransactionalDTO;
import com.atorizepoc.miniautorizador.service.ITransactionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/transacoes")
@AllArgsConstructor
@CrossOrigin("*")
public class TransactionController {

    private ITransactionService service;

    @Operation(summary = "Realiza Transação",
            description = "Realiza Transação",
            tags = {"Criação"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "422", description = "Alguma regra de autorização Saldo Insuficiente" +
                    " ou Senha Inválida ou Cartão Inexistente",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CardDTO.class))),
            @ApiResponse(responseCode = "201", description = "Realiza Transação",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Object.class)))
    })
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> executeTransaction(@RequestBody TransactionalDTO transactionalDTO) {
        return service.executeTransaction(transactionalDTO);
    }

}
