/*
 * Copyright 2002-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.samples.petclinic.casilla;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.accion.Accion;
import org.springframework.samples.petclinic.accion.AccionService;
import org.springframework.samples.petclinic.user.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration test of the Service and the Repository layer.
 * <p>
 * ClinicServiceSpringDataJpaTests subclasses benefit from the following services provided
 * by the Spring TestContext Framework:
 * </p>
 * <ul>
 * <li><strong>Spring IoC container caching</strong> which spares us unnecessary set up
 * time between test execution.</li>
 * <li><strong>Dependency Injection</strong> of test fixture instances, meaning that we
 * don't need to perform application context lookups. See the use of
 * {@link Autowired @Autowired} on the <code>{@link
 * CasillaServiceTests#clinicService clinicService}</code> instance variable, which uses
 * autowiring <em>by type</em>.
 * <li><strong>Transaction management</strong>, meaning each test method is executed in
 * its own transaction, which is automatically rolled back by default. Thus, even if tests
 * insert or otherwise change database state, there is no need for a teardown or cleanup
 * script.
 * <li>An {@link org.springframework.context.ApplicationContext ApplicationContext} is
 * also inherited and can be used for explicit bean lookup if necessary.</li>
 * </ul>
 *
 * @author Ken Krebs
 * @author Rod Johnson
 * @author Juergen Hoeller
 * @author Sam Brannen
 * @author Michael Isvy
 * @author Dave Syer
 */

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
