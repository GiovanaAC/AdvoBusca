package com.tcc.advobusca.Controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api")
public class PageController {
	
	@GetMapping("/home")
	public String goHome(HttpSession session) {
		return "home";
	}
	@GetMapping("/sairCliente")
	public String sairCliente(HttpSession session) {
		session.removeAttribute("Cliente");
		return "home";
	}
	
	@GetMapping("/sairAdvogado")
	public String sairAdvogado(HttpSession session) {
		session.removeAttribute("Advogado");
		return "home";
	}
}
