package com.tcc.saboresmoduloclientes.services;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.tcc.saboresmoduloclientes.pojo.Cidade;
import com.tcc.saboresmoduloclientes.pojo.Cliente;
import com.tcc.saboresmoduloclientes.pojo.Estado;
import com.tcc.saboresmoduloclientes.pojo.Login;
import com.tcc.saboresmoduloclientes.repositories.CidadeRepository;
import com.tcc.saboresmoduloclientes.repositories.ClienteRepository;
import com.tcc.saboresmoduloclientes.repositories.EstadoRepository;
import com.tcc.saboresmoduloclientes.repositories.LoginRepository;
import com.tcc.saboresmoduloclientes.services.exceptions.DataIntegrityException;
import com.tcc.saboresmoduloclientes.services.exceptions.ObjetoNaoEncontradoException;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private LoginRepository loginRepository;
	@Autowired
	private EstadoRepository estadoRepository;
	@Autowired
	private CidadeRepository cidadeRepository;
	
	public Cliente obterPeloId(Integer id) {
		Optional<Cliente> cliente = clienteRepository.findById(id);
		return cliente.orElseThrow(() -> new ObjetoNaoEncontradoException("Cliente Não Encontrado para este ID"));
	}
	
	public List<Cliente> listarTodos() {
		List<Cliente> clientes = clienteRepository.findAll();
		return clientes;
	}
	
	public Cliente insert(Map<String,Object> mapCliente) throws Exception {
		Map<String,String> mapClienteFinal = new HashMap<String,String>();
		for (Map.Entry<String, Object> mp : mapCliente.entrySet()) {
			if (mp.getValue() != null && (String)mp.getValue() != "" && (String)mp.getValue() != "undefined") {
				mapClienteFinal.put(mp.getKey(), (String)mp.getValue());
			}
		}
		
		Cliente cliente = new Cliente(mapClienteFinal);
		
		if (cliente.getId() == null) {
			String senha = cliente.getLogin().getPassword();
			cliente.setLogin(null);
			cliente.setDtHrCadastro(new Date());
			cliente = clienteRepository.save(cliente);
			Login login = new Login();
			login.setPassword(senha);
			login.setCliente(cliente);
			loginRepository.save(login);
			cliente.setLogin(login);
		} else {
			cliente.setLogin(null);
			cliente = clienteRepository.update(cliente);
		}
		return cliente;
	}
	
	public Cliente update(Cliente cliente) {
		obterPeloId(cliente.getId());
		return clienteRepository.save(cliente);
	}
	
	public void delete(Integer id) {
		Cliente cliente = obterPeloId(id);
		Login login = loginRepository.obterLogin(cliente);
		try {
			loginRepository.delete(login);
			clienteRepository.deleteById(id);
		} catch (DataIntegrityViolationException dive) {
			throw new DataIntegrityException("Não é possivel excluir o recurso, verifique dependências.");
		}
	}
	
	//Endereco
	public List<Estado> carregarOpcoesUf() {
		return estadoRepository.findAll();
	}
	
	public List<Cidade> carregarCidadePorUf(Integer idEstado) {
		return cidadeRepository.carregarCidadesPorUf(idEstado);
	}

	public Estado obterEstado(Integer id) {
		return estadoRepository.findById(id).get();
	}

	public Cidade obterCidade(Integer id) {
		return cidadeRepository.findById(id).get();
	}
}
