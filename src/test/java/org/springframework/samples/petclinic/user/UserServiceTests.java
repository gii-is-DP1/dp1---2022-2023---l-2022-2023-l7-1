package org.springframework.samples.petclinic.user;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.logros.LogroService;
import org.springframework.samples.petclinic.tablero.Tablero;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
class UserServiceTests {

    private static final String FRAVILPAE = "fravilpae";
    private static final String JESZAMGUE = "jeszamgue";

    @Autowired
    protected UserService userService;

    @Autowired
    protected LogroService logroService;

    protected User user = new User();
    protected Tablero tablero = new Tablero();

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
	void shouldGetUserByUsernameOptional() throws DataAccessException, DuplicatedUsernameException {
        this.userService.saveUser(user);
		User user = this.userService.findUserOptional("diegarlin").get();
		assertThat(user.getUsername()).isEqualTo("diegarlin");
	}

    @Test
    void shouldDeleteUserByUsername() throws DataAccessException, DuplicatedUsernameException{
        this.userService.saveUser(user);
        this.userService.deleteUserById("diegarlin");
        boolean user0 = this.userService.findUser("diegarlin").isEmpty();
        assertThat(user0).isTrue();
    }

    @Test
    void shouldGetFriendsByUsername() throws DataAccessException, DuplicatedUsernameException{
        this.userService.saveUser(user);
        List<User> list = this.userService.getFriends(user.getUsername());
        boolean listEmpty = list.isEmpty();
        assertThat(listEmpty).isTrue();
        List<User> list2 = this.userService.getFriends(JESZAMGUE); //user jeszamgue has friends
        boolean listNotEmpty = !list2.isEmpty();
        assertThat(listNotEmpty).isTrue();
        boolean friends = list2.size()>0;
        assertThat(friends).isTrue();
    }

    @Test
    void shouldDeleteFriendsByUsernames() throws DataAccessException, DuplicatedUsernameException{
        List<User> list = this.userService.getFriends(FRAVILPAE);
        List<User> list2 = this.userService.getFriends(JESZAMGUE);
        User jeszamgue = this.userService.getUserById(JESZAMGUE);
        User fravilpae = this.userService.getUserById(FRAVILPAE);
        boolean AreFriends = list.contains(jeszamgue) && list2.contains(fravilpae);
        assertThat(AreFriends).isTrue();
        this.userService.deleteFriend(JESZAMGUE, FRAVILPAE);
        list = this.userService.getFriends(FRAVILPAE);
        list2 = this.userService.getFriends(JESZAMGUE);
        boolean AreNOtFriends = !(list.contains(jeszamgue) && list2.contains(fravilpae));
        assertThat(AreNOtFriends).isTrue();
    }

    @Test
    void shouldFindUsers() throws DataAccessException, DuplicatedUsernameException{
        this.userService.saveUser(user);
		List<User> user = this.userService.findUsers("diegarlin");
		assertThat(user.size()==1);
        assertThat(user.get(0).getUsername()== "diegarlin");
        List<User> user2 = this.userService.findUsers("bkvewbovcobuwevoew");
        assertThat(user2.size()==0);
        List<User> users = this.userService.findUsers("");
        assertThat(users.size()>1);
    }

    @Test
    void shouldFindTableros() throws DataAccessException, DuplicatedUsernameException{
        this.userService.saveUser(user);
		List<Tablero> user = this.userService.getTableroByUser("diegarlin");
		assertThat(user.size()==0);
        List<Tablero> user2 = this.userService.getTableroByUser("raymon"); //has 1 tablero
        assertThat(user2.size()!=0);
    }

    @Test
    void shouldDeleteAllFriends() throws DataAccessException {
        List<User> list = this.userService.getFriends(JESZAMGUE);
        assertThat(list.size()!=0);
        this.userService.deleteFriends(JESZAMGUE);
        List<User> list2 = this.userService.getFriends(JESZAMGUE);
        assertThat(list2.size()!=0);
    }

    @Test
    void shouldGetLogrosByUser() throws DataAccessException {
        assertThat(userService.getLogrosByUser(userService.getUserById(FRAVILPAE)).size()).isEqualTo(0);
    }

    @Test
    void shouldCalcularEstadisticas() throws DataAccessException {
        userService.save(user);
        userService.calculaEstadisticas(user);
        assertThat(user.getMatchesPlayed()).isEqualTo(0);
        assertThat(user.getGamesWin()).isEqualTo(0);
        assertThat(user.getWinRatio()).isEqualTo(0);
        assertThat(user.getTotalPoints()).isEqualTo(0);
        assertThat(user.getMaxPoints()).isEqualTo(0);
        assertThat(user.getTimesUsedTerritory1()).isEqualTo(0);
        assertThat(user.getTimesUsedTerritory2()).isEqualTo(0);
        assertThat(user.getTimesUsedTerritory3()).isEqualTo(0);
        assertThat(user.getTimesUsedTerritory4()).isEqualTo(0);
        assertThat(user.getTimesUsedTerritory5()).isEqualTo(0);
        assertThat(user.getTimesUsedTerritory6()).isEqualTo(0);
    }

    @Test
    void shouldCalcularEstadisticasGlobales() throws DataAccessException {
        List<Integer> lista = userService.calculaEstadisticasGlobales();
        assertThat(lista.size()).isEqualTo(8);
    }
}
