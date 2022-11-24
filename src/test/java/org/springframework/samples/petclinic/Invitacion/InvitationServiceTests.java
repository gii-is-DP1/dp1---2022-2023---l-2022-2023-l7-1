package org.springframework.samples.petclinic.Invitacion;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.user.DuplicatedUsernameException;
import org.springframework.samples.petclinic.user.User;
import org.springframework.samples.petclinic.user.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class InvitationServiceTests {
	
	@Autowired
    protected InvitationService invitationService;
	
	@Autowired
    protected UserService userService;

    @Autowired
    protected InvitationRepository invitationRepository;

    protected User user1 = new User();
    protected User user2 = new User();
    
    protected Invitation invitation = new Invitation();
    
    @BeforeEach
    public void SetUser() throws DataAccessException, DuplicatedUsernameException{
        user1.setBirthDate(LocalDate.of(2002, 1, 2));
        user1.setEmail("diegarlin@user.com");
        user1.setLastName("Linares");
        user1.setName("Diego");
        user1.setUsername("diegarlin");
        user1.setEnabled(true);
        user1.setPassword("password");
        user1.setPhone("633787878");
        this.userService.saveUser(user1);
        
        user2.setBirthDate(LocalDate.of(2002, 1, 2));
        user2.setEmail("diegarlin@user.com");
        user2.setLastName("Linares");
        user2.setName("Diego");
        user2.setUsername("lolito");
        user2.setEnabled(true);
        user2.setPassword("password");
        user2.setPhone("633787878");
        this.userService.saveUser(user2);
    }
    
    @Test
   	void shouldGetInvitationsByUsername() {
        boolean inv1 = this.invitationService.getInvitationsOf(user1.getUsername()).isEmpty();
        assertThat(inv1).isTrue();
        invitation.setReceiver(user1);
        invitation.setSender(user2);
        this.invitationRepository.save(invitation);
        boolean inv2 = !(this.invitationService.getInvitationsOf(user1.getUsername()).isEmpty());
        assertThat(inv2).isTrue();
    }

    @Transactional
    @Test
   	void shouldSendInvitation() {
        boolean inv1 = this.invitationService.getInvitationsOf(user1.getUsername()).isEmpty();
        assertThat(inv1).isTrue();
        this.invitationService.sendInvitation(user2.getUsername(), user1.getUsername());
        boolean inv2 = !(this.invitationService.getInvitationsOf(user1.getUsername()).isEmpty());
        assertThat(inv2).isTrue();
        boolean inv3 = this.invitationService.getInvitationsOf(user2.getUsername()).isEmpty();
        assertThat(inv3).isTrue();
    }

    @Transactional
    @Test
   	void shouldNotSendInvitation() {
        boolean inv1 = this.invitationService.getInvitationsOf(user1.getUsername()).isEmpty();
        assertThat(inv1).isTrue();
        this.invitationService.sendInvitation(user2.getUsername(), user2.getUsername());
        boolean inv4 = this.invitationService.getInvitationsOf(user2.getUsername()).isEmpty();
        assertThat(inv4).isTrue();
    }

    @Transactional
    @Test
   	void shouldAcceptInvitation() {
        invitation.setReceiver(user1);
        invitation.setSender(user2);
        this.invitationRepository.save(invitation);
        this.invitationService.acceptInvitation(user1.getUsername(), invitation.getId()); 
        boolean inv3 = this.invitationService.getInvitationsOf(user1.getUsername()).isEmpty();
        assertThat(inv3).isTrue();
        boolean f = user1.getFriends().contains(user2) && user2.getFriends().contains(user1);
        assertThat(f).isTrue();
    }

    @Transactional
    @Test
   	void shouldgetAvailableUsers() {
        boolean availableUsers = this.invitationService.getAvailableUsers(user1.getUsername())
        .contains(userService.findUserOptional(user2.getUsername()).get())
        && this.invitationService.getAvailableUsers(user2.getUsername())
        .contains(userService.findUserOptional(user1.getUsername()).get());
        assertThat(availableUsers).isTrue();
    }

}
