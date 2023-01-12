package org.springframework.samples.kingdommaps.logros;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.samples.kingdommaps.model.BaseEntity;
import org.springframework.samples.kingdommaps.user.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "logros")
public class Logro extends BaseEntity{

    
    @NotEmpty
    private String titulo;

    @NotEmpty
    private String descripcion;

    @NotEmpty
    private String logo;

    @NotNull
    private Integer reqPuntos;

    @ManyToOne
    private User user;

    public String getReqDescripcion(){
        return descripcion.replace("<puntos>",String.valueOf(reqPuntos));
    }

}
