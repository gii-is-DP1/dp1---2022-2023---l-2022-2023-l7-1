package org.springframework.samples.petclinic.logros;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
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
    @NotNull
    private String titulo;

    @NotNull
    private String descripcion;

    @NotNull
    private String logo;

    @NotNull
    private Integer reqPuntos;

    @ManyToOne
    private User user;

    public String getReqDescripcion(){
        return descripcion.replace("<puntos>",String.valueOf(reqPuntos));
    }

}
