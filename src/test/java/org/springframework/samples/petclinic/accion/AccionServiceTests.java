package org.springframework.samples.petclinic.accion;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.casilla.CasillaService;
import org.springframework.samples.petclinic.tablero.Tablero;
import org.springframework.samples.petclinic.tablero.TableroService;
import org.springframework.samples.petclinic.turnos.Turno;
import org.springframework.samples.petclinic.turnos.TurnoService;
import org.springframework.samples.petclinic.user.UserService;
import org.springframework.samples.petclinic.util.Territorio;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
class AccionServiceTests { 
    
    @Autowired
    protected AccionService accionService;
    @Autowired
    protected TurnoService turnoService;
    @Autowired
    protected TableroService tableroService;
    @Autowired
    protected CasillaService casillaService;
    @Autowired
    protected UserService userService;

    @Test
    public void shouldGetCasillasPorTurno(){ 
        List<Accion> ls = this.accionService.getCasillasPorTurno(1, 1);
        assertThat(ls.size()).isEqualTo(1);
    }

    @Test
    public void shouldGetAccionById(){ 
        Accion accion = this.accionService.getAccionById(1);
        assertThat(accion.getCasilla().getId()).isEqualTo(7);
    }

    @Test
    public void shouldNotGetAccionById(){ 
        assertThat(this.accionService.getAccionById(10)).isNull();
    }

    @Test
    void shouldSaveAccion(){
        assertThat(this.accionService.getAccionById(10)).isNull();
        Accion accion = new Accion();
        accion.setCasilla(casillaService.getCasillaById(32));
        accionService.save(accion);
        assertThat(this.accionService.getAccionById(10).getCasilla().getId()).isEqualTo(32);
    }

    @Test
    void shouldGetAccionesByTablero(){
        List<Accion> ls = this.accionService.getAccionesByTablero(1);
        assertThat(ls.size()).isEqualTo(9);
    }

    @Test
    void ShouldDeleteAccion(){
        List<Accion> acciones = new ArrayList<>();
        acciones.add(this.accionService.getAccionById(1));
        Boolean b1 = acciones.size() == 1;
        this.accionService.delete(this.accionService.getAccionById(1));;
        Boolean b2 = acciones.size() == 0;
        assertThat(b1).isNotEqualTo(b2);
    }

    @Test
    void shouldGetNumTerritorios(){
        Integer i = this.accionService.getNumTerritorios(this.userService.getUserById("aitroddue"), Territorio.BOSQUE);
        assertThat(i).isEqualTo(1);
    }

    @Test
    void shouldGetNumTerritoriosTotales(){
        Integer i = this.accionService.getNumTerritoriosTotales(Territorio.BOSQUE);
        assertThat(i).isEqualTo(1);
    }

}
