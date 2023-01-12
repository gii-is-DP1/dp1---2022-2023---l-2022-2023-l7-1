package org.springframework.samples.kingdommaps.criterios;

import java.util.ArrayList;
import java.util.List;

import org.springframework.samples.kingdommaps.accion.Accion;
import org.springframework.samples.kingdommaps.casilla.Casilla;
import org.springframework.samples.kingdommaps.turnos.Turno;
import org.springframework.samples.kingdommaps.util.Territorio;

public class CriterioB3 implements StrategyInterface{

    @Override
    public Integer calcularCriterio(List<Accion> acciones, List<Turno> turnos) {
        List<Turno> turnosQueContienenBosque = new ArrayList<Turno>();
        for(Turno turno: turnos) {
            if(turno.getTerritorio().equals(Territorio.BOSQUE)) {
                turnosQueContienenBosque.add(turno);
            }
        }
        List<Casilla> casillasBosqueConBorde = new ArrayList<Casilla>();
        for(Accion accion: acciones) {
            if(turnosQueContienenBosque.contains(accion.getTurno()) && accion.getCasilla().getBorde()==true) {
                casillasBosqueConBorde.add(accion.getCasilla());
            }
        }
        
        List<Casilla> casillasBosque = new ArrayList<Casilla>();
        for(Accion accion: acciones) {
            if(turnosQueContienenBosque.contains(accion.getTurno())) {
                casillasBosque.add(accion.getCasilla());
            }
        }
        Integer puntos = 0;
        Boolean res = false;
        Integer casillaOpuesta;
        List<Integer> idCasillasConBorde = new ArrayList<Integer>();
        for(Casilla casilla : casillasBosqueConBorde) {
            idCasillasConBorde.add(casilla.getId());
        }
        List<Casilla> casillasBosqueAux = new ArrayList<>();
        for(Casilla casilla : casillasBosqueConBorde) {
            casillasBosqueAux.addAll(casillasBosque);
            casillaOpuesta = casilla.getCasillaOpuesta();
            if(idCasillasConBorde.contains(casillaOpuesta)) {
                casillasBosqueAux.remove(casilla);
                res = detectarCaminoACasillaOpuesta(casilla, casilla, res, casillasBosqueAux);
            }
            if(res) {
                puntos+=5; // Cuenta el camino de ida y el de vuelta, que sería +10
            }
            res=false;
            casillasBosqueAux.clear();
        }
        return puntos;
    }

    private Boolean detectarCaminoACasillaOpuesta(Casilla casillaOriginal, Casilla casillaIteradora, Boolean res, List<Casilla> casillasBosqueAux) {
        for (Casilla casillaB: casillaIteradora.getAdyacencia()){
            if(casillaB.getId().equals(casillaOriginal.getCasillaOpuesta())) {
                res = true;
                break;
            }
            if(casillasBosqueAux.contains(casillaB)){
                casillasBosqueAux.remove(casillaB);
                res = detectarCaminoACasillaOpuesta(casillaOriginal, casillaB, res, casillasBosqueAux);
            }
        }
        return res;
    }
    
}
