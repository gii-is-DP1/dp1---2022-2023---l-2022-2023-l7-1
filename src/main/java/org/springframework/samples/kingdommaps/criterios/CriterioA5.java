package org.springframework.samples.kingdommaps.criterios;

import java.util.ArrayList;
import java.util.List;

import org.springframework.samples.kingdommaps.accion.Accion;
import org.springframework.samples.kingdommaps.casilla.Casilla;
import org.springframework.samples.kingdommaps.turnos.Turno;
import org.springframework.samples.kingdommaps.util.Territorio;

public class CriterioA5 implements StrategyInterface{

    @Override
    public Integer calcularCriterio(List<Accion> acciones, List<Turno> turnos) {

        List<Turno> turnosQueContienenPoblado = new ArrayList<Turno>();
        for(Turno turno: turnos) {
            if(turno.getTerritorio().equals(Territorio.POBLADO)) {
                turnosQueContienenPoblado.add(turno);
            }
        }
        List<Casilla> casillasPoblado = new ArrayList<Casilla>();
        for(Accion accion: acciones) {
            if(turnosQueContienenPoblado.contains(accion.getTurno())) {
                casillasPoblado.add(accion.getCasilla());
            }
        }

        Integer numeroDeGruposDistintos = getNumeroDeGruposDistintos(casillasPoblado);
        return numeroDeGruposDistintos*5;
    }

    private Integer getNumeroDeGruposDistintos(List<Casilla> casillasPoblado) { 
        Integer res=0;
        if(casillasPoblado.isEmpty()){
            return res;
        } else {
            res++;
            Casilla casilla = casillasPoblado.get(0);
            casillasPoblado.remove(casilla);
            res =  getNumeroDeGruposDistintos_aux(casillasPoblado,res,casilla);
            return res;
        }
    }

    private Integer getNumeroDeGruposDistintos_aux(List<Casilla> casillasPoblado,
     Integer casillasFinales, Casilla casilla) {
        casillasPoblado = eliminarCasillasAdyacentesPoblado(casillasPoblado, casilla);
        if(!casillasPoblado.isEmpty()){
            Casilla casilla2 = casillasPoblado.get(0);
            casillasPoblado.remove(casilla2);
            casillasFinales++;
            casillasFinales = getNumeroDeGruposDistintos_aux(casillasPoblado, casillasFinales, casilla2);
        }
        return casillasFinales;
    }

    private List<Casilla> eliminarCasillasAdyacentesPoblado(List<Casilla> casillasPoblado, Casilla casilla) {
        for (Casilla casillaP: casilla.getAdyacencia()){
            if(casillasPoblado.contains(casillaP)){
                casillasPoblado.remove(casillaP);
                eliminarCasillasAdyacentesPoblado(casillasPoblado, casillaP);
            }
        }
        return casillasPoblado;
    }
    
}
