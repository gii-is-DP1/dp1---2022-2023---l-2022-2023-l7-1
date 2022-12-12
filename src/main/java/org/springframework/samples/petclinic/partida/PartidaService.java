package org.springframework.samples.petclinic.partida;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.h2.store.RangeInputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.accion.AccionService;
import org.springframework.samples.petclinic.casilla.Casilla;
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

   AccionService accionService;
   
   
   @Autowired
   public PartidaService(PartidaRepository partidaRepo, TableroService tableroService, TurnoService turnoService, AccionService accionService) {
    this.partidaRepo = partidaRepo;

    this.tableroService = tableroService;

    this.turnoService = turnoService;
    this.accionService = accionService;
   }

   public Partida getPartidaById(Integer id){
      return partidaRepo.findById(id).get();
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
      p.setTableros(List.of(tablero));
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

      turnoService.saveTurno(turno);
  
      return List.of(p.getId(),turno.getId());

   }

   public List<Integer> casillasPorDibujar(Integer tableroId, Integer partidaId){
      List<Casilla> casillasDibujadas = new ArrayList<>();
      casillasDibujadas = accionService.getIdAcciones(partidaId,tableroId).stream().map(x-> x.getCasilla()).collect(Collectors.toList());
      List<Casilla> casillasAdyacentes = new ArrayList<> ();
      if(casillasDibujadas.isEmpty()){
         return IntStream.range(1, 68).boxed().collect(Collectors.toList());
      }

      casillasAdyacentes = casillasDibujadas.stream().map(x->x.getAdyacencia()).flatMap(List::stream).collect(Collectors.toList());
      casillasAdyacentes.removeAll(casillasDibujadas);
      return casillasAdyacentes.stream().map(x-> x.getId()).collect(Collectors.toList());
   }
   
   
}
