package br.com.alura.challenger.backendjava.controller;

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
        
        return "redirect:/importarTransacao";
    }

    private double convertTamanhoParaMB(long tamanho) {
        return Long.valueOf(tamanho).doubleValue() / 1024000;
    }
}
