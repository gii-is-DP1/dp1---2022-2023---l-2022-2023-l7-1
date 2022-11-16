package org.springframework.samples.petclinic.turnos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.springframework.samples.petclinic.model.BaseEntity;
import org.springframework.samples.petclinic.util.Territorio;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "turnos")
public class Turno extends BaseEntity{
    
    @NotEmpty
    @Column(name = "idJugador")
    private Integer idJugador;

    @NotEmpty
    @Column(name = "idPartida")
    private Integer idPartida;

    @NotEmpty
    @Column(name = "numTerritoriosJ1")
    private Integer numTerritoriosJ1;

    @Column(name = "numTerritoriosJ2")
    private Integer numTerritoriosJ2;

    @Column(name = "numTerritoriosJ3")
    private Integer numTerritoriosJ3;

    @Column(name = "numTerritoriosJ4")
    private Integer numTerritoriosJ4;

    @NotEmpty
    private Territorio territorio;

    public String toString(){
        return "Turno"+ getId()+" Territorio"+  getTerritorio();
    }
}
