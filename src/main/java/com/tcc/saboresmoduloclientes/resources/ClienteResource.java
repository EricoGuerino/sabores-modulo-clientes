package com.tcc.saboresmoduloclientes.resources;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tcc.saboresmoduloclientes.pojo.Cidade;
import com.tcc.saboresmoduloclientes.pojo.Cliente;
import com.tcc.saboresmoduloclientes.pojo.Estado;
import com.tcc.saboresmoduloclientes.pojo.Login;
import com.tcc.saboresmoduloclientes.pojo.enums.TipoLogradouro;
import com.tcc.saboresmoduloclientes.services.ClienteService;
import com.tcc.saboresmoduloclientes.services.LoginService;
import com.tcc.saboresmoduloclientes.utils.Util;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private LoginService loginService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> listarClientes() {
		List<Cliente> clientes = clienteService.listarTodos();
		return Util.buildResponse(HttpStatus.OK).body(clientes);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> obterPorId(@PathVariable("id")Integer id) throws Exception {
		Cliente cliente = clienteService.obterPeloId(id);
		return Util.buildResponse(HttpStatus.OK).body(cliente);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> insert(@RequestBody Map<String,Object> mapCliente) {
		Cliente cliente = null; 
		Map<String,Object> retorno = new HashMap<String,Object>();
		try {
			cliente = clienteService.insert(mapCliente);
			retorno.put("mensagem", "");
			retorno.put("ok", true);
			retorno.put("cliente", cliente);
			return Util.buildResponse(HttpStatus.OK).body(retorno);
		} catch (Exception e) {
			retorno.put("mensagem", e.getMessage());
			retorno.put("ok", false);
			return Util.buildResponse(HttpStatus.INTERNAL_SERVER_ERROR).body(retorno);
		}
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> update(@RequestBody Map<String,Object> mapCliente) {
		Cliente cliente = null; 
		Map<String,Object> retorno = new HashMap<String,Object>();
		try {
			cliente = clienteService.insert(mapCliente);
			retorno.put("mensagem", "");
			retorno.put("ok", true);
			retorno.put("cliente", cliente);
			return Util.buildResponse(HttpStatus.OK).body(retorno);
		} catch (Exception e) {
			retorno.put("mensagem", e.getMessage());
			retorno.put("ok", false);
			return Util.buildResponse(HttpStatus.INTERNAL_SERVER_ERROR).body(retorno);
		}
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> delete(@PathVariable("id")Integer id) {
		Map<String,Object> retornoWS = new HashMap<String,Object>();
		try {
			clienteService.delete(id);
			retornoWS.put("ok", Boolean.TRUE);
		} catch (Exception e) {
			retornoWS.put("ok", Boolean.FALSE);
			retornoWS.put("mensagem", e.getMessage());
		}
		return Util.buildResponse(HttpStatus.OK).body(retornoWS);
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<Login> login(@RequestBody Map<String, Object> loginMap) {
		String email = (String)loginMap.get("email");
		String password = (String)loginMap.get("password");
		Login login = loginService.doLogin(email, password);
		return Util.buildResponse(HttpStatus.OK).body(login);
	}
	
	@RequestMapping(value = "/login/{id}", method = RequestMethod.GET)
	public ResponseEntity<Login> obterLoginPorCliente(@PathVariable("id") Integer id) {
		Login login = loginService.obterLogin(id);
		return Util.buildResponse(HttpStatus.OK).body(login);
	}
	
	@RequestMapping(value = "/login/trocarSenha/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> trocarSenha(@RequestBody Map<String,Object> mapLogin, @PathVariable("id") Integer id) {
		Cliente cliente = clienteService.obterPeloId(id); 
		Map<String,Object> retorno = new HashMap<String,Object>();
		try {
			Login login = loginService.trocarSenha(cliente, mapLogin);
			retorno.put("mensagem", "");
			retorno.put("ok", true);
			retorno.put("login", login);
			return Util.buildResponse(HttpStatus.OK).body(retorno);
		} catch (Exception e) {
			retorno.put("mensagem", e.getMessage());
			retorno.put("ok", false);
			return Util.buildResponse(HttpStatus.INTERNAL_SERVER_ERROR).body(retorno);
		}
	}
	@RequestMapping(value = "/login/checkSession", method = RequestMethod.POST)
	public ResponseEntity<Login> checkSession(@RequestBody Map<String, Object> loginMap) {
		String session = (String)loginMap.get("session");
		Login login = loginService.checkSession(session);
		return Util.buildResponse(HttpStatus.OK).body(login);
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	public ResponseEntity<Login> logout(@RequestBody Map<String, Object> loginMap) {
		String clienteId = (String)loginMap.get("clienteId");
		Cliente cliente = clienteService.obterPeloId(Integer.valueOf(clienteId));
		Login login = loginService.doLogout(cliente);
		return Util.buildResponse(HttpStatus.OK).body(login);
	}
	
	@RequestMapping(value = "/endereco/uf", method = RequestMethod.GET)
	public ResponseEntity<?> carregarOpcoesUf() throws Exception {
		List<Estado> estados = clienteService.carregarOpcoesUf();
		return Util.buildResponse(HttpStatus.OK).body(estados);
	}
	
	@RequestMapping(value = "/endereco/cidades/{estado_id}", method = RequestMethod.GET)
	public ResponseEntity<?> carregarCidadesPorUf(@PathVariable("estado_id")Integer id) throws Exception {
		List<Cidade> cidades = clienteService.carregarCidadePorUf(id);
		return Util.buildResponse(HttpStatus.OK).body(cidades);
	}
	
	@RequestMapping(value = "/endereco/tiposLogradouro", method = RequestMethod.GET)
	public ResponseEntity<?> carregarOpcoesTipoLogradouro() throws Exception {
		List<TipoLogradouro> tipos = TipoLogradouro.listar();
		return Util.buildResponse(HttpStatus.OK).body(tipos);
	}
	
	@RequestMapping(value = "/endereco/estado/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> obterEstado(@PathVariable("id") Integer id) throws Exception {
		Estado estado = clienteService.obterEstado(id);
		return Util.buildResponse(HttpStatus.OK).body(estado);
	}
	
	@RequestMapping(value = "/endereco/cidade/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> obterCidade(@PathVariable("id") Integer id) throws Exception {
		Cidade cidade = clienteService.obterCidade(id);
		return Util.buildResponse(HttpStatus.OK).body(cidade);
	}
}
