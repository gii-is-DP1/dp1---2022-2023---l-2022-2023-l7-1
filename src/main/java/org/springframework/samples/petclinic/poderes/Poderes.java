package org.springframework.samples.petclinic.poderes;

import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.samples.petclinic.jugador.Jugador;
import org.springframework.samples.petclinic.model.BaseEntity;
import org.springframework.samples.petclinic.partida.Partida;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "poderes")
public class Poderes extends BaseEntity{

    @OneToOne(optional = false)
    @JoinColumn(name = "partidaid")
    @NotEmpty
    Partida idpartida;

    @OneToOne(optional = false)
    @JoinColumn(name = "jugadorid")
    @NotEmpty
    Jugador idjugador;

    @Column(name = "poder+-1")
    @NotEmpty
    @Min(0)
    @Max(6)
    @Value("0")
    Integer poder1; //Si lo pongo con el +-1 da error ya que lo toma como operacion

    @Column(name = "poder?")
    @NotEmpty
    @Value("0")
    Integer poder2; //Igual q el otro poder
  


    
}
