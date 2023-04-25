package com.atorizepoc.miniautorizador.controller;

import com.atorizepoc.miniautorizador.external.dto.CardDTO;
import com.atorizepoc.miniautorizador.external.dto.CardSaveDTO;
import com.atorizepoc.miniautorizador.service.impl.ICardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.websocket.server.PathParam;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/cartoes")
@AllArgsConstructor
@CrossOrigin("*")
public class CardController {

    private ICardService service;

    @Operation(summary = "Listagem de Cartões",
            description = "Listagem de Cartões",
            tags = {"Listagem de registros"})
    @ApiResponse(responseCode = "200", description = "Listagem de registros")
    @ApiResponses(value = {
            @ApiResponse(content = {
                    @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = CardDTO.class))
                    )})})
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "/all")
    public ResponseEntity<List<CardDTO>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @Operation(summary = "Pesquisa Saldo do Cartão",
            description = "Pesquisa Saldo do Cartão",
            tags = {"Pesquisa por nome"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pesquisa Saldo Válido do Cartão",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CardDTO.class))),
            @ApiResponse(responseCode = "404", description = "Pesquisa Saldo Inválido do Cartão",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CardDTO.class)))
    })
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "/{cardNumber}")
    public ResponseEntity<Object> findByCardBalance(@PathParam("cardNumber") String cardNumber) {
        return service.findByCardBalance(cardNumber);
    }

    @Operation(summary = "Inclusão de Cartão",
            description = "Inclusão de Cartão",
            tags = {"Criação"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "422", description = "Retorna o Cartão se já houver " +
                    "cadastrado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CardDTO.class))),
            @ApiResponse(responseCode = "201", description = "Retorna a Pauta Salva",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CardDTO.class)))
    })
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CardSaveDTO> save(@RequestBody CardSaveDTO cardSaveDTO) {
        return service.save(cardSaveDTO);
    }

    @Operation(summary = "Atualizão de Cartão",
            description = "Atualizão de Cartão",
            tags = {"Atualização"})
    @ApiResponse(responseCode = "200", description = "Atualizão de Cartão será efetivada somente se " +
            "Numero do Cartão informado for válido")
    @ApiResponses(value = {
            @ApiResponse(content = {
                    @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CardDTO.class))
            })})
    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "/update")
    public ResponseEntity<CardDTO> update(@RequestBody CardDTO cardDTO) {
        return ResponseEntity.ok(service.update(cardDTO));
    }

    @Operation(summary = "Delete Cartão pelo Numero",
            description = "Delete Cartão pelo Numero",
            tags = {"Delete"})
    @ApiResponse(responseCode = "200", description = "Deleção de Cartão pelo Numero")
    @ApiResponses(value = {
            @ApiResponse(content = {
                    @Content(mediaType = "application/json",
                            schema = @Schema(implementation = java.lang.Object.class))
            })})
    @DeleteMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "/{cardNumber}")
    public ResponseEntity<java.lang.Object> deleteByCardNumber(@PathVariable("cardNumber") String cardNumber) {
        service.deleteByCardNumber(cardNumber);
        return ResponseEntity.ok(HttpStatus.OK);
    }

}
