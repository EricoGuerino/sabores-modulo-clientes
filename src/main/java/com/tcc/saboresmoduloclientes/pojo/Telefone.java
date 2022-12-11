package com.tcc.saboresmoduloclientes.pojo;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.tcc.saboresmoduloclientes.pojo.enums.TipoTelefone;

@Entity
public class Telefone implements Serializable {
	
	private static final long serialVersionUID = 7973136360900486371L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Integer ddd;
	private Integer numero;
	@Enumerated(EnumType.STRING)
	private TipoTelefone tipo;
	private Boolean isWhatsapp;
	
	public Telefone() {}
	
	public Integer getId() 			{ return id; 			}
	public Integer getDdd() 		{ return ddd; 			}
	public Integer getNumero() 		{ return numero; 		}
	public TipoTelefone getTipo() 	{ return tipo; 			}
	public Boolean getIsWhatsapp() 	{ return isWhatsapp; 	}

	public void setId(Integer id) 					{ this.id = id; 				}
	public void setDdd(Integer ddd) 				{ this.ddd = ddd; 				}
	public void setNumero(Integer numero) 			{ this.numero = numero; 		}
	public void setTipo(TipoTelefone tipo) 			{ this.tipo = tipo; 			}
	public void setIsWhatsapp(Boolean isWhatsapp) 	{ this.isWhatsapp = isWhatsapp; }
	
	public String getTipoTelefoneFmt() {
		if (this.tipo != null) {
			if (tipo.equals(TipoTelefone.CELULAR)) {
				return "Celular";
			} else {
				return "Fixo";
			}
		}
		
		return "";
	}
	public String getTelefoneFmt() {
		if (this.ddd != null && this.getNumero() != null) {
			return "("+this.ddd.toString()+") "+this.numero;
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
		Telefone other = (Telefone) obj;
		return Objects.equals(id, other.id);
	}
}
