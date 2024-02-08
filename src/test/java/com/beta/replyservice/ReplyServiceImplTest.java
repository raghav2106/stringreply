package com.beta.replyservice;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.beta.replyservice.ReplyServiceImpl;

@ExtendWith(SpringExtension.class)
public class ReplyServiceImplTest {

	@InjectMocks
	private ReplyServiceImpl service;

	@Test
	void processWithRule_Success_Same_Rule_Repeating_Reverse() {
		String expectedResponse="kbzw9ru";
		String message="kbzw9ru";
		String rule="11";
		String actualResponse = service.processWithRule(message, rule);
		Assertions.assertEquals(expectedResponse, actualResponse);
	}
	
	@Test
	void processWithRule_Success_Same_Rule_Reverse() {
		String expectedResponse="kbzw9ru";
		String message="ur9wzbk";
		String rule="1";
		String actualResponse = service.processWithRule(message, rule);
		Assertions.assertEquals(expectedResponse, actualResponse);
	}
	
	@Test
	void processWithRule_Success_Different_Rules() {
		String expectedResponse="5a8973b3b1fafaeaadf10e195c6e1dd4";
		String message="kbzw9ru";
		String rule="12";
		String actualResponse = service.processWithRule(message, rule);
		Assertions.assertEquals(expectedResponse, actualResponse);
	}
	
	@Test
	void processWithRule_Success_Same_Rule_Repeating_Encoding() {
		String expectedResponse="e8501e64cf0a9fa45e3c25aa9e77ffd5";
		String message="kbzw9ru";
		String rule="22";
		String actualResponse = service.processWithRule(message, rule);
		Assertions.assertEquals(expectedResponse, actualResponse);
	}
	
	@Test
	void processWithRule_Success_Same_Rule_Encoding() {
		String expectedResponse="0fafeaae780954464c1b29f765861fad";
		String message="kbzw9ru";
		String rule="2";
		String actualResponse = service.processWithRule(message, rule);
		Assertions.assertEquals(expectedResponse, actualResponse);
	}
}
