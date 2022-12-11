package com.tcc.saboresmoduloclientes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tcc.saboresmoduloclientes.pojo.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer>{
	abstract void popularEstados();
	abstract void popularCidades();
	abstract Cliente update(Cliente cliente);
}
