package com.tcc.saboresmoduloclientes.pojo;

import javax.persistence.Embeddable;

import com.tcc.saboresmoduloclientes.pojo.enums.TipoLogradouro;

@Embeddable
public class Endereco {
	
	private String tipoLogradouro;
	private String logradouro;
	private Integer numLogradouro;
	private String complemento;
	private String bairro;
	private Integer cep;
	private Integer cidade;
	private Integer uf;
	
	public String getTipoLogradouro() 	{ return tipoLogradouro; 	}
	public String getLogradouro() 		{ return logradouro; 		}
	public Integer getNumLogradouro() 	{ return numLogradouro; 	}
	public String getComplemento() 		{ return complemento; 		}
	public String getBairro() 			{ return bairro; 			}
	public Integer getCep() 			{ return cep; 				}
	public Integer getCidade() 			{ return cidade; 			}
	public Integer getUf() 				{ return uf; 				}
	
	public void setTipoLogradouro(String tipoLogradouro) 	{ this.tipoLogradouro = tipoLogradouro; }
	public void setLogradouro(String logradouro) 			{ this.logradouro = logradouro; 		}
	public void setNumLogradouro(Integer numLogradouro) 	{ this.numLogradouro = numLogradouro; 	}
	public void setComplemento(String complemento) 			{ this.complemento = complemento; 		}
	public void setBairro(String bairro) 					{ this.bairro = bairro; 				}
	public void setCep(Integer cep) 						{ this.cep = cep; 						}
	public void setCidade(Integer cidade) 					{ this.cidade = cidade; 				}
	public void setUf(Integer uf) 							{ this.uf = uf; 						}
	
	public String getTipoLogradouroFmt() {
		if (this.tipoLogradouro != null) {
			TipoLogradouro tipo = TipoLogradouro.obterTipoLogradouro(this.tipoLogradouro);
			if (tipo != null) {
				return tipo.getDescricao();
			}
		}
		return "";
	}
}
