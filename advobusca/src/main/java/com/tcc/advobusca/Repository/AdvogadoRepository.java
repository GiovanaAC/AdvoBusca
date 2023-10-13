package com.tcc.advobusca.Repository;

import org.springframework.stereotype.Repository;

import com.tcc.advobusca.Entity.Advogado;

import org.springframework.data.repository.CrudRepository;

@Repository

public interface AdvogadoRepository extends CrudRepository<Advogado, Long> {
    Advogado findByEmail(String email);
}