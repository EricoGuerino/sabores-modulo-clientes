package com.tcc.saboresmoduloclientes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tcc.saboresmoduloclientes.pojo.Cliente;
import com.tcc.saboresmoduloclientes.pojo.Login;

@Repository
public interface LoginRepository extends JpaRepository<Login, Cliente>{
	
	@Query("SELECT log FROM Login log WHERE log.cliente.email = ?1 AND log.password = ?2")
	abstract Login obterLogin(String email, String password);
	
	@Query("SELECT log FROM Login log WHERE log.cliente = ?1")
	abstract Login obterLogin(Cliente cliente);
	
	@Query("SELECT log FROM Login log WHERE log.cliente.id = ?1")
	abstract Login obterLogin(Integer id);
	
	@Query("SELECT log FROM Login log WHERE log.session = ?1")
	abstract Login checkSession(String session);
}
