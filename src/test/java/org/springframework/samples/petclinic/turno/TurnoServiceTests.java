package org.springframework.samples.petclinic.turno;

import static org.assertj.core.api.Assertions.assertThat;
import java.time.LocalDateTime;
import java.util.List;

import javax.transaction.Transactional;

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
        partida.setDateTime(LocalDateTime.of(2023, 1, 4, 17, 55));
        partida.setId(2);
        partida.setIdCriterioA1(2);
        partida.setIdCriterioA2(1);
        partida.setIdCriterioB1(5);
        partida.setIdCriterioB2(3);
        partidaService.savePartida(partida);
        turno.setId(7);
        turno.setNumTerritoriosJ1(0);
        turno.setNumTerritoriosJ2(0);
        turno.setNumTerritoriosJ3(0);
        turno.setNumTerritoriosJ4(0);
        turno.setPartida(partida);
        turno.setTerritorio(Territorio.BOSQUE);
    }

    @Test
    @Transactional
    public void shouldSaveTurno(){
        Boolean turnoNotSave = turnoService.getTurnosByPartida(2).isEmpty();
        assertThat(turnoNotSave).isTrue();
        turnoService.saveTurno(turno);
        Boolean turnoSave = turnoService.getTurnosByPartida(2).isEmpty();
        assertThat(turnoSave).isFalse();
    }

    @Test
    @Transactional
    public void shouldGetTurnoById(){
        Turno turno1 = turnoService.getTurnoById(7);
        assertThat(turno1).isNull();
        turnoService.saveTurno(turno);
        Turno turno2 = turnoService.getTurnoById(7);
        assertThat(turno2).isNotNull();
    }

    @Test
    @Transactional
    public void shouldGetTurnosByPartida(){
        List<Turno> turno1 = turnoService.getTurnosByPartida(2);
        assertThat(turno1).isEmpty();
        turnoService.saveTurno(turno);
        List<Turno> turno2 = turnoService.getTurnosByPartida(2);
        assertThat(turno2).isNotEmpty();
    }

    @Test
    @Transactional
    public void shouldDelete(){
        turnoService.saveTurno(turno);
        Turno turno1 = turnoService.getTurnoById(7);
        assertThat(turno1).isNotNull();
        turnoService.delete(turno);
        Turno turno2 = turnoService.getTurnoById(7);
        assertThat(turno2).isNull();
    }

}