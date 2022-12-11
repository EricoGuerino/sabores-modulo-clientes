package com.tcc.saboresmoduloclientes.utils;

import java.net.URI;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;

public class Util {
	
	public static BodyBuilder buildResponse(HttpStatus status) {
		BodyBuilder body = ResponseEntity.status(status);
		//body.header("Access-Control-Allow-Origin", "*");
		body.contentType(MediaType.APPLICATION_JSON);
		//body.header("content-type", "application/json;charset=UTF-8");
		return body;
	}
	
	public static BodyBuilder buildResponse(URI uri) {
		BodyBuilder body = ResponseEntity.created(uri);
		//body.header("Access-Control-Allow-Origin", "*");
		body.contentType(MediaType.APPLICATION_JSON);
		return body;
	}
	
	public static String formataCPF(String cpf) {
        if(cpf == null) return "000.000.000-00";
        // Se o cpf dado ja contiver ponto e traao, assume que ja esta formatado corretamente:
        if(cpf.contains(".") && cpf.contains("-")) return cpf;
        
        String aux1 = cpf;
        int len = aux1.length();
        
        
        // Completa com 0 se o cpf for menor que 11 digitos
        String zeros = "00000000000";
        if(len < 11) aux1 = zeros.substring(0, 11 - len) + aux1;
        
        return  aux1.substring(0, 3) + "." + 
                aux1.substring(3, 6) + "." + 
                aux1.substring(6, 9) + "-" +
                aux1.substring(9);       
    }
	
	public static String formataCNPJ(String cnpj) {
        if(cnpj == null) return "00.000.000/0000-00";
        // Se o cpf dado ja contiver ponto e traao, assume que ja esta formatado corretamente:
        if(cnpj.contains(".") && cnpj.contains("/") && cnpj.contains("-")) return cnpj;
        
        String aux1 = cnpj;
        int len = aux1.length();
        
        
        // Completa com 0 se o cpf for menor que 11 digitos
        String zeros = "00000000000000";
        if(len < 14) aux1 = zeros.substring(0, 14 - len) + aux1;
        
        return  aux1.substring(0, 2) + "." + 
                aux1.substring(2, 5) + "." + 
                aux1.substring(5, 8) + "/" +
                aux1.substring(8, 12) + "-" +
                aux1.substring(12);       
    }
}
