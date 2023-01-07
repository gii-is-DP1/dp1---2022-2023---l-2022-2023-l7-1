package org.springframework.samples.petclinic.Invitacion;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import org.springframework.samples.petclinic.model.BaseEntity;
import org.springframework.samples.petclinic.user.User;

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


    public InvitationGame(User anfitrion, User posibleJugador) {
        this.anfitrion = anfitrion;
		this.posibleJugador = posibleJugador;

    }

	public InvitationGame(User anfitrion) {
        this.anfitrion = anfitrion;

    }
}
