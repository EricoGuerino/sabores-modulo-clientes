package com.tcc.saboresmoduloclientes.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
public class Login implements Serializable {
	
	private static final long serialVersionUID = -7127158437848562029L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@OneToOne
	private Cliente cliente;
	
	private String session;
	private String password;
	private Boolean ativo;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dtHrLogin;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dtHrUltimoLogin;
	
	@Transient
	private Boolean ok;
	
	@Transient
	private String mensagem;
	
	public Login() {}
	public Login(Cliente cliente, String password) {
		setCliente(cliente);
		setSession(criarHashSession());
		setPassword(password);
		setAtivo(Boolean.FALSE);
		setDtHrLogin(null);
		setDtHrUltimoLogin(null);
	}
	public Login(Cliente cliente, String session, String password, Boolean ativo, Date dtHrLogin, Date dtHrUltimoLogin) {
		setCliente(cliente);
		setSession(session);
		setPassword(password);
		setAtivo(ativo);
		setDtHrLogin(dtHrLogin);
		setDtHrUltimoLogin(dtHrUltimoLogin);
	}
	
	public Integer getId() 				{ return id; 				}
	public Cliente getCliente() 		{ return cliente; 			}
	public String getSession() 			{ return session; 			}
	public String getPassword() 		{ return password; 			}
	public Boolean getAtivo() 			{ return ativo; 			}
	public Date getDtHrLogin() 			{ return dtHrLogin; 		}
	public Date getDtHrUltimoLogin() 	{ return dtHrUltimoLogin; 	}
	public Boolean getOk() 				{ return ok; 				}
	public String getMensagem() 		{ return mensagem;			}
	
	public void setId(Integer id) 							{ this.id = id; 							}
	public void setCliente(Cliente cliente) 				{ this.cliente = cliente; 					}
	public void setSession(String session) 					{ this.session = session; 					}
	public void setPassword(String password) 				{ this.password = password; 				}
	public void setAtivo(Boolean ativo) 					{ this.ativo = ativo; 						}
	public void setDtHrLogin(Date dtHrLogin) 				{ this.dtHrLogin = dtHrLogin; 				}
	public void setDtHrUltimoLogin(Date dtHrUltimoLogin) 	{ this.dtHrUltimoLogin = dtHrUltimoLogin;	}
	public void setOk(Boolean ok) 							{ this.ok = ok;								}
	public void setMensagem(String mensagem) 				{ this.mensagem = mensagem;					}
	
	public String criarHashSession() {
		return String.valueOf(cliente.getEmail().concat(new Date().toString()).hashCode());
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Login other = (Login) obj;
		return Objects.equals(id, other.id);
	}
}
