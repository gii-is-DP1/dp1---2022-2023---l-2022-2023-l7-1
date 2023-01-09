package org.springframework.samples.petclinic.Invitacion;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.tablero.Tablero;
import org.springframework.samples.petclinic.tablero.TableroService;
import org.springframework.samples.petclinic.user.User;
import org.springframework.samples.petclinic.user.UserRepository;
import org.springframework.samples.petclinic.user.UserService;
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

	@Autowired
	private UserService userService;

	@Autowired
	private TableroService tableroService;

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
	
	public List<InvitationGame> getInvitationsGameOf(String username) {
		return invitationGameRepository.getInvitationsGameOf(username);
	}

	@Transactional
	public void sendInvitationToGame(String username, String jugador) {
		User anfitrion = userRepository.findById(username).get();
		User posibleJugador = userRepository.findById(jugador).get();
		invitationGameRepository.save(new InvitationGame(anfitrion, posibleJugador));
	}

	public InvitationGame geInvitationGameByID(Integer id){
		return invitationGameRepository.findById(id).get();	
	}

	public void checkAnfitrionEnJugadoresAceptados(User anfitrion){
		if(anfitrion.getJugadoresAceptados().isEmpty()){
			anfitrion.getJugadoresAceptados().add(anfitrion);
		}
		else if(!anfitrion.getJugadoresAceptados().contains(anfitrion)){
			anfitrion.getJugadoresAceptados().set(0,anfitrion);
		}
	}

	@Transactional
	public void acceptInvitationGame(String username, Integer invitation_id) {
		InvitationGame invitationG = invitationGameRepository.findById(invitation_id).get();
		User anfitrion = invitationG.getAnfitrion();
		checkAnfitrionEnJugadoresAceptados(anfitrion);
		if(invitationG != null && invitationG.getPosibleJugador().getUsername().equals(username)) {
			invitationG.aceptGame();
			invitationGameRepository.deleteById(invitation_id);
		}
	}

	@Transactional
	public void deleteInvitationGame(Integer id){
        invitationGameRepository.deleteById(id);
    }

	@Transactional
    public List<User> getAmigosDisponiblesParaJugar(String username) {
		User anfitrion = userRepository.findById(username).get();
        List<User> friends = userService.getFriends(username);
        List<User> result = new ArrayList<User>();
        for (User posibleJugador : friends) {
            if (anfitrion.canInviteToGame(posibleJugador.getUsername())) {
                result.add(posibleJugador);
            }
        }
        return result;
    }
	@Transactional
    public Tablero getTableroActiveUser(User user) {
		return tableroService.getTableroActiveByUser(user);
	}
}
