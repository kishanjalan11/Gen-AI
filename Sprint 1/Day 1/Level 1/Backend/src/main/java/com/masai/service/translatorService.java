package com.masai.service;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface translatorService {
	String translate(String input,String output,String data) throws JsonProcessingException;
}
