package br.com.alura.challenger.backendjava.utils;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public abstract class ManipularArquivo<T> {
    protected final String separador;
    protected final List<String> linhas;

    public ManipularArquivo(byte[] conteudo, String separador) {
        this.separador = separador;
        this.linhas = convertLinhasEmList(conteudo);
    }

    private List<String> convertLinhasEmList(byte[] conteudo){
        try (
            ByteArrayInputStream bais = new ByteArrayInputStream(conteudo);
            InputStreamReader isr = new InputStreamReader(bais);
            BufferedReader reader = new BufferedReader(isr)
        ) {
           return reader.lines().collect(Collectors.toList());
        }catch(IOException e){
            e.printStackTrace();
        }
        return Collections.emptyList();
    }
    
    public abstract List<T> get();
}
