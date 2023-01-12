package org.springframework.samples.petclinic.accion;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.samples.petclinic.casilla.Casilla;
import org.springframework.samples.petclinic.model.BaseEntity;
import org.springframework.samples.petclinic.tablero.Tablero;
import org.springframework.samples.petclinic.turnos.Turno;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "acciones")
public class Accion extends BaseEntity{

    
    @ManyToOne
    private Turno turno;

    @ManyToOne
    private Casilla casilla;

    @ManyToOne
    private Tablero tablero;
    

    @Override
    public String toString() {
        return "{" +
            " turno='" + getTurno() + "'" +
            ", casilla='" + getCasilla() + "'" +
            ", tablero='" + getTablero().getId() + "'" +
            "}";
    }

}
