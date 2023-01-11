package org.springframework.samples.petclinic.turno;

import static org.assertj.core.api.Assertions.assertThat;
import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;
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
import org.springframework.transaction.annotation.Transactional;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
class TurnoServiceTests {

    @Autowired
    protected TurnoService turnoService;

    @Autowired
    protected PartidaService partidaService;

    protected Turno turno = new Turno();

    protected Partida partida = new Partida();

    @BeforeEach
    public void setPartida(){
        turno.setId(7);
        turno.setNumTerritoriosJ1(0);
        turno.setNumTerritoriosJ2(0);
        turno.setNumTerritoriosJ3(0);
        turno.setNumTerritoriosJ4(0);
        turno.setPartida(partidaService.getPartidaById(1));
        turno.setTerritorio(Territorio.BOSQUE);
        turnoService.saveTurno(turno);
    }


    @Test
    @Transactional
    public void shouldGetTurnoById(){
        Turno turno2 = turnoService.getTurnoById(1);
        assertThat(turno2).isNotNull();
    }

    @Test
    @Transactional
    public void shouldGetTurnosByPartida(){
        List<Turno> turno2 = turnoService.getTurnosByPartida(1);
        assertThat(turno2).isNotEmpty();
    }

}