package org.springframework.samples.kingdommaps.Invitacion;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface InvitationGameRepository extends CrudRepository<InvitationGame, Integer> {
    @Query("SELECT i FROM InvitationGame i WHERE i.posibleJugador.username = ?1")
	List<InvitationGame> getInvitationsGameOf(String username);
}
