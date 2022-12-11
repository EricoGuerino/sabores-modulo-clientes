package com.tcc.saboresmoduloclientes.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tcc.saboresmoduloclientes.pojo.Cidade;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Integer> {
	
	@Query("SELECT cidade FROM Cidade cidade WHERE cidade.estado.id = ?1")
	List<Cidade> carregarCidadesPorUf(Integer idEstado);
}
