package org.springframework.samples.petclinic.logros;

import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.trace.http.HttpTrace.Principal;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LogroController {
    
    private final String LOGROS_LISTING_VIEW="logros/logrosListing";
    private final String LOGROS_FORM="/logros/editLogro";
    private final String LOGROS_NEW_FORM="/logros/newLogro";


    private LogroService service;

    @Autowired
    public LogroController(LogroService service){
        this.service=service;
    }

    @Transactional(readOnly = true)
    @GetMapping("/logros")
    public ModelAndView showLogros(){
        ModelAndView result=new ModelAndView(LOGROS_LISTING_VIEW);
        result.addObject("logros", service.getLogros());
        return result;
    }

    @Transactional
    @GetMapping("/logros/{id}/delete")
    public ModelAndView deleteLogro(@PathVariable Integer id){
        service.deleteLogroById(id);        
        return showLogros();
    }

    @Transactional(readOnly = true)
    @GetMapping("/logros/{id}/edit")
    public ModelAndView editLogro(@PathVariable Integer id){
        Logro logro=service.getById(id);        
        ModelAndView result=new ModelAndView(LOGROS_FORM);
        result.addObject("logro", logro);
        return result;
    }

    @Transactional
    @PostMapping("/logros/{id}/edit")
    public ModelAndView saveLogro(@PathVariable Integer id,Logro logro){
        Logro logroEdited=service.getById(id);
        BeanUtils.copyProperties(logro,logroEdited,"id");
        service.save(logroEdited);
        return showLogros();
    }

    @Transactional(readOnly = true)
    @GetMapping("/logros/new")
    public ModelAndView createLogro(){
        Logro logro=new Logro();
        ModelAndView result=new ModelAndView(LOGROS_NEW_FORM);
        result.addObject("logro", logro);
        return result;
    }

    @Transactional
    @PostMapping("/logros/new")
    public ModelAndView saveNewAchievement(Logro logro, BindingResult br){
        service.save(logro);
        ModelAndView result=showLogros();
        result.addObject("message", "The achievement was created successfully ;)");
        return result;
    }

   
}
