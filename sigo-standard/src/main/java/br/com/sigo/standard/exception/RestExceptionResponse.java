package br.com.sigo.standard.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.Collections;
import java.util.List;

@Data
@AllArgsConstructor
public class RestExceptionResponse {

    private HttpStatus status;

    private String message;

    private List<String> errors;

    public RestExceptionResponse(HttpStatus status, String message) {
        super();
        this.status = status;
        this.message = message;
    }

    public RestExceptionResponse(HttpStatus status, String message, String error) {
        super();
        this.status = status;
        this.message = message;
        errors = Collections.singletonList(error);
    }
}