package com.atorizepoc.miniautorizador.external.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * Represent a TransactionalDTO
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TransactionalDTO {

    private String numeroCartao;
    private String senhaCartao;
    private BigDecimal valor;

}
