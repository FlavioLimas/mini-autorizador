package com.atorizepoc.miniautorizador.external.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * Represent a CardDTO
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CardDTO {

    private Long id;
    private String numeroCartao;
    private String senha;
    private BigDecimal valor;

}
