package br.com.alura.challenger.backendjava.actions;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.opensymphony.xwork2.ActionSupport;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.alura.challenger.backendjava.model.ImportarTransacao;
import br.com.alura.challenger.backendjava.model.MultiPartFile;
import br.com.alura.challenger.backendjava.service.ArquivoImportacaoVazioException;
import br.com.alura.challenger.backendjava.service.DataImportacaoJaRealizadaException;
import br.com.alura.challenger.backendjava.service.ImportarTransacaoService;
import lombok.Getter;
import lombok.Setter;

public class ImportarTransacaoAction extends ActionSupport{
    @Autowired
    private ImportarTransacaoService service;

    @Getter
    private List<ImportarTransacao> importacoes = new ArrayList<>();

    @Getter @Setter
    private String erroImportacao;
    
    @Getter
    private MultiPartFile multiPartFile = new MultiPartFile();
    
    @Action(value = "/", results = {
        @Result(name="success", location = "importar-transacao.jsp")
    })
    public String get(){
       carregarImportacoesNaLista();

      return SUCCESS;
    }

    @Action(value = "/upload", results = {
        @Result(
            name="redirect", 
            type = "redirect",
            location = "/",
            params = {
                "erroImportacao", "${erroImportacao}",
                "suppressEmptyParameters", "true"
        }) 
    })
    public String upload(){
        try{
            service.processarArquivo(multiPartFile);
        }catch(ArquivoImportacaoVazioException | DataImportacaoJaRealizadaException e){
           erroImportacao = e.getMessage();
        }
       
        return "redirect";
    }

    private void carregarImportacoesNaLista() {
        importacoes.addAll(service.todasOrdenadasPorDataTransacaoDesc());
    }

    public void setFile(File file){
        this.multiPartFile.setFile(file);
    }

    public void setFileFileName(String fileName){
        this.multiPartFile.setName(fileName);
    }

    public void setFileContentType(String contentType){
        this.multiPartFile.setContentType(contentType);
    }

    public boolean isSemImportacoes(){
        return !importacoes.isEmpty();
    }

    public boolean isSemErroDeImportacao(){
        return null != erroImportacao;
    }

}
