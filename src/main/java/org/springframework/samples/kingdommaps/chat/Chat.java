package org.springframework.samples.kingdommaps.chat;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.springframework.samples.kingdommaps.model.BaseEntity;
import org.springframework.samples.kingdommaps.partida.Partida;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Chat extends BaseEntity{

    @OneToMany(cascade = CascadeType.ALL)
    private List<Mensaje> mensajes;

    @OneToOne
    @JoinColumn(name = "partida_id")
    private Partida partida;
    
}
