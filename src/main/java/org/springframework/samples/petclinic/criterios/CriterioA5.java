package org.springframework.samples.petclinic.criterios;

import java.util.ArrayList;
import java.util.List;

import org.springframework.samples.petclinic.accion.Accion;
import org.springframework.samples.petclinic.casilla.Casilla;
import org.springframework.samples.petclinic.turnos.Turno;
import org.springframework.samples.petclinic.util.Territorio;

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

        List<Casilla> casillasDondeEmpiezaCadaGrupo = eliminarCasillasAdyacentesPoblado(casillasPoblado);

        return casillasDondeEmpiezaCadaGrupo.size()*5;
    }

    private List<Casilla> eliminarCasillasAdyacentesPoblado(List<Casilla> casillasPoblado) { // TODO Queremos hacer una recursiva que elimine a las casillas adyacentes dada una, para que solo nos que un poblado por grupo
        return null;
    }
    
}
