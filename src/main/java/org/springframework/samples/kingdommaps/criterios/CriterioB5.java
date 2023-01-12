package org.springframework.samples.kingdommaps.criterios;

import java.util.ArrayList;
import java.util.List;

import org.springframework.samples.kingdommaps.accion.Accion;
import org.springframework.samples.kingdommaps.casilla.Casilla;
import org.springframework.samples.kingdommaps.turnos.Turno;
import org.springframework.samples.kingdommaps.util.Territorio;

public class CriterioB5 implements StrategyInterface{

    @Override
    public Integer calcularCriterio(List<Accion> acciones, List<Turno> turnos) {
        List<Turno> turnosQueContienenRio = new ArrayList<Turno>();
        for(Turno turno: turnos) {
            if(turno.getTerritorio().equals(Territorio.RIO)) {
                turnosQueContienenRio.add(turno);
            }
        }
        List<Accion> accionesRio = new ArrayList<Accion>();
        for(Accion accion: acciones) {
            if(turnosQueContienenRio.contains(accion.getTurno())) {
                accionesRio.add(accion);
            }
        }

        Integer puntos = 0;
        Integer bosquesConectados = 0;
        for(Accion accion: accionesRio) {
            List<Casilla> casillasAdyacentes = accion.getCasilla().getAdyacencia();
            for(Casilla casilla : casillasAdyacentes) {

                for(Accion accion3: acciones) {
                    if(accion3.getCasilla().equals(casilla)) {
                        if(accion3.getTurno().getTerritorio().equals(Territorio.BOSQUE)) {
                            bosquesConectados ++;
                            break;
                        }
                    }
                }
            }
            if (bosquesConectados >= 2) {
                puntos +=2;
            }
            bosquesConectados=0;
        }
        
        return puntos;
    }
    
}
