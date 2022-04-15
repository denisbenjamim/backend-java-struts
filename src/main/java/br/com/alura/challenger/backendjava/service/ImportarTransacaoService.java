package br.com.alura.challenger.backendjava.service;

import java.io.IOException;
import java.text.MessageFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.alura.challenger.backendjava.model.ImportarTransacao;
import br.com.alura.challenger.backendjava.repository.ImportarTransacaoRepository;
import br.com.alura.challenger.backendjava.utils.LerArquivoImportarTransacao;
import br.com.alura.challenger.backendjava.utils.ManipularArquivo;

@Service
public class ImportarTransacaoService {
    
    @Autowired
    private ImportarTransacaoRepository repository;

    private void importar(ImportarTransacao  importarTransacao){
        repository.save(importarTransacao);
    }

    public void processarArquivo(MultipartFile file) throws ArquivoImportacaoVazioException, DataImportacaoJaRealizadaException{
        exibirNomeETamanhoArquivoCarregado(file);
        
        try {
            ManipularArquivo<ImportarTransacao> arquivo = new LerArquivoImportarTransacao(file.getBytes(), ",");
            List<ImportarTransacao> transacoes = arquivo.get();

            if(transacoes.isEmpty()){
                throw new ArquivoImportacaoVazioException("O arquivo não pode estar vazio");
            }
            
            LocalDate dataTransacaoBase = transacoes.get(0).getDataTransacao();
            
            if(repository.existsByDataTransacao(dataTransacaoBase)){
                Date data = Date.from(dataTransacaoBase.atStartOfDay(ZoneId.systemDefault()).toInstant());
                throw new DataImportacaoJaRealizadaException(MessageFormat.format("Já foi realizada importação para data {0,date,short}", data));
            }

            transacoes.stream()
                .filter(tr -> tr.getDataTransacao().equals(dataTransacaoBase))
                .forEach(tr -> {
                    importar(tr);
                });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void exibirNomeETamanhoArquivoCarregado(MultipartFile file) {
        String nomeArquivo = file.getOriginalFilename();
        double tamanhoArquivo = convertTamanhoParaMB(file.getSize()) ;
        System.out.println(MessageFormat.format("Nome: {0} - Tamanho: {1} Mb", nomeArquivo, tamanhoArquivo ));
    }

    private double convertTamanhoParaMB(long tamanho) {
        return Long.valueOf(tamanho).doubleValue() / 1024000;
    }
}
