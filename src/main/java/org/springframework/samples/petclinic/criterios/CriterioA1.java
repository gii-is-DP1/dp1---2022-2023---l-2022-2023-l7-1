package org.springframework.samples.petclinic.criterios;

import java.util.ArrayList;
import java.util.List;

import org.springframework.samples.petclinic.accion.Accion;
import org.springframework.samples.petclinic.casilla.Casilla;
import org.springframework.samples.petclinic.turnos.Turno;
import org.springframework.samples.petclinic.util.Territorio;

public class CriterioA1 implements StrategyInterface{

    @Override
    public Integer calcularCriterio(List<Accion> acciones, List<Turno> turnos) {
        List<Turno> turnosQueContienenCastillo = new ArrayList<Turno>();
        for(Turno turno: turnos) {
            if(turno.getTerritorio().equals(Territorio.CASTILLO)) {
                turnosQueContienenCastillo.add(turno);
            }
        }
        List<Accion> accionesCastilloSinBorde = new ArrayList<Accion>();
        for(Accion accion: acciones) {
            if(turnosQueContienenCastillo.contains(accion.getTurno()) && accion.getCasilla().getBorde()==false) {
                accionesCastilloSinBorde.add(accion);
            }
        }

        Integer puntos = 0;
        for(Accion accion: accionesCastilloSinBorde) {
            List<Casilla> casillasAdyacentes = accion.getCasilla().getAdyacencia();
            Integer contador = 0;
            for(Casilla casilla : casillasAdyacentes) {

                for(Accion accion3: acciones) {
                    if(accion3.getCasilla().equals(casilla)) {
                        contador ++;
                    }
                }
                if (contador == 6) {
                    puntos +=2;
                }
                contador = 0;
            }
        }
        
        return puntos;
    }
    
}
