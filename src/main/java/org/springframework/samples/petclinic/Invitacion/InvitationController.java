package org.springframework.samples.petclinic.Invitacion;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.user.User;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class InvitationController {
    
    @Autowired
	private InvitationService invitationService;

    private static final String VIEW_INVITATIONS_LIST = "/users/invitations";
    private static final String VIEW_AVAILABLE_INVITATIONS_LIST = "/users/invites";

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
		return "redirect:/friends/"+username;
	}

    @Transactional
	@GetMapping(value = "/invitationCancelled/{username}/{id}")
    public String cancelInvitation(@PathVariable("username") String username, @PathVariable("id") Integer id){
        invitationService.deleteInvitationById(id);        
        return "redirect:/invitations/"+username;
    }
}
