package com.tcc.saboresmoduloclientes.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.tcc.saboresmoduloclientes.pojo.Cliente;
import com.tcc.saboresmoduloclientes.pojo.Endereco;
import com.tcc.saboresmoduloclientes.pojo.Login;
import com.tcc.saboresmoduloclientes.pojo.Telefone;
import com.tcc.saboresmoduloclientes.pojo.enums.TipoCliente;
import com.tcc.saboresmoduloclientes.pojo.enums.TipoLogradouro;
import com.tcc.saboresmoduloclientes.pojo.enums.TipoTelefone;

@Component
public class SetarValidarCliente {
	
	public static void dadosPessoais(Map<String,String> mapCliente, Cliente cliente) throws Exception {
		String _id = mapCliente.get("idCliente");
		String _tipoCliente = mapCliente.get("tipoCliente");
		String _cnpj = mapCliente.get("cnpj");
		String _cpf = mapCliente.get("cpf");
		String _ie = mapCliente.get("ie");
		String _nome = mapCliente.get("nome");
		String _nomeFantasia = mapCliente.get("nomeFantasia");
		String _razaoSocial = mapCliente.get("razaoSocial");
		String _site = mapCliente.get("site");
		
		if (_tipoCliente == null) {
			throw new Exception("Tipo Cliente é um campo obrigatório!");
		}
		TipoCliente tipoCliente = TipoCliente.valueOf(_tipoCliente);
		try {
			if (_id != null) {
				cliente.setId(Integer.valueOf(_id));
			}
			cliente.setTipoCliente(tipoCliente);
			if (tipoCliente.equals(TipoCliente.FISICA)) {
				if (_cpf == null) {
					throw new Exception("CPF é um campo obrigatório!");
				} else {
					cliente.setCpfCnpj(Long.valueOf(_cpf));
				}
				if (_nome == null) {
					throw new Exception("Nome é um campo obrigatório!");
				} else {
					cliente.setNome(_nome);
				}
			} else if (tipoCliente.equals(TipoCliente.JURIDICA)) {
				if (_cnpj == null) {
					throw new Exception("CNPJ é um campo obrigatório!");
				} else {
					cliente.setCpfCnpj(Long.valueOf(_cnpj));
				}
				if (_nomeFantasia == null) {
					throw new Exception("Nome Fantasia é um campo obrigatório!");
				} else {
					cliente.setNomeFantasia(_nomeFantasia);
				}
				if (_razaoSocial == null) {
					throw new Exception("Razão Social é um campo obrigatório!");
				} else {
					cliente.setRazaoSocial(_razaoSocial);
				}
				
				if (_ie != null) {
					cliente.setIe(_ie);
				}
				if (_site != null) {
					cliente.setSite(_site);
				}
			} else {
				throw new Exception("Tipo de Pessoa desconhecido!");
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	public static void endereco(Map<String,String> mapCliente, Cliente cliente) throws Exception {
		String _tipoLogradouro = mapCliente.get("tipoLogradouro");
		String logradouro = mapCliente.get("logradouro");
		String numero = mapCliente.get("numero");
		String complemento = mapCliente.get("complemento");
		String bairro = mapCliente.get("bairro");
		String cep = mapCliente.get("cep");
		String _uf = mapCliente.get("uf");
		String _cidade = mapCliente.get("cidade");
		
		Endereco endereco = new Endereco();
		try {
			if (_tipoLogradouro == null) {
				throw new Exception("Tipo de Logradouro é campo Obrigatório!");
			} else {
				TipoLogradouro tipoLogradouro = TipoLogradouro.valueOf(_tipoLogradouro);
				endereco.setTipoLogradouro(tipoLogradouro.getDescricao());
			}
			if (logradouro == null) {
				throw new Exception("Logradouro é campo Obrigatório!");
			} else {
				endereco.setLogradouro(logradouro);
			}
			if (numero == null) {
				throw new Exception("Número é campo Obrigatório!");
			} else {
				endereco.setNumLogradouro(Integer.valueOf(numero));
			}
			if (complemento != null) {
				endereco.setComplemento(complemento);
			}
			if (bairro == null) {
				throw new Exception("Bairro é campo Obrigatório!");
			} else {
				endereco.setBairro(bairro);
			}
			if (cep == null) {
				throw new Exception("CEP é campo Obrigatório!");
			} else {
				endereco.setCep(Integer.valueOf(cep));
			}
			if (_cidade == null) {
				throw new Exception("Cidade é campo Obrigatório!");
			} else {
				endereco.setCidade(Integer.valueOf(_cidade));
			}
			if (_uf == null) {
				throw new Exception("Estado (UF) é campo Obrigatório!");
			} else {
				endereco.setUf(Integer.valueOf(_uf));
			}
			cliente.setEndereco(endereco);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	public static void contatos(Map<String,String> mapCliente, Cliente cliente) throws Exception {
		String id1 = mapCliente.get("idTelefone1");
		String tipoTelefone1 = mapCliente.get("tipoTelefone1");
		String ddd1 = mapCliente.get("ddd1");
		String telefone1 = mapCliente.get("telefone1");
		String isWhatsapp1 = mapCliente.get("isWhatsapp1");
		
		String id2 = mapCliente.get("idTelefone2");
		String tipoTelefone2 = mapCliente.get("tipoTelefone2");
		String ddd2 = mapCliente.get("ddd2");
		String telefone2 = mapCliente.get("telefone2");
		String isWhatsapp2 = mapCliente.get("isWhatsapp2");
		
		String id3 = mapCliente.get("idTelefone3");
		String tipoTelefone3 = mapCliente.get("tipoTelefone3");
		String ddd3 = mapCliente.get("ddd3");
		String telefone3 = mapCliente.get("telefone3");
		String isWhatsapp3 = mapCliente.get("isWhatsapp3");
		
		Boolean validarTel1 = Boolean.FALSE, validarTel2 = Boolean.FALSE, validarTel3 = Boolean.FALSE;
		if ((tipoTelefone1 != null && !tipoTelefone1.equals("0")) || ddd1 != null  || telefone1 != null) {
			validarTel1 = Boolean.TRUE;
		}
		if ((tipoTelefone2 != null && !tipoTelefone2.equals("0")) || ddd2 != null  || telefone2 != null) {
			validarTel2 = Boolean.TRUE;
		}
		if ((tipoTelefone3 != null  && !tipoTelefone3.equals("0")) || ddd3 != null  || telefone3 != null) {
			validarTel3 = Boolean.TRUE;
		}
		
		if (!validarTel1 && !validarTel2 && !validarTel3) {
			throw new Exception("Pelo menos um dos contatos precisa ser preenchido!!");
		}
		
		List<Telefone> contatos = new ArrayList<Telefone>();
		
		try {
			if (validarTel1) {
				Telefone telefone = new Telefone();
				if (id1 != null) {
					telefone.setId(Integer.valueOf(id1));
				}
				if (tipoTelefone1 == null) {
					throw new Exception("Tipo Telefone do Contato 1 é campo Obrigatório!");
				} else {
					TipoTelefone tipo = TipoTelefone.valueOf(tipoTelefone1);
					telefone.setTipo(tipo);
				}
				if (ddd1 == null) {
					throw new Exception("DDD do Contato 1 é campo Obrigatório!");
				} else {
					telefone.setDdd(Integer.valueOf(ddd1));
				}
				if (telefone1 == null) {
					throw new Exception("Telefone do Contato 1 é campo Obrigatório!");
				} else {
					telefone.setNumero(Integer.valueOf(telefone1));
				}
				if (isWhatsapp1 != null) {
					telefone.setIsWhatsapp(isWhatsapp1.equals("true") || isWhatsapp1.equals("checked") ? Boolean.TRUE : Boolean.FALSE);
				}
				contatos.add(telefone);
			}
			
			if (validarTel2) {
				Telefone telefone = new Telefone();
				if (id2 != null) {
					telefone.setId(Integer.valueOf(id2));
				}
				if (tipoTelefone2 == null) {
					throw new Exception("Tipo Telefone do Contato 2 é campo Obrigatório!");
				} else {
					TipoTelefone tipo = TipoTelefone.valueOf(tipoTelefone2);
					telefone.setTipo(tipo);
				}
				if (ddd2 == null) {
					throw new Exception("DDD do Contato 2 é campo Obrigatório!");
				} else {
					telefone.setDdd(Integer.valueOf(ddd2));
				}
				if (telefone2 == null) {
					throw new Exception("Telefone do Contato 2 é campo Obrigatório!");
				} else {
					telefone.setNumero(Integer.valueOf(telefone2));
				}
				if (isWhatsapp2 != null) {
					telefone.setIsWhatsapp(isWhatsapp2.equals("true") || isWhatsapp2.equals("checked") ? Boolean.TRUE : Boolean.FALSE);
				}
				contatos.add(telefone);
			}
			
			if (validarTel3) {
				Telefone telefone = new Telefone();
				if (id3 != null) {
					telefone.setId(Integer.valueOf(id3));
				}
				if (tipoTelefone3 == null) {
					throw new Exception("Tipo Telefone do Contato 3 é campo Obrigatório!");
				} else {
					TipoTelefone tipo = TipoTelefone.valueOf(tipoTelefone3);
					telefone.setTipo(tipo);
				}
				if (ddd3 == null) {
					throw new Exception("DDD do Contato 3 é campo Obrigatório!");
				} else {
					telefone.setDdd(Integer.valueOf(ddd3));
				}
				if (telefone3 == null) {
					throw new Exception("Telefone do Contato 3 é campo Obrigatório!");
				} else {
					telefone.setNumero(Integer.valueOf(telefone3));
				}
				if (isWhatsapp3 != null) {
					telefone.setIsWhatsapp(isWhatsapp3.equals("true") || isWhatsapp3.equals("checked") ? Boolean.TRUE : Boolean.FALSE);
				}
				contatos.add(telefone);
			}
			cliente.setContato(contatos);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	public static void login(Map<String,String> mapCliente, Cliente cliente) throws Exception {
		String email = mapCliente.get("email");
		String password = mapCliente.get("password");
		if (email == null) {
			throw new Exception("Email é campo Obrigatório!");
		}
		if (cliente.getId() == null) {
			if (password == null) {
				throw new Exception("Senha é campo Obrigatório!");
			}
		}
		Login login = new Login();
		cliente.setEmail(email);
		login.setPassword(password);
		cliente.setLogin(login);
	}
}
