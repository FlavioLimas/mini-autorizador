package com.atorizepoc.miniautorizador.external.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represent a CardSaveDTO
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CardSaveDTO {

    private String numeroCartao;
    private String senha;

}
