package org.springframework.samples.petclinic.criterios;

import java.util.ArrayList;
import java.util.List;

import org.springframework.samples.petclinic.accion.Accion;
import org.springframework.samples.petclinic.turnos.Turno;
import org.springframework.samples.petclinic.util.Territorio;

public class CriterioA6 implements StrategyInterface{

    @Override
    public Integer calcularCriterio(List<Accion> acciones, List<Turno> turnos) {
        List<Turno> turnosQueContienenMontana = new ArrayList<Turno>();
        for(Turno turno: turnos) {
            if(turno.getTerritorio().equals(Territorio.MONTANA)) {
                turnosQueContienenMontana.add(turno);
            }
        }
        Integer puntos = 0;
        for(Accion accion: acciones) {
            if(turnosQueContienenMontana.contains(accion.getTurno()) && accion.getCasilla().getBorde()==true) {
               puntos++;
            }
        }
        return puntos;
    }
    
}
