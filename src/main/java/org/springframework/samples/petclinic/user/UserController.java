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

import java.util.Collection;
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
	private static final String STATS_LISTING_VIEW = "users/stats";
	private static final String USER_STATS_LISTING_VIEW = "users/userStats";
	private static final String VIEW_USER_LISTING = "users/userListing";
	private static final String VIEW_USERNAME_EDITING = "users/userEdit";
    private static final String VIEW_FIND_USER = "users/findUsers";
    private static final String VIEW_USER_DETAILS = "users/userDetails";
    private static final String VIEW_USER_FRIENDS = "users/friends";


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
    public String deleteUser(@PathVariable String username){
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
        "winRatio", "matchesPlayed", "gamesWin", "totalPoints", "maxPoints", "timesUsedPowerQuestion", "timesUsedPower1", "friends");
            userService.saveUser(userToBeUpdated);
        return "redirect:/users/all";
        }
    }

    @Transactional(readOnly = true)
	@GetMapping(value = "/users/{username}/userEdit")
    public ModelAndView editUsername(@PathVariable("username") String username){
        User user=userService.getUserById(username);        
        ModelAndView result=new ModelAndView(VIEW_USERNAME_EDITING);
        result.addObject("user", user);
        return result;
    }

    @Transactional()
    @PostMapping(value = "/users/{username}/userEdit")
    public String saveUsername(@PathVariable("username") String username,@Valid User user, BindingResult br) throws DataAccessException, DuplicatedUsernameException{
        if (br.hasErrors()) {
            return VIEW_USERNAME_EDITING;
        } else {
        User usernameToBeUpdated=userService.getUserById(username);
        BeanUtils.copyProperties(user,usernameToBeUpdated, 
         "winRatio", "matchesPlayed", "gamesWin", "totalPoints", "maxPoints", "timesUsedPowerQuestion", "timesUsedPower1", "friends");
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

    @Transactional
    @GetMapping(value = "/users/find")
	public String initFindForm(Map<String, Object> model) {
		model.put("user", new User());
		return VIEW_FIND_USER;
	}

    @Transactional
    @GetMapping("/users/{username}")
	public ModelAndView showUser(@PathVariable("username") String username) {
		ModelAndView mav = new ModelAndView(VIEW_USER_DETAILS);
		mav.addObject("user", this.userService.getUserById(username));
		return mav;
	}

    @Transactional
    @GetMapping(value = "/users")
	public String processFindForm(User user, BindingResult result, Map<String, Object> model) {
        
		// allow parameterless GET request for /users to return all records
		if (user.getUsername() == null) {
			user.setUsername(""); // empty string signifies broadest possible search
		}

		// find users by user name
		List<User> results = this.userService.findUsers(user.getUsername());
		if (results.isEmpty()) {
			// no users found
			result.rejectValue("username", "notFound", "not found");
			return VIEW_FIND_USER;
		}
		else if (results.size() == 1) {
			// 1 user found
			user = results.iterator().next();
			return "redirect:/users/" + user.getUsername();
		}
		else {
			// multiple users found
			model.put("users", results);
			return VIEW_USER_LISTING;
		}
	}

    @Transactional
    @GetMapping("/users/{username}/friends")
	public ModelAndView showFriends(@PathVariable("username") String username) {
		List<User> friends = userService.getFriends(username);
		ModelAndView mav = new ModelAndView(VIEW_USER_FRIENDS);
		mav.addObject("friends", friends);
		mav.addObject("user", this.userService.getUserById(username));
		return mav;
	}

	@Transactional
	@GetMapping(value = "/users/{username}/friends/{username2}/delete")
    public String deleteFriend(@PathVariable String username, @PathVariable String username2){
        userService.Deletefriend(username, username2);        
        return "redirect:/users/"+username+"/friends";
    }

}
