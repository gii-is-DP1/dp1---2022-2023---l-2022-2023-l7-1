package org.springframework.samples.kingdommaps.Invitacion;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import org.springframework.samples.kingdommaps.model.BaseEntity;
import org.springframework.samples.kingdommaps.user.User;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class InvitationGame extends BaseEntity{
	@ManyToOne
	private User anfitrion;
	@ManyToOne
	private User posibleJugador;

	public InvitationGame(){

	}
	
	public InvitationGame(User anfitrion, User posibleJugador) {
		this.anfitrion = anfitrion;
		this.posibleJugador = posibleJugador;
		
		anfitrion.getSendedInvitationsToGame().add(this);
		posibleJugador.getReceivedInvitationsToGame().add(this);		
	}

    public void aceptGame() {
		anfitrion.getJugadoresAceptados().add(posibleJugador);
		posibleJugador.getAnfitrionDelJugador().add(anfitrion);
	}

	public boolean esDelUsuarioG(String username) {
		return anfitrion.getUsername().equals(username) || posibleJugador.getUsername().equals(username);
	}

}
