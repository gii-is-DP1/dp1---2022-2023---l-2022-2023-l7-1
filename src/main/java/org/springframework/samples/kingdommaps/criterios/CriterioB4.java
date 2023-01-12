package org.springframework.samples.kingdommaps.criterios;

import java.util.ArrayList;
import java.util.List;

import org.springframework.samples.kingdommaps.accion.Accion;
import org.springframework.samples.kingdommaps.casilla.Casilla;
import org.springframework.samples.kingdommaps.turnos.Turno;
import org.springframework.samples.kingdommaps.util.Territorio;

public class CriterioB4 implements StrategyInterface{

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
        Integer contador = 0;
        List<Territorio> territoriosUsados = new ArrayList<Territorio>();
        for(Accion accion: accionesCastilloSinBorde) {
            List<Casilla> casillasAdyacentes = accion.getCasilla().getAdyacencia();
            for(Casilla casilla : casillasAdyacentes) {

                for(Accion accion3: acciones) {
                    if(accion3.getCasilla().equals(casilla) && !territoriosUsados.contains(accion3.getTurno().getTerritorio())) {
                        contador ++;
                        territoriosUsados.add(accion3.getTurno().getTerritorio());
                    }
                }
            }
            if (contador == 6) {
                puntos +=12;
            }
            contador = 0;
            territoriosUsados.clear();
        }
        
        return puntos;
    }
    
}
