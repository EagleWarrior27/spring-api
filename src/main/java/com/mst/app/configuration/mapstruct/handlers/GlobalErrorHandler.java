package com.mst.app.configuration.mapstruct.handlers;

import com.mst.app.configuration.mapstruct.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class GlobalErrorHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleStatusException(MethodArgumentNotValidException ex, WebRequest request) {
        return ErrorResponse.builder()
                .exception(ex)
                .mensaje("Ocurrió un error al validar la información de la petición")
                .ruta(request.getDescription(false).substring(4))
                .entidad();
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> exceptionManager(Exception ex, WebRequest request) {
        return ErrorResponse.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .mensaje("Ocurrió un error al procesar la petición")
                .ruta(request.getDescription(false).substring(4))
                .entidad();
    }
}
