package org.springframework.samples.petclinic.user;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Value;
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

    @Column(name = "enabled")
    boolean enabled;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	private Set<Authorities> authorities;

    @Column(name = "games_played")
    @Value("0")
    Integer matchesPlayed;

    @Column(name = "games_win")
    @Value("0")
    Integer gamesWin;

    @Transient
    @Digits(fraction = 2, integer = 4)
    Double winRatio;

    @Column(name = "total_points")
    @Value("0")
    Integer totalPoints;

    @Column(name = "max_points")
    @Value("0")
    Integer maxPoints;

    @Column(name = "times_used_power_question")
    @Value("0")
    Integer timesUsedPowerQuestion;

    @Column(name = "times_used_power_one")
    @Value("0")
    Integer timesUsedPower1;






 
    @Transient
    public Double getWinRatio() {
        return (double) (gamesWin / (matchesPlayed-gamesWin));
    }

    

}
