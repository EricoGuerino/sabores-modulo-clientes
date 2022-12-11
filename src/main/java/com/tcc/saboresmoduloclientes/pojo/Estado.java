package com.tcc.saboresmoduloclientes.pojo;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Estado {
	@Id
	private Integer id;
	private String nome;
	private String uf;
	
	public Estado() {}
	
	public Integer getId() 	{ return id; 	}
	public String getNome() { return nome; 	}
	public String getUf() 	{ return uf; 	}
	
	public void setId(Integer id) { this.id = id; 			}
	public void setNome(String nome) { this.nome = nome; 	}
	public void setUf(String uf) { this.uf = uf; 			}

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
		Estado other = (Estado) obj;
		return Objects.equals(id, other.id);
	}
}
