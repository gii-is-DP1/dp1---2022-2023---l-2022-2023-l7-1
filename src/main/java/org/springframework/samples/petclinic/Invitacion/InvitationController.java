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
    @GetMapping("users/{username}/invitations")
	public String getInvitationes(@PathVariable("username") String username, Model model) {
		List<Invitation> invitations = invitationService.getInvitationsOf(username);
		model.addAttribute("invitationsList", invitations);
        model.addAttribute("username", username);
		
		return VIEW_INVITATIONS_LIST;
	}

    @Transactional
    @GetMapping("users/{username}/invite")
    public String getUsersToInvite(@PathVariable("username") String username, Model model) {
        List<User> users = invitationService.getAvailableUsers(username);
        System.out.println(users);
        model.addAttribute("availableList", users);
        model.addAttribute("username", username);
		
		return VIEW_AVAILABLE_INVITATIONS_LIST;
    }

    @Transactional
    @GetMapping("users/{username1}/invitate/{username2}")
	public String invitateUser(@PathVariable("username1") String username1, @PathVariable("username2") String username2 ) {
		invitationService.sendInvitation(username1, username2);		
		
		return "redirect:/users/"+ username1;
	}

    @Transactional
    @GetMapping("users/{username}/accept/{id}")
	public String acceptInvitation(@PathVariable("username") String username, @PathVariable Integer id ) {
		invitationService.acceptInvitation(username, id);
		
		return "redirect:/users/"+username+"/friends";
	}

    @Transactional
	@GetMapping(value = "/users/{username}/cancelInvite/{id}")
    public String cancelInvitation(@PathVariable("username") String username, @PathVariable("id") Integer id){
        invitationService.deleteInvitationById(id);        
        return "redirect:/users/"+username+"/invitations";
    }
}
