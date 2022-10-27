package org.springframework.samples.petclinic.jugador;
package org.springframework.samples.petclinic.Jugador;
package org.springframework.samples.petclinic.Jugador;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.springframework.samples.petclinic.model.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "jugadores")
public class Jugador extends BaseEntity{
    
    @NotEmpty
    @Value("False")
    @Column(name = "activo")
    private Boolean activo;

    @NotEmpty
    @Column(name = "nombreReino")
    private String nombreReino;

    @NotEmpty
    @Max(4)
    @Min(0)
    @Column(name = "usosTerritorio")
    private Integer usosTerritorio;

    @NotEmpty
    @Column(name = "anfitrión")
    private Boolean anfitrion;

    @NotEmpty
    @Column(name = "idUsuario")
    private Integer iddUsuario;

}
