package com.masai.service;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface summarizerService {
	String generateSummary(String data) throws JsonProcessingException;
}
