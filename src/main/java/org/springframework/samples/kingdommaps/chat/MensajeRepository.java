package org.springframework.samples.kingdommaps.chat;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MensajeRepository  extends CrudRepository<Mensaje, Integer>{

    List<Mensaje> findAll();

    @Query("SELECT m FROM Mensaje m WHERE m.user.username = ?1")
    List<Mensaje> findByUserId(String username);
    
}
