package org.springframework.samples.petclinic.turno;

import static org.assertj.core.api.Assertions.assertThat;
import java.time.LocalDateTime;
import java.util.List;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.partida.Partida;
import org.springframework.samples.petclinic.partida.PartidaService;
import org.springframework.samples.petclinic.turnos.Turno;
import org.springframework.samples.petclinic.turnos.TurnoService;
import org.springframework.samples.petclinic.util.Territorio;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
class TurnoServiceTests {

    // @Autowired
    // protected TurnoService turnoService;
    // protected PartidaService partidaService;

    // protected Turno turno = new Turno();
    // protected Partida p = new Partida();

    // @BeforeEach
    // public void SetPartida(){
    //     p.setDateTime(LocalDateTime.of(2023, 1, 4, 17, 55));
    //     p.setId(2);
    //     p.setIdCriterioA1(2);
    //     p.setIdCriterioA2(1);
    //     p.setIdCriterioB1(5);
    //     p.setIdCriterioB2(3);
    //     p.setIdTablero2(1);
    //     p.setIdTablero3(2);    
    // }

    // public void SetTurno(){
    //     turno.setNumTerritoriosJ1(0);
    //     turno.setNumTerritoriosJ2(0);
    //     turno.setNumTerritoriosJ3(0);
    //     turno.setNumTerritoriosJ4(0);
    //     turno.setPartida(p);
    //     turno.setTerritorio(Territorio.BOSQUE);
    // }

    // @Test
    // public void shouldSaveTurno(){
    //     assertThat(turno.getTerritorio()).isNotEqualTo(Territorio.BOSQUE);
    //     this.turnoService.saveTurno(turno);
    //     assertThat(turno.getTerritorio()).isEqualTo(Territorio.BOSQUE);
    // }
}