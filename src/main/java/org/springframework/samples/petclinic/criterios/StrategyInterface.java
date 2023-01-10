package org.springframework.samples.petclinic.criterios;

import java.util.List;

import org.springframework.samples.petclinic.accion.Accion;
import org.springframework.samples.petclinic.turnos.Turno;


public interface StrategyInterface {

    Integer calcularCriterio(List<Accion> acciones, List<Turno> turnos);
}
