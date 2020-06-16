package com.desafio.codenation.Criptografia.Julio.Cesar.controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.math.BigInteger;
import java.security.MessageDigest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.desafio.codenation.Criptografia.Julio.Cesar.model.ResponseJson;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class EncodingController {

	@RequestMapping("/")
	public String index() {
		return "Greetings from Spring Boot!";
	}

	@GetMapping("/getMessage")
	public ResponseEntity<String> getMessage() {
		RestTemplate restTemplate = new RestTemplate(); // 1
		String url = "https://api.codenation.dev/v1/challenge/dev-ps/generate-data?token=920f5c485dc787ff09769cf61cf09c1e798c7907";
		ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

		try {
			File file = new File("src/main/resources/answer.json");

			String str = response.getBody();
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));

			byte[] jsonData = str.toString().getBytes();

			ObjectMapper mapper = new ObjectMapper();
			ResponseJson responseJson = mapper.readValue(jsonData, ResponseJson.class);

			responseJson.setDecifrado(decifra(responseJson.getNumero_casas(), responseJson.getCifrado()));
			responseJson.setResumo_criptografico(encriptSha1(responseJson.getDecifrado()));

			writer.write(responseJson.toJson(responseJson));
			writer.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	private static String encriptSha1(String texto) {

		MessageDigest crypt = null;
		try {
			crypt = MessageDigest.getInstance("SHA-1");
			crypt.reset();
			crypt.update(texto.getBytes("UTF-8"));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new BigInteger(1, crypt.digest()).toString(16);
	}

	private static String decifra(Integer casas, String cifrado) {
		StringBuilder decifrado = new StringBuilder();
		int tamanhoTexto = cifrado.length();

		for (int index = 0; index < tamanhoTexto; index++) {

			int letraDecifrada = ((int) cifrado.charAt(index)) - casas;

			if (letraDecifrada < 97) {
				if (letraDecifrada < 65) {
					decifrado.append(cifrado.charAt(index));
				} else {
					int alph = 97 - letraDecifrada;
					int letra = 123 - alph;
					decifrado.append((char) letra);
				}
			} else {
				decifrado.append((char) letraDecifrada);
			}
		}
		return decifrado.toString();
	}

	@PostMapping
	public void postMessage() {

	}

}
