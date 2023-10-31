package com.masai.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class translatorServiceImpl implements translatorService {

	@Value("${api.openai.secretkey}")
	private String secretKey;
	@Value("${api.openai.url}")
	private String url;
	
	RestTemplate restTemplate;
	ObjectMapper objectMapper;
	HttpHeaders httpHeaders;
	
	
	public translatorServiceImpl() {
		super();
	}

	@Autowired
	public translatorServiceImpl(RestTemplate restTemplate, ObjectMapper objectMapper, HttpHeaders httpHeaders) {
		super();
		this.restTemplate = restTemplate;
		this.objectMapper = objectMapper;
		this.httpHeaders = httpHeaders;
	}

	@Override
	public String translate(String input, String output, String data) throws JsonProcessingException {
		
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		httpHeaders.set("Authorization", "Bearer " + secretKey);
		
		String prompt="Act as a language translator having knowledge of almost all the languages, you have the understanding to convert any text from one language to another easily.\r\n"
				+ "Task\r\n"
				+ "You have to convert the given paragraph/sentence/word from the certain language to another language as asked \r\n"
				+ "Example \r\n"
				+ "\r\n"
				+ "Paragraph : Hello \r\n"
				+ "Input Language: English\r\n"
				+ "Output Language : Hindi\r\n"
				+ "नमस्ते\r\n"
				+ "\r\n"
				+ "Paragraph : Hello\r\n"
				+ "Input Language: English\r\n"
				+ "Output Language : Punjabi\r\n"
				+ "ਸਤ ਸ੍ਰੀ ਅਕਾਲ\r\n"
				+ "\r\n"
				+ "Only Return the translated text.\r\n"
				+ "\r\n"
				+ "Task:-\r\n"
				+ "\r\n"
				+ "Paragraph : "+data+"\r\n"
				+ "Input Language: "+input+"\r\n"
				+ "Output Language : "+output+"\r\n"
				+ "";
		
		Map<String,String> userRole=new HashMap<>();
		userRole.put("role", "user");
		userRole.put("content", prompt);
		
		Map<String, String> systemRole = new HashMap<>();
		systemRole.put("role", "system");
		systemRole.put("content", "Act as an language Translator");
		
		List<Map<String, String>> roles = new ArrayList<>();
		roles.add(systemRole);
		roles.add(userRole);
		
		Body body = new Body("gpt-3.5-turbo", roles, 1000, 1.0, 0.9, 1.0, 1.0);
		
		HttpEntity<String> requestEntity = new HttpEntity<>(objectMapper.writeValueAsString(body), httpHeaders);
		ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, requestEntity, String.class);
		
		JsonNode responseJson = objectMapper.readTree(responseEntity.getBody());
		String response = responseJson.get("choices").get(0).get("message").get("content").asText();
		
		return response;
	}

}
