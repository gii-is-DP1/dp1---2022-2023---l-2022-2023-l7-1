package org.springframework.samples.petclinic.Chat;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatRepository extends CrudRepository<Chat, Integer> {

    List<Chat> findAll();

    @Query("SELECT chat FROM Chat chat WHERE chat.partida.id = ?1")
    Chat findByPartidaId(Integer partidaId);
    
}
