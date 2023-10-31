package com.masai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.masai.service.summarizerService;

@RestController
@CrossOrigin(origins = "*")
public class summarizerController {
	
	@Autowired
	private summarizerService summService;
	
	@PostMapping("/summarizer")
	ResponseEntity<String> generate(@RequestBody String data) throws JsonProcessingException	{
			return new ResponseEntity<String>(summService.generateSummary(data),HttpStatus.OK);
	}
}
