package br.com.alura.challenger.backendjava.controller;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.MessageFormat;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/importarTransacao")
public class ImportarTransacaoController {

    @GetMapping
    public ModelAndView get(){
        return new ModelAndView("importar-transacao.html");
    }

    @PostMapping
    public String upload(@RequestParam("file") MultipartFile file, ModelMap modelMap, RedirectAttributes redirectAttributes){
        String nomeArquivo = file.getOriginalFilename();
        
        double tamanhoArquivo = convertTamanhoParaMB(file.getSize()) ;
        
        System.out.println(MessageFormat.format("Nome: {0} - Tamanho: {1} Mb", nomeArquivo, tamanhoArquivo ));
        
        try (
            ByteArrayInputStream bais = new ByteArrayInputStream(file.getBytes());
            InputStreamReader isr = new InputStreamReader(bais);
            BufferedReader reader = new BufferedReader(isr)
        ) {
           reader.lines().forEach(linha -> System.out.println(linha));            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return "redirect:/importarTransacao";
    }

    private double convertTamanhoParaMB(long tamanho) {
        return Long.valueOf(tamanho).doubleValue() / 1024000;
    }
}
