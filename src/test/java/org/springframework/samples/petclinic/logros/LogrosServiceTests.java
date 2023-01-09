package org.springframework.samples.petclinic.logros;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.user.UserService;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
class LogrosServiceTests {                
    
	@Autowired
	protected LogroService logrosService;
    @Autowired
	protected UserService userService;

    @Test
    public void shouldFindAllLogros(){ 
        List<Logro> logros = this.logrosService.getLogros();
        assertThat(logros.size()).isEqualTo(4);
    }

    @Test
	void shouldFindLogroById(){
		Logro logroById = this.logrosService.getById(1);
		assertThat(logroById.getReqPuntos()).isEqualTo(80);
        assertThat(logroById.getTitulo()).isEqualTo("Explorador de Patio");
	}

    @Test
    void shouldNotFindLogroById(){
        assertThrows(NoSuchElementException.class, () -> this.logrosService.getById(5));
    }

    @Test
    void DeleteLogroById(){
        List<Logro> logros = new ArrayList<>();
        logros.add(this.logrosService.getById(1));
        Boolean b1 = logros.size() == 1;
        this.logrosService.deleteLogroById(1);
        Boolean b2 = logros.size() == 0;
        assertThat(b1).isNotEqualTo(b2);
    }

    @Test
    void shouldSaveLogro(){
        assertThrows(NoSuchElementException.class, () -> this.logrosService.getById(5));
        Logro logro = new Logro();
        logro.setTitulo("Nuevo Logro");
        logro.setReqPuntos(50);
        logro.setLogo("logo5.png");
        logro.setDescripcion("Descripcion");
        logro.setUser(userService.getUserById("aitroddue"));
        logrosService.save(logro);
        assertThat(this.logrosService.getById(5).getTitulo()).isEqualTo("Nuevo Logro");
    }
}
