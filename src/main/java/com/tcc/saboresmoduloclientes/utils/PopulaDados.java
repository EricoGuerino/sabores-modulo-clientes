package com.tcc.saboresmoduloclientes.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tcc.saboresmoduloclientes.pojo.Cliente;
import com.tcc.saboresmoduloclientes.pojo.Endereco;
import com.tcc.saboresmoduloclientes.pojo.Login;
import com.tcc.saboresmoduloclientes.pojo.Telefone;
import com.tcc.saboresmoduloclientes.pojo.enums.TipoCliente;
import com.tcc.saboresmoduloclientes.pojo.enums.TipoLogradouro;
import com.tcc.saboresmoduloclientes.pojo.enums.TipoTelefone;
import com.tcc.saboresmoduloclientes.repositories.ClienteRepository;
import com.tcc.saboresmoduloclientes.repositories.LoginRepository;

@Component
public class PopulaDados {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private LoginRepository loginRepository;
	
	@PostConstruct
	public void populaDados() {
		List<Cliente> clientes = clienteRepository.findAll();
		
		if (clientes.isEmpty()) {
			Cliente cliente = new Cliente();
			cliente.setId(1);
			cliente.setNome("Erico Guerino");
			cliente.setEmail("ericofgg@gmail.com");
			cliente.setTipoCliente(TipoCliente.FISICA);
			cliente.setCpfCnpj(5467871692L);
			cliente.setDtHrCadastro(new Date());
			Endereco endereco = new Endereco();
			endereco.setBairro("São Francisco");
			endereco.setCep(80510070);
			endereco.setCidade(2878);
			endereco.setComplemento("Apto 123");
			endereco.setLogradouro("Paula Gomes");
			endereco.setNumLogradouro(644);
			endereco.setTipoLogradouro(TipoLogradouro.RUA.name());
			endereco.setUf(18);
			cliente.setEndereco(endereco);
			Telefone tel = new Telefone();
			tel.setDdd(41);
			//tel.setId(1);
			tel.setIsWhatsapp(Boolean.TRUE);
			tel.setNumero(992511354);
			tel.setTipo(TipoTelefone.CELULAR);
			cliente.setContato(new ArrayList<Telefone>(Arrays.asList(tel)));
			
			clienteRepository.save(cliente);
			
			Login login = new Login();
			login.setId(1);
			login.setAtivo(Boolean.FALSE);
			login.setPassword("123");
			login.setCliente(cliente);
			login.setDtHrLogin(null);
			login.setDtHrUltimoLogin(null);
			
			loginRepository.save(login);
			clienteRepository.popularEstados();
			clienteRepository.popularCidades();
		}
	}
	
}
