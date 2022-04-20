package br.com.alura.challenger.backendjava.utils;

import java.util.ArrayList;
import java.util.List;

import br.com.alura.challenger.backendjava.model.CSVInvalidoException;
import br.com.alura.challenger.backendjava.model.CampoInvalidoException;
import br.com.alura.challenger.backendjava.model.Importacao;
import br.com.alura.challenger.backendjava.model.Transacao;

public class LerArquivoImportarTransacao extends ManipularArquivo<Transacao>{

    public LerArquivoImportarTransacao(byte[] conteudo, String separador) {
        super(conteudo, separador);
    }

    @Override
    public List<Transacao> get() throws CSVInvalidoException{
        final Importacao IMPORTACAO = new Importacao();
        final List<Transacao> TRANSACOES = new ArrayList<>();
        
        String[] colunas;
        Transacao transacao;
        for(String linha : linhas){
            colunas = linha.split(separador);
            
            try {
                transacao = new Transacao(colunas, IMPORTACAO);
            } catch (CampoInvalidoException e) {
                continue;
            }

            TRANSACOES.add(transacao);
        }   
        return TRANSACOES;
    }
}
