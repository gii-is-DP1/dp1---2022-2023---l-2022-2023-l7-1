package org.springframework.samples.petclinic.logros;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.samples.petclinic.model.BaseEntity;
import org.springframework.samples.petclinic.user.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "logros")
public class Logro extends BaseEntity{

    
    @NotEmpty(message="No debe estar vacío")
    private String titulo;

    @NotEmpty(message="No debe estar vacío")
    private String descripcion;

    @NotEmpty(message="No debe estar vacío")
    private String logo;

    @NotNull(message="No debe estar vacío")
    private Integer reqPuntos;

    @ManyToOne
    private User user;

    public String getReqDescripcion(){
        return descripcion.replace("<puntos>",String.valueOf(reqPuntos));
    }

}
