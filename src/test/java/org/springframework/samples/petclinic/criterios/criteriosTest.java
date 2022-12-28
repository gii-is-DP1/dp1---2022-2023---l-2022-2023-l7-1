package org.springframework.samples.petclinic.criterios;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.accion.Accion;
import org.springframework.samples.petclinic.casilla.CasillaService;
import org.springframework.samples.petclinic.turnos.Turno;
import org.springframework.samples.petclinic.util.Territorio;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class criteriosTest {
	
	private static StrategyInterface strategy;

    @Autowired
    CasillaService casillaService;

    // -------------------------------------------------------------------------------------------
	// --- Acciones ------------------------------------------------------------------------------
	// -------------------------------------------------------------------------------------------

    protected Accion accion1 = new Accion();
    protected Accion accion2 = new Accion();
    protected Accion accion3 = new Accion();
    protected Accion accion4 = new Accion();
    protected Accion accion5 = new Accion();
    protected Accion accion6 = new Accion();
    protected Accion accion7 = new Accion();
    protected Accion accion8 = new Accion();
    protected Accion accion9 = new Accion();
    protected Accion accion10 = new Accion();
    protected Accion accion11 = new Accion();
    protected Accion accion12 = new Accion();
    protected Accion accion13 = new Accion();
    protected Accion accion14 = new Accion();
    protected Accion accion15 = new Accion();
    protected Accion accion16 = new Accion();
    protected Accion accion17 = new Accion();
    protected Accion accion18 = new Accion();
    protected Accion accion19 = new Accion();
    protected Accion accion20 = new Accion();
    protected Accion accion21 = new Accion();
    protected Accion accion22 = new Accion();
    protected Accion accion23 = new Accion();
    protected Accion accion24 = new Accion();

    // -------------------------------------------------------------------------------------------
	// --- Turnos --------------------------------------------------------------------------------
	// -------------------------------------------------------------------------------------------

    protected Turno  turno1 = new Turno();
    protected Turno  turno2 = new Turno();
    protected Turno  turno3 = new Turno();
    protected Turno  turno4 = new Turno();
    protected Turno  turno5 = new Turno();
    protected Turno  turno6 = new Turno();


    @Test
    public void puntosCriterioA1(){
        List<Accion> acciones= new ArrayList<Accion>();
        List<Turno> turnos = new ArrayList<Turno>();
        strategy = new CriterioA1();
        Integer res = strategy.calcularCriterio(acciones, turnos);
        assertThat(res).isEqualTo(0);
        turno4.setTerritorio(Territorio.CASTILLO);
        accion1.setTurno(turno4); accion1.setCasilla(this.casillaService.getCasillaById(7)); 
        acciones.add(accion1);
        accion2.setTurno(turno4); accion2.setCasilla(this.casillaService.getCasillaById(6)); 
        acciones.add(accion2);
        accion3.setTurno(turno4); accion3.setCasilla(this.casillaService.getCasillaById(1)); 
        acciones.add(accion3);
        accion4.setTurno(turno4); accion4.setCasilla(this.casillaService.getCasillaById(2)); 
        acciones.add(accion4);
        accion5.setTurno(turno4); accion5.setCasilla(this.casillaService.getCasillaById(3)); 
        acciones.add(accion5);
        accion6.setTurno(turno4); accion6.setCasilla(this.casillaService.getCasillaById(4)); 
        acciones.add(accion6);
        turnos.add(turno4);
        turno1.setTerritorio(Territorio.RIO);
        accion7.setTurno(turno1); accion7.setCasilla(this.casillaService.getCasillaById(8)); 
        acciones.add(accion7);
        accion8.setTurno(turno1); accion8.setCasilla(this.casillaService.getCasillaById(5)); 
        acciones.add(accion8);
        accion9.setTurno(turno1); accion9.setCasilla(this.casillaService.getCasillaById(12)); 
        acciones.add(accion9);
        accion10.setTurno(turno1); accion10.setCasilla(this.casillaService.getCasillaById(13)); 
        acciones.add(accion10);
        accion11.setTurno(turno1); accion11.setCasilla(this.casillaService.getCasillaById(14)); 
        acciones.add(accion11);
        accion12.setTurno(turno1); accion12.setCasilla(this.casillaService.getCasillaById(9)); 
        acciones.add(accion12);
        turnos.add(turno1);
        res = strategy.calcularCriterio(acciones, turnos);
        assertThat(res).isEqualTo(2); //casilla 7
        turno2.setTerritorio(Territorio.CASTILLO);
        accion13.setTurno(turno2); accion13.setCasilla(this.casillaService.getCasillaById(10)); 
        acciones.add(accion13);
        accion14.setTurno(turno2); accion14.setCasilla(this.casillaService.getCasillaById(11)); 
        acciones.add(accion14);
        accion15.setTurno(turno2); accion15.setCasilla(this.casillaService.getCasillaById(15)); 
        acciones.add(accion15);
        accion16.setTurno(turno2); accion16.setCasilla(this.casillaService.getCasillaById(16)); 
        acciones.add(accion16);
        accion17.setTurno(turno2); accion17.setCasilla(this.casillaService.getCasillaById(17)); 
        acciones.add(accion17);
        accion18.setTurno(turno2); accion18.setCasilla(this.casillaService.getCasillaById(18)); 
        acciones.add(accion18);
        turnos.add(turno2);
        res = strategy.calcularCriterio(acciones, turnos);
        assertThat(res).isEqualTo(4); // casilla 7 y 10
        turno3.setTerritorio(Territorio.RIO);
        accion19.setTurno(turno3); accion19.setCasilla(this.casillaService.getCasillaById(60)); 
        acciones.add(accion19);
        accion20.setTurno(turno3); accion20.setCasilla(this.casillaService.getCasillaById(22)); 
        acciones.add(accion20);
        accion21.setTurno(turno3); accion21.setCasilla(this.casillaService.getCasillaById(32)); 
        acciones.add(accion21);
        accion22.setTurno(turno3); accion22.setCasilla(this.casillaService.getCasillaById(19)); 
        acciones.add(accion22);
        accion23.setTurno(turno3); accion23.setCasilla(this.casillaService.getCasillaById(20)); 
        acciones.add(accion23);
        accion24.setTurno(turno3); accion24.setCasilla(this.casillaService.getCasillaById(31)); 
        acciones.add(accion24);
        turnos.add(turno3);
        res = strategy.calcularCriterio(acciones, turnos);
        assertThat(res).isEqualTo(4); // casilla 7 y 10      
    }

    @Test
    public void puntosCriterioA2(){
        List<Accion> acciones= new ArrayList<Accion>();
        List<Turno> turnos = new ArrayList<Turno>();
        strategy = new CriterioA2();
        Integer res = strategy.calcularCriterio(acciones, turnos);
        assertThat(res).isEqualTo(0);
        turno4.setTerritorio(Territorio.PRADERA);
        accion1.setTurno(turno4); accion1.setCasilla(this.casillaService.getCasillaById(7)); 
        acciones.add(accion1);
        accion2.setTurno(turno4); accion2.setCasilla(this.casillaService.getCasillaById(6)); 
        acciones.add(accion2);
        accion3.setTurno(turno4); accion3.setCasilla(this.casillaService.getCasillaById(1)); 
        acciones.add(accion3);
        accion4.setTurno(turno4); accion4.setCasilla(this.casillaService.getCasillaById(2)); 
        acciones.add(accion4);
        accion5.setTurno(turno4); accion5.setCasilla(this.casillaService.getCasillaById(3)); 
        acciones.add(accion5);
        accion6.setTurno(turno4); accion6.setCasilla(this.casillaService.getCasillaById(4)); 
        acciones.add(accion6);
        turnos.add(turno4);
        turno3.setTerritorio(Territorio.RIO);
        accion7.setTurno(turno3); accion7.setCasilla(this.casillaService.getCasillaById(14)); 
        acciones.add(accion7);
        turnos.add(turno3);
        turno1.setTerritorio(Territorio.MONTANA);
        accion8.setTurno(turno1); accion8.setCasilla(this.casillaService.getCasillaById(5)); 
        acciones.add(accion8);
        accion9.setTurno(turno1); accion9.setCasilla(this.casillaService.getCasillaById(12)); 
        acciones.add(accion9);
        accion10.setTurno(turno1); accion10.setCasilla(this.casillaService.getCasillaById(9)); 
        acciones.add(accion10);
        accion11.setTurno(turno1); accion11.setCasilla(this.casillaService.getCasillaById(13)); 
        acciones.add(accion11);
        accion12.setTurno(turno1); accion12.setCasilla(this.casillaService.getCasillaById(28)); 
        acciones.add(accion12);
        turnos.add(turno1);
        res = strategy.calcularCriterio(acciones, turnos);
        assertThat(res).isEqualTo(3); //casilla 7 con casilla 13 y 14
        turno2.setTerritorio(Territorio.RIO);
        accion13.setTurno(turno2); accion13.setCasilla(this.casillaService.getCasillaById(18)); 
        acciones.add(accion13);
        accion14.setTurno(turno2); accion14.setCasilla(this.casillaService.getCasillaById(10)); 
        acciones.add(accion14);
        turnos.add(turno2);
        res = strategy.calcularCriterio(acciones, turnos);
        assertThat(res).isEqualTo(6);  //casilla 7 con casilla 13 y 14, casilla 4 con casilla 10 y 9
        turno5.setTerritorio(Territorio.BOSQUE);
        accion15.setTurno(turno5); accion15.setCasilla(this.casillaService.getCasillaById(8)); 
        acciones.add(accion15);
        turnos.add(turno5);  
        res = strategy.calcularCriterio(acciones, turnos);
        assertThat(res).isEqualTo(7); //casilla 7 con casilla 13 , 14 y 8(bosque), casilla 4 con casilla 10 y 9
    }

    @Test
    public void puntosCriterioA5(){
        List<Accion> acciones= new ArrayList<Accion>();
        List<Turno> turnos = new ArrayList<Turno>();
        strategy = new CriterioA5();
        Integer res = strategy.calcularCriterio(acciones, turnos);
        assertThat(res).isEqualTo(0);
        turno4.setTerritorio(Territorio.POBLADO);
        accion1.setTurno(turno4); accion1.setCasilla(this.casillaService.getCasillaById(7)); 
        acciones.add(accion1);
        accion2.setTurno(turno4); accion2.setCasilla(this.casillaService.getCasillaById(6)); 
        acciones.add(accion2);
        accion3.setTurno(turno4); accion3.setCasilla(this.casillaService.getCasillaById(1)); 
        acciones.add(accion3);
        accion4.setTurno(turno4); accion4.setCasilla(this.casillaService.getCasillaById(2)); 
        acciones.add(accion4);
        accion5.setTurno(turno4); accion5.setCasilla(this.casillaService.getCasillaById(3)); 
        acciones.add(accion5);
        accion6.setTurno(turno4); accion6.setCasilla(this.casillaService.getCasillaById(4)); 
        acciones.add(accion6);
        turnos.add(turno4);
        turno3.setTerritorio(Territorio.POBLADO);
        accion7.setTurno(turno3); accion7.setCasilla(this.casillaService.getCasillaById(14)); 
        acciones.add(accion7);
        turnos.add(turno3);
        turno1.setTerritorio(Territorio.POBLADO);
        accion8.setTurno(turno1); accion8.setCasilla(this.casillaService.getCasillaById(5)); 
        acciones.add(accion8);
        accion9.setTurno(turno1); accion9.setCasilla(this.casillaService.getCasillaById(12)); 
        acciones.add(accion9);
        accion10.setTurno(turno1); accion10.setCasilla(this.casillaService.getCasillaById(9)); 
        acciones.add(accion10);
        accion11.setTurno(turno1); accion11.setCasilla(this.casillaService.getCasillaById(13)); 
        acciones.add(accion11);
        accion12.setTurno(turno1); accion12.setCasilla(this.casillaService.getCasillaById(8)); 
        acciones.add(accion12);
        turnos.add(turno1);
        res = strategy.calcularCriterio(acciones, turnos);
        assertThat(res).isEqualTo(5); //1 grupo de 12
        turno2.setTerritorio(Territorio.POBLADO);
        accion13.setTurno(turno2); accion13.setCasilla(this.casillaService.getCasillaById(28)); 
        acciones.add(accion13);
        accion14.setTurno(turno2); accion14.setCasilla(this.casillaService.getCasillaById(10)); 
        acciones.add(accion14);
        turnos.add(turno2);
        res = strategy.calcularCriterio(acciones, turnos);
        assertThat(res).isEqualTo(10);  //1 grupo de 13 y 1 de 1 
        turno5.setTerritorio(Territorio.BOSQUE);
        accion15.setTurno(turno5); accion15.setCasilla(this.casillaService.getCasillaById(31)); 
        acciones.add(accion15);
        turnos.add(turno5);  
        res = strategy.calcularCriterio(acciones, turnos);
        assertThat(res).isEqualTo(10); //1 grupo de 13 y 1 de 1
        turno6.setTerritorio(Territorio.POBLADO);
        accion16.setTurno(turno6); accion16.setCasilla(this.casillaService.getCasillaById(29)); 
        acciones.add(accion16);
        accion17.setTurno(turno6); accion17.setCasilla(this.casillaService.getCasillaById(30)); 
        acciones.add(accion17);
        accion18.setTurno(turno6); accion18.setCasilla(this.casillaService.getCasillaById(61)); 
        acciones.add(accion18);
        accion19.setTurno(turno6); accion19.setCasilla(this.casillaService.getCasillaById(45)); 
        acciones.add(accion19);
        turnos.add(turno6);
        res = strategy.calcularCriterio(acciones, turnos);
        assertThat(res).isEqualTo(20);  //1 grupo de 13, 2 de 1 y 1 de 3
    }
    
}
