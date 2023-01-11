package org.springframework.samples.petclinic.user;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.samples.petclinic.tablero.Tablero;
import org.springframework.samples.petclinic.tablero.TableroService;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {

	private static final String VIEWS_USER_CREATE_UPDATE_FORM = "users/createOrUpdateUserForm";
	private static final String VIEW_USERNAME_EDITING = "users/userEdit";
    private static final String VIEW_USER_DETAILS = "users/userDetails";
    private static final String VIEW_USER_FRIENDS = "users/friends";
	private static final String VIEW_USER_FRIENDS_PARTIDAS = "users/friendsPartida";
	private static final String COULD_NOT_DELETE_USER = "deleteUnsuccessfull";

	private final UserService userService;

    @Autowired
    private AuthoritiesService authoritiesService;

	@Autowired
    private TableroService tableroService;

	@Autowired
	public UserController(UserService userService, TableroService tableroService) {
		this.userService = userService;
		this.tableroService = tableroService;
	}

	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}

	@Transactional
	@GetMapping(value = "/users/all")
	public ModelAndView showUsers(@RequestParam Map<String, Object> params, Model res, Principal principal){ //show users con paginacion
        Integer page = params.get("page") != null ? (Integer.valueOf(params.get("page").toString()) - 1) : 0;
        Pageable pageable = PageRequest.of(page, 3);
        Page<User> users = userService.getAll(pageable);
        Integer totalPage = users.getTotalPages();
        if(totalPage > 0) {
			List<Integer> pages = IntStream.rangeClosed(1, totalPage).boxed().collect(Collectors.toList());
			res.addAttribute("pages", pages);
		}
		res.addAttribute("current", page + 1);
		res.addAttribute("next", page + 2);
		res.addAttribute("prev", page);
		res.addAttribute("last", totalPage);
		res.addAttribute("users", users.getContent());

		ModelAndView result = new ModelAndView("users/userListingPage");
		result.addObject("username", principal.getName());
		
		return result;
	}

    @Transactional(readOnly = true)
	@GetMapping(value = "/users/new")
	public String initCreationForm(Map<String, Object> model) {
		User user = new User();
		model.put("user", user);
		return VIEWS_USER_CREATE_UPDATE_FORM;
	}

    @Transactional(rollbackFor = DuplicatedUsernameException.class)
	@PostMapping(value = "/users/new")
	public String processCreationForm(@Valid User user, BindingResult result) {
		if (result.hasErrors()) {
			return VIEWS_USER_CREATE_UPDATE_FORM;
		}
		else {
            try{
                this.userService.saveUser(user);
                this.authoritiesService.saveAuthorities(user.username, "player");
            }catch(DuplicatedUsernameException ex){
                result.rejectValue("username", "duplicate", "already exists");
                return VIEWS_USER_CREATE_UPDATE_FORM;
            }
			return "redirect:/";
		}
	}

    @Transactional
	@GetMapping(value = "/users/{username}/delete")
    public String deleteUser(@PathVariable String username){
		userService.deleteFriends(username);
		if(tableroService.tieneUnaPartida(userService.getUserById(username))) {
			return COULD_NOT_DELETE_USER;
		}
        userService.deleteUserById(username);        
        return "redirect:/users/all";
    }

    @Transactional(readOnly = true)
    @GetMapping(value = "/users/{username}/edit")
    public ModelAndView editUser(@PathVariable String username){
        User user=userService.getUserById(username);        
        ModelAndView result=new ModelAndView(VIEW_USERNAME_EDITING);
        result.addObject("user", user);
        return result;
    }

    @Transactional()
    @PostMapping(value = "/users/{username}/edit")
    public String saveUser(@PathVariable String username,@Valid User user, BindingResult br) throws DataAccessException, DuplicatedUsernameException{
        if (br.hasErrors()) {
			return VIEW_USERNAME_EDITING;
		} else{
            user.setUsername(username);
            User userToBeUpdated=userService.getUserById(username);
            BeanUtils.copyProperties(user,userToBeUpdated,
        "winRatio", "matchesPlayed", "gamesWin", "totalPoints", "maxPoints", "timesUsedPowerQuestion", "timesUsedPower1", "friends", "estado");
            userService.saveUser(userToBeUpdated);
        return "redirect:/users/all";
        }
    }

    @Transactional(readOnly = true)
	@GetMapping(value = "/user/{username}/userEdit")
    public ModelAndView editUsername(Principal principal, @PathVariable String username){
        User user=userService.getUserById(principal.getName());        
        ModelAndView result=new ModelAndView(VIEW_USERNAME_EDITING);
        result.addObject("user", user);
        return result;
    }

    @Transactional()
    @PostMapping(value = "/user/{username}/userEdit")
    public String saveUsername(@PathVariable String username, Principal principal,@Valid User user, BindingResult br) throws DataAccessException, DuplicatedUsernameException{
        if (br.hasErrors()) {
            return VIEW_USERNAME_EDITING;
        } else {
        User usernameToBeUpdated=userService.getUserById(username);
        BeanUtils.copyProperties(user,usernameToBeUpdated, 
         "winRatio", "matchesPlayed", "gamesWin", "totalPoints", "maxPoints", "timesUsedPowerQuestion", "timesUsedPower1", "friends", "estado");
        userService.saveUser(usernameToBeUpdated);
        return "redirect:/";
        }
    }

    @Transactional
    @GetMapping(value = "/users/find")
	public ModelAndView initFindForm(Map<String, Object> model, Principal principal) {
		model.put("user", new User());

		ModelAndView res = new ModelAndView("users/findUsers");
		res.addObject("username", principal.getName());
		return res;
	}

    @Transactional
    @GetMapping("/user")
	public ModelAndView showUser(Principal principal) {
		ModelAndView res = new ModelAndView(VIEW_USER_DETAILS);
		res.addObject("user", this.userService.getUserById(principal.getName()));
		return res;
	}

    @Transactional
    @GetMapping(value = "/users")
	public ModelAndView processFindForm(User user, BindingResult result, Map<String, Object> model, Principal principal) {
        
		// allow parameterless GET request for /users to return all records
		if (user.getUsername() == null) {
			user.setUsername(""); // empty string signifies broadest possible search
		}

		// find users by user name
		List<User> results = this.userService.findUsers(user.getUsername());
		if (results.isEmpty()) {
			// no users found
			result.rejectValue("username", "notFound", "not found");

			ModelAndView res = new ModelAndView("users/findUsers");
			res.addObject("username", principal.getName());
			
			return res;
		}
		else {
			// multiple users found
			model.put("users", results);

			ModelAndView res = new ModelAndView("users/userListingFound");
			res.addObject("username", principal.getName());
			return res;
		}
	}

	// -------------------------------------------------------------------------------------------
	// --- FRIENDS -------------------------------------------------------------------------------
	// -------------------------------------------------------------------------------------------

    @Transactional
    @GetMapping("/friends")
	public ModelAndView showFriends(Principal principal) {
		List<User> friends = userService.getFriends(principal.getName());
		ModelAndView mav = new ModelAndView(VIEW_USER_FRIENDS);
		mav.addObject("friends", friends);
		mav.addObject("user", this.userService.getUserById(principal.getName()));
		return mav;
	}

	@Transactional
	@GetMapping(value = "/friends/{usernameFriend}/delete")
    public String deleteFriend(Principal principal, @PathVariable String usernameFriend){
        userService.deleteFriend(principal.getName(), usernameFriend);        
        return "redirect:/friends";
    }

	@Transactional
    @GetMapping("/friends/partidas")
	public ModelAndView showPartidasAmigo(Principal principal) {
		List<User> friends = userService.getFriends(principal.getName());
		ModelAndView mav = new ModelAndView(VIEW_USER_FRIENDS_PARTIDAS);
		List<Tablero> tableros = new ArrayList<>();
		for(User user: friends){
			tableros.addAll(tableroService.getTablerosByUser(user));
		}
		mav.addObject("tableros", tableros);
		mav.addObject("user", this.userService.getUserById(principal.getName()));
		return mav;
	}

	// -------------------------------------------------------------------------------------------
	// --- STATS ---------------------------------------------------------------------------------
	// -------------------------------------------------------------------------------------------

	@Transactional
    @GetMapping(value = "/stats")
    public ModelAndView showStats(@RequestParam Map<String, Object> params, Model res, Principal principal) {
	  	Integer page = params.get("page") != null ? (Integer.valueOf(params.get("page").toString()) - 1) : 0;
        Pageable pageable = PageRequest.of(page, 3);
        Page<User> users = userService.getAll(pageable);
        Integer totalPage = users.getTotalPages();
        if(totalPage > 0) {
			List<Integer> pages = IntStream.rangeClosed(1, totalPage).boxed().collect(Collectors.toList());
			res.addAttribute("pages", pages);
		}
		for(User user: users){
			userService.calculaEstadisticas(user);
		}
		List<Integer> statsTotales = userService.calculaEstadisticasGlobales();
		res.addAttribute("current", page + 1);
		res.addAttribute("next", page + 2);
		res.addAttribute("prev", page);
		res.addAttribute("last", totalPage);
		res.addAttribute("users", users.getContent());
		res.addAttribute("statsTotales", statsTotales);
		ModelAndView result = new ModelAndView("stats/stats");
		result.addObject("username", principal.getName());
		return result; 
	}

	@Transactional
    @GetMapping(value = "/stat")
    public ModelAndView showMyStats(Map<String, Object> model, Principal principal) {
        User user = userService.getUserById(principal.getName());
		userService.calculaEstadisticas(user);
        model.put("user", user);

		ModelAndView res = new ModelAndView("stats/userStats");
		res.addObject("username", principal.getName());
        return res;
    }


}
