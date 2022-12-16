package org.springframework.samples.petclinic.turnos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.samples.petclinic.model.BaseEntity;
import org.springframework.samples.petclinic.tablero.Tablero;
import org.springframework.samples.petclinic.util.Territorio;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "turnos")
public class Turno extends BaseEntity{

    @Column(name = "numTerritoriosJ1")
    private Integer numTerritoriosJ1;

    @Column(name = "numTerritoriosJ2")
    private Integer numTerritoriosJ2;

    @Column(name = "numTerritoriosJ3")
    private Integer numTerritoriosJ3;

    @Column(name = "numTerritoriosJ4")
    private Integer numTerritoriosJ4;

    private Territorio territorio;

    @ManyToOne
    private Tablero tablero;


    @Override
    public String toString() {
        return "{" +
            " numTerritoriosJ1='" + getNumTerritoriosJ1() + "'" +
            ", numTerritoriosJ2='" + getNumTerritoriosJ2() + "'" +
            ", numTerritoriosJ3='" + getNumTerritoriosJ3() + "'" +
            ", numTerritoriosJ4='" + getNumTerritoriosJ4() + "'" +
            ", territorio='" + getTerritorio() + "'" +
            "}";
    }
    
}
