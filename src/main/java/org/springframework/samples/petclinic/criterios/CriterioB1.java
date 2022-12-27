package org.springframework.samples.petclinic.criterios;

import java.util.ArrayList;
import java.util.List;

import org.springframework.samples.petclinic.accion.Accion;
import org.springframework.samples.petclinic.casilla.Casilla;
import org.springframework.samples.petclinic.turnos.Turno;
import org.springframework.samples.petclinic.util.Territorio;


public class CriterioB1 implements StrategyInterface{

    @Override
    public Integer calcularCriterio(List<Accion> acciones, List<Turno> turnos) {

        List<Turno> turnosQueContienenMontana = new ArrayList<Turno>();
        for(Turno turno: turnos) {
            if(turno.getTerritorio().equals(Territorio.MONTANA)) {
                turnosQueContienenMontana.add(turno);
            }
        }
        List<Casilla> casillasMontana = new ArrayList<Casilla>();
        for(Accion accion: acciones) {
            if(turnosQueContienenMontana.contains(accion.getTurno())) {
                casillasMontana.add(accion.getCasilla());
            }
        }

        List<Integer> cantidadDeMontanasEnCadaGrupo = getCantidadDeMontanasEnCadaGrupo(casillasMontana);
        Integer puntos = 0;
        if(cantidadDeMontanasEnCadaGrupo.size()>=2) {
            for(Integer cantidadDeMontanas : cantidadDeMontanasEnCadaGrupo) {
                if (cantidadDeMontanas > puntos) {
                    puntos = cantidadDeMontanas;
                }
            }
            return puntos;
        } else {
            return 0;
        }
    }

    private List<Integer> getCantidadDeMontanasEnCadaGrupo(List<Casilla> casillasMontana) {
        List<Integer> res = new ArrayList<Integer>();
        if(casillasMontana.isEmpty()){
            return res;
        } else {
            Casilla casilla = casillasMontana.get(0);
            List<Casilla> grupoDeCasillasMontana = new ArrayList<Casilla>();
            grupoDeCasillasMontana.add(casilla);
            casillasMontana.remove(casilla);
            return getCantidadDeMontanasEnCadaGrupo_aux(casillasMontana, grupoDeCasillasMontana, res, casilla);
        }
    }

    private List<Integer> getCantidadDeMontanasEnCadaGrupo_aux(List<Casilla> casillasMontana,
        List<Casilla> grupoDeCasillasMontana, List<Integer> res, Casilla casilla) {

        grupoDeCasillasMontana = getCasillasAdyacentesMontana(casillasMontana, grupoDeCasillasMontana, casilla);
        casillasMontana = eliminarCasillasAdyacentesMontana(casillasMontana, casilla);
        res.add(grupoDeCasillasMontana.size());
        grupoDeCasillasMontana.clear();
        
        if(!casillasMontana.isEmpty()){
            Casilla casilla2 = casillasMontana.get(0);
            casillasMontana.remove(casilla2);
            getCantidadDeMontanasEnCadaGrupo_aux(casillasMontana, grupoDeCasillasMontana, res, casilla2);
        }

        return res;
    }

    private List<Casilla> eliminarCasillasAdyacentesMontana(List<Casilla> casillasMontana, Casilla casilla) {
        for (Casilla casillaM: casilla.getAdyacencia()){
            if(casillasMontana.contains(casillaM)){
                casillasMontana.remove(casillaM);
                eliminarCasillasAdyacentesMontana(casillasMontana, casillaM);
            }
        }
        return casillasMontana;
    }

    private List<Casilla> getCasillasAdyacentesMontana(List<Casilla> casillasMontana, List<Casilla> grupoDeCasillasMontana, Casilla casilla) {
        for (Casilla casillaM: casilla.getAdyacencia()){
            if(casillasMontana.contains(casillaM)){
                grupoDeCasillasMontana.add(casillaM);
                getCasillasAdyacentesMontana(casillasMontana, grupoDeCasillasMontana, casillaM);
            }
        }
        return grupoDeCasillasMontana;
    }
    
}
