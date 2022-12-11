package com.tcc.saboresmoduloclientes.services;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tcc.saboresmoduloclientes.pojo.Cliente;
import com.tcc.saboresmoduloclientes.pojo.Login;
import com.tcc.saboresmoduloclientes.pojo.enums.TipoCliente;
import com.tcc.saboresmoduloclientes.repositories.LoginRepository;
import com.tcc.saboresmoduloclientes.services.exceptions.ObjetoNaoEncontradoException;

@Service
public class LoginService {
	
	@Autowired
	private LoginRepository loginRepository;
	
	public Login doLogin(String email, String password) {
		Login login = null;
		try {
			login = loginRepository.obterLogin(email, password);
			if (login == null) {
				throw new ObjetoNaoEncontradoException("Login / Cliente não encontrado! Favor realizar o cadastro.");
			}
		} catch (Exception e) {
			login = new Login();
			login.setOk(Boolean.FALSE);
			login.setMensagem(e.getMessage());
			
			return login;
		}
		
		TipoCliente tipoCliente = login.getCliente().getTipoCliente();
		login.setAtivo(Boolean.TRUE);
		login.setDtHrLogin(new Date());
		login.setDtHrUltimoLogin(login.getDtHrLogin());
		login.setSession(login.criarHashSession());
		login.setOk(Boolean.TRUE);
		login.setMensagem("Seja bem vindo, "+(tipoCliente == TipoCliente.FISICA 
				? login.getCliente().getNome()
				: login.getCliente().getNomeFantasia()));
		
		loginRepository.save(login);
		
		return login;
	}
	
	public Login doLogout(Cliente cliente) {
		Login login = null;
		try {
			login = loginRepository.obterLogin(cliente);
			if (login == null) {
				throw new ObjetoNaoEncontradoException("Login / Cliente não encontrado!");
			}
			
		} catch (Exception e) {
			login = new Login();
			login.setOk(Boolean.FALSE);
			login.setMensagem(e.getMessage());
			
			return login;
		}
		
		login.setAtivo(Boolean.FALSE);
		login.setDtHrLogin(null);
		login.setDtHrUltimoLogin(null);
		login.setSession(null);
		login.setOk(Boolean.TRUE);
		login.setMensagem("Logoff realizado com sucesso.");
		
		loginRepository.save(login);
		
		return login;
	}
	
	public Login checkSession(String session) {
		Login login = null;
		
		try {
			login = loginRepository.checkSession(session);
			if (login == null) {
				throw new ObjetoNaoEncontradoException("Cliente não está logado");
			}
			
			Calendar agora = Calendar.getInstance();
			agora.add(Calendar.MINUTE, -30);
			Date agoraMenos30Minutos = agora.getTime();
			
			if (!login.getAtivo() || login.getDtHrLogin().before(agoraMenos30Minutos)) {
				throw new Exception("Sua sessão expirou, favor logar-se novamente.");
			}
			
			login.setAtivo(Boolean.TRUE);
			login.setDtHrLogin(new Date());
			login.setOk(Boolean.TRUE);
			
			loginRepository.save(login);
			
		} catch (Exception e) {
			login = new Login();
			login.setOk(Boolean.FALSE);
			login.setMensagem(e.getMessage());
			
			return login;
		}
		
		return login;
	}

	public Login obterLogin(Integer id) {
		return loginRepository.obterLogin(id);
	}

	public Login trocarSenha(Cliente cliente, Map<String,Object> mapLogin) throws Exception {
		Login login = loginRepository.obterLogin(cliente);
		String passwordAntigo = (String)mapLogin.get("passwordAntigo");
		String passwordNovo1 = (String)mapLogin.get("passwordNovo1");
		String passwordNovo2 = (String)mapLogin.get("passwordNovo2");
		
		try {
			if (passwordAntigo == null || passwordAntigo.equals("")) {
				throw new Exception("Password Antigo é Obrigatório!");
			}
			if ((passwordNovo1 == null || passwordNovo1.equals("")) && (passwordNovo2 == null || passwordNovo2.equals(""))) {
				throw new Exception("Para trocar a senha é preciso preencher o campo de 'Nova Senha' e também o 'Confirmar Senha'!");
			}
			if (!passwordAntigo.equals(login.getPassword())) {
				throw new Exception("Senha Antiga não corresponde a senha gravada!");
			}
			if (!passwordNovo1.equals(passwordNovo2)) {
				throw new Exception("Para trocar a senha é preciso que os campos 'Nova Senha' e 'Confirmar Senha' sejam iguais!");
			}
			
			login.setPassword(passwordNovo1);
			login = loginRepository.save(login);
		} catch (Exception e) {
			throw e;
		}
		
		return login;
	}
	
}
