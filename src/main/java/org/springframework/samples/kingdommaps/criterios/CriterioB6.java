package org.springframework.samples.kingdommaps.criterios;

import java.util.ArrayList;
import java.util.List;

import org.springframework.samples.kingdommaps.accion.Accion;
import org.springframework.samples.kingdommaps.casilla.Casilla;
import org.springframework.samples.kingdommaps.turnos.Turno;
import org.springframework.samples.kingdommaps.util.Territorio;

public class CriterioB6 implements StrategyInterface{

    @Override
    public Integer calcularCriterio(List<Accion> acciones, List<Turno> turnos) {
        List<Turno> turnosQueContienenPoblado = new ArrayList<Turno>();
        for(Turno turno: turnos) {
            if(turno.getTerritorio().equals(Territorio.POBLADO)) {
                turnosQueContienenPoblado.add(turno);
            }
        }
        List<Accion> accionesPoblado = new ArrayList<Accion>();
        for(Accion accion: acciones) {
            if(turnosQueContienenPoblado.contains(accion.getTurno())) {
                accionesPoblado.add(accion);
            }
        }

        Integer puntos = 0;
        Boolean hayMontana = false;
        Boolean hayRio = false;
        Boolean hayBosque = false;
        Boolean hayPoblado = false;
        List<Casilla> casillasAdyacentes;
        for(Accion accion: accionesPoblado) {
            casillasAdyacentes = accion.getCasilla().getAdyacencia();
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
                        if(accion3.getTurno().getTerritorio().equals(Territorio.POBLADO)) {
                            hayPoblado = true;
                            break;
                        }
                    }
                }
                if (hayPoblado) break;
            }
            if (hayMontana && hayRio && hayBosque && !hayPoblado) {
                puntos +=8;
            } 
            hayMontana=false;
            hayRio=false;
            hayBosque=false;
            hayPoblado=false;
        }
        
        return puntos;
    }
    
}
