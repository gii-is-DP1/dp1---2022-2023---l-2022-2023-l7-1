package org.springframework.samples.petclinic.Invitacion;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.partida.Partida;
import org.springframework.samples.petclinic.tablero.Tablero;
import org.springframework.samples.petclinic.user.User;
import org.springframework.samples.petclinic.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class InvitationController {
    
    @Autowired
	private InvitationService invitationService;

    @Autowired
	private UserService userService;

    private static final String VIEW_INVITATIONS_LIST = "/users/invitations";
    private static final String VIEW_AVAILABLE_INVITATIONS_LIST = "/users/invites";
    private static final String VIEW_USER_FRIENDS = "partidas/lobby";
    private static final String VIEW_INVITATIONS_TO_GAME_LIST = "/users/invitationsToPlay";

    @Transactional
    @GetMapping("/invitations/{username}")
	public String getInvitationes(@PathVariable("username") String username, Model model) {
		List<Invitation> invitations = invitationService.getInvitationsOf(username);
		model.addAttribute("invitationsList", invitations);
        model.addAttribute("username", username);
		
		return VIEW_INVITATIONS_LIST;
	}

    @Transactional
    @GetMapping("/invite/{username}")
    public String getUsersToInvite(@PathVariable("username") String username, Model model) {
        List<User> users = invitationService.getAvailableUsers(username);
        model.addAttribute("availableList", users);
        model.addAttribute("username", username);
		
		return VIEW_AVAILABLE_INVITATIONS_LIST;
    }

    @Transactional
    @GetMapping(value = "/invitate/{usernameLogged}/{usernameReceiver}")
	public String invitateUser(@PathVariable("usernameLogged") String usernameLogged, @PathVariable("usernameReceiver") String usernameReceiver ) {
		invitationService.sendInvitation(usernameLogged, usernameReceiver);	
		return "redirect:/invitations/"+ usernameLogged;
	}

    @Transactional
    @GetMapping(value = "/invitationAccepted/{username}/{id}")
	public String acceptInvitation(@PathVariable("username") String username, @PathVariable Integer id ) {
		invitationService.acceptInvitation(username, id);
		return "redirect:/friends";
	}

    @Transactional
	@GetMapping(value = "/invitationCancelled/{username}/{id}")
    public String cancelInvitation(@PathVariable("username") String username, @PathVariable("id") Integer id){
        invitationService.deleteInvitationById(id);        
        return "redirect:/invitations/"+username;
    }

    //------------------------------------------------------------------------
    // Lobby Multijugador ----------------------------------------------------------
    //------------------------------------------------------------------------
   
	@Transactional
    @GetMapping(value = "/lobby")
	public ModelAndView showFriendsForLobby(Principal principal) {
        String username = principal.getName();
		List<User> friends = invitationService.getAmigosDisponiblesParaJugar(username);
		ModelAndView mav = new ModelAndView(VIEW_USER_FRIENDS);
		mav.addObject("friendsToPlay", friends);
		mav.addObject("user", this.userService.getUserById(username));

		if(principal != null){
			mav.addObject("username", principal.getName());
		}

		return mav;
	}
    

    @Transactional
    @GetMapping("/invitationsGame")
	public String getInvitationsToGame( Model model, Principal principal) {
        String username = principal.getName();
		List<InvitationGame> invitations = invitationService.getInvitationsGameOf(username);
		model.addAttribute("invitationToPlay", invitations);
        model.addAttribute("username", username);
		
		return VIEW_INVITATIONS_TO_GAME_LIST;
	}

    @Transactional
    @GetMapping(value = "/invitateToPlay/{posibleJugador}")
	public String invitateUserToPlay( @PathVariable("posibleJugador") String posibleJugador, Principal principal ) {
        String anfitrion = principal.getName();
		invitationService.sendInvitationToGame(anfitrion, posibleJugador);	
		return "redirect:/lobby";
	}

    @Transactional
    @GetMapping(value = "/invitationToGameAccepted/{id}")
	public String acceptInvitationToGame(@PathVariable Integer id,  Principal principal) {
        String posibleJugador = principal.getName();
		invitationService.acceptInvitationGame(posibleJugador, id);
		return "redirect:/salaDeEsperaJugadores";
	}

    @Transactional
	@GetMapping(value = "/invitationToGameCancelled/{id}")
    public String cancelInvitationToGame(@PathVariable("id") Integer id){
        invitationService.deleteInvitationGame(id);        
        return "redirect:/";
    }

    @Transactional
    @GetMapping(value = "/salaDeEspera")
    public ModelAndView showSalaDeEspera(Principal principal){
        ModelAndView mav = new ModelAndView("partidas/salaDeEspera");
        String username = principal.getName();
        User anfitrion = userService.getUserById(username);
        invitationService.checkAnfitrionEnJugadoresAceptados(anfitrion);
        mav.addObject("jugadoresAceptados", anfitrion.getJugadoresAceptados());
        mav.addObject("username", username);
        mav.addObject("tamañoJugadores", anfitrion.getJugadoresAceptados().size());
        return mav;
    }

    @Transactional
    @GetMapping(value = "/salaDeEsperaJugadores")
    public ModelAndView showSalaDeEsperaJugadores(Principal principal){
        ModelAndView mav = new ModelAndView("partidas/esperaDeJugadores");
        String username = principal.getName();
        User anfitrion = userService.getUserById(username);
        Tablero tablero = invitationService.getTableroActiveUser(anfitrion);
        if(tablero!=null){
            Partida partida = tablero.getPartida();
            mav.setViewName("redirect:/partida/Multijugador/espera/dado/" + partida.getId());
        }
        invitationService.checkAnfitrionEnJugadoresAceptados(anfitrion);
        mav.addObject("jugadoresAceptados", anfitrion.getJugadoresAceptados());
        mav.addObject("username", username);
        return mav;
    }


}
