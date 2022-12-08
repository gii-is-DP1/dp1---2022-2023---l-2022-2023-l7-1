package org.springframework.samples.petclinic.partida;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.tablero.Tablero;
import org.springframework.samples.petclinic.tablero.TableroRepository;
import org.springframework.samples.petclinic.tablero.TableroService;
import org.springframework.samples.petclinic.turnos.Turno;
import org.springframework.samples.petclinic.turnos.TurnoRepository;
import org.springframework.samples.petclinic.turnos.TurnoService;
import org.springframework.samples.petclinic.user.User;
import org.springframework.stereotype.Service;

@Service
public class PartidaService {
    
   PartidaRepository partidaRepo;

   TableroService tableroService;

   TurnoService turnoService;
   
   @Autowired
   public PartidaService(PartidaRepository partidaRepo, TableroService tableroService, TurnoService turnoService) {
    this.partidaRepo = partidaRepo;

    this.tableroService = tableroService;

    this.turnoService = turnoService;
   }

   public List<Integer> crearPartidaSolitario(User user){
      Turno turno = new Turno();

      Tablero tablero = new Tablero();

      Partida p = new Partida();

      p.setDateTime(LocalDateTime.now());
      p.setIdCriterioA1(1);
      p.setIdCriterioA2(2);
      p.setIdCriterioB1(1);
      p.setIdCriterioB2(2);
      partidaRepo.save(p);

      tablero.setPartida(p);
      tablero.setUser(user);
      tablero.setPuntos(0);
      tableroService.saveTablero(tablero);

      turno.setTablero(tablero);
      turnoService.saveTurno(turno);
  
      return List.of(p.getId(),turno.getId());
   }
}
