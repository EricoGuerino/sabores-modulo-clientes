package com.tcc.saboresmoduloclientes.pojo.enums;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum TipoLogradouro {
	RUA			(1, "Rua", 		"R."	),
	AVENIDA		(2, "Avenida", 	"Av."	),
	ALAMEDA		(3, "Alameda", 	"Al."	),
	TRAVESSA	(4, "Travessa", "Tv."	),
	RODOVIA		(5, "Rodovia", 	"Rod."	);
	
	private Integer valor;
	private String descricao;
	private String abreviatura;
	
	private TipoLogradouro(Integer valor, String descricao, String abreviatura) {
		setValor(valor);
		setDescricao(descricao);
		setAbreviatura(abreviatura);
	}
	
	public Integer getValor() 		{ return valor; 		}
	public String getDescricao() 	{ return descricao; 	}
	public String getAbreviatura() 	{ return abreviatura; 	}
	
	private void setValor(Integer valor) 			{ this.valor = valor; 				}
	private void setDescricao(String descricao) 	{ this.descricao = descricao; 		}
	private void setAbreviatura(String abreviatura) { this.abreviatura = abreviatura; 	}
	
	public static List<TipoLogradouro> listar() {
		return new ArrayList<TipoLogradouro>(Arrays.asList(TipoLogradouro.values()));
	}
	public static TipoLogradouro obterTipoLogradouro(Integer valor) {
		for (TipoLogradouro tl : listar()) {
			if (tl.getValor().equals(valor)) {
				return tl;
			}
		}
		
		return null;
	}
	public static TipoLogradouro obterTipoLogradouro(String tipo) {
		for (TipoLogradouro tl : listar()) {
			if (tl.name().equals(tipo)) {
				return tl;
			}
		}
		
		return null;
	}
}
