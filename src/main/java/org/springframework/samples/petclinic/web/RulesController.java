package org.springframework.samples.petclinic.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RulesController {

	@GetMapping(value = "/rules")
	public String showRules() {
		return "/rules";
	}
}
