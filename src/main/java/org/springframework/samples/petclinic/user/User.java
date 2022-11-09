package org.springframework.samples.petclinic.user;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User{
    @Id
	@Column(name = "username")
    @NotEmpty
    String username;

    @Column(name = "name")
    @NotEmpty
    String name;

    @Column(name = "lastName")
    @NotEmpty
    String lastName;
    
    

    @Column(name = "password")
    @NotEmpty
    String password;

    @Column(name = "birthDate")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @NotNull
    LocalDate birthDate;

    @Column(name = "email")
    @NotEmpty
    String email;

    @Column(name = "phone")
    @NotEmpty
    @Digits(fraction = 0, integer = 10)
    String phone;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	private Set<Authorities> authorities;

}
