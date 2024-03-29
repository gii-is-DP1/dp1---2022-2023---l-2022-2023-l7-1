package org.springframework.samples.petclinic.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * @author japarejo
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	DataSource dataSource;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				// Permisos developers
				.antMatchers("/resources/**","/webjars/**","/h2-console/**").permitAll()
				// Permisos de pagina de inicio
				.antMatchers(HttpMethod.GET, "/","/oups").permitAll()
				// Permisos de users
				.antMatchers("/users/new").permitAll()
				.antMatchers("/users/find").authenticated()
				.antMatchers("/users").authenticated()
				.antMatchers("/user/**").authenticated()
				.antMatchers("/users/{username}/**").authenticated()
				.antMatchers("/users/all").hasAnyAuthority("admin")
				// Permisos de stats
				.antMatchers("/stats").hasAnyAuthority("admin")
				.antMatchers("/stat").authenticated()
				// Permisos de stats
				.antMatchers("/chat/**").authenticated()
				// Permisos de logros
				.antMatchers("/logrosUsuario").authenticated()
				.antMatchers("/logros/**").hasAnyAuthority("admin")
				// Permisos de partidas
				.antMatchers("/partida/**").authenticated()
				.antMatchers("/partidas").authenticated()
				.antMatchers("/partidas/**").authenticated()
				.antMatchers("/partidasUsuario").authenticated()
				// Permisos de amigos
				.antMatchers("/friends/**").authenticated()
				// Permisos de invitacion
				.antMatchers("/invitations/{username}").authenticated()
				.antMatchers("/invitationsGame").authenticated()
				.antMatchers("/invite/{username}").authenticated()
				.antMatchers("/invitate/**").authenticated()
				.antMatchers("/invitateToPlay/**").authenticated()
				.antMatchers("/invitationAccepted/**").authenticated()
				.antMatchers("/invitationToGameAccepted/**").authenticated()
				.antMatchers("/invitationCancelled/**").authenticated()
				.antMatchers("/invitationToGameCancelled/**").authenticated()
				// Permisos de las reglas
				.antMatchers("/rules").permitAll()

				.antMatchers("/session/**").permitAll()
				.antMatchers("/admin/**").hasAnyAuthority("admin")

				.antMatchers("/{username}/crearPartida").hasAnyAuthority("admin", "player")
				.antMatchers("/lobby").hasAnyAuthority("admin", "player")
				.antMatchers("/salaDeEspera").hasAnyAuthority("admin", "player")
				.antMatchers("/**/espera/dado").hasAnyAuthority("admin", "player")
				.antMatchers("/salaDeEsperaJugadores").hasAnyAuthority("admin", "player")
				.antMatchers("/prueba/**").hasAnyAuthority("admin", "player")

				
				.antMatchers("/p").permitAll()
				.anyRequest().denyAll()
				.and()
				 	.formLogin()
				 	/*.loginPage("/login")*/
				 	.failureUrl("/login-error")
				.and()
					.logout()
						.logoutSuccessUrl("/"); 
                // Configuración para que funcione la consola de administración 
                // de la BD H2 (deshabilitar las cabeceras de protección contra
                // ataques de tipo csrf y habilitar los framesets si su contenido
                // se sirve desde esta misma página.
                http.csrf().ignoringAntMatchers("/h2-console/**");
                http.headers().frameOptions().sameOrigin();
	}

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication()
	      .dataSource(dataSource)
	      .usersByUsernameQuery(
	       "select username,password,enabled "
	        + "from users "
	        + "where username = ?")
	      .authoritiesByUsernameQuery(
	       "select username, authority "
	        + "from authorities "
	        + "where username = ?")	      	      
	      .passwordEncoder(passwordEncoder());	
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {	    
		PasswordEncoder encoder =  NoOpPasswordEncoder.getInstance();
	    return encoder;
	}
	
}


