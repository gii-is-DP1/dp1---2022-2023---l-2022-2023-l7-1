package org.springframework.samples.petclinic.partida;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.samples.petclinic.tablero.Tablero;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "partidas")
public class Partida {
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Integer id;

    @Column(name = "fecha")
    @NotNull
    @DateTimeFormat(iso = ISO.DATE_TIME)
    LocalDateTime dateTime;
    
    
    @OneToMany
    @JoinTable(name = "PARTIDAS_TABLEROS",
        joinColumns = @JoinColumn(name="PARTIDA_ID", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "TABLERO_ID", referencedColumnName = "id"))
    List<Tablero> tableros;
    
    @Column(name = "IdCriterioA1")
    @NotNull
    Integer idCriterioA1;

    @Column(name = "IdCriterioA2")
    @NotNull
    Integer idCriterioA2;

    @Column(name = "IdCriterioB1")
    @NotNull
    Integer idCriterioB1;

    @Column(name = "IdCriterioB2")
    @NotNull
    Integer idCriterioB2;
    
}
