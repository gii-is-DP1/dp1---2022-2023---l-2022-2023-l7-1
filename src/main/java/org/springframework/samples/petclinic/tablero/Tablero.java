package org.springframework.samples.petclinic.tablero;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.samples.petclinic.model.BaseEntity;
import org.springframework.samples.petclinic.model.NamedEntity;
import org.springframework.samples.petclinic.partida.Partida;
import org.springframework.samples.petclinic.user.User;
import org.springframework.samples.petclinic.util.Territorio;

import lombok.Getter;
import lombok.Setter;
import lombok.Builder.Default;


@Getter
@Setter
@Entity
@Table(name = "tablero")
public class Tablero extends BaseEntity{
    
    @NotNull
    @ManyToOne(optional = false, targetEntity = Partida.class)
    private Partida partida;
    
    @Column(columnDefinition = "integer default 0")
    private Integer poder1 = 0;

    @Column(columnDefinition = "integer default 0")
    private Integer poder2 = 0;

    @NotNull
    @Column(columnDefinition = "integer default 0")
    private Integer puntos;

    @NotNull
    @Column(name = "en_curso")
    private Boolean partidaEnCurso;

    @NotNull
    @Column(name = "creada")
    private Boolean partidaCreada;

    @NotNull
    @ManyToOne(optional = false)
    private User user;

    private Integer Usos0;
    
    private Integer Usos1;
    
    private Integer Usos2;
    
    private Integer Usos3;
    
    private Integer Usos4;

    private Integer Usos5;
}
