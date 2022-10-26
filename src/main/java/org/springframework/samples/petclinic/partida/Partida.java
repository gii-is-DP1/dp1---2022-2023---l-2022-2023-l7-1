package org.springframework.samples.petclinic.partida;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "partida")
public class Partida {
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Integer id;

    @Column(name = "Fecha y Hora")
    @NotEmpty
    @DateTimeFormat
    LocalDateTime dateTime;
    /*  Creemos que sobre ya que se relaciona por tablero
    @Column(name = "IdJugador1")
    @NotEmpty
    Integer idJ1;
    
    @Column(name = "IdJugador2")
    Integer idJ2;

    @Column(name = "IdJugador3")
    Integer idJ3;

    @Column(name = "IdJugador4")
    Integer idJ4;
    */
    @Column(name = "IdTablero1")
    @NotEmpty
    Integer idTablero1;
    
    @Column(name = "IdTablero2")
    Integer idTablero2;

    @Column(name = "IdTablero3")
    Integer idTablero3;

    @Column(name = "IdTablero4")
    Integer idTablero4;
    /*Este igual */
    @Column(name = "PuntosTablero1")
    @NotEmpty
    Integer PuntosTablero1;
    
    @Column(name = "PuntosTablero2")
    Integer PuntosTablero2;

    @Column(name = "PuntosTablero3")
    Integer PuntosTablero3;

    @Column(name = "PuntosTablero4")
    Integer PuntosTablero4;

    @Column(name = "IdCriterioA1")
    @NotEmpty
    Integer idCriterioA1;

    @Column(name = "IdCriterioA2")
    @NotEmpty
    Integer idCriterioA2;

    @Column(name = "IdCriterioB1")
    @NotEmpty
    Integer idCriterioB1;

    @Column(name = "IdCriterioB2")
    @NotEmpty
    Integer idCriterioB2;
    
}
