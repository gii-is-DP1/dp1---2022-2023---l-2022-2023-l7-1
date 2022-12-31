package org.springframework.samples.petclinic.partida;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.h2.store.RangeInputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.samples.petclinic.accion.Accion;
import org.springframework.samples.petclinic.accion.AccionService;
import org.springframework.samples.petclinic.casilla.Casilla;
import org.springframework.samples.petclinic.tablero.Tablero;
import org.springframework.samples.petclinic.tablero.TableroRepository;
import org.springframework.samples.petclinic.tablero.TableroService;
import org.springframework.samples.petclinic.turnos.Turno;
import org.springframework.samples.petclinic.turnos.TurnoRepository;
import org.springframework.samples.petclinic.turnos.TurnoService;
import org.springframework.samples.petclinic.user.User;
import org.springframework.samples.petclinic.util.Territorio;
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


   
   /** 
    * @param idPartida
    * @param turno
    * @param listaTerritorios
    *
    * Este método actualiza los usos del tablero dependiendo del Territorio elegido.
    *
    */
   public Integer actualizarUso(Integer idPartida, Turno turno, List<Territorio> listaTerritorios){
            Tablero tablero = getPartidaById(idPartida).getTableros().get(0);
            Integer numTerritorio = listaTerritorios.indexOf(turno.getTerritorio());
            Integer control = null;
            switch(numTerritorio){
                case 0:
                tablero.setUsos0(tablero.getUsos0()-1);
                tableroService.saveTablero(tablero);
                control = tablero.getUsos0();
                break;
                case 1:
                tablero.setUsos1(tablero.getUsos1()-1);
                tableroService.saveTablero(tablero);
                control = tablero.getUsos1();
                break;
                case 2:
                tablero.setUsos2(tablero.getUsos2()-1);
                tableroService.saveTablero(tablero);
                control = tablero.getUsos2();
                break;
                case 3:
                tablero.setUsos3(tablero.getUsos3()-1);
                tableroService.saveTablero(tablero);
                control = tablero.getUsos3();
                break;
                case 4:
                tablero.setUsos4(tablero.getUsos4()-1);
                tableroService.saveTablero(tablero);
                control = tablero.getUsos4();
                break;
                case 5:
                tablero.setUsos5(tablero.getUsos5()-1);
                tableroService.saveTablero(tablero);
                control = tablero.getUsos5();
                break;
            }
            return control;
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
      tablero.setPartidaEnCurso(true);
      tablero.setPartidaCreada(true);
      tableroService.saveTablero(tablero);
      turno.setTablero(tablero);

      turnoService.saveTurno(turno);
  
      return List.of(p.getId(),turno.getId());

   }

   public Set<Integer> casillasPorDibujar(Integer tableroId, Integer partidaId){
      List<Accion> acciones = accionService.getIdAcciones(partidaId,tableroId);
      Set<Integer> todos = IntStream.range(1, 62).boxed().collect(Collectors.toSet());
      Set<Integer> quitar1 = new HashSet();
      if(acciones.size()==1){
         return todos;
      }else{
         List<Integer> casillasDibujadas = new ArrayList<>();
         for(Accion a: acciones){
            if(a.getCasilla() != null){
               quitar1.addAll(a.getCasilla().getAdyacencia().stream().map(x->x.getId()).collect(Collectors.toList()));
               casillasDibujadas.add(a.getCasilla().getId());
            }
         }
         quitar1.removeAll(casillasDibujadas);
         return quitar1;
      }
     
      
   }
   
   
}
