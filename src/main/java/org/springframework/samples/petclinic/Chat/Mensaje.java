package org.springframework.samples.petclinic.Chat;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.samples.petclinic.model.BaseEntity;
import org.springframework.samples.petclinic.user.User;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Mensaje extends BaseEntity{

    @NotBlank
    @Size(min=1, max=240)
    private String contenido;

    @ManyToOne
    private User user;

}
