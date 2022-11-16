package org.springframework.samples.petclinic.user;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


public interface UserRepository extends  CrudRepository<User, String>{

    List<User> findAll();

    @Query("SELECT user FROM User user WHERE user.username = :username")
    Collection<User> findByUsername(String username) throws DataAccessException;

    @Query("SELECT user FROM User user WHERE user.username = :username")
    Optional<User> findByUsernameOptional(String username) throws DataAccessException;
	
}
