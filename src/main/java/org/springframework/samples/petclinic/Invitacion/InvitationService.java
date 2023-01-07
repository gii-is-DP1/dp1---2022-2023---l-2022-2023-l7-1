package org.springframework.samples.petclinic.Invitacion;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.user.User;
import org.springframework.samples.petclinic.user.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class InvitationService {
    
    @Autowired
	private InvitationRepository invitationRepository;
	
	@Autowired
	private InvitationGameRepository invitationGameRepository;

	@Autowired
	private UserRepository userRepository;

	public Optional<Invitation> geInvitationByID(Integer id){
		return invitationRepository.findById(id);	
	}

    public List<Invitation> getInvitationsOf(String username) {
		return invitationRepository.getInvitationsOf(username);
	}

    @Transactional
	public void sendInvitation(String sender_username, String receiver_username) {
		User receiver = userRepository.findById(receiver_username).get();
		User sender = userRepository.findById(sender_username).get();
		
		if(receiver != null && sender.canInvite(receiver.getUsername())) {
			invitationRepository.save(new Invitation(sender, receiver));
		}
	}

    @Transactional
	public void acceptInvitation(String username, Integer invitation_id) {
		Invitation invitation = invitationRepository.findById(invitation_id).get();
		
		if(invitation != null && invitation.getReceiver().getUsername().equals(username)) {
			invitation.accept();
			invitationRepository.deleteById(invitation_id);
		}
	}

	@Transactional
	public void deleteInvitationById(Integer id){
        invitationRepository.deleteById(id);
    }

    @Transactional
    public List<User> getAvailableUsers(String username) {
        List<User> users = userRepository.findAll();
        List<User> result = new ArrayList<User>();
        User sender = userRepository.findById(username).get();
        for (User receiver : users) {
            if (sender.canInvite(receiver.getUsername())) {
                result.add(receiver);
            }
        }
        return result;
    }

	//---------------------------------------------------------------------------------------------------------------
	//__MULTI__
	/* 

	public InvitationGame geInvitationGameByID(Integer id){
		return invitationGameRepository.findById(id).get();	
	}

	public List<InvitationGame> getInvitationsGameOf(String username) {
		return invitationGameRepository.getInvitationsGameOf(username);
	}
	
	@Transactional
	public void sendInvitationGame(String username, List<String> jugadores) {
		User anfitrion = userRepository.findById(username).get();
		for (String s: jugadores){
			User posibleJugador = userRepository.findById(s).get();
			invitationGameRepository.save(new InvitationGame(anfitrion, posibleJugador));
		}
	}

	@Transactional
	public void acceptInvitationGame(String username, Integer invitation_id) {
		InvitationGame invitationG = invitationGameRepository.findById(invitation_id).get();
		
		if(invitationG != null && invitationG.getPosibleJugador().getUsername().equals(username)) {
			invitationG.aceptGame();
			invitationGameRepository.deleteById(invitation_id);
		}
	}

	@Transactional
	public void deleteInvitationGame(Integer id){
        invitationGameRepository.deleteById(id);
    }*/
}
