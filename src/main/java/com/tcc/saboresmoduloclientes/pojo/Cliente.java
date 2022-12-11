package com.tcc.saboresmoduloclientes.pojo;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tcc.saboresmoduloclientes.pojo.enums.TipoCliente;
import com.tcc.saboresmoduloclientes.repositories.CidadeRepository;
import com.tcc.saboresmoduloclientes.repositories.EstadoRepository;
import com.tcc.saboresmoduloclientes.utils.SetarValidarCliente;
import com.tcc.saboresmoduloclientes.utils.Util;

@Entity
public class Cliente implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Long cpfCnpj;
	private String ie;
	@Enumerated(value = EnumType.STRING)
	private TipoCliente tipoCliente;
	private String nome;
	private String nomeFantasia;
	private String razaoSocial;
	private String site;
	private String email;
	@Temporal(TemporalType.TIMESTAMP)
	private Date dtHrCadastro;
	@Embedded
	@AttributeOverrides({
	       @AttributeOverride(name="tipoLogradouro", 	column=@Column(name="tipo_logradouro")	),
	       @AttributeOverride(name="logradouro", 		column=@Column(name="logradouro")		),
	       @AttributeOverride(name="numLogradouro", 	column=@Column(name="numero")			),
	       @AttributeOverride(name="complemento", 		column=@Column(name="complemento")		),
	       @AttributeOverride(name="bairro", 			column=@Column(name="bairro")			),
	       @AttributeOverride(name="cep", 				column=@Column(name="cep")				),
	       @AttributeOverride(name="uf", 				column=@Column(name="uf")				)
	   })
	private Endereco endereco;
	@OneToMany(cascade = CascadeType.ALL)
	private List<Telefone> contato;
	@JsonIgnore
	@OneToOne(mappedBy = "cliente")
	@JoinColumn(name = "LOGIN_ID", referencedColumnName = "id")
	private Login login;
	
	public Cliente() {}
	public Cliente(Map<String,String> mapCliente) throws Exception {
		SetarValidarCliente.dadosPessoais(mapCliente, this);
		SetarValidarCliente.endereco(mapCliente, this);
		SetarValidarCliente.contatos(mapCliente, this);
		SetarValidarCliente.login(mapCliente, this);
	}
	
	public Integer getId() 				{ return id; 			}
	public Long getCpfCnpj() 			{ return cpfCnpj; 		}
	public String getIe() 				{ return ie; 			}
	public TipoCliente getTipoCliente() { return tipoCliente; 	}
	public String getNome() 			{ return nome; 			}
	public String getNomeFantasia() 	{ return nomeFantasia; 	}
	public String getRazaoSocial() 		{ return razaoSocial; 	}
	public String getSite() 			{ return site; 			}
	public String getEmail()			{ return email;			}
	public Date getDtHrCadastro() 		{ return dtHrCadastro; 	}
	public Endereco getEndereco() 		{ return endereco; 		}
	public List<Telefone> getContato() 	{ return contato; 		}
	public Login getLogin() 			{ return login;			}
	
	public void setId(Integer id) 						{ this.id = id;						}
	public void setCpfCnpj(Long cpfCnpj) 				{ this.cpfCnpj = cpfCnpj; 			}
	public void setIe(String ie) 						{ this.ie = ie; 					}
	public void setTipoCliente(TipoCliente tipoCliente) { this.tipoCliente = tipoCliente; 	}
	public void setNome(String nome) 					{ this.nome = nome; 				}
	public void setNomeFantasia(String nomeFantasia) 	{ this.nomeFantasia = nomeFantasia; }
	public void setRazaoSocial(String razaoSocial) 		{ this.razaoSocial = razaoSocial; 	}
	public void setSite(String site) 					{ this.site = site; 				}
	public void setEmail(String email)					{ this.email = email;				}
	public void setDtHrCadastro(Date dtHrCadastro) 		{ this.dtHrCadastro = dtHrCadastro; }
	public void setEndereco(Endereco endereco) 			{ this.endereco = endereco; 		}
	public void setContato(List<Telefone> contato) 		{ this.contato = contato; 			}
	public void setLogin(Login login) 					{ this.login = login;				}
	
	public String getDtHrCadastroFmt() {
		if (this.dtHrCadastro != null) {
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy hh:mm", new Locale("PT", "BR"));
			return df.format(this.dtHrCadastro);
		}
		return "";
	}
	public String getCpfFmt() {
		if (this.cpfCnpj != null && this.tipoCliente == TipoCliente.FISICA) {
			return Util.formataCPF(this.cpfCnpj.toString());
		}
		return "";
	}
	public String getCnpjFmt() {
		if (this.cpfCnpj != null && this.tipoCliente == TipoCliente.JURIDICA) {
			return Util.formataCNPJ(this.cpfCnpj.toString());
		}
		return "";
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
		Cliente other = (Cliente) obj;
		return Objects.equals(id, other.id);
	}
	
}
