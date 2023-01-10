package org.springframework.samples.petclinic.partida;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.Chat.Chat;
import org.springframework.samples.petclinic.Chat.ChatService;
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
import org.springframework.transaction.annotation.Transactional;



@Service
public class PartidaService {
    
   PartidaRepository partidaRepo;

   TableroService tableroService;

   TurnoService turnoService;

   AccionService accionService;

   UserService userService;

   ChatService chatService;

   private static StrategyInterface strategy;
   
   
   @Autowired
   public PartidaService(PartidaRepository partidaRepo, TableroService tableroService, TurnoService turnoService, AccionService accionService, UserService userService, ChatService chatService) {
    this.partidaRepo = partidaRepo;

    this.tableroService = tableroService;

    this.turnoService = turnoService;
    this.accionService = accionService;
    this.userService = userService;
    this.chatService= chatService;
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

   @Transactional
   public void savePartida(Partida p){
      partidaRepo.save(p);
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
      tablero.setPartidaEnEspera(false);
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
         if(a.getTurno().getId().equals(turno.getId())){
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

      Chat c = new Chat();
      c.setPartida(p);
      chatService.save(c);

      User anfitrion = jugadores.get(0);
      anfitrion.setEstado(true);
      anfitrion.setJugadoresAceptados(new ArrayList<>());
      userService.save(anfitrion);

      if(jugadores.size()>1){
         tablero1.setPartida(p);
         tablero1.setUser(jugadores.get(0));
         tablero1.setPuntos(0);
         tablero1.setPartidaEnCurso(true);
         tablero1.setPartidaCreada(true);
         tablero1.setPartidaEnEspera(false);
         tablero2.setPartida(p);

         tablero2.setUser(jugadores.get(1));
         User user2 = userService.getUserById(jugadores.get(1).getUsername());
         user2.setAnfitrionDelJugador(new ArrayList<>());
         user2.setEstado(false);
         userService.save(user2);
         tablero2.setPuntos(0);
         tablero2.setPartidaEnCurso(true);
         tablero2.setPartidaCreada(false);
         tablero2.setPartidaEnEspera(false);

         if(jugadores.size()>2){        
            tablero3.setPartida(p);
            tablero3.setUser(jugadores.get(2));
            User user3 = userService.getUserById(jugadores.get(2).getUsername());
            user3.setEstado(false);
            user3.setAnfitrionDelJugador(new ArrayList<>());
            userService.save(user3);
            tablero3.setPuntos(0); 
            tablero3.setPartidaEnCurso(true);
            tablero3.setPartidaCreada(false);
            tablero3.setPartidaEnEspera(false);
            if(jugadores.size()>3){
               tablero4.setPartida(p);
               tablero4.setUser(jugadores.get(3));
               User user4 = userService.getUserById(jugadores.get(3).getUsername());
               user4.setEstado(false);
               user4.setAnfitrionDelJugador(new ArrayList<>());
               userService.save(user4);
               tablero4.setPartidaEnCurso(true);
               tablero4.setPartidaCreada(false);
               tablero4.setPartidaEnEspera(false);
               //4 tableros 1 uso por territorio
               actualizarTableros(tableros,1);
            } else{            
               //3 tableros 2 usos por territorio
               actualizarTableros(tableros,2);
            }
         } else{
               //2 tableros 3 usos por territorio
               actualizarTableros(tableros,3);
         }
      }
      turno.setPartida(p);
      turnoService.saveTurno(turno);
      return List.of(p.getId(),turno.getId());
   }

   private void actualizarTableros(List<Tablero> tableros, int i) {
      for(Tablero tablero:tableros){
         tablero.setUsos0(i);
         tablero.setUsos1(i);
         tablero.setUsos2(i);
         tablero.setUsos3(i);
         tablero.setUsos4(i);
         tablero.setUsos5(i); 
         tableroService.saveTablero(tablero);
      }
   }

   public Integer getNumJugador(Tablero tablero, List<Tablero> tableros) {
      Integer res=0;
      for(Integer i=0; i<tableros.size();i++){
         if(tableros.get(i)==tablero){
             res = i+1;
             break;
         }
     }
     return res;

   }

   public List<Integer> quitarUsoDado(Integer x, List<Integer> dadosFijos, @Valid Turno turno) {
      if(x==1){
         for(Integer i=0; i<dadosFijos.size();i++){
             if(dadosFijos.get(i).equals(turno.getNumTerritoriosJ1())){
                 dadosFijos.remove(dadosFijos.get(i));
                 break;
             }
         }
     }
     if(x==2){
         for(Integer i=0; i<dadosFijos.size();i++){
             if(dadosFijos.get(i).equals(turno.getNumTerritoriosJ2())){
                 dadosFijos.remove(dadosFijos.get(i));
                 break;
             }
         }
     }
     if(x==3){
         for(Integer i=0; i<dadosFijos.size();i++){
             if(dadosFijos.get(i).equals(turno.getNumTerritoriosJ3())){
                 dadosFijos.remove(dadosFijos.get(i));
                 break;
             }
         }
     }
     if(x==4){
         for(Integer i=0; i<dadosFijos.size();i++){
             if(dadosFijos.get(i).equals(turno.getNumTerritoriosJ4())){
                 dadosFijos.remove(dadosFijos.get(i));
                 break;
             }
         }
     }
   return dadosFijos;
   }

   public void actualizarPoderes(Accion accion, Tablero tablero, Integer idPartida) {
      if(accion.getCasilla().getPoder1()) {
         tablero.setPoder1(tablero.getPoder1()+1);
         tableroService.saveTablero(tablero);
      }

      if(accion.getCasilla().getPoder2()){
         Partida partida = getPartidaById(idPartida);
         List<Turno> turnos = turnoService.getTurnosByPartida(idPartida);
         if(turnos.get(turnos.size()-1).getTerritorio()==null){
            turnos.remove(turnos.size()-1);
         }
         List<Accion> acciones = accionService.getAccionesByTablero(tablero.getId()).stream().filter(x-> x.getCasilla() != null).collect(Collectors.toList());
         Integer puntosPoder2 = calcularPoder2(acciones, turnos, partida);
         tablero.setPoder2(puntosPoder2);
         tableroService.saveTablero(tablero);
      }
   }

   public void actualizarUso1(Turno turnoPost, Turno turno, Tablero tablero, Integer numJugador, Accion accionToBeUpdated) {
      if(numJugador==1){
         if(turnoPost.getNumTerritoriosJ1() == null|| turnoPost.getNumTerritoriosJ1() == 0){
             turno.setNumTerritoriosJ1(turno.getNumTerritoriosJ1()-1);
         }else if(turnoPost.getNumTerritoriosJ1() == -1 && turno.getNumTerritoriosJ1()==1){
             accionService.delete(accionToBeUpdated);
             tablero.setPoder1(tablero.getPoder1()-1);
             turno.setNumTerritoriosJ1(0);
         }else if(turnoPost.getNumTerritoriosJ1() == -1){
             turno.setNumTerritoriosJ1(turno.getNumTerritoriosJ1()-2);
             tablero.setPoder1(tablero.getPoder1()-1);
         }else if(turnoPost.getNumTerritoriosJ1() == 1){
             tablero.setPoder1(tablero.getPoder1()-1);
         }
     }
     if(numJugador==2){
         if(turnoPost.getNumTerritoriosJ2() == null|| turnoPost.getNumTerritoriosJ2() == 0){
             turno.setNumTerritoriosJ2(turno.getNumTerritoriosJ2()-1);
         }else if(turnoPost.getNumTerritoriosJ2() == -1 && turno.getNumTerritoriosJ2()==1){
             accionService.delete(accionToBeUpdated);
             tablero.setPoder1(tablero.getPoder1()-1);
             turno.setNumTerritoriosJ2(0);
         }else if(turnoPost.getNumTerritoriosJ2() == -1){
             turno.setNumTerritoriosJ2(turno.getNumTerritoriosJ2()-2);
             tablero.setPoder1(tablero.getPoder1()-1);
         }else if(turnoPost.getNumTerritoriosJ2() == 1){
             tablero.setPoder1(tablero.getPoder1()-1);
         }
     }
     if(numJugador==3){
         if(turnoPost.getNumTerritoriosJ3() == null|| turnoPost.getNumTerritoriosJ3() == 0){
             turno.setNumTerritoriosJ3(turno.getNumTerritoriosJ3()-1);
         }else if(turnoPost.getNumTerritoriosJ3() == -1 && turno.getNumTerritoriosJ3()==1){
             accionService.delete(accionToBeUpdated);
             tablero.setPoder1(tablero.getPoder1()-1);
             turno.setNumTerritoriosJ3(0);
         }else if(turnoPost.getNumTerritoriosJ3() == -1){
             turno.setNumTerritoriosJ3(turno.getNumTerritoriosJ3()-2);
             tablero.setPoder1(tablero.getPoder1()-1);
         }else if(turnoPost.getNumTerritoriosJ3() == 1){
             tablero.setPoder1(tablero.getPoder1()-1);
         }
     }
     if(numJugador==4){
         if(turnoPost.getNumTerritoriosJ4() == null|| turnoPost.getNumTerritoriosJ4() == 0){
             turno.setNumTerritoriosJ4(turno.getNumTerritoriosJ4()-1);
         }else if(turnoPost.getNumTerritoriosJ4() == -1 && turno.getNumTerritoriosJ4()==1){
             accionService.delete(accionToBeUpdated);
             tablero.setPoder1(tablero.getPoder1()-1);
             turno.setNumTerritoriosJ4(0);
         }else if(turnoPost.getNumTerritoriosJ4() == -1){
             turno.setNumTerritoriosJ4(turno.getNumTerritoriosJ4()-2);
             tablero.setPoder1(tablero.getPoder1()-1);
         }else if(turnoPost.getNumTerritoriosJ4() == 1){
             tablero.setPoder1(tablero.getPoder1()-1);
         }
     }
   }

   public void saveTableroTurnoAccion(Tablero tablero, Turno turno, Accion accion) {
      accion.setTablero(tablero);
      accion.setTurno(turno);
      accionService.save(accion);  
      turnoService.saveTurno(turno);
      tableroService.saveTablero(tablero);
   }

   public void saveTurno(Turno t) {
      turnoService.saveTurno(t);
   }

   public Accion getAccionById(Integer idAccion) {
      return accionService.getAccionById(idAccion);
   }

   public Turno getTurnoById(Integer idTurno) {
      return turnoService.getTurnoById(idTurno);
   }

   public Tablero getTableroActiveByUser(Principal principal) {
      return tableroService.getTableroActiveByUser(userService.getUserById(principal.getName()));
   }

   public List<Tablero> getTablerosByPartidaId(Integer idPartida) {
      return tableroService.getTablerosByPartida(getPartidaById(idPartida));
   }

   public void saveAccion(Accion accionToBeUpdated) {
      accionService.save(accionToBeUpdated);
   }

   public Integer getAccionesPorDibujar(Turno turno, Integer numJugador) {
      Integer res=0;
      if(numJugador==1){
         res = turno.getNumTerritoriosJ1();
      } else if(numJugador==2){
         res = turno.getNumTerritoriosJ2();
      } else if(numJugador==3){
         res = turno.getNumTerritoriosJ3();
      } else if(numJugador==4){
         res = turno.getNumTerritoriosJ4();
      }
      return res;
   }

   public void saveUserEstadoFalse(User user) {
      user.setEstado(false);
      userService.save(user);
   }

   public void saveTableroEnEspera(Tablero tablero) {
      tablero.setPartidaEnEspera(true);
      tableroService.saveTablero(tablero);
   }

   public Integer getNumTablerosEnEsperaDado(List<Tablero> tableros) {
      Integer contador=0;
      for(Tablero t: tableros){
         if(t.getUser().getEstado()!=null && t.getUser().getEstado()==false){
            contador++;
         }
      }
      return contador;
   }

   public Boolean getPartidaEnEspera(List<Tablero> tableros) {
      for(Tablero t: tableros){
         if(t.getPartidaEnEspera()){
            return true;
         }
      }
      return false;
   }

   public User getUserById(Principal principal) {
      return userService.getUserById(principal.getName());
   }

   public List<Accion> getAccionesByTablero(Integer idTablero) {
      return accionService.getAccionesByTablero(idTablero);
   }

   public Integer getUltimoJugadorActivo(List<Tablero> tableros, Turno turno) {
      Integer res=tableros.size();
      Integer x=0;
      Integer sum=0;
      for(Integer i=0; i<tableros.size();i++){
         if(i!=tableros.size()-1){
            x=i+1;
         } else{
            x=0;
         }
         sum = tableros.get(i).getUsos0()+ tableros.get(i).getUsos1()+ tableros.get(i).getUsos2() +
         tableros.get(i).getUsos3()+ tableros.get(i).getUsos4()+ tableros.get(i).getUsos5() - (
         tableros.get(x).getUsos0()+ tableros.get(x).getUsos1()+ tableros.get(x).getUsos2() +
         tableros.get(x).getUsos3()+ tableros.get(x).getUsos4()+ tableros.get(x).getUsos5()); //usos jugador i - el siguiente
         if(sum<0){
            res=x;
         }
      }
      if(res==tableros.size()){
         res=0;
      }
      return res;
   }

   public void saveJugadorActivo(Principal principal) {
      User user = userService.getUserById(principal.getName());
      user.setEstado(true);
      userService.save(user);
   }

   public Turno getUltimoTurno(Partida partida) {
      List<Turno> turnos= turnoService.getTurnosByPartida(partida.getId());
      return turnos.get(turnos.size()-1);
  }

   public void saveTablero(Tablero tablero) {
      tableroService.saveTablero(tablero);
   }

   public Boolean getPartidaFinalizada(List<Tablero> tableros) {
      Integer contador=0;
      for(Tablero t: tableros){
         if(t.getPartidaEnEspera()){
            contador++;
         }
      }
      if(contador==tableros.size()){
         return true;
      }
      return false;
   }

   public List<Turno> getTurnosByPartida(Integer id) {
      return turnoService.getTurnosByPartida(id);
   }

   public Integer getPosicionPartida(List<Tablero> tableros, Tablero tablero) {
      tableros.sort(Comparator.comparing(Tablero::getPuntos));
      for(Integer i=0;i<tableros.size();i++){
         if(tableros.get(i).equals(tablero)){
            return i+1;
         }
      }
      return 0;
   }

   public void cancelarPartida(User usuario) {
      Tablero tablero = tableroService.getTableroActiveByUser(usuario);
      Partida partida = getPartidaById(tablero.getPartida().getId());
      Chat chat = chatService.getByPartidaId(partida.getId());
      List<Tablero> tableros = tableroService.getTablerosByPartida(partida);
      List<Accion> acciones = new ArrayList<>();
      List<Turno> turnos = turnoService.getTurnosByPartida(partida.getId());
      for(Tablero t: tableros){
         usuario =t.getUser();
         usuario.setJugadoresAceptados(new ArrayList<>());
         usuario.setReceivedInvitationsToGame(new HashSet<>());
         usuario.setAnfitrionDelJugador(new ArrayList<>());
         usuario.setSendedInvitationsToGame(new HashSet<>());
         userService.save(usuario);
         acciones.addAll(accionService.getAccionesByTablero(t.getId()));
         tableroService.delete(t);
      }
      for(Accion a: acciones){
         accionService.delete(a);
      }
      for(Turno t: turnos){
         turnoService.delete(t);
      }
      chatService.delete(chat);
      delete(partida);
   }

   public List<Tablero> getTablerosByUser(User user) {
      return tableroService.getTablerosByUser(user);
  }

}
