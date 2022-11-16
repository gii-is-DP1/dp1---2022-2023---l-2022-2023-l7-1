package org.springframework.samples.petclinic.jugador;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.samples.petclinic.model.BaseEntity;
import org.springframework.samples.petclinic.tablero.Tablero;
import org.springframework.samples.petclinic.user.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "jugador")
public class Jugador extends BaseEntity{
    
    @NotEmpty
    @Value("False")
    @Column(name = "activo")
    private Boolean activo;

    @NotEmpty
    @Column(name = "nombrereino")
    private String nombreReino;

    @NotEmpty
    @Max(3)
    @Min(0)
    @Column(name = "usosterritorio1")
    private Integer usosTerritorio1;

    @NotEmpty
    @Max(3)
    @Min(0)
    @Column(name = "usosterritorio2")
    private Integer usosTerritorio2;

    @NotEmpty
    @Max(3)
    @Min(0)
    @Column(name = "usosterritorio3")
    private Integer usosTerritorio3;

    @NotEmpty
    @Max(3)
    @Min(0)
    @Column(name = "usosterritorio4")
    private Integer usosTerritorio4;

    @NotEmpty
    @Max(3)
    @Min(0)
    @Column(name = "usosterritorio5")
    private Integer usosTerritorio5;

    @NotEmpty
    @Max(3)
    @Min(0)
    @Column(name = "usosterritorio6")
    private Integer usosTerritorio6;

    @NotEmpty
    @Column(name = "anfitrión")
    private Boolean anfitrion;

    @NotEmpty
    @ManyToOne(optional = false)
    @JoinColumn(name = "usuario_id")
    private User idUsuario;

    @NotEmpty
    @OneToOne(optional = false)
    @JoinColumn(name = "tablero_id")
    private Tablero idTablero;

}
