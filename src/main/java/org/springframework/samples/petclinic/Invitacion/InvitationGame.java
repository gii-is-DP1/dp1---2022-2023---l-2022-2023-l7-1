package org.springframework.samples.petclinic.Invitacion;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import org.springframework.samples.petclinic.model.BaseEntity;
import org.springframework.samples.petclinic.user.User;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class InvitationGame extends BaseEntity{
    @ManyToOne
	private User jugadores;
	
	@ManyToOne
	private User anfitrion;
    
}
