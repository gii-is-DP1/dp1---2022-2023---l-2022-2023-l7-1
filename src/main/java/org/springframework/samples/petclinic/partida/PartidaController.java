package org.springframework.samples.petclinic.partida;

import java.security.Principal;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.samples.petclinic.accion.Accion;
import org.springframework.samples.petclinic.accion.AccionService;
import org.springframework.samples.petclinic.tablero.Tablero;
import org.springframework.samples.petclinic.tablero.TableroService;
import org.springframework.samples.petclinic.turnos.Turno;
import org.springframework.samples.petclinic.turnos.TurnoService;
import org.springframework.samples.petclinic.user.User;
import org.springframework.samples.petclinic.user.UserService;
import org.springframework.samples.petclinic.util.Territorio;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.extern.slf4j.Slf4j;

@Controller
public class PartidaController {

    private static final String VIEW_CREAR_PARTIDA = "partidas/crearPartida";
    private static final String VIEW_ELIGE_TERRITORIO = "partidas/eligeTerritorio";
    private static final String VIEW_ELIGE_TERRITORIO2 = "partidas/eligeTerritorio2";
    private static final String VIEW_DIBUJAR = "partidas/dibujar";
    private static final String VIEW_AMIGOS = "partidas/lobby";

    private static final List<Territorio> listaTerritorios = List.of(Territorio.BOSQUE, Territorio.CASTILLO, Territorio.MONTANA, Territorio.POBLADO, Territorio.PRADERA, Territorio.RIO);
    private static final List<Integer> poder = List.of(0,1,-1);

    private PartidaService partidaService;
    private UserService userService;
    private TurnoService turnoService;
    private AccionService accionService;
    private TableroService tableroService;
    
    @Autowired
    public PartidaController(PartidaService service, UserService userService, TurnoService turnoService, AccionService accionService, TableroService tableroService) {
        this.partidaService=service;
        this.userService = userService;
        this.turnoService = turnoService;
        this.accionService = accionService;
        this.tableroService = tableroService;
    }

    @Transactional
    @GetMapping("/partidas")
	public ModelAndView showPartidas(@RequestParam Map<String, Object> params, Model res, Principal principal) {
        /*
        Integer page = params.get("page") != null ? (Integer.valueOf(params.get("page").toString()) - 1) : 0;
        Pageable pageable = PageRequest.of(page, 1);
        Page<Tablero> partidas = tableroService.getAll(pageable);
        Integer totalPage = partidas.getTotalPages();
        if(totalPage > 0) {
			List<Integer> pages = IntStream.rangeClosed(1, totalPage).boxed().collect(Collectors.toList());
			res.addAttribute("pages", pages);
		}
		res.addAttribute("current", page + 1);
		res.addAttribute("next", page + 2);
		res.addAttribute("prev", page);
		res.addAttribute("last", totalPage);
		res.addAttribute("users", partidas.getContent());
         */ // NO FUNCIONA LA PAGINACIÓN EN ESTE LISTING POR AHORA
        List<Tablero> tableros = userService.getTableros();
		ModelAndView result = new ModelAndView("users/partida");
		result.addObject("tablero", tableros);
        if(principal != null){
			result.addObject("username", principal.getName());
		}
		return result;
	}

    @Transactional
    @GetMapping(value = "/partidas/{username}")
	public ModelAndView showPartidasJugador(@PathVariable("username") String username, Principal principal) {
		List<Tablero> tableros = userService.getTableroByUser(username);
		ModelAndView res = new ModelAndView("users/partida");
		res.addObject("tablero", tableros);
        if(principal != null){
            res.addObject("username", principal.getName());
        }
		return res;
	}

    @Transactional
	@GetMapping(value = "/partida/crearPartida")
	public ModelAndView creacionPartida(){
		ModelAndView res = new ModelAndView(VIEW_CREAR_PARTIDA);
		return res;
	}

    @Transactional
    @GetMapping(value = "/partida/crearPartidaSolitaria")
	public String getPartidaSolitaria(Principal principal){
        User usuario = userService.getUserById(principal.getName());
        List<User> usersActive = tableroService.getActivePlayers();
        if (usersActive.contains(usuario)){
            Tablero tablero = tableroService.getTableroByUser(usuario);
            List<Accion> acciones = accionService.getAccionesByTablero(tablero);
            List<Turno> turnos = turnoService.getTurnosByTablero(tablero);
            Turno turno = turnos.get(turnos.size()-1);
            if(!acciones.isEmpty()){
                Accion accion = acciones.get(acciones.size()-1);
                if(turno.getId()!=accion.getTurno().getId()){
                    if ((turno.getId()-turnos.get(0).getId())%2==0){
                        return "redirect:/partida/eligeTerritorio3/"+tablero.getPartida().getId()+"/"+turno.getId();
                    } else {
                        return "redirect:/partida/eligeTerritorio2/"+tablero.getPartida().getId()+"/"+turno.getId();
                }
                } else{
                    if ((turno.getId()-turnos.get(0).getId())%2==0){
                        return "redirect:/partida/dibujar3/"+tablero.getPartida().getId()+"/"+turno.getId()+"/"+accion.getId();
                    } else {
                        return "redirect:/partida/dibujar2/"+tablero.getPartida().getId()+"/"+turno.getId()+"/"+accion.getId();
                    }
                }
            }
            return "redirect:/partida/eligeTerritorio3/"+tablero.getPartida().getId()+"/"+turno.getId();
        }
		List<Integer> x = this.partidaService.crearPartidaSolitario(usuario);

		return "redirect:/partida/eligeTerritorio3/"+x.get(0)+"/"+x.get(1);
	}

    // Metodo para los lanzamientos del dado
    static int[] lanzamiento(int num) {
		int[] resultados = new int[num]; //Se tiran dos dados

        for (int i=0;i<num;i++) {
            resultados[i] = (int) (Math.random()*6 + 1);
        }		
		return resultados;
	}

    @Transactional
    @GetMapping(value = "/partida/eligeTerritorio3/{idpartida}/{idturno}")
    public ModelAndView eligeTerritorio3(@PathVariable("idpartida") Integer idpartida, @PathVariable("idturno") Integer idturno){
        ModelAndView res = new ModelAndView(VIEW_ELIGE_TERRITORIO);
        Turno turno = turnoService.getTurnoById(idturno);
        List<Accion> acciones =accionService.getIdAcciones(idpartida, partidaService.getPartidaById(idpartida).getTableros().get(0).getId()); 
        Tablero tablero = partidaService.getPartidaById(idpartida).getTableros().get(0);
        turno.setTablero(tablero);   
        res.addObject("acciones", acciones);
        res.addObject("dados", lanzamiento(3));
        res.addObject("turno", turno);
        res.addObject("territorios", listaTerritorios);
        Partida partida = partidaService.getPartidaById(idpartida);
        res.addObject("criterios", List.of(partida.idCriterioA1,partida.idCriterioA2,partida.idCriterioB1,partida.idCriterioB2));
        
        return res;
    }

    @Transactional
    @PostMapping(value = "/partida/eligeTerritorio3/{idpartida}/{idturno}")
    public String eligeTerritorio3Post(@Valid Turno turno, BindingResult result, @PathVariable("idpartida") Integer idpartida, @PathVariable("idturno") Integer idturno,
     Map<String, Object> model){
        if (result.hasErrors()) {
			return VIEW_ELIGE_TERRITORIO;
		}
		else {
                Accion ac = new Accion();
                ac.setTablero(partidaService.getPartidaById(idpartida).getTableros().get(0));
                Turno turnoToBeUpdated = turnoService.getTurnoById(idturno);
                BeanUtils.copyProperties(turno, turnoToBeUpdated, "id","numTerritoriosJ2","numTerritoriosJ3","numTerritoriosJ4");
                turnoToBeUpdated.setTablero(ac.getTablero());
                turnoService.saveTurno(turnoToBeUpdated);
                ac.setTurno(turnoToBeUpdated);
                accionService.save(ac);
           return "redirect:/partida/dibujar3/"+idpartida+"/"+idturno+"/"+ac.getId();
		}
        
    }

    @Transactional  
    @GetMapping(value = "/partida/dibujar3/{idpartida}/{idturno}/{idaccion}")
    public ModelAndView dibujar(Accion accion, @PathVariable("idpartida") Integer idpartida, @PathVariable("idturno") Integer idturno, @PathVariable("idaccion") Integer idaccion) {
        ModelAndView res = new ModelAndView(VIEW_DIBUJAR);
        
        List<Accion> acciones =accionService.getIdAcciones(idpartida, partidaService.getPartidaById(idpartida).getTableros().get(0).getId());    
        res.addObject("acciones", acciones);
        res.addObject("action", accion);
        Set<Integer> casillas = partidaService.casillasPorDibujar(partidaService.getPartidaById(idpartida).getTableros().get(0).getId(), idpartida);
        res.addObject("casillas", casillas);
        Tablero tablero = partidaService.getPartidaById(idpartida).getTableros().get(0);
        res.addObject("tablero", tablero);
        res.addObject("poder", poder);
        Turno turno = new Turno();
        res.addObject("turno", turno);
        Partida partida = partidaService.getPartidaById(idpartida);
        res.addObject("criterios", List.of(partida.idCriterioA1,partida.idCriterioA2,partida.idCriterioB1,partida.idCriterioB2));
        return res;
    }

    @Transactional  
    @PostMapping(value = "/partida/dibujar3/{idpartida}/{idturno}/{idaccion}")
    public String dibujarPost(Turno turnoPost, Accion accion,@PathVariable("idpartida") Integer idpartida, @PathVariable("idturno") Integer idturno, @PathVariable("idaccion") Integer idaccion
    , Map<String, Object> model){
        Turno turno = turnoService.getTurnoById(idturno);
        Tablero tablero = partidaService.getPartidaById(idpartida).getTableros().get(0);
        turno.setTablero(tablero);
        if(turno.getNumTerritoriosJ1()>1){
            if(accion.getCasilla().getPoder1()){
                tablero.setPoder1(tablero.getPoder1()+1);
                tableroService.saveTablero(tablero);
            }
            Accion accionToBeUpdated = accionService.getAccionById(idaccion);
            BeanUtils.copyProperties(accion, accionToBeUpdated, "id","tablero","turno");
            accionService.save(accionToBeUpdated);
            Accion ac = new Accion();
            ac.setTablero(partidaService.getPartidaById(idpartida).getTableros().get(0));
            ac.setTurno(turnoService.getTurnoById(idturno));
            accionService.save(ac); 
            if(turnoPost.getNumTerritoriosJ1() != null){
                if(turnoPost.getNumTerritoriosJ1()==1){
                    turno.setNumTerritoriosJ1(turno.getNumTerritoriosJ1());
                    turnoService.saveTurno(turno);
                    tablero.setPoder1(tablero.getPoder1()-1);
                    tableroService.saveTablero(tablero);
                }else if(turnoPost.getNumTerritoriosJ1()==-1){
                    turno.setNumTerritoriosJ1(turno.getNumTerritoriosJ1()-2);
                    turnoService.saveTurno(turno);
                    tablero.setPoder1(tablero.getPoder1()-1);
                    tableroService.saveTablero(tablero);
                }else if(turnoPost.getNumTerritoriosJ1() == 0){
                    turno.setNumTerritoriosJ1(turno.getNumTerritoriosJ1()-1);
                    turnoService.saveTurno(turno);
                }
            }else{
                turno.setNumTerritoriosJ1(turno.getNumTerritoriosJ1()-1);
                turnoService.saveTurno(turno);
            }
                
            
          
            model.put("action", ac);
            return "redirect:/partida/dibujar3/"+idpartida+"/"+idturno+"/"+ac.getId();
        }else{
            Accion accionToBeUpdated = accionService.getAccionById(idaccion);
            BeanUtils.copyProperties(accion, accionToBeUpdated, "id","tablero","turno");
            accionService.save(accionToBeUpdated);
            Turno t= new Turno();
            turnoService.saveTurno(t);
            model.put("turno", t);
            return "redirect:/partida/eligeTerritorio2/"+idpartida+"/"+t.getId();
        }
        
    }


    @Transactional  
    @GetMapping(value = "/partida/eligeTerritorio2/{idpartida}/{idturno}")
    public ModelAndView eligeTerritorio2(@PathVariable("idpartida") Integer idpartida, @PathVariable("idturno") Integer idturno, HttpSession session){

        ModelAndView res = new ModelAndView(VIEW_ELIGE_TERRITORIO2);
        Turno turno = turnoService.getTurnoById(idturno);
        int[] dados = lanzamiento(2);
        List<Accion> acciones =accionService.getIdAcciones(idpartida, partidaService.getPartidaById(idpartida).getTableros().get(0).getId()); 
        turno.setTablero(acciones.get(0).getTablero());     
        res.addObject("acciones", acciones);
        res.addObject("dados", dados);
        res.addObject("turno", turno);
        session.setAttribute("dados", dados);
        Partida partida = partidaService.getPartidaById(idpartida);
        res.addObject("criterios", List.of(partida.idCriterioA1,partida.idCriterioA2,partida.idCriterioB1,partida.idCriterioB2));
        return res;
    }
  
    @Transactional  
    @PostMapping(value = "/partida/eligeTerritorio2/{idpartida}/{idturno}")
    public String eligeTerritorio2Post(@Valid Turno turno, BindingResult result, @PathVariable("idpartida") Integer idpartida, @PathVariable("idturno") Integer idturno,
    Map<String, Object> model, HttpSession session){
        if (result.hasErrors()) {
			return VIEW_ELIGE_TERRITORIO2;
		}
		else {
           
                Turno turnoToBeUpdated = turnoService.getTurnoById(idturno);
                BeanUtils.copyProperties(turno, turnoToBeUpdated, "id","territorio","numTerritoriosJ2","numTerritoriosJ3","numTerritoriosJ4");
                Integer territorio;
                int[] dados = (int[]) session.getAttribute("dados");

                if(dados[0] == turno.getNumTerritoriosJ1()){
                    territorio = dados[1]-1;
                }else{
                    territorio = dados[0]-1;
                }
                Accion ac = new Accion();
                ac.setTablero(partidaService.getPartidaById(idpartida).getTableros().get(0));
                turnoToBeUpdated.setTerritorio(listaTerritorios.get(territorio));
                turnoToBeUpdated.setTablero(ac.getTablero());
                turnoService.saveTurno(turnoToBeUpdated);
                ac.setTurno(turnoToBeUpdated);
                accionService.save(ac);
                model.put("turno", turnoToBeUpdated);
           return "redirect:/partida/dibujar2/"+idpartida+"/"+idturno+"/"+ac.getId();
		}
    }

    @Transactional  
    @GetMapping(value = "/partida/dibujar2/{idpartida}/{idturno}/{idaccion}")
    public ModelAndView dibujar2(Accion accion, @PathVariable("idpartida") Integer idpartida, @PathVariable("idturno") Integer idturno, @PathVariable("idaccion") Integer idaccion) {
        ModelAndView res = new ModelAndView(VIEW_DIBUJAR);
        List<Accion> acciones =accionService.getIdAcciones(idpartida, partidaService.getPartidaById(idpartida).getTableros().get(0).getId());    
        res.addObject("acciones", acciones);
        res.addObject("action", accion);
        Set<Integer> casillas = partidaService.casillasPorDibujar(partidaService.getPartidaById(idpartida).getTableros().get(0).getId(), idpartida);
        res.addObject("casillas", casillas);
        Tablero tablero = partidaService.getPartidaById(idpartida).getTableros().get(0);
        res.addObject("tablero", tablero);
        res.addObject("poder", poder);
        Turno turno = new Turno();
        res.addObject("turno", turno);
        Partida partida = partidaService.getPartidaById(idpartida);
        res.addObject("criterios", List.of(partida.idCriterioA1,partida.idCriterioA2,partida.idCriterioB1,partida.idCriterioB2));
        return res;
    }

    @Transactional  
    @PostMapping(value = "/partida/dibujar2/{idpartida}/{idturno}/{idaccion}")
    public String dibujar2Post(Turno turnoPost, Accion accion,@PathVariable("idpartida") Integer idpartida, @PathVariable("idturno") Integer idturno, @PathVariable("idaccion") Integer idaccion
    , Map<String, Object> model){
        Turno turno = turnoService.getTurnoById(idturno);
        Tablero tablero = partidaService.getPartidaById(idpartida).getTableros().get(0);
        turno.setTablero(tablero);
        if(turno.getNumTerritoriosJ1()>1){
            if(accion.getCasilla().getPoder1()){
                tablero.setPoder1(tablero.getPoder1()+1);
                tableroService.saveTablero(tablero);
            }
            Accion accionToBeUpdated = accionService.getAccionById(idaccion);
            BeanUtils.copyProperties(accion, accionToBeUpdated, "id","tablero","turno");
            accionService.save(accionToBeUpdated);
            Accion ac = new Accion();
            ac.setTablero(partidaService.getPartidaById(idpartida).getTableros().get(0));
            ac.setTurno(turnoService.getTurnoById(idturno));
            accionService.save(ac); 
            if(turnoPost.getNumTerritoriosJ1() != null){
                if(turnoPost.getNumTerritoriosJ1()==1){
                    turno.setNumTerritoriosJ1(turno.getNumTerritoriosJ1());
                    turnoService.saveTurno(turno);
                    tablero.setPoder1(tablero.getPoder1()-1);
                    tableroService.saveTablero(tablero);
                }else if(turnoPost.getNumTerritoriosJ1()==-1){
                    turno.setNumTerritoriosJ1(turno.getNumTerritoriosJ1()-2);
                    turnoService.saveTurno(turno);
                    tablero.setPoder1(tablero.getPoder1()-1);
                    tableroService.saveTablero(tablero);
                }else if(turnoPost.getNumTerritoriosJ1() == 0){
                    turno.setNumTerritoriosJ1(turno.getNumTerritoriosJ1()-1);
                    turnoService.saveTurno(turno);
                }
            }else{
                turno.setNumTerritoriosJ1(turno.getNumTerritoriosJ1()-1);
                turnoService.saveTurno(turno);
            }
            model.put("action", ac);
            return "redirect:/partida/dibujar2/"+idpartida+"/"+idturno+"/"+ac.getId();
        }else{
            Accion accionToBeUpdated = accionService.getAccionById(idaccion);
            BeanUtils.copyProperties(accion, accionToBeUpdated, "id","tablero","turno");
            accionService.save(accionToBeUpdated);
            Turno t= new Turno();
            turnoService.saveTurno(t);
            model.put("turno", t);
            return "redirect:/partida/eligeTerritorio3/"+idpartida+"/"+t.getId();
        }
        
    }

	@GetMapping(value = "/partida/{username}/lobby")
	public ModelAndView mostrarAmigos(@PathVariable("username") String username){
		ModelAndView res = new ModelAndView(VIEW_AMIGOS);
		res.addObject("username", username);
		return res;
	}
}
