package br.ufpi.estagio.cge.controle;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IndexController {
	
	@RequestMapping("/")
	public String getIndexPage() {
		return "index";
	}
	
	@RequestMapping("/views/{page}")
	public String getpartialHandlerPage(@PathVariable("page") final String page) {
		return page;
	}
	
}
