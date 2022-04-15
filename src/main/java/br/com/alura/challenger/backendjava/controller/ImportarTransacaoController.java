package br.com.alura.challenger.backendjava.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.alura.challenger.backendjava.service.ArquivoImportacaoVazioException;
import br.com.alura.challenger.backendjava.service.DataImportacaoJaRealizadaException;
import br.com.alura.challenger.backendjava.service.ImportarTransacaoService;

@Controller
@RequestMapping("/importarTransacao")
public class ImportarTransacaoController {

    @Autowired
    private ImportarTransacaoService service;

    @GetMapping
    public ModelAndView get(){
        return new ModelAndView("importar-transacao.html");
    }

    @PostMapping
    public String upload(@RequestParam("file") MultipartFile file, ModelMap modelMap, RedirectAttributes redirectAttributes){
        try{
            service.processarArquivo(file);
        }catch(ArquivoImportacaoVazioException | DataImportacaoJaRealizadaException e){
            redirectAttributes.addFlashAttribute("erroImportacao", e.getMessage());
        }
        
        return "redirect:/importarTransacao";
    }
}
