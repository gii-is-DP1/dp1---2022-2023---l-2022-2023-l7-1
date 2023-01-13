package org.springframework.samples.petclinic.chat;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.partida.Partida;
import org.springframework.samples.petclinic.partida.PartidaService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
@TestInstance(Lifecycle.PER_CLASS)
public class ChatServiceTests {

    @Autowired
    protected ChatService chatService;

    @Autowired
    protected PartidaService partidaService;

    protected Chat chat = new Chat();

    protected Partida partida = new Partida();

    protected Mensaje msj1 = new Mensaje();

    protected Mensaje msj2 = new Mensaje();

    @BeforeAll
    public void crear(){
        partida.setId(1);
        partida.setIdCriterioA1(1);
        partida.setIdCriterioA2(2);
        partida.setIdCriterioB1(1);
        partida.setIdCriterioB2(2);
        partida.setDateTime(LocalDateTime.now());

        msj1.setId(1);
        msj1.setContenido("msj1");
        msj2.setId(2);
        msj2.setContenido("msj2");

        chat.setId(1);
        chat.setMensajes(List.of(msj1,msj2));
        chat.setPartida(partida);
        
        
        chatService.save(chat);
        partidaService.savePartida(partida);
    }

    @Test
    @Transactional
    public void shouldGetPartidaById(){
        Chat chat = chatService.getByPartidaId(1);
        assertThat(chat.getId()).isEqualTo(1);
    }

    @Test
    @Transactional
    public void shouldGetAllChats(){
        List<Chat> chats = chatService.getAll();
        
        assertThat(chats.size()).isEqualTo(1);
        assertThat(chats.get(0).getId()).isEqualTo(1);
    }
    
    @Test
    @Transactional
    public void shouldEdit(){
        chat.setId(2);
        chat.setMensajes(List.of(msj1));
        chatService.edit(chat);
        assertThat(chat.getId()).isEqualTo(2);
        assertThat(chat.getMensajes().size()).isEqualTo(1);
    }

    @Test
    @Transactional
    public void shouldDelete(){
        chatService.delete(chat);
        List<Chat> chats = chatService.getAll();

        assertThat(chats).isEmpty();
    }
}
