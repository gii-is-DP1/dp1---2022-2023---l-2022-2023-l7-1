package org.springframework.samples.petclinic.criterios;

import java.util.ArrayList;
import java.util.List;

import org.springframework.samples.petclinic.accion.Accion;
import org.springframework.samples.petclinic.casilla.Casilla;
import org.springframework.samples.petclinic.turnos.Turno;
import org.springframework.samples.petclinic.util.Territorio;

public class CriterioA2 implements StrategyInterface{

    @Override
    public Integer calcularCriterio(List<Accion> acciones, List<Turno> turnos) {


        List<Turno> turnosQueContienenPradera = new ArrayList<Turno>();
        for(Turno turno: turnos) {
            if(turno.getTerritorio().equals(Territorio.PRADERA)) {
                turnosQueContienenPradera.add(turno);
            }
        }
        List<Accion> accionesPradera = new ArrayList<Accion>();
        for(Accion accion: acciones) {
            if(turnosQueContienenPradera.contains(accion.getTurno())) {
                accionesPradera.add(accion);
            }
        }

        Integer puntos = 0;
        Boolean hayMontana = false;
        Boolean hayRio = false;
        Boolean hayBosque = false;
        for(Accion accion: accionesPradera) {
            List<Casilla> casillasAdyacentes = accion.getCasilla().getAdyacencia();
            for(Casilla casilla : casillasAdyacentes) {

                for(Accion accion3: acciones) {
                    if(accion3.getCasilla().equals(casilla)) {
                        if(accion3.getTurno().getTerritorio().equals(Territorio.MONTANA)) {
                            hayMontana = true;
                            break;
                        }
                        if(accion3.getTurno().getTerritorio().equals(Territorio.RIO)) {
                            hayRio = true;
                            break;
                        }
                        if(accion3.getTurno().getTerritorio().equals(Territorio.BOSQUE)) {
                            hayBosque = true;
                            break;
                        }
                    }
                }
            }
            if (hayMontana && hayRio && !hayBosque) {
                puntos +=3;
            } else if (hayMontana && hayRio && hayBosque) {
                puntos +=4;
            }
            hayMontana=false;
            hayRio=false;
            hayBosque=false;
        }
        
        return puntos;
    }
    
}
