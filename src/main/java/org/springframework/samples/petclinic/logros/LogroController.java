package org.springframework.samples.petclinic.logros;

import java.util.Map;

import javax.validation.Valid;

import java.security.Principal;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.user.DuplicatedUsernameException;
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
    public ModelAndView showLogros(Principal principal){
        ModelAndView result=new ModelAndView(LOGROS_LISTING_VIEW);
        result.addObject("logros", service.getLogros());
        if(principal != null){
            result.addObject("username", principal.getName());
        }
        return result;
    }
    

    @Transactional
    @GetMapping("/logros/{id}/delete")
    public String deleteLogro(@PathVariable Integer id){
        service.deleteLogroById(id);        
        return "redirect:/logros";
    }

    @Transactional(readOnly = true)
    @GetMapping("/logros/{id}/edit")
    public ModelAndView editLogro(@PathVariable Integer id,Principal principal){
        Logro logro=service.getById(id);        
        ModelAndView result=new ModelAndView(LOGROS_FORM);
        result.addObject("logro", logro);
        if(principal != null){
            result.addObject("username", principal.getName());
        }
        return result;
    }

    @Transactional
    @PostMapping("/logros/{id}/edit")
    public ModelAndView saveLogro(@PathVariable Integer id,@Valid Logro logro, BindingResult br,Principal principal){
        if (br.hasErrors()){
            ModelAndView result = new ModelAndView(LOGROS_FORM);
            if(principal != null){
                result.addObject("username", principal.getName());
            }
            return result;
        }else {
            Logro logroEdited=service.getById(id);
            BeanUtils.copyProperties(logro,logroEdited,"id");
            service.save(logroEdited);
            ModelAndView result=new ModelAndView("redirect:/logros");
            result.addObject("message", "El logro se ha editado correctamente ;)");
            return result;
        }        
    }

    @Transactional(readOnly = true)
    @GetMapping("/logros/new")
    public ModelAndView createLogro(Map<String, Object> model, Principal principal){
        Logro logro=new Logro();
        model.put("logro", logro);

        ModelAndView result = new ModelAndView(LOGROS_NEW_FORM);
        if(principal != null){
			result.addObject("username", principal.getName());
		}
        return result;
    }

    @Transactional
    @PostMapping("/logros/new")
    public ModelAndView saveNewAchievement(@Valid Logro logro, BindingResult br,Principal principal){
        if (br.hasErrors()){
            ModelAndView result = new ModelAndView(LOGROS_NEW_FORM);
            if(principal != null){
                result.addObject("username", principal.getName());
            }
            return result;
        }else {
            service.save(logro);
            ModelAndView result=new ModelAndView("redirect:/logros");
            result.addObject("message", "El logro se ha creado correctamente ;)");
            return result;
        }   
    }
   
}
