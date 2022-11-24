package org.springframework.samples.petclinic.Invitacion;

import java.util.ArrayList;
import java.util.List;

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
	private UserRepository userRepository;

    public List<Invitation> getInvitationsOf(String username) {
		return invitationRepository.getInvitationsOf(username);
	}

    @Transactional
	public void sendInvitation(String sender_username, String receiver_username) {
		User receiver = userRepository.findById(receiver_username).get();
		User sender = userRepository.findById(sender_username).get();
		
		if(receiver != null && sender.canInvite(receiver.getEmail())) {
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
}
