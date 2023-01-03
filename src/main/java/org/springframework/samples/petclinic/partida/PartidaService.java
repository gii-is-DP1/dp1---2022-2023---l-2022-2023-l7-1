package org.springframework.samples.petclinic.partida;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import javax.validation.constraints.Max;

import org.h2.store.RangeInputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.samples.petclinic.accion.Accion;
import org.springframework.samples.petclinic.accion.AccionService;
import org.springframework.samples.petclinic.casilla.Casilla;
import org.springframework.samples.petclinic.criterios.CriterioA1;
import org.springframework.samples.petclinic.criterios.CriterioA2;
import org.springframework.samples.petclinic.criterios.CriterioA3;
import org.springframework.samples.petclinic.criterios.CriterioA4;
import org.springframework.samples.petclinic.criterios.CriterioA5;
import org.springframework.samples.petclinic.criterios.CriterioA6;
import org.springframework.samples.petclinic.criterios.CriterioB1;
import org.springframework.samples.petclinic.criterios.CriterioB2;
import org.springframework.samples.petclinic.criterios.CriterioB3;
import org.springframework.samples.petclinic.criterios.CriterioB4;
import org.springframework.samples.petclinic.criterios.CriterioB5;
import org.springframework.samples.petclinic.criterios.CriterioB6;
import org.springframework.samples.petclinic.criterios.StrategyInterface;
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

   private static StrategyInterface strategy;
   
   
   @Autowired
   public PartidaService(PartidaRepository partidaRepo, TableroService tableroService, TurnoService turnoService, AccionService accionService) {
    this.partidaRepo = partidaRepo;

    this.tableroService = tableroService;

    this.turnoService = turnoService;
    this.accionService = accionService;
   }

   public Integer getMaximo(int a, int b, int c, int d) {
      Integer max = Math.max(a, b);
      max = Math.max(c, max);
      max = Math.max(d, max);
      return max;
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

   
   
   public Set<Integer> casillasDisponibles(Integer idTurno, Integer idTablero){
      List<Accion> accionesTurnoX = accionService.getCasillasPorTurno(idTurno, idTablero);

      //Casillas dibujadas en toda la partida
      List<Integer> casillasDibujadas = accionService.getAccionesByTablero(idTablero).stream().filter(x-> x.getCasilla() != null).map(x-> x.getCasilla().getId())
      .collect(Collectors.toList());


      //Calculamos las adyacentes del turno que queramos
      HashSet<Integer> adyacentes = new HashSet<>();
      for(Accion a: accionesTurnoX){
         if(a.getCasilla() != null){
            adyacentes.addAll(a.getCasilla().getAdyacencia().stream().map(x->x.getId()).collect(Collectors.toList()));
         }
      }
      
      adyacentes.removeAll(casillasDibujadas);
      
      return adyacentes;
   }

   public Set<Integer> casillasDisponiblesPrimeraAccion(Integer idTablero){
      List<Accion> accionesTableroX = accionService.getAccionesByTablero(idTablero);
      
      //Controla el primer turno de la partida
      if(accionesTableroX.size() == 1){
         Set<Integer> todos = IntStream.range(1, 62).boxed().collect(Collectors.toSet());
         return todos;
      }

      
      List<Integer> casillasDibujadas = accionesTableroX.stream().filter(x-> x.getCasilla() != null).map(x-> x.getCasilla().getId())
      .collect(Collectors.toList());

      HashSet<Integer> adyacentes = new HashSet<>();
      for(Accion a: accionesTableroX){
         if(a.getCasilla() != null){
            adyacentes.addAll(a.getCasilla().getAdyacencia().stream().map(x->x.getId()).collect(Collectors.toList()));
         }
      }

      adyacentes.removeAll(casillasDibujadas);
      
      return adyacentes;
   }

   public Integer calcularPuntosTablero(List<Accion> acciones, List<Turno> turnos, Partida partida){
      Integer res1 = 0;

      // A1
      switch(partida.getIdCriterioA1()){
         case(1):{
            strategy = new CriterioA1();
            res1 = res1 + strategy.calcularCriterio(acciones, turnos);
            break;
         }
         case(2):{
            strategy = new CriterioA2();
            res1 = res1 + strategy.calcularCriterio(acciones, turnos);
            break;
         }
         case(3):{
            strategy = new CriterioA3();
            res1 = res1 + strategy.calcularCriterio(acciones, turnos);
            break;
         }
         case(4):{
            strategy = new CriterioA4();
            res1 = res1 + strategy.calcularCriterio(acciones, turnos);
            break;
         }
         case(5):{
            strategy = new CriterioA5();
            res1 = res1 + strategy.calcularCriterio(acciones, turnos);
            break;
         }
         case(6):{
            strategy = new CriterioA6();
            res1 = res1 + strategy.calcularCriterio(acciones, turnos);
            break;
         }
      }

      Integer res2 = 0;
      //A2
      switch(partida.getIdCriterioA2()){
         case(1):{
            strategy = new CriterioA1();
            res2 = res2 + strategy.calcularCriterio(acciones, turnos);
            break;
         }
         case(2):{
            strategy = new CriterioA2();
            res2 = res2 + strategy.calcularCriterio(acciones, turnos);
            break;
         }
         case(3):{
            strategy = new CriterioA3();
            res2 = res2 + strategy.calcularCriterio(acciones, turnos);
            break;
         }
         case(4):{
            strategy = new CriterioA4();
            res2 = res2 + strategy.calcularCriterio(acciones, turnos);
            break;
         }
         case(5):{
            strategy = new CriterioA5();
            res2 = res2 + strategy.calcularCriterio(acciones, turnos);
            break;
         }
         case(6):{
            strategy = new CriterioA6();
            res2 = res2 + strategy.calcularCriterio(acciones, turnos);
            break;
         }
      }

      Integer res3 = 0;
      //B1
      switch(partida.getIdCriterioB1()){
         case(1):{
            strategy = new CriterioB1();
            res3 = res3 + strategy.calcularCriterio(acciones, turnos);
            break;
         }
         case(2):{
            strategy = new CriterioB2();
            res3 = res3 + strategy.calcularCriterio(acciones, turnos);
            break;
         }
         case(3):{
            strategy = new CriterioB3();
            res3 = res3 + strategy.calcularCriterio(acciones, turnos);
            break;
         }
         case(4):{
            strategy = new CriterioB4();
            res3 = res3 + strategy.calcularCriterio(acciones, turnos);
            break;
         }
         case(5):{
            strategy = new CriterioB5();
            res3 = res3 + strategy.calcularCriterio(acciones, turnos);
            break;
         }
         case(6):{
            strategy = new CriterioB6();
            res3 = res3 + strategy.calcularCriterio(acciones, turnos);
            break;
         }
      }

      Integer res4 =0;
      //B2
      switch(partida.getIdCriterioB2()){
         case(1):{
            strategy = new CriterioB1();
            res4 = res4 + strategy.calcularCriterio(acciones, turnos);
            break;
         }
         case(2):{
            strategy = new CriterioB2();
            res4 = res4 + strategy.calcularCriterio(acciones, turnos);
            break;
         }
         case(3):{
            strategy = new CriterioB3();
            res4 = res4 + strategy.calcularCriterio(acciones, turnos);
            break;
         }
         case(4):{
            strategy = new CriterioB4();
            res4 = res4 + strategy.calcularCriterio(acciones, turnos);
            break;
         }
         case(5):{
            strategy = new CriterioB5();
            res4 = res4 + strategy.calcularCriterio(acciones, turnos);
            break;
         }
         case(6):{
            strategy = new CriterioB6();
            res4 = res4 + strategy.calcularCriterio(acciones, turnos);
            break;
         }
      }

      return res1+res2+res3+res4;
   }

   public Integer calcularPuntosPoder2(List<Accion> acciones, List<Turno> turnos, Partida partida){
      Integer res1 = 0;

      // A1
      switch(partida.getIdCriterioA1()){
         case(1):{
            strategy = new CriterioA1();
            res1 = res1 + strategy.calcularCriterio(acciones, turnos);
            break;
         }
         case(2):{
            strategy = new CriterioA2();
            res1 = res1 + strategy.calcularCriterio(acciones, turnos);
            break;
         }
         case(3):{
            strategy = new CriterioA3();
            res1 = res1 + strategy.calcularCriterio(acciones, turnos);
            break;
         }
         case(4):{
            strategy = new CriterioA4();
            res1 = res1 + strategy.calcularCriterio(acciones, turnos);
            break;
         }
         case(5):{
            strategy = new CriterioA5();
            res1 = res1 + strategy.calcularCriterio(acciones, turnos);
            break;
         }
         case(6):{
            strategy = new CriterioA6();
            res1 = res1 + strategy.calcularCriterio(acciones, turnos);
            break;
         }
      }

      Integer res2 = 0;
      //A2
      switch(partida.getIdCriterioA2()){
         case(1):{
            strategy = new CriterioA1();
            res2 = res2 + strategy.calcularCriterio(acciones, turnos);
            break;
         }
         case(2):{
            strategy = new CriterioA2();
            res2 = res2 + strategy.calcularCriterio(acciones, turnos);
            break;
         }
         case(3):{
            strategy = new CriterioA3();
            res2 = res2 + strategy.calcularCriterio(acciones, turnos);
            break;
         }
         case(4):{
            strategy = new CriterioA4();
            res2 = res2 + strategy.calcularCriterio(acciones, turnos);
            break;
         }
         case(5):{
            strategy = new CriterioA5();
            res2 = res2 + strategy.calcularCriterio(acciones, turnos);
            break;
         }
         case(6):{
            strategy = new CriterioA6();
            res2 = res2 + strategy.calcularCriterio(acciones, turnos);
            break;
         }
      }

      Integer res3 = 0;
      //B1
      switch(partida.getIdCriterioB1()){
         case(1):{
            strategy = new CriterioB1();
            res3 = res3 + strategy.calcularCriterio(acciones, turnos);
            break;
         }
         case(2):{
            strategy = new CriterioB2();
            res3 = res3 + strategy.calcularCriterio(acciones, turnos);
            break;
         }
         case(3):{
            strategy = new CriterioB3();
            res3 = res3 + strategy.calcularCriterio(acciones, turnos);
            break;
         }
         case(4):{
            strategy = new CriterioB4();
            res3 = res3 + strategy.calcularCriterio(acciones, turnos);
            break;
         }
         case(5):{
            strategy = new CriterioB5();
            res3 = res3 + strategy.calcularCriterio(acciones, turnos);
            break;
         }
         case(6):{
            strategy = new CriterioB6();
            res3 = res3 + strategy.calcularCriterio(acciones, turnos);
            break;
         }
      }

      Integer res4 =0;
      //B2
      switch(partida.getIdCriterioB2()){
         case(1):{
            strategy = new CriterioB1();
            res4 = res4 + strategy.calcularCriterio(acciones, turnos);
            break;
         }
         case(2):{
            strategy = new CriterioB2();
            res4 = res4 + strategy.calcularCriterio(acciones, turnos);
            break;
         }
         case(3):{
            strategy = new CriterioB3();
            res4 = res4 + strategy.calcularCriterio(acciones, turnos);
            break;
         }
         case(4):{
            strategy = new CriterioB4();
            res4 = res4 + strategy.calcularCriterio(acciones, turnos);
            break;
         }
         case(5):{
            strategy = new CriterioB5();
            res4 = res4 + strategy.calcularCriterio(acciones, turnos);
            break;
         }
         case(6):{
            strategy = new CriterioB6();
            res4 = res4 + strategy.calcularCriterio(acciones, turnos);
            break;
         }
      }

      return getMaximo(res1, res2, res3, res4);
   }

   public void delete(Partida partida) {
      partidaRepo.deleteById(partida.getId());
   }

   public Integer getPrimeraAccion(List<Accion> acciones, Turno turno) {
      Integer contador=0;
      for(Accion a: acciones){
         if(a.getTurno().getId()==turno.getId()){
               contador++;
         }
      }
      if(contador>1){
         return 0;
      }else {
         return 1;
      }
   }

}
