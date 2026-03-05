package com.estudos.biblioteca.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ErrorResponse {

    private int status;
    private String mensagem;
    private Long timestamp;

    public ErrorResponse(int status, String mensagem) {
        this.status = status;
        this.mensagem = mensagem;
        this.timestamp = System.currentTimeMillis();
    }
}
