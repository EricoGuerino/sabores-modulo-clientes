package com.tcc.saboresmoduloclientes.pojo;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Cidade implements Serializable {
	
	private static final long serialVersionUID = 4313812465880649508L;
	@Id
	private Integer id;
	private String nome;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "id_estado", referencedColumnName = "id")
	private Estado estado;
	
	public Cidade() {}
	
	public Integer getId() 		{ return id; 		}
	public String getNome() 	{ return nome; 		}
	public Estado getEstado() 	{ return estado; 	}
	
	public void setId(Integer id) 			{ this.id = id; 		}
	public void setNome(String nome) 		{ this.nome = nome; 	}
	public void setEstado(Estado estado) 	{ this.estado = estado; }

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
		Cidade other = (Cidade) obj;
		return Objects.equals(id, other.id);
	}
}
