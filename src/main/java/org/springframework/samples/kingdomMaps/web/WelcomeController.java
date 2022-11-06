package org.springframework.samples.kingdomMaps.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.samples.kingdomMaps.model.Person;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WelcomeController {
	
	
	  @GetMapping({"/","/welcome"})
	  public String welcome(Map<String, Object> model) {	    
		List<Person> persons = new ArrayList<Person>();
		Person diego = new Person();
		Person julio = new Person();
		Person francis = new Person();
		Person aitor = new Person();
		Person ramon = new Person();
		Person jesus = new Person();
		diego.setFirstName("Diego");
		diego.setLastName("Linares");
		julio.setFirstName("Julio");
		julio.setLastName("Ribas");
		francis.setFirstName("Francisco Manuel");
		francis.setLastName("Villalobos");
		aitor.setFirstName("Tor");
		aitor.setLastName("Rodriguez");
		ramon.setFirstName("ramon");
		ramon.setLastName("Guerrero");
		jesus.setFirstName("Jesús");
		jesus.setLastName("Zambrana");
		persons.add(diego);
		persons.add(julio);
		persons.add(francis);
		persons.add(aitor);
		persons.add(ramon);
		persons.add(jesus);
		model.put("persons", persons);
		model.put("title","My project");
		model.put("group", "Teachers");
	    return "welcome";
	  }
}
