package org.springframework.samples.petclinic.criterios;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.samples.petclinic.accion.Accion;
import org.springframework.samples.petclinic.turnos.Turno;
import org.springframework.samples.petclinic.util.Territorio;

public class CriterioA4 implements StrategyInterface{

    @Override
    public Integer calcularCriterio(List<Accion> acciones, List<Turno> turnos) {
        List<Turno> turnosQueContienenPradera = new ArrayList<Turno>();
        List<Turno> turnosQueContienenRio = new ArrayList<Turno>();
        for(Turno turno: turnos) {
            if(turno.getTerritorio().equals(Territorio.PRADERA)) {
                turnosQueContienenPradera.add(turno);
            } else if(turno.getTerritorio().equals(Territorio.RIO)) {
                turnosQueContienenRio.add(turno);
            }
        }

        List<Accion> accionesSePintoPradera = new ArrayList<Accion>();
        List<Accion> accionesSePintoRio = new ArrayList<Accion>();
        Set<Double> filas = new HashSet<Double>();
        for(Accion accion: acciones) {
            if(turnosQueContienenPradera.contains(accion.getTurno())) {
                accionesSePintoPradera.add(accion);
            } else if (turnosQueContienenRio.contains(accion.getTurno())) {
                accionesSePintoRio.add(accion);
            }
            filas.add(accion.getCasilla().getColumna());
        }

        Integer puntos = 0;
        Boolean praderaEnFila = false;
        Boolean rioEnFila = false;
        for(Double fila : filas) {
            for (Accion accion: accionesSePintoPradera) {
                if(accion.getCasilla().getColumna().equals(fila)) {
                    praderaEnFila = true;
                    break;
                }
            }
            for (Accion accion: accionesSePintoRio) {
                if(accion.getCasilla().getColumna().equals(fila)) {
                    rioEnFila = true;
                    break;
                }
            }
            if (praderaEnFila && rioEnFila) {
                puntos +=2;
            }
        praderaEnFila = false;
        rioEnFila = false;
        }
        return puntos;
    }
    
}
