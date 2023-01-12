package org.springframework.samples.petclinic.user;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.samples.petclinic.Invitacion.Invitation;
import org.springframework.samples.petclinic.Invitacion.InvitationGame;
import org.springframework.samples.petclinic.logros.Logro;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Audited
@Entity
@Table(name = "users")
public class User{
    @Id
	@Column(name = "username")
    @NotEmpty(message="No debe estar vacío")
    @NotAudited
    String username;

    @Column(name = "name")
    @NotEmpty(message="No debe estar vacío")
    String name;

    @Column(name = "lastName")
    @NotEmpty(message="No debe estar vacío")
    String lastName;

    @Column(name = "password")
    @NotEmpty(message="No debe estar vacío")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$")
    String password;

    @Column(name = "birthDate")
    @Past
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @NotNull(message = "No debe estar vacío")
    LocalDate birthDate;

    @Column(name = "email")
    @NotEmpty(message="No debe estar vacío")
    @Email(regexp = ".+[@].+[\\.].+")
    String email;

    @Column(name = "phone")
    @NotEmpty(message="No debe estar vacío")
    @Digits(fraction = 0, integer = 10)
    String phone;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    @NotAudited
	private Set<Authorities> authorities;

    //************************************************//
    //ESTADÍSTICAS 
    //************************************************//
    @Value("0")
    @NotAudited
    @Transient
    Integer matchesPlayed;

    @Value("0")
    @NotAudited
    @Transient
    Integer gamesWin;

    @Value("0")
    @Transient
    @NotAudited
    Integer winRatio;

    @Value("0")
    @NotAudited
    @Transient
    Integer totalPoints;

    @Value("0")
    @NotAudited
    @Transient
    Integer maxPoints;

    @Value("0")
    @Transient
    @NotAudited
    Integer timesUsedTerritory1;

    @Transient
    @Value("0")
    @NotAudited
    Integer timesUsedTerritory2;

    @Transient
    @Value("0")
    @NotAudited
    Integer timesUsedTerritory3;

    @Transient
    @Value("0")
    @NotAudited
    Integer timesUsedTerritory4;

    @Transient
    @Value("0")
    @NotAudited
    Integer timesUsedTerritory5;

    @Transient
    @Value("0")
    @NotAudited
    Integer timesUsedTerritory6;

    @Transient
    @Value("0")
    @NotAudited
    Integer maxTime;


    @Column(name = "enabled")
    @NotAudited
    Boolean enabled;
 
    @Transient
    public Integer getWinRatio() {
        return (int)  ((double) gamesWin / (double) matchesPlayed) * 100;
    }

    @ManyToMany(cascade = {CascadeType.PERSIST , CascadeType.REFRESH, CascadeType.MERGE, CascadeType.DETACH})
	@JoinTable(name="friends",
			joinColumns= {@JoinColumn(name="friend_id")},
			inverseJoinColumns = {@JoinColumn(name="aux_friend_id")})
    @NotAudited
	private Set<User> friends = new HashSet<User>();  //Set de mis amigos de un user

    @ManyToMany(mappedBy="friends", cascade = {CascadeType.PERSIST , CascadeType.REFRESH, CascadeType.MERGE, CascadeType.DETACH})
    @NotAudited
	private Set<User> auxFriends = new HashSet<User>(); //quienes son amigos de un user

	@OneToMany(mappedBy = "receiver", cascade = CascadeType.ALL)
    @NotAudited
	private Set<Invitation> receivedInvitations = new HashSet<Invitation>();
    
	@OneToMany(mappedBy = "sender", cascade = CascadeType.ALL)
    @NotAudited
	private Set<Invitation> sendedInvitations = new HashSet<Invitation>();

    public boolean canInvite(String username) {
		if(getUsername().equals(username))
			return false;
		for(User friend : friends) 
			if(friend.getUsername().equals(username))
				return false;
		for(User friend : auxFriends) 
			if(friend.getUsername().equals(username))
				return false;
		for(Invitation sended : sendedInvitations) 
			if(sended.esDelUsuario(username))
				return false;
		for(Invitation receiver : receivedInvitations) 
			if(receiver.esDelUsuario(username))
				return false;
		return true;
	}

    @OneToMany
    @NotAudited
    private List<Logro> logrosUser;

    @ManyToMany(cascade = {CascadeType.PERSIST , CascadeType.REFRESH, CascadeType.MERGE, CascadeType.DETACH})
	@JoinTable(name="jugadoresAceptados",
			joinColumns= {@JoinColumn(name="anfitrion")})
    @NotAudited
    private List<User> jugadoresAceptados = new ArrayList<User>();

    @ManyToMany(mappedBy="jugadoresAceptados", cascade = {CascadeType.PERSIST , CascadeType.REFRESH, CascadeType.MERGE, CascadeType.DETACH})
    @NotAudited
	private List<User> anfitrionDelJugador = new ArrayList<User>();

	@OneToMany(mappedBy = "posibleJugador", cascade = CascadeType.ALL)
    @NotAudited
	private Set<InvitationGame> receivedInvitationsToGame = new HashSet<InvitationGame>();
    
	@OneToMany(mappedBy = "anfitrion", cascade = CascadeType.ALL)
    @NotAudited
	private Set<InvitationGame> sendedInvitationsToGame = new HashSet<InvitationGame>();

    public boolean canInviteToGame(String username) {
		if(getUsername().equals(username))
			return false;
		for(User j : jugadoresAceptados) 
			if(j.getUsername().equals(username))
				return false;
		for(User j : anfitrionDelJugador) 
			if(j.getUsername().equals(username))
				return false;
		for(InvitationGame anfitrion : sendedInvitationsToGame) 
			if(anfitrion.esDelUsuarioG(username)){
				return false;
            } else if (sendedInvitationsToGame.size()>=3){
                return false;
            }
		for(InvitationGame posibleJugador : receivedInvitationsToGame) 
			if(posibleJugador.esDelUsuarioG(username))
				return false;
		return true;
	}

    @Column(name = "estado")
    @NotAudited
    private Boolean estado;

}
