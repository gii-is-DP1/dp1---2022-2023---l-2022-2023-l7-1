package org.springframework.samples.kingdommaps.criterios;

import java.util.ArrayList;
import java.util.List;

import org.springframework.samples.kingdommaps.accion.Accion;
import org.springframework.samples.kingdommaps.turnos.Turno;
import org.springframework.samples.kingdommaps.util.Territorio;

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
