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
public class generatorServiceImpl implements generatorService {

	@Value("${api.openai.secretkey}")
	private String secretKey;
	@Value("${api.openai.url}")
	private String url;
	
	RestTemplate restTemplate;
	ObjectMapper objectMapper;
	HttpHeaders httpHeaders;
	
	public generatorServiceImpl() {
		super();
	}

	@Autowired
	public generatorServiceImpl(RestTemplate restTemplate, ObjectMapper objectMapper, HttpHeaders httpHeaders) {
		super();
		this.restTemplate = restTemplate;
		this.objectMapper = objectMapper;
		this.httpHeaders = httpHeaders;
	}

	@Override
	public String generate(String topic) throws JsonProcessingException {

		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		httpHeaders.set("Authorization", "Bearer " + secretKey);
		
		String prompt="Act as an expert teacher, having the capability to explain any given topic,who can generate very human-like text around any topic and that text can be understandable even for a beginner of the language.\r\n"
				+ "\r\n"
				+ "Task-\r\n"
				+ "You have to generate the text in the points format around a topic in the given language\r\n"
				+ "\r\n"
				+ "Example-\r\n"
				+ "Topic - What is Gen AI?\r\n"
				+ "Language - English\r\n"
				+ "Output\r\n"
				+ "Gen AI refers to the next generation of artificial intelligence that combines advanced machine learning, deep neural networks, problem-solving and natural language understanding.\r\n"
				+ "Gen AI incorporates cutting-edge technology.\r\n"
				+ "It mimics human cognitive functions.Used for complex problem-solving\r\n"
				+ "Understands and processes natural language\r\n"
				+ "Promises significant advancements in AI\r\n"
				+ "and so on..\r\n"
				+ "\r\n"
				+ "Example-	\r\n"
				+ "Topic-What is Android?\"\r\n"
				+ "Language - Hindi\r\n"
				+ "एंड्रॉयड एक मोबाइल ऑपरेटिंग सिस्टम है जिसे Google ने विकसित किया है। इसमें ऐप्स को चलाने के लिए होता है।\r\n"
				+ "1. गूगल द्वारा विकसित किया गया है।\r\n"
				+ "2. ऐप्स को चलाने के लिए होता है।\r\n"
				+ "3. ओपन सोर्स है।\r\n"
				+ "4. स्मार्टफोन और टैबलेट के लिए है।\r\n"
				+ "5. एंड्रॉयड स्टूडियो से ऐप्स विकसित होती हैं।\r\n"
				+ "6. यह दुनियाभर में पॉप्युलर है।\r\n"
				+ "7. customization की सुविधा है।\r\n"
				+ "8. ऐप स्टोर: Google Play Store।\r\n"
				+ "\r\n"
				+ "Note-generate not more than 100 words\r\n"
				+ "\r\n"
				+ "Task:-\r\n"
				+ "Topic-"+topic+"\r\n"
				+ "Response language- English";
		
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
