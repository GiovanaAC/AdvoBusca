package com.tcc.advobusca.Controller;

import java.io.IOException;
import java.util.EnumSet;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.tcc.advobusca.Entity.Advogado;
import com.tcc.advobusca.Entity.AreaAtuacao;
import com.tcc.advobusca.Enums.Genero;
import com.tcc.advobusca.Repository.AdvogadoRepository;
import com.tcc.advobusca.Repository.AreaAtuacaoRepository;
import com.tcc.advobusca.Service.AdvogadoService;

@Controller
@RequestMapping("/advogado")
public class AdvogadoController {

	private AdvogadoService advogadoService;
	private AdvogadoRepository advogadoRepository;
	private AreaAtuacaoRepository areaAtuacaoRepository;
	
	
	public AdvogadoController(AdvogadoService advogadoService, AdvogadoRepository advogadoRepository, AreaAtuacaoRepository areaAtuacaoRepository) {
		super();
		this.advogadoService = advogadoService;
		this.advogadoRepository = advogadoRepository;
		this.areaAtuacaoRepository = areaAtuacaoRepository;
	}
	
	private String noImage = "/images/images.png";
	private Advogado advogado = null;
	private String serverMessage = null;
	private String foto = "";
	
	
	@GetMapping("/home")
	public String acessar(HttpSession session, ModelMap model) {
	    if (session.getAttribute("advogado") == null) {
	    } else {
	        session.setAttribute("advogado", session.getAttribute("advogado"));
	    }
	    
	    return "home-adv";
	}
	
	
	@GetMapping("/login")
	public String getLogin(ModelMap model) {
		
		model.addAttribute("advogado", new Advogado());
		model.addAttribute("serverMessage",serverMessage);
		
		return "loginAdvogado";
		
	}
	
	
	@PostMapping("/entrar")
	public String entrar(@RequestParam("email") String email, @RequestParam("senha") String senha, HttpSession session, ModelMap model) {
	    Advogado advogado = advogadoService.entrar(email, senha);
	    
	    if (advogado != null) {
	        session.setAttribute("advogado", advogado);
	        return "redirect:/advogado/home";
	    }
	    
	    serverMessage = "Dados incorretos!!";
	    model.addAttribute("serverMessage", serverMessage);
	    
	    return "redirect:/advogado/login";
	}
	
	@GetMapping("/cadastro")
	public String getCadastro(ModelMap model) {
		
		model.addAttribute("advogado", new Advogado());
		model.addAttribute("generos", EnumSet.allOf(Genero.class));
		model.addAttribute("noImage", noImage);
		model.addAttribute("serverMessage", serverMessage);
		List<AreaAtuacao> areasAtuacao = areaAtuacaoRepository.findAll();
        model.addAttribute("areasAtuacao", areasAtuacao);
		
		return "new-account-adv";
		
	}
	
	@PostMapping("/cadastrar")
	public String postCadastro(
			ModelMap model,
			@RequestParam(value = "file", required = false) MultipartFile file, @ModelAttribute("advogado") Advogado advogado) {

		Advogado p = advogadoService.findByEmail(advogado.getEmail());
		
		if(p != null) {
			
			serverMessage = "Advogado cadastrado já existe";
			return "redirect:/advogado/cadastro" ;
			
		}
			
		advogadoService.saveNew(file, advogado);
			
		serverMessage = "Advogado cadastrado com sucesso!!!";
		
		return "redirect:/advogado/cadastro";
	}
	
	
	@GetMapping("/showImage/{id}")
	@ResponseBody
	public ResponseEntity<byte[]> showImage(
	    @PathVariable("id") long id, HttpServletResponse response, Advogado advogado)
	    throws ServletException, IOException {

	    advogado = advogadoService.findById(id);

	    response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
	    if (advogado.getFoto() != null) {
	        return ResponseEntity.ok(advogado.getFoto());
	    } else {
	        return ResponseEntity.notFound().build();
	    }
	}
	
	@GetMapping("/profile")
	public String ProfilePage(@RequestParam("id") long id, Model model) {
	    try {
	        Advogado advogado = advogadoService.findById(id);
	        if (advogado != null) {
	            model.addAttribute("advogado", advogado);
	            return "profile";
	        } else {
	            model.addAttribute("error", "Advogado não encontrado");
	            return "error"; 
	        }
	    } catch (Exception e) {
	        model.addAttribute("error", "Ocorreu um erro ao buscar o advogado");
	        return "error";
	   }
	}
	@GetMapping("/minhaConta")
	public String profilePage(@RequestParam("id") long id, HttpSession session, Model model) {
	    try {
	        Advogado advogado = advogadoService.findById(id);
	        if (advogado != null) {
	        	session.setAttribute("advogado", advogado);
	            return "my-account-adv";
	        } else {
	            model.addAttribute("error", "advogado não encontrado");
	            return "error"; 
	        }
	    } catch (Exception e) {
	        model.addAttribute("error", "Ocorreu um erro ao buscar o advogado");
	        return "error";
	   }
	}
}
