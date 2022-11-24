package org.springframework.samples.petclinic.Invitacion;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvitationRepository extends CrudRepository<Invitation, Integer> {
    
    @Query("SELECT i FROM Invitation i WHERE i.receiver.username = ?1")
	List<Invitation> getInvitationsOf(String username);
}
