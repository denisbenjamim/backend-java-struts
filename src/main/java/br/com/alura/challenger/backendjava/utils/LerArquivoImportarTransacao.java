package br.com.alura.challenger.backendjava.utils;

import java.util.ArrayList;
import java.util.List;

import br.com.alura.challenger.backendjava.model.CampoInvalidoException;
import br.com.alura.challenger.backendjava.model.ImportarTransacao;

public class LerArquivoImportarTransacao extends ManipularArquivo<ImportarTransacao>{

    public LerArquivoImportarTransacao(byte[] conteudo, String separador) {
        super(conteudo, separador);
    }

    @Override
    public List<ImportarTransacao> get() {
        List<ImportarTransacao> importacoes = new ArrayList<>();
        
        String[] colunas;
        ImportarTransacao transacao;
        for(String linha : linhas){
            colunas = linha.split(separador);
            
            try {
                transacao = new ImportarTransacao(colunas);
            } catch (CampoInvalidoException e) {
                continue;
            }

            importacoes.add(transacao);
        }   
        return importacoes;
    }
}
