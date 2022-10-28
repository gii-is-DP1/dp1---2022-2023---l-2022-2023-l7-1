package org.springframework.samples.kingdomMaps.usuario;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "usuario")
public class Usuario {
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Integer id;

    @Column(name = "name")
    @NotEmpty
    String name;

    @Column(name = "lastName")
    @NotEmpty
    String lastName;
    
    @Column(name = "username")
    @NotEmpty
    String username;

    @Column(name = "password")
    @NotEmpty
    String password;

    @Column(name = "birthDate")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @NotEmpty
    LocalDate birthDate;

    @Column(name = "email")
    @NotEmpty
    String email;

    @Column(name = "phone")
    @NotEmpty
    @Digits(fraction = 0, integer = 10)
    String phone;

    
}
