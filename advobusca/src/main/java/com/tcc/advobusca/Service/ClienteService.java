package com.tcc.advobusca.Service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MultipartFile;

import com.tcc.advobusca.Entity.Cliente;
import com.tcc.advobusca.Entity.Advogado;
import com.tcc.advobusca.Enums.Status;
import com.tcc.advobusca.Repository.AdvogadoRepository;
import com.tcc.advobusca.Repository.ClienteRepository;

@Service
public class ClienteService {
	
	private AdvogadoRepository advogadoRepository;

	private ClienteRepository clienteRepository;
	
	public ClienteService(ClienteRepository clienteRepository, AdvogadoRepository advogadoRepository) {
		super();
		this.clienteRepository = clienteRepository ;
		this.advogadoRepository = advogadoRepository;
	}
	
	@Autowired
    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }
	public Cliente findById(long id) {
		return clienteRepository.findById(id).get();
	}
	public Cliente findByEmail(String email){
		
		Cliente cliente = clienteRepository.findByEmail(email);
		
		return cliente;
		
	}
	
	
	
	@Transactional
	public Cliente entrar(String email, String senha) {
		
		Cliente cliente = clienteRepository.findByEmail(email);
		
		if(cliente != null && !cliente.getStatusCliente().equals(Status.INATIVO)) {
			if(cliente.getSenha().equals(senha)) {
				
				return cliente;
			}
			
		}
		
		return null;
		
	}
	
	
	@Transactional
	public Cliente saveNew( Cliente cliente) {

		
		cliente.setStatusCliente(Status.ATIVO);
		
		return clienteRepository.save(cliente);
	}
	

	
}
