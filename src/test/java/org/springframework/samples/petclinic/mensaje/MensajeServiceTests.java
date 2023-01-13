package org.springframework.samples.petclinic.mensaje;


import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.chat.Mensaje;
import org.springframework.samples.petclinic.chat.MensajeService;
import org.springframework.samples.petclinic.user.User;
import org.springframework.samples.petclinic.user.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class MensajeServiceTests {

    @Autowired
    protected MensajeService mensajeService;

    @Autowired
    protected UserService userService;

    protected User user = new User();

    protected Mensaje msj1 = new Mensaje();

    protected Mensaje msj2 = new Mensaje();

    protected Mensaje msj3 = new Mensaje();
    
    @BeforeEach
    public void setUsuarioYMensajes( ){
        user.setBirthDate(LocalDate.of(2002, 1, 2));
        user.setEmail("diegarlin@user.com");
        user.setLastName("Linares");
        user.setName("Diego");
        user.setUsername("diegarlin");
        user.setEnabled(true);
        user.setPassword("password");
        user.setPhone("633787878");

        userService.save(user);

        msj1.setContenido("msj1");
        msj1.setId(1);
        msj1.setUser(user);

        msj2.setContenido("msj2");
        msj2.setId(2);
        msj2.setUser(user);
        
    }

    

    @Test
    @Transactional
    public void shouldFindMensajesByUsername(){
        mensajeService.saveMensaje(msj1);
        mensajeService.saveMensaje(msj2);
        List<Mensaje> mensajes = mensajeService.getMensajesByUsername("diegarlin");
        assertThat(mensajes.size()).isEqualTo(2);
        assertThat(mensajes.get(0).getContenido()).isEqualTo("msj1");
    }

    @Test
    @Transactional
    public void shouldGetAll(){
        mensajeService.saveMensaje(msj1);
        mensajeService.saveMensaje(msj2);
        List<Mensaje> mensajes = mensajeService.getAll();
        assertThat(mensajes.size()).isEqualTo(2);
    }

    @Test
    @Transactional
    public void shouldSaveMensaje(){
        mensajeService.saveMensaje(msj1);
        mensajeService.saveMensaje(msj2);
        List<Mensaje> mensajes1 = mensajeService.getAll();
        assertThat(mensajes1.size()).isEqualTo(2);

        msj3.setId(3);
        msj3.setContenido("msj3");
        msj3.setUser(user);
        mensajeService.saveMensaje(msj3);

        List<Mensaje> mensajes2 = mensajeService.getAll();
        assertThat(mensajes2.size()).isEqualTo(3);

    }


    @Test
    @Transactional
    public void shouldGetUltimoId(){
        mensajeService.saveMensaje(msj1);
        mensajeService.saveMensaje(msj2);
        List<Mensaje> mensajes = mensajeService.getAll();
        Integer res = mensajes.get(0).getId()+2;
        Integer ultimo = mensajeService.getUltimoId();
        assertThat(ultimo).isEqualTo(res);
    }

    

}
