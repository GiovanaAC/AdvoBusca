package com.tcc.advobusca.Controller;

import java.security.Principal;
import java.util.Base64;
import java.util.EnumSet;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tcc.advobusca.Entity.Advogado;
import com.tcc.advobusca.Entity.Cliente;
import com.tcc.advobusca.Enums.Genero;
import com.tcc.advobusca.Service.AdvogadoService;
import com.tcc.advobusca.Service.ClienteService;
import com.tcc.advobusca.Repository.AdvogadoRepository;

@Controller
@RequestMapping("/cliente")
public class ClienteController {
	List<Advogado> advogado = null;
	

	private String noImage = "/images/images.png";
	private String foto = null;

	private ClienteService clienteService;
	private AdvogadoService advogadoService;
	private AdvogadoRepository advogadoRepository;
	
	public ClienteController(ClienteService clienteService, AdvogadoService advogadoService, AdvogadoRepository advogadoRepository) {
		super();
		this.clienteService = clienteService;
		this.advogadoService = advogadoService;
		this.advogadoRepository = advogadoRepository;
		
	}
	
	private Cliente cliente = null;
	private String serverMessage = null;
	
	@GetMapping("/home")
	public String acessar(HttpSession session, ModelMap model) {
	    if (session.getAttribute("cliente") == null) {
	    } else {
	        session.setAttribute("cliente", session.getAttribute("cliente"));
	    }
	    
	    return "home-lgg";
	}
	
	@GetMapping("/login")
	public String getLogin(ModelMap model) {
		
		model.addAttribute("cliente", new Cliente());
		model.addAttribute("serverMessage",serverMessage);
		
		return "loginCliente";
		
	}
	
	
	@PostMapping("/entrar")
	public String entrar(@RequestParam("email") String email, @RequestParam("senha") String senha, HttpSession session, ModelMap model) {
	    Cliente cliente = clienteService.entrar(email, senha);
	    
	    if (cliente != null) {
	        session.setAttribute("cliente", cliente);
	        return "redirect:/cliente/home";
	    }
	    
	    serverMessage = "Dados incorretos!!";
	    model.addAttribute("serverMessage", serverMessage);
	    
	    return "redirect:/cliente/login";
	}
	
	@GetMapping("/cadastro")
	public String getCliente(ModelMap model) {

		model.addAttribute("cliente", new Cliente());
		model.addAttribute("serverMessage", serverMessage);
		model.addAttribute("generos", EnumSet.allOf(Genero.class));

		return "new-account";
	}
	
	@PostMapping("/cadastrar")
	public String postCadastro(
			ModelMap model,
			@ModelAttribute("cliente") Cliente cliente, HttpSession session) {

		Cliente p = clienteService.findByEmail(cliente.getEmail());
		
		if(p != null) {
			
			serverMessage = "Usuario já existe";
			return "cliente/cadastro" ;
			
		}
			
		clienteService.saveNew(cliente);
			
		serverMessage = "Usuário cadastrado com sucesso!!!";
		
		return "redirect:/cliente/home";
	}

	
	
	@GetMapping("/lista")
    public String listarAdvogados(Model model) {
        List<Advogado> advogados = (List<Advogado>) advogadoRepository.findAll();
        model.addAttribute("advogados", advogados);
        return "list";
    }
	
	@GetMapping("/minhaConta")
	public String profilePage(@RequestParam("id") long id, Model model) {
	    try {
	        Cliente cliente = clienteService.findById(id);
	        if (cliente != null) {
	            model.addAttribute("cliente", cliente);
	            return "my-account";
	        } else {
	            model.addAttribute("error", "Cliente não encontrado");
	            return "error"; 
	        }
	    } catch (Exception e) {
	        model.addAttribute("error", "Ocorreu um erro ao buscar o Cliente");
	        return "error";
	   }
	}

	
	
}
