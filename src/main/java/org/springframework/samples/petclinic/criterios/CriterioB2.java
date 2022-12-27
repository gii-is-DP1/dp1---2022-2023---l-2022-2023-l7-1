package org.springframework.samples.petclinic.criterios;

import java.util.ArrayList;
import java.util.List;

import org.springframework.samples.petclinic.accion.Accion;
import org.springframework.samples.petclinic.casilla.Casilla;
import org.springframework.samples.petclinic.turnos.Turno;
import org.springframework.samples.petclinic.util.Territorio;

public class CriterioB2 implements StrategyInterface{

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
        Boolean hayPoblado = false;
        Integer castillosConectados = 0;
        for(Accion accion: accionesPradera) {
            List<Casilla> casillasAdyacentes = accion.getCasilla().getAdyacencia();
            for(Casilla casilla : casillasAdyacentes) {

                for(Accion accion3: acciones) {
                    if(accion3.getCasilla().equals(casilla)) {
                        if(accion3.getTurno().getTerritorio().equals(Territorio.POBLADO)) {
                            hayPoblado = true;
                            break;
                        }
                        if(accion3.getTurno().getTerritorio().equals(Territorio.CASTILLO)) {
                            castillosConectados ++;
                            break;
                        }
                    }
                }
            }
            if (hayPoblado && castillosConectados <2) {
                puntos ++;
            } else if (hayPoblado && castillosConectados >=2) {
                puntos +=4;
            }
            hayPoblado=false;
            castillosConectados=0;
        }
        
        return puntos;
    }
    
}
