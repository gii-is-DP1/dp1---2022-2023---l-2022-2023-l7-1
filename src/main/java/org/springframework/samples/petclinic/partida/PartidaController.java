package org.springframework.samples.petclinic.partida;

import java.security.Principal;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PartidaController {

    private static final String VIEW_ELIGE_TERRITORIO = "partidas/eligeTerritorio";

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

    //-------------------------------------------------------------------------
    // Mostrar Partida --------------------------------------------------------
    //-------------------------------------------------------------------------
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

    //-------------------------------------------------------------------------
    // PartidaAsociadaJugador -------------------------------------------------
    //-------------------------------------------------------------------------
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

    //-------------------------------------------------------------------------
    // CrearPartida -----------------------------------------------------------
    //-------------------------------------------------------------------------
    @Transactional
	@GetMapping(value = "/partida/crearPartida")
	public ModelAndView creacionPartida(Principal principal){
		ModelAndView res = new ModelAndView("partidas/crearPartida");
        if(principal != null){
            res.addObject("username", principal.getName());
        }
		return res;
	}

    //-------------------------------------------------------------------------
    //PartidaSolitaria---------------------------------------------------------
    //-------------------------------------------------------------------------

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
                        return "redirect:/partida/eligeTerritorio/"+tablero.getPartida().getId()+"/"+turno.getId()+"/3";
                    } else {
                        return "redirect:/partida/eligeTerritorio/"+tablero.getPartida().getId()+"/"+turno.getId()+"/2";
                    }
                } else{
                    if ((turno.getId()-turnos.get(0).getId())%2==0){
                        return "redirect:/partida/dibujar/"+tablero.getPartida().getId()+"/"+turno.getId()+"/"+accion.getId()+"/3";
                    } else {
                        return "redirect:/partida/dibujar/"+tablero.getPartida().getId()+"/"+turno.getId()+"/"+accion.getId()+"/2";
                    }
                }
            }
            return "redirect:/partida/eligeTerritorio/"+tablero.getPartida().getId()+"/"+turno.getId()+"/3";
        }
		List<Integer> x = this.partidaService.crearPartidaSolitario(usuario);

		return "redirect:/partida/eligeTerritorio/"+x.get(0)+"/"+x.get(1)+"/3";
	}

    // Metodo para los lanzamientos del dado
    static int[] lanzamiento(int num) {
		int[] resultados = new int[num];

        for (int i=0;i<num;i++) {
            resultados[i] = (int) (Math.random()*6 + 1);
        }		
		return resultados;
	}

    //-------------------------------------------------------------------------
    // Elegir Territorio ------------------------------------------------------
    //-------------------------------------------------------------------------

    @Transactional
    @GetMapping(value = "/partida/eligeTerritorio/{idPartida}/{idTurno}/{numTiradas}")
    public ModelAndView eligeTerritorio(Principal principal, @PathVariable("idPartida") Integer idPartida, 
                                        @PathVariable("idTurno") Integer idTurno, @PathVariable("numTiradas") Integer numTiradas,
                                        HttpSession session) {
        ModelAndView res = new ModelAndView(VIEW_ELIGE_TERRITORIO);

        Turno turno = turnoService.getTurnoById(idTurno);
        Partida partida = partidaService.getPartidaById(idPartida);
        List<Integer> criterios = List.of(partida.idCriterioA1,partida.idCriterioA2,partida.idCriterioB1,partida.idCriterioB2);
        Tablero tablero = partidaService.getPartidaById(idPartida).getTableros().get(0);
        List<Accion> acciones = accionService.getIdAcciones(idPartida, tablero.getId());

        Boolean eligeTerritorio;
        if(numTiradas == 2) {
            turno.setTablero(acciones.get(0).getTablero());
            int[] dados = lanzamiento(numTiradas);
            session.setAttribute("dados", dados);
            res.addObject("dados", dados);
            eligeTerritorio = false;
            
        } else {
            turno.setTablero(tablero);
            res.addObject("territorios", listaTerritorios);
            res.addObject("dados", lanzamiento(numTiradas));
            eligeTerritorio = true;
        }
        res.addObject("eligeTerritorio", eligeTerritorio);

        res.addObject("acciones", acciones);
        
        res.addObject("turno", turno);
        res.addObject("criterios", criterios);
        if(principal != null){
            res.addObject("username", principal.getName());
        }
        return res;
    }

    @Transactional
    @PostMapping(value = "/partida/eligeTerritorio/{idPartida}/{idTurno}/{numTiradas}")
    public ModelAndView eligeTerritorioPost(@Valid Turno turno, BindingResult result, Principal principal, Map<String, Object> model,
                                        @PathVariable("idPartida") Integer idPartida, @PathVariable("idTurno") Integer idTurno, 
                                        @PathVariable("numTiradas") Integer numTiradas, HttpSession session) {
        ModelAndView res = new ModelAndView();
        if (result.hasErrors()) {
            if(principal != null){
                res.addObject("username", principal.getName());
            }
            res.setViewName(VIEW_ELIGE_TERRITORIO);
			return res;
		} else {
            Turno turnoToBeUpdated = turnoService.getTurnoById(idTurno);
            BeanUtils.copyProperties(turno, turnoToBeUpdated, "id","numTerritoriosJ2","numTerritoriosJ3","numTerritoriosJ4");

            if (numTiradas == 2) {
                int[] dados = (int[]) session.getAttribute("dados");
                Integer territorio = dados[0]-1;

                if(dados[0] == turno.getNumTerritoriosJ1()){
                    territorio = dados[1]-1;
                }
                turnoToBeUpdated.setTerritorio(listaTerritorios.get(territorio));
                model.put("turno", turnoToBeUpdated);
            }

            Integer control = partidaService.actualizarUso(idPartida, turnoToBeUpdated, listaTerritorios);
            if(control <0){
                //Aquí hay que poner una vista que te lleve a una pantalla con el tablero completo y los puntos conseguidos
                Tablero tablero = partidaService.getPartidaById(idPartida).getTableros().get(0);
                tablero.setPartidaEnCurso(false);
                tableroService.saveTablero(tablero);
                res.setViewName("welcome");
                return res;
            }

            Accion ac = new Accion();
            turnoToBeUpdated.setTablero(ac.getTablero());
            turnoService.saveTurno(turnoToBeUpdated);
            ac.setTablero(partidaService.getPartidaById(idPartida).getTableros().get(0));
            ac.setTurno(turnoToBeUpdated);
            accionService.save(ac);

            res.setViewName("redirect:/partida/dibujar/"+idPartida+"/"+idTurno+"/"+ac.getId()+"/"+numTiradas);
            if(principal != null){
                res.addObject("username", principal.getName());
            }
			return res;
        }
    }

    //-------------------------------------------------------------------------
    // Dibujar Tablero --------------------------------------------------------
    //-------------------------------------------------------------------------

    @Transactional  
    @GetMapping(value = "/partida/dibujar/{idPartida}/{idTurno}/{idAccion}/{numTiradas}")
    public ModelAndView dibujar(Accion accion, @PathVariable("idPartida") Integer idPartida, @PathVariable("idTurno") Integer idTurno,
                                 @PathVariable("idAccion") Integer idAccion, @PathVariable("numTiradas") Integer numTiradas, Principal principal) {
        ModelAndView res = new ModelAndView();
        
        Integer idTablero = partidaService.getPartidaById(idPartida).getTableros().get(0).getId();
        List<Accion> acciones =accionService.getIdAcciones(idPartida, idTablero);    
        Set<Integer> casillas = partidaService.casillasPorDibujar(idTablero, idPartida);

        if(casillas.isEmpty()){
            //Aquí hay que poner una vista que te lleve a una pantalla con el tablero completo y los puntos conseguidos
            Tablero tablero = partidaService.getPartidaById(idPartida).getTableros().get(0);
            tablero.setPartidaEnCurso(false);
            tableroService.saveTablero(tablero);
            res.setViewName("welcome");
        }else{
            res.setViewName("partidas/dibujar");
        }
        
        Tablero tablero = partidaService.getPartidaById(idPartida).getTableros().get(0);
        Turno turno = new Turno();
        Partida partida = partidaService.getPartidaById(idPartida);
        List<Integer> criterios = List.of(partida.idCriterioA1,partida.idCriterioA2,partida.idCriterioB1,partida.idCriterioB2);

        res.addObject("acciones", acciones);
        res.addObject("action", accion);
        res.addObject("casillas", casillas);
        res.addObject("tablero", tablero);
        res.addObject("poder", poder);
        res.addObject("turno", turno);
        res.addObject("criterios", criterios);
        if(principal != null){
            res.addObject("username", principal.getName());
        }
        return res;
    }

    @Transactional
    @PostMapping(value = "/partida/dibujar/{idPartida}/{idTurno}/{idAccion}/{numTiradas}")
    public ModelAndView dibujarPost(Turno turnoPost, Accion accion, @PathVariable("idPartida") Integer idPartida, Principal principal,
                            @PathVariable("idTurno") Integer idTurno, @PathVariable("idAccion") Integer idAccion,
                            @PathVariable("numTiradas") Integer numTiradas, Map<String, Object> model){
        ModelAndView res = new ModelAndView();
        if(principal != null){
            res.addObject("username", principal.getName());
        }
        Turno turno = turnoService.getTurnoById(idTurno);
        Accion accionToBeUpdated = accionService.getAccionById(idAccion);
        BeanUtils.copyProperties(accion, accionToBeUpdated, "id","tablero","turno");
        accionService.save(accionToBeUpdated);
        Tablero tablero = partidaService.getPartidaById(idPartida).getTableros().get(0);
        turno.setTablero(tablero);

        if(turno.getNumTerritoriosJ1()>1){
            if(accion.getCasilla().getPoder1()) {
                tablero.setPoder1(tablero.getPoder1()+1);
                tableroService.saveTablero(tablero);
            }
            Accion ac = new Accion();
            ac.setTablero(tablero);
            ac.setTurno(turno);
            model.put("action", ac);
            accionService.save(ac); 
            if(turnoPost.getNumTerritoriosJ1() == null || turnoPost.getNumTerritoriosJ1() == 0){
                turno.setNumTerritoriosJ1(turno.getNumTerritoriosJ1()-1);
            } else {
                if(turnoPost.getNumTerritoriosJ1()==1){
                    turno.setNumTerritoriosJ1(turno.getNumTerritoriosJ1());
                } else if(turnoPost.getNumTerritoriosJ1()==-1) {
                    turno.setNumTerritoriosJ1(turno.getNumTerritoriosJ1()-2);
                }
                tablero.setPoder1(tablero.getPoder1()-1);
                tableroService.saveTablero(tablero);
            }
            turnoService.saveTurno(turno);

            res.setViewName("redirect:/partida/dibujar/"+idPartida+"/"+idTurno+"/"+ac.getId()+"/"+numTiradas);
            return res;
        } else{
            Turno t = new Turno();
            turnoService.saveTurno(t);
            model.put("turno", t);
            if (numTiradas == 2) {
                res.setViewName("redirect:/partida/eligeTerritorio/"+idPartida+"/"+t.getId()+"/3");
            } else {
                res.setViewName("redirect:/partida/eligeTerritorio/"+idPartida+"/"+t.getId()+"/2");
            }
            return res;  
        }
    }

    //------------------------------------------------------------------------
    // VueltaLobby ----------------------------------------------------------
    //------------------------------------------------------------------------
	@GetMapping(value = "/partida/{username}/lobby")
	public ModelAndView mostrarAmigos(@PathVariable("username") String username){
		ModelAndView res = new ModelAndView("partidas/lobby");
		res.addObject("username", username);
		return res;
	}
}
