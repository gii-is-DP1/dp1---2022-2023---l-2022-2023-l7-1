package org.springframework.samples.kingdommaps.accion;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.samples.kingdommaps.casilla.Casilla;
import org.springframework.samples.kingdommaps.model.BaseEntity;
import org.springframework.samples.kingdommaps.tablero.Tablero;
import org.springframework.samples.kingdommaps.turnos.Turno;

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
