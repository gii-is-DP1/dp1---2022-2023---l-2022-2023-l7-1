package org.springframework.samples.petclinic.partida;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.accion.Accion;
import org.springframework.samples.petclinic.accion.AccionService;
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
import org.springframework.samples.petclinic.tablero.TableroService;
import org.springframework.samples.petclinic.turnos.Turno;
import org.springframework.samples.petclinic.turnos.TurnoService;
import org.springframework.samples.petclinic.user.User;
import org.springframework.samples.petclinic.user.UserService;
import org.springframework.samples.petclinic.util.Territorio;
import org.springframework.stereotype.Service;



@Service
public class PartidaService {
    
   PartidaRepository partidaRepo;

   TableroService tableroService;

   TurnoService turnoService;

   AccionService accionService;

   UserService userService;

   private static StrategyInterface strategy;
   
   
   @Autowired
   public PartidaService(PartidaRepository partidaRepo, TableroService tableroService, TurnoService turnoService, AccionService accionService, UserService userService) {
    this.partidaRepo = partidaRepo;

    this.tableroService = tableroService;

    this.turnoService = turnoService;
    this.accionService = accionService;
    this.userService = userService;
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
    * @param Tablero
    *
    * Este método actualiza los usos del tablero dependiendo del Territorio elegido.
    *
    */
   public Integer actualizarUso(Integer idPartida, Turno turno, List<Territorio> listaTerritorios, Tablero tablero){
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
      turno.setPartida(p);

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

   

   public Integer calcularPuntosCriterioA1(List<Accion> acciones, List<Turno> turnos, Partida partida){
      Integer res1 = 0;
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
      return res1;
   }

   public Integer calcularPuntosCriterioA2(List<Accion> acciones, List<Turno> turnos, Partida partida){
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
      return res2;
   }
   public Integer calcularPuntosCriterioB1(List<Accion> acciones, List<Turno> turnos, Partida partida){
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
      return res3;
   }
   public Integer calcularPuntosCriterioB2(List<Accion> acciones, List<Turno> turnos, Partida partida){
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
      return res4;
   }
   
   public Integer calcularPoder2(List<Accion> acciones, List<Turno> turnos, Partida partida){
      Integer criterioA1 = calcularPuntosCriterioA1(acciones, turnos, partida);
      Integer criterioA2 = calcularPuntosCriterioA2(acciones, turnos, partida);
      Integer criterioB1 = calcularPuntosCriterioB1(acciones, turnos, partida);
      Integer criterioB2 = calcularPuntosCriterioB2(acciones, turnos, partida);
      Integer max = getMaximo(criterioA1, criterioA2, criterioB1, criterioB2);
      return max;
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

   public List<Integer> crearPartidaMultijugador(List<User> jugadores) {
      int[] criteriosA = criterioAleatorio();
      int[] criteriosB = criterioAleatorio();

      Turno turno = new Turno();
      List<Tablero> tableros = new ArrayList<Tablero>();
      Tablero tablero1 = new Tablero();
      Tablero tablero2 = new Tablero();
      Tablero tablero3 = new Tablero();
      Tablero tablero4 = new Tablero();

      if(jugadores.size()>1){
         tableros.add(tablero1);
         tableros.add(tablero2);
         if(jugadores.size()>2){        
            tableros.add(tablero3);
            if(jugadores.size()>3){
               tableros.add(tablero4);
            }
         }
      }
      
      Partida p = new Partida();
      
      p.setDateTime(LocalDateTime.now());
      
      p.setIdCriterioA1(criteriosA[0]);
      p.setIdCriterioA2(criteriosA[1]);

      p.setIdCriterioB1(criteriosB[0]);
      p.setIdCriterioB2(criteriosB[1]);
      p.setTableros(tableros);
      partidaRepo.save(p);

      User anfitrion = jugadores.get(0);
      anfitrion.setEstado(true);
      userService.save(anfitrion);

      if(jugadores.size()>1){
         tablero1.setPartida(p);
         tablero1.setUser(jugadores.get(0));
         tablero1.setPuntos(0);
         tablero1.setPartidaEnCurso(true);
         tablero1.setPartidaCreada(true);
         
         tablero2.setPartida(p);
         tablero2.setUser(jugadores.get(1));
         tablero2.setPuntos(0);
         tablero2.setPartidaEnCurso(true);
         tablero2.setPartidaCreada(false);

         if(jugadores.size()>2){        
            tablero3.setPartida(p);
            tablero3.setUser(jugadores.get(2));
            tablero3.setPuntos(0); 
            tablero3.setPartidaEnCurso(true);
            tablero3.setPartidaCreada(false);

            if(jugadores.size()>3){

               //4 tableros 1 uso por territorio
               tablero1.setUsos0(1);
               tablero1.setUsos1(1);
               tablero1.setUsos2(1);
               tablero1.setUsos3(1);
               tablero1.setUsos4(1);
               tablero1.setUsos5(1); 
               tableroService.saveTablero(tablero1);

               tablero2.setUsos0(1);
               tablero2.setUsos1(1);
               tablero2.setUsos2(1);
               tablero2.setUsos3(1);
               tablero2.setUsos4(1);
               tablero2.setUsos5(1); 
               tableroService.saveTablero(tablero2);

               tablero3.setUsos0(1);
               tablero3.setUsos1(1);
               tablero3.setUsos2(1);
               tablero3.setUsos3(1);
               tablero3.setUsos4(1);
               tablero3.setUsos5(1); 
               tableroService.saveTablero(tablero3);

               tablero4.setPartida(p);
               tablero4.setUser(jugadores.get(3));
               tablero4.setPuntos(0);
               tablero4.setUsos0(1);
               tablero4.setUsos1(1);
               tablero4.setUsos2(1);
               tablero4.setUsos3(1);
               tablero4.setUsos4(1);
               tablero4.setUsos5(1); 
               tablero4.setPartidaEnCurso(true);
               tablero4.setPartidaCreada(false);
               tableroService.saveTablero(tablero4);
            } else{

               //3 tableros 2 usos por territorio
               tablero1.setUsos0(2);
               tablero1.setUsos1(2);
               tablero1.setUsos2(2);
               tablero1.setUsos3(2);
               tablero1.setUsos4(2);
               tablero1.setUsos5(2); 
               tableroService.saveTablero(tablero1);

               tablero2.setUsos0(2);
               tablero2.setUsos1(2);
               tablero2.setUsos2(2);
               tablero2.setUsos3(2);
               tablero2.setUsos4(2);
               tablero2.setUsos5(2); 
               tableroService.saveTablero(tablero2);

               tablero3.setUsos0(2);
               tablero3.setUsos1(2);
               tablero3.setUsos2(2);
               tablero3.setUsos3(2);
               tablero3.setUsos4(2);
               tablero3.setUsos5(2); 
               tableroService.saveTablero(tablero3);

            }
         } else{

               //2 tableros 3 usos por territorio
               tablero1.setUsos0(3);
               tablero1.setUsos1(3);
               tablero1.setUsos2(3);
               tablero1.setUsos3(3);
               tablero1.setUsos4(3);
               tablero1.setUsos5(3); 
               tableroService.saveTablero(tablero1);

               tablero2.setUsos0(3);
               tablero2.setUsos1(3);
               tablero2.setUsos2(3);
               tablero2.setUsos3(3);
               tablero2.setUsos4(3);
               tablero2.setUsos5(3); 
               tableroService.saveTablero(tablero2);
         }
      }
      turno.setPartida(p);
      turnoService.saveTurno(turno);
      return List.of(p.getId(),turno.getId());
   }

}
