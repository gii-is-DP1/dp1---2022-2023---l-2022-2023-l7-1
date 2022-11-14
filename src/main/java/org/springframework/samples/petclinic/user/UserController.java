/*
 * Copyright 2002-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.samples.petclinic.user;

import java.security.Principal;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Juergen Hoeller
 * @author Ken Krebs
 * @author Arjen Poutsma
 * @author Michael Isvy
 */
@Controller
public class UserController {

	private static final String VIEWS_USER_CREATE_UPDATE_FORM = "users/createOrUpdateUserForm";
    private static final String VIEWS_ADMIN_UPDATE_FORM = "users/adminUpdateUserForm";
	private static final String STATS_LISTING_VIEW = "users/stats";
	private static final String USER_STATS_LISTING_VIEW = "users/userStats";
	private static final String VIEW_USER_LISTING = "users/userListing";
	private static final String VIEW_USERNAME_EDITING = "users/userEdit";


	private final UserService userService;

    @Autowired
    private AuthoritiesService authoritiesService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}

	@Transactional
	@GetMapping(value = "/users/all")
	public ModelAndView showUsers(){
		ModelAndView res = new ModelAndView(VIEW_USER_LISTING);
		res.addObject("users", userService.getAll());
		return res;
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
    public ModelAndView deleteUser(@PathVariable String username){
        userService.deleteUserById(username);        
        return showUsers();
    }

    @Transactional(readOnly = true)
    @GetMapping(value = "/users/{username}/edit")
    public ModelAndView editUser(@PathVariable String username){
        User user=userService.getUserById(username);        
        ModelAndView result=new ModelAndView(VIEWS_ADMIN_UPDATE_FORM);
        result.addObject("user", user);
        return result;
    }

    @Transactional
    @PostMapping(value = "/users/{username}/edit")
    public ModelAndView saveUser(@PathVariable String username,User user) throws DataAccessException, DuplicatedUsernameException{

        User userToBeUpdated=userService.getUserById(username);
        BeanUtils.copyProperties(user,userToBeUpdated,
         "winRatio", "matchesPlayed", "gamesWin", "totalPoints", "maxPoints", "timesUsedPowerQuestion", "timesUsedPower1", "password", "birthDate");
        userService.saveUser(userToBeUpdated);
        return showUsers();
    }

    @Transactional(readOnly = true)
	@GetMapping(value = "/users/{username}/userEdit")
    public ModelAndView editUsername(@PathVariable("username") String username){
        User user=userService.getUserById(username);        
        ModelAndView result=new ModelAndView(VIEW_USERNAME_EDITING);
        result.addObject("user", user);
        return result;
    }

    @Transactional
    @PostMapping(value = "/users/{username}/userEdit")
    public String saveUsername(@PathVariable("username") String username, User user, BindingResult br) throws DataAccessException, DuplicatedUsernameException{
        if (br.hasErrors()) {
            return VIEW_USERNAME_EDITING;
        } else {
        User usernameToBeUpdated=userService.getUserById(username);
        BeanUtils.copyProperties(user,usernameToBeUpdated, 
         "winRatio", "matchesPlayed", "gamesWin", "totalPoints", "maxPoints", "timesUsedPowerQuestion", "timesUsedPower1", "name", "lastName", "birthDate");
        userService.saveUser(usernameToBeUpdated);
        return "redirect:/";
        }
    }

	@Transactional
    @GetMapping(value = "/stats")
    public String showStats(Map<String, Object> model) {
        List<User> users = userService.getAll();
        model.put("users", users);
        return STATS_LISTING_VIEW;
    }

	@Transactional
    @GetMapping(value = "/users/{username}/stats")
    public String showStats(@PathVariable String username,Map<String, Object> model) {
        User user = userService.getUserById(username);
        model.put("user", user);
        return USER_STATS_LISTING_VIEW;
    }

}
