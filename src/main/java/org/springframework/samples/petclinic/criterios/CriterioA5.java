package org.springframework.samples.petclinic.criterios;

import java.util.ArrayList;
import java.util.List;

import org.springframework.samples.petclinic.accion.Accion;
import org.springframework.samples.petclinic.casilla.Casilla;
import org.springframework.samples.petclinic.turnos.Turno;
import org.springframework.samples.petclinic.util.Territorio;

public class CriterioA5 implements StrategyInterface{

    @Override
    public Integer calcularCriterio(List<Accion> acciones, List<Turno> turnos) {

        List<Turno> turnosQueContienenPoblado = new ArrayList<Turno>();
        for(Turno turno: turnos) {
            if(turno.getTerritorio().equals(Territorio.POBLADO)) {
                turnosQueContienenPoblado.add(turno);
            }
        }
        List<Casilla> casillasPoblado = new ArrayList<Casilla>();
        for(Accion accion: acciones) {
            if(turnosQueContienenPoblado.contains(accion.getTurno())) {
                casillasPoblado.add(accion.getCasilla());
            }
        }

        Integer casillasDondeEmpiezaCadaGrupo = eliminarCasillasAdyacentesPoblado(casillasPoblado);

        return casillasDondeEmpiezaCadaGrupo*5;
    }

    private Integer eliminarCasillasAdyacentesPoblado(List<Casilla> casillasPoblado) { 
        if(casillasPoblado.isEmpty()){
            return 0;
        } else {
            Casilla casilla = casillasPoblado.get(0);
            casillasPoblado.remove(casilla);
            return eliminarCasillasAdyacentesPoblado_aux(casillasPoblado, 1,casilla);
        }
    }

    private Integer eliminarCasillasAdyacentesPoblado_aux(List<Casilla> casillasPoblado,
     Integer casillasFinales, Casilla casilla) {
        casillasPoblado = eliminarCasillasAdyacentesPoblado_aux2(casillasPoblado, casilla);
        if(!casillasPoblado.isEmpty()){
            Casilla casilla2 = casillasPoblado.get(0);
            casillasPoblado.remove(casilla2);
            eliminarCasillasAdyacentesPoblado_aux(casillasPoblado, casillasFinales++, casilla2);
        }
        return casillasFinales;
    }

    private List<Casilla> eliminarCasillasAdyacentesPoblado_aux2(List<Casilla> casillasPoblado, Casilla casilla) {
        for (Casilla casillaP: casilla.getAdyacencia()){
            if(casillasPoblado.contains(casillaP)){
                casillasPoblado.remove(casillaP);
                eliminarCasillasAdyacentesPoblado_aux2(casillasPoblado, casillaP);
            }
        }
        return casillasPoblado;
    }
    
}
