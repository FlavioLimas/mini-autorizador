package com.atorizepoc.miniautorizador.controller;

import com.atorizepoc.miniautorizador.external.dto.CardDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/card")
@AllArgsConstructor
@CrossOrigin("*")
public class CardController {

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

}
