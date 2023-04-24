package com.atorizepoc.miniautorizador.exception;

import lombok.Getter;

public enum MiniAutorizationErrors {

    CARDS_NOT_FOUND("Cards Not Found"),
    CARD_NOT_FOUND("Card Not Found"),
    VALUE_INVALID("Value Invalid"),
    CARD_NOT_SAVED("Card Not Saved"),
    CARD_NOT_UPDATED("Card Not Updated");

    @Getter
    private String message;

    MiniAutorizationErrors(String message) {
        this.message = message;
    }
}
