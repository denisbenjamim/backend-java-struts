package br.com.alura.challenger.backendjava.model;

public class CampoInvalidoException extends RuntimeException {
    public CampoInvalidoException(String message){
        super(message);
    }
}
