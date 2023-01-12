package org.springframework.samples.kingdommaps.criterios;

import java.util.List;

import org.springframework.samples.kingdommaps.accion.Accion;
import org.springframework.samples.kingdommaps.turnos.Turno;


public interface StrategyInterface {

    Integer calcularCriterio(List<Accion> acciones, List<Turno> turnos);
}
