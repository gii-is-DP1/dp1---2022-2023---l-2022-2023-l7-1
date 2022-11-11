package org.springframework.samples.petclinic.accion;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.springframework.samples.petclinic.casilla.Casilla;
import org.springframework.samples.petclinic.model.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "acciones")
public class Accion extends BaseEntity{

    @NotEmpty
    @Column(name = "idTurno")
    private Integer idTurno;

    @NotEmpty
    @Column(name = "idJugador")
    private Integer idjugador;

    @ManyToOne
    private Casilla idCasilla;
    
}
