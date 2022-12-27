package org.springframework.samples.petclinic.criterios;

import java.util.ArrayList;
import java.util.List;

import org.springframework.samples.petclinic.accion.Accion;
import org.springframework.samples.petclinic.casilla.Casilla;
import org.springframework.samples.petclinic.turnos.Turno;
import org.springframework.samples.petclinic.util.Territorio;

public class CriterioA3 implements StrategyInterface{

    @Override
    public Integer calcularCriterio(List<Accion> acciones, List<Turno> turnos) {

        List<Turno> turnosQueContienenBosque = new ArrayList<Turno>();
        for(Turno turno: turnos) {
            if(turno.getTerritorio().equals(Territorio.BOSQUE)) {
                turnosQueContienenBosque.add(turno);
            }
        }
        List<Casilla> casillasBosque = new ArrayList<Casilla>();
        for(Accion accion: acciones) {
            if(turnosQueContienenBosque.contains(accion.getTurno())) {
                casillasBosque.add(accion.getCasilla());
            }
        }

        List<Integer> cantidadDeBosquesEnCadaGrupo = getCantidadDeBosquesEnCadaGrupo(casillasBosque);
        Integer puntos = 100;
        if(cantidadDeBosquesEnCadaGrupo.size()>=2) {
            for(Integer cantidadDeBosques : cantidadDeBosquesEnCadaGrupo) {
                if (cantidadDeBosques < puntos) {
                    puntos = cantidadDeBosques;
                }
            }
            return puntos*2;
        } else {
            return 0;
        }
    }

    private List<Integer> getCantidadDeBosquesEnCadaGrupo(List<Casilla> casillasBosque) {
        List<Integer> res = new ArrayList<Integer>();
        if(casillasBosque.isEmpty()){
            return res;
        } else {
            Casilla casilla = casillasBosque.get(0);
            List<Casilla> grupoDeCasillasBosque = new ArrayList<Casilla>();
            grupoDeCasillasBosque.add(casilla);
            casillasBosque.remove(casilla);
            return getCantidadDeBosquesEnCadaGrupo_aux(casillasBosque, grupoDeCasillasBosque, res, casilla);
        }
    }

    private List<Integer> getCantidadDeBosquesEnCadaGrupo_aux(List<Casilla> casillasBosque,
        List<Casilla> grupoDeCasillasBosque, List<Integer> res, Casilla casilla) {

        grupoDeCasillasBosque = getCasillasAdyacentesBosque(casillasBosque, grupoDeCasillasBosque, casilla);
        casillasBosque = eliminarCasillasAdyacentesBosque(casillasBosque, casilla);
        res.add(grupoDeCasillasBosque.size());
        grupoDeCasillasBosque.clear();
        
        if(!casillasBosque.isEmpty()){
            Casilla casilla2 = casillasBosque.get(0);
            casillasBosque.remove(casilla2);
            getCantidadDeBosquesEnCadaGrupo_aux(casillasBosque, grupoDeCasillasBosque, res, casilla2);
        }

        return res;
    }

    private List<Casilla> eliminarCasillasAdyacentesBosque(List<Casilla> casillasBosque, Casilla casilla) {
        for (Casilla casillaB: casilla.getAdyacencia()){
            if(casillasBosque.contains(casillaB)){
                casillasBosque.remove(casillaB);
                eliminarCasillasAdyacentesBosque(casillasBosque, casillaB);
            }
        }
        return casillasBosque;
    }

    private List<Casilla> getCasillasAdyacentesBosque(List<Casilla> casillasBosque, List<Casilla> grupoDeCasillasBosque, Casilla casilla) {
        for (Casilla casillaB: casilla.getAdyacencia()){
            if(casillasBosque.contains(casillaB)){
                grupoDeCasillasBosque.add(casillaB);
                getCasillasAdyacentesBosque(casillasBosque, grupoDeCasillasBosque, casillaB);
            }
        }
        return grupoDeCasillasBosque;
    }

}
