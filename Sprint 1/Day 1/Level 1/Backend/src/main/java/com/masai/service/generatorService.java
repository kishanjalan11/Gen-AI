package com.masai.service;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface generatorService {

	String generate(String topic) throws JsonProcessingException;
	
}
