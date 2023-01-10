package org.springframework.samples.petclinic.chat;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.partida.PartidaService;
import org.springframework.samples.petclinic.tablero.Tablero;
import org.springframework.samples.petclinic.user.User;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ChatController {

    public static final String SHOW_CHAT = "partidas/chat";
    public static final String ESCRIBIR_MENSAJE = "partidas/escribirMensaje";
    
    MensajeService mensajeService;

    @Autowired
    PartidaService partidaService;

    @Autowired
    ChatService chatService;


    @Autowired
    public ChatController(MensajeService repo){
        this.mensajeService = repo;
    }

    @GetMapping("/chat/{id}")
    public ModelAndView chatDePartida(@PathVariable("id") Integer id, Principal principal, HttpServletResponse response){
        ModelAndView result = new ModelAndView(SHOW_CHAT);
        Chat c = chatService.getByPartidaId(id);
        User user = partidaService.getUserById(principal);
        List<Tablero> userTableros = partidaService.getTablerosByUser(user);
        Boolean perteneceAChat=false;
        for(Tablero t: userTableros){
            if(t.getPartida().getId().equals(id)){
                perteneceAChat=true;
                break;
            }
        }
        if(perteneceAChat.equals(true)){
            result.addObject("user", user);
            result.addObject("id", id);
            result.addObject("chat", c);
            return result;
        }
        result.setViewName("redirect:/");
        return result;
    }

    @GetMapping("chat/escribirMensaje/{id}")
    public ModelAndView escribirMensaje(@PathVariable("id") Integer id, Principal principal){
        ModelAndView result = new ModelAndView(ESCRIBIR_MENSAJE);
        Chat chat = chatService.getByPartidaId(id);
        User user = partidaService.getUserById(principal);
        Mensaje mensaje = new Mensaje();
        result.addObject("user", user);
        result.addObject("id", id);
        result.addObject("chat", chat);
        result.addObject("mensaje", mensaje);
        return result;
    }

    @PostMapping("chat/escribirMensaje/{id}")
    public ModelAndView escribirMensajePost(@PathVariable("id") Integer id, @Valid Mensaje mensaje, BindingResult br, Principal principal){
        if(br.hasErrors()){
            return new ModelAndView(ESCRIBIR_MENSAJE,br.getModel());
        }
        User user = partidaService.getUserById(principal);
        Chat c = chatService.getByPartidaId(id);
        Integer mensajeid = mensajeService.getUltimoId();
        mensaje.setUser(user);
        mensaje.setId(mensajeid);
        mensajeService.saveMensaje(mensaje);
        List<Mensaje> m = c.getMensajes();
        m.add(mensaje);
        c.setMensajes(m);
        chatService.edit(c);
        ModelAndView result = new ModelAndView("redirect:/chat/" + id);
        return result;
    }

}
