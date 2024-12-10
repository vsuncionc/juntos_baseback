package com.base.base.excepcion;

public class ResultadoException extends Exception {
    public String mensajeException;

    public ResultadoException(String mensaje) {
        super(mensaje);
        this.mensajeException = mensaje;
    }
}
