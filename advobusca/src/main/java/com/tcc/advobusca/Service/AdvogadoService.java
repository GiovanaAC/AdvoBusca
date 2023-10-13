package com.tcc.advobusca.Service;

import java.io.IOException;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.tcc.advobusca.Entity.Advogado;
import com.tcc.advobusca.Entity.Cliente;
import com.tcc.advobusca.Enums.Status;
import com.tcc.advobusca.Repository.AdvogadoRepository;

@Service
public class AdvogadoService {

    private AdvogadoRepository advogadoRepository;

    @Autowired
    public AdvogadoService(AdvogadoRepository advogadoRepository) {
        this.advogadoRepository = advogadoRepository;
    }
    
    public Advogado findById(long id) {
		return advogadoRepository.findById(id).get();
	}

	public Advogado findByEmail(String email){
		
		Advogado advogado = advogadoRepository.findByEmail(email);
		
		return advogado;
		
	}
	
	@Transactional
	public Advogado entrar(String email, String senha) {
		
		Advogado advogado = advogadoRepository.findByEmail(email);
		
		if(advogado != null && !advogado.getStatusAdvogado().equals(Status.INATIVO)) {
			if(advogado.getSenha().equals(senha)) {
				
				return advogado;
			}
			
		}
		
		return null;
		
	}
	
	@Transactional
	public Advogado saveNew(MultipartFile file, Advogado advogado) {
		
	    if (file != null && file.getSize() > 0) {
	        try {
	           byte[] foto = file.getBytes();
	            advogado.setFoto(foto);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    } else {
	        advogado.setFoto(null);
	    }

	    advogado.setStatusAdvogado(Status.ATIVO);
	    
	    return advogadoRepository.save(advogado);
	}

	public List<Advogado> findAll(){
		return (List<Advogado>) advogadoRepository.findAll();
	}

}




