package com.desafio.codenation.Criptografia.Julio.Cesar.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ResponseJson {
	private int numero_casas;
	private String token;
	private String cifrado;
	private String decifrado;
	private String resumo_criptografico;

	public int getNumero_casas() {
		return numero_casas;
	}

	public void setNumero_casas(int numero_casas) {
		this.numero_casas = numero_casas;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getCifrado() {
		return cifrado;
	}

	public void setCifrado(String cifrado) {
		this.cifrado = cifrado;
	}

	public String getDecifrado() {
		return decifrado;
	}

	public void setDecifrado(String decifrado) {
		this.decifrado = decifrado;
	}

	public String getResumo_criptografico() {
		return resumo_criptografico;
	}

	public void setResumo_criptografico(String resumo_criptografico) {
		this.resumo_criptografico = resumo_criptografico;
	}

	public String toJson(ResponseJson response) {
		ObjectMapper mapper = new ObjectMapper();
		String json = "";
		try {
			json = mapper.writeValueAsString(response);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		return json;
	}
}
