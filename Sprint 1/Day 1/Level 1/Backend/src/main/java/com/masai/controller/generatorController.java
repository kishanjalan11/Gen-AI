package com.masai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.masai.service.generatorService;

@RestController
@CrossOrigin(origins = "*")
public class generatorController {

	@Autowired
	private generatorService genService;
	
	@PostMapping("/generator")
	ResponseEntity<String> generate(@RequestBody String topic) throws JsonProcessingException	{
			return new ResponseEntity<String>(genService.generate(topic),HttpStatus.OK);
	}
}
