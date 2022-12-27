package org.springframework.samples.petclinic.web;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.samples.petclinic.model.Person;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class WelcomeController {
	
	  @GetMapping({"/","/welcome"})
	  public ModelAndView welcome(Map<String, Object> model,  Principal principal) {

		List<Person> persons = new ArrayList<Person>();
		Person diego = new Person();
		Person julio = new Person();
		Person francis = new Person();
		Person aitor = new Person();
		Person raymon = new Person();
		Person jesus = new Person();

		diego.setFirstName("Diego");
		diego.setLastName("Linares");
		julio.setFirstName("Julio");
		julio.setLastName("Ribas");
		francis.setFirstName("Francisco Manuel");
		francis.setLastName("Villalobos");
		aitor.setFirstName("Aitor");
		aitor.setLastName("Rodriguez");
		raymon.setFirstName("ramon");
		raymon.setLastName("Guerrero");
		jesus.setFirstName("Jesús");
		jesus.setLastName("Zambrana");

		persons.add(diego);
		persons.add(julio);
		persons.add(francis);
		persons.add(aitor);
		persons.add(raymon);
		persons.add(jesus);

		model.put("persons", persons);
		model.put("title","My project");
		model.put("group", "Teachers");

		ModelAndView res = new ModelAndView("welcome");
		if(principal != null){
			res.addObject("username", principal.getName());
		}
		return res;
	  }

}
