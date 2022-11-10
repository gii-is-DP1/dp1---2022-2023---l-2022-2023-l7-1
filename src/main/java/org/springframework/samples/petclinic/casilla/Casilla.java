package org.springframework.samples.petclinic.casilla;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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

    @ManyToMany
    Collection<Casilla> adyacencia;
}
