package org.springframework.samples.petclinic.tablero;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.samples.petclinic.model.BaseEntity;
import org.springframework.samples.petclinic.model.NamedEntity;
import org.springframework.samples.petclinic.util.Territorio;

import lombok.Getter;
import lombok.Setter;
import lombok.Builder.Default;


@Getter
@Setter
@Entity
@Table(name = "tablero")
public class Tablero extends BaseEntity{
    
    @NotEmpty
    @Column(name = "idpartida")
    private Integer idPartida;

    @NotEmpty
    @Column(name = "idpoderes")
    private Integer idPoderes;

    @NotEmpty
    @Column(columnDefinition = "integer default 0")
    private Integer puntos;
}
