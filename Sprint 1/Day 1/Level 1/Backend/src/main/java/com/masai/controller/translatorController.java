package com.masai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.masai.service.translatorService;

@RestController
@CrossOrigin(origins = "*")
public class translatorController {

	@Autowired
	translatorService service;
	
	@PostMapping("/translator/{input}/{output}")
	ResponseEntity<String> generate(@PathVariable String input, @PathVariable String output,@RequestBody String data) throws JsonProcessingException	{
			return new ResponseEntity<String>(service.translate(input, output, data),HttpStatus.OK);
	}
}
