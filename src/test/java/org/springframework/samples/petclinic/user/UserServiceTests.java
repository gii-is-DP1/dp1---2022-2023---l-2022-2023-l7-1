package org.springframework.samples.petclinic.user;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
class UserServiceTests {
<<<<<<< HEAD
   /*  @Autowired
    protected UserService userSerivce;

    @Test
    @Transactional
    public void shouldInsertUser(){
        try {
        boolean user0 = this.userSerivce.findUser("diegarlin").isEmpty();
        User user = new User();
        user.setBirthDate(LocalDate.of(2002, 1, 2));
        user.setEmail("diegarlin@user.com");
        user.setLastName("Linares");
        user.setName("Diego");
        user.setUsername("diegarlin");
        user.setEnabled(true);
        user.setPassword("password");
        user.setPhone("633787878");

        this.userSerivce.saveUser(user);
        assertThat(user.getUsername().length()).isNotEqualTo(0);

        boolean user1 = this.userSerivce.findUser("diegarlin").isEmpty();
		assertThat(user1).isNotEqualTo(user0);
    }*/
=======
   
>>>>>>> 4b1e9fb570e001ef0ca48ce70eaab853174914c0
}
