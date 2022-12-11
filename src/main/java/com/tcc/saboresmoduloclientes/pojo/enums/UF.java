package com.tcc.saboresmoduloclientes.pojo.enums;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum UF {
	
	ACRE				(1,		"Acre",					"AC"),
	ALAGOAS				(2,		"Alagoas",				"AL"),
	AMAPA				(3,		"Amapá",				"AP"),
	AMAZONAS			(4,		"Amazonas",				"AM"),
	BAHIA				(5,		"Bahia",				"BA"),
	CEARA				(6,		"Ceará",				"CE"),
	DISTRITO_FEDERAL	(7,		"Distrito Federal",		"DF"),
	ESPIRITO_SANTO		(8,		"Espírito Santo",		"ES"),
	GOIAS				(9,		"Goiás",				"GO"),
	MARANHAO			(10,	"Maranhão",				"MA"),
	MATO_GROSSO			(11,	"Mato Grosso",			"MT"),
	MATO_GROSSO_DO_SUL	(12,	"Mato Grosso do Sul",	"MS"),
	MINAS_GERAIS		(13,	"Minas Gerais",			"MG"),
	PARA				(14,	"Pará",					"PA"),
	PARAIBA				(15,	"Paraíba",				"PB"),
	PARANA				(16,	"Paraná",				"PR"),
	PERNAMBUCO			(17,	"Pernambuco",			"PE"),
	PIAUI				(18,	"Piauí",				"PI"),
	SANTA_CATARINA		(19,	"Santa Catarina",		"SC"),
	SAO_PAULO			(20,	"São Paulo",			"SP"),
	SERGIPE				(21,	"Sergipe",				"SE"),
	RIO_DE_JANEIRO		(22,	"Rio de Janeiro",		"RJ"),
	RIO_GRANDE_DO_NORTE	(23,	"Rio Grande do Norte",	"RN"),
	RIO_GRANDE_DO_SUL	(24,	"Rio Grande do Sula",	"RS"),
	RONDONIA			(25,	"Rondônia",				"RO"),
	RORAIMA				(26,	"Roraima",				"RR"),
	TOCANTINS			(27,	"Tocantis",				"TO");
	
	private Integer valor;
	private String descricao;
	private String sigla;
	
	private UF(Integer valor, String descricao, String sigla) {
		setValor(valor);
		setDescricao(descricao);
		setSigla(sigla);
	}
	
	public Integer getValor() 		{ return valor; 	}
	public String getDescricao() 	{ return descricao; }
	public String getSigla() 		{ return sigla; 	}
	
	private void setValor(Integer valor) 		{ this.valor = valor; 			}
	private void setDescricao(String descricao) { this.descricao = descricao; 	}
	private void setSigla(String sigla) 		{ this.sigla = sigla; 			}
	
	public List<UF> listar() {
		return new ArrayList<UF>(Arrays.asList(UF.values()));
	}
	
	public UF obter(Integer valor) {
		for (UF uf : listar()) {
			if (uf.getValor().equals(valor)) {
				return uf;
			}
		}
		
		return null;
	}
}
