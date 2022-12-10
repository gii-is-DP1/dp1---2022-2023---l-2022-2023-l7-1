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

   public int[] criterioAleatorio(){

      int i = 0, cantidad = 2, rango = 6;
      int arreglo[] = new int[cantidad];

      arreglo[i] = (int)(Math.random()*rango + 1);
      for(i=1; i < cantidad; i++){
         arreglo[i] = (int)(Math.random()*rango + 1);
         for(int j=0; j < 1; j++){
            if(arreglo[i] == arreglo[j]){
               i--;
            }
         }
      }
      return arreglo;
   }

   
   public List<Integer> crearPartidaSolitario(User user){
      int[] criteriosA = criterioAleatorio();
      int[] criteriosB = criterioAleatorio();
      Turno turno = new Turno();

      Tablero tablero = new Tablero();

      Partida p = new Partida();
      
      p.setDateTime(LocalDateTime.now());
      
      p.setIdCriterioA1(criteriosA[0]);
      p.setIdCriterioA2(criteriosA[1]);

      p.setIdCriterioB1(criteriosB[0]);
      p.setIdCriterioB2(criteriosB[1]);
      partidaRepo.save(p);

      tablero.setPartida(p);
      tablero.setUser(user);
      tablero.setPuntos(0);
      tablero.setUsos0(3);
      tablero.setUsos1(3);
      tablero.setUsos2(3);
      tablero.setUsos3(3);
      tablero.setUsos4(3);
      tablero.setUsos5(3);
      tableroService.saveTablero(tablero);

      turno.setTablero(tablero);
      turnoService.saveTurno(turno);
  
      return List.of(p.getId(),turno.getId());

   }

   public void eligeTerritorio(Turno turno){
      
   }
   
}
