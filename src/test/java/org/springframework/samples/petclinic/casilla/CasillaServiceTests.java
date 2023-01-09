package org.springframework.samples.petclinic.casilla;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;

import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
class CasillaServiceTests {                
    
	@Autowired
	protected CasillaService casillaService;

	@Test
	void shouldFindCasillasAdyacentes() {
		List<Casilla> casillasAdyacentes = this.casillaService.getCasillasAdyacentes(14);
		assertThat(casillasAdyacentes.size()).isEqualTo(6);
		List<Casilla> casillasAdyacentesBorde = this.casillaService.getCasillasAdyacentes(2);
		assertThat(casillasAdyacentesBorde.size()).isEqualTo(4);
		List<Casilla> casillasAdyacentesEsquina = this.casillaService.getCasillasAdyacentes(1);
		assertThat(casillasAdyacentesEsquina.size()).isEqualTo(3);
	}

	@Test
	void shouldFindTodasCasillas() {
		List<Casilla> casillasTotales = this.casillaService.getTodasCasillas();
		assertThat(casillasTotales.size()).isEqualTo(61);
	}

	@Test
	void shouldFindCasillasById(){
		Casilla casillaById = this.casillaService.getCasillaById(6);
		assertThat(casillaById.borde).isTrue();
		assertThat(casillaById.poder1 && casillaById.poder2).isFalse();
		assertThat(casillaById.casillaOpuesta).isEqualTo(56);
	}

	@Test
    void negativeFindById(){
        assertThrows(NoSuchElementException.class, () -> this.casillaService.getCasillaById(62));
    }


}
