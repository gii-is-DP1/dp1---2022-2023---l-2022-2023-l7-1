package org.springframework.samples.kingdommaps.web;

import java.security.Principal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import java.util.Map;

@Controller
public class RulesController {

	@GetMapping({"/rules"})
	public ModelAndView showRules(Map<String, Object> model, Principal principal) {
		ModelAndView res = new ModelAndView("rules");
		if(principal != null){
			res.addObject("username", principal.getName());
		}
		return res;
	}
}
