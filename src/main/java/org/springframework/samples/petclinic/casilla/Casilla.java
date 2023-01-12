package org.springframework.samples.petclinic.casilla;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.springframework.samples.petclinic.model.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "casillas")
public class Casilla extends BaseEntity{
    

    @NotBlank
    Boolean borde;

    @NotBlank
    Boolean poder1;

    @NotBlank
    Boolean poder2;
    
    @NotBlank
    Double fila;

    @NotBlank
    Double columna;

    @ManyToMany(fetch = FetchType.EAGER)
    List<Casilla> adyacencia;

    @Column(name = "casilla_opuesta_id")
    Integer casillaOpuesta;
    
    @Override
    public String toString(){
        return "Id: "+id+" esBorde: "+borde+" esPoder1: "+poder1+" esPoder2: "+poder2;
    }
}
