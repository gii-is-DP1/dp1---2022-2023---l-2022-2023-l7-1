package org.springframework.samples.petclinic.user;

import static org.assertj.core.api.Assertions.assertThat;
import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
class UserServiceTests {
    @Autowired
    protected UserService userService;

    protected User user = new User();

    @BeforeEach
    public void SetUser(){
        user.setBirthDate(LocalDate.of(2002, 1, 2));
        user.setEmail("diegarlin@user.com");
        user.setLastName("Linares");
        user.setName("Diego");
        user.setUsername("diegarlin");
        user.setEnabled(true);
        user.setPassword("password");
        user.setPhone("633787878");
    }

    @Test
    @Transactional
    public void shouldInsertUser() throws DataAccessException, DuplicatedUsernameException{
        boolean user0 = this.userService.findUser("diegarlin").isEmpty();
        this.userService.saveUser(user);
        assertThat(user.getUsername().length()).isNotEqualTo(0);
        boolean user1 = this.userService.findUser("diegarlin").isEmpty();
		assertThat(user1).isNotEqualTo(user0);
    }

    @Test
    @Transactional
    public void shouldNotInsertUser() throws DataAccessException, DuplicatedUsernameException{
        this.userService.saveUser(user);
        assertThat(user.getUsername().length()).isNotEqualTo(0);
        this.userService.saveUser(user);
        assertThat(DuplicatedUsernameException.class);
    }

    @Test
	void shouldGetUserByUsername() throws DataAccessException, DuplicatedUsernameException {
        this.userService.saveUser(user);
		User user = this.userService.getUserById("diegarlin");
		assertThat(user.getUsername()).isEqualTo("diegarlin");
	}

    @Test
    void shouldDeleteUserByUsername() throws DataAccessException, DuplicatedUsernameException{
        this.userService.saveUser(user);
        this.userService.deleteUserById("diegarlin");
        boolean user0 = this.userService.findUser("diegarlin").isEmpty();
        assertThat(user0).isTrue();
    }
}
