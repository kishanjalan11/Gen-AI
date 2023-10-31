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
public class summarizerServiceImpl implements summarizerService{

	@Value("${api.openai.secretkey}")
	private String secretKey;
	@Value("${api.openai.url}")
	private String url;
	
	RestTemplate restTemplate;
	ObjectMapper objectMapper;
	HttpHeaders httpHeaders;
	
	public summarizerServiceImpl() {
		super();
	}

	@Autowired
	public summarizerServiceImpl(RestTemplate restTemplate, ObjectMapper objectMapper, HttpHeaders httpHeaders) {
		super();
		this.restTemplate = restTemplate;
		this.objectMapper = objectMapper;
		this.httpHeaders = httpHeaders;
	}

	@Override
	public String generateSummary(String data) throws JsonProcessingException {
		
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		httpHeaders.set("Authorization", "Bearer " + secretKey);
		String prompt="Act as an expert teacher, having capability to summarize any paragraph to a human-like text can be understandable even for a beginner of the language.\r\n"
				+ "Task\r\n"
				+ "Summarize the given paragraph \r\n"
				+ "Task:-\r\n"
				+ "Paragraph - "+data+"\r\n"
				+ "Response Language - English\r\n"
				+ "Note-generate not more than 60 words";
		
		Map<String,String> userRole=new HashMap<>();
		userRole.put("role", "user");
		userRole.put("content", prompt);
		
		Map<String, String> systemRole = new HashMap<>();
		systemRole.put("role", "system");
		systemRole.put("content", "Act as an Expert Teacher");
		
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
