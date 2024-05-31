package com.mst.app.configuration.mapstruct.builders;

import com.mst.app.configuration.mapstruct.ErrorResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.HashMap;
import java.util.Map;

public class ErrorResponseBuilder {
    private int estatus;
    private String mensaje;
    private Map<String, String> errores;
    private String ruta;

    public ErrorResponseBuilder estatus(int estatus) {
        this.estatus = estatus;
        return this;
    }

    public ErrorResponseBuilder status(HttpStatus estatus) {
        this.estatus = estatus.value();

        if (estatus.isError()) {
            this.errores.put("error", estatus.getReasonPhrase());
        }

        return this;
    }

    public ErrorResponseBuilder errores(Map<String, String> error) {
        this.errores = errores;
        return this;
    }

    public ErrorResponseBuilder mensaje(String mensaje) {
        this.mensaje = mensaje;
        return this;
    }
    public ErrorResponseBuilder exception(MethodArgumentNotValidException exception) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        this.estatus = status.value();

        errores = new HashMap<>();

        exception.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errores.put(fieldName, errorMessage);
        });

        return this;
    }

    public ErrorResponseBuilder ruta(String ruta) {
        this.ruta = ruta;
        return this;
    }

    public ErrorResponse build() {
        ErrorResponse respuesta = new ErrorResponse();
        respuesta.setEstatus(estatus);
        respuesta.setMensaje(mensaje);
        respuesta.setErrores(errores);
        respuesta.setRuta(ruta);
        return respuesta;
    }

    public ResponseEntity<ErrorResponse> entidad() {
        return ResponseEntity.status(estatus).headers(HttpHeaders.EMPTY).body(build());
    }
}
