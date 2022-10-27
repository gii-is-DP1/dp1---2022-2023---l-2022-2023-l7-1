package org.springframework.samples.petclinic.tablero;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.samples.petclinic.model.BaseEntity;
import org.springframework.samples.petclinic.model.NamedEntity;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name = "tableros")
public class Tablero extends BaseEntity{
    
    @NotEmpty
    @Column(name = "idPartida")
    private Integer idPartida;

    @NotEmpty
    @Column(name = "idJugador")
    private Integer idJugador;

    @NotEmpty
    @Column(name = "idPoderes")
    private Integer idPoderes;

    @NotEmpty
    @Value("0")
    private Integer puntos;

    //private Mapa mapa

}