package br.com.alura.challenger.backendjava.model;

public class CSVInvalidoException extends RuntimeException {
    public CSVInvalidoException(String message){
        super(message);
    }
}
