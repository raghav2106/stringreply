package com.beta.replyservice;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.beta.replyservice.ReplyController;
import com.beta.replyservice.ReplyMessage;
import com.beta.replyservice.ReplyService;

@ExtendWith(SpringExtension.class)
public class ReplyControllerTest {

	@InjectMocks
	private ReplyController controller;

	@Mock
	private ReplyService replyService;

	private final static String INVALID_INPUT = "Invalid input format";

	@Test
	void replyMsg_Invalid_Message_Length() {
		String message = "test";
		ResponseEntity<ReplyMessage> actual = controller.replyMsg(message);
		Assertions.assertEquals(actual.getStatusCode(), HttpStatus.BAD_REQUEST);
		Assertions.assertEquals(actual.getBody().getMessage(), INVALID_INPUT);
	}

	@Test
	void replyMsg_Invalid_Rule_Length() {
		String message = "111-test";
		ResponseEntity<ReplyMessage> actual = controller.replyMsg(message);
		Assertions.assertEquals(actual.getStatusCode(), HttpStatus.BAD_REQUEST);
		Assertions.assertEquals(actual.getBody().getMessage(), INVALID_INPUT);
	}

	@Test
	void replyMsg_Invalid_Rule_Length_Characters() {
		String message = "1av-test";
		ResponseEntity<ReplyMessage> actual = controller.replyMsg(message);
		Assertions.assertEquals(actual.getStatusCode(), HttpStatus.BAD_REQUEST);
		Assertions.assertEquals(actual.getBody().getMessage(), INVALID_INPUT);
	}

	@Test
	void replyMsg_Valid_Same_Rule_Repeating_Reverse() {
		String message = "11-kbzw9ru";
		String expected = "kbzw9ru";
		when(replyService.processWithRule(anyString(), anyString())).thenReturn("kbzw9ru");
		ResponseEntity<ReplyMessage> actual = controller.replyMsg(message);
		Assertions.assertEquals(actual.getBody().getMessage(), expected);
		Assertions.assertEquals(actual.getStatusCode(), HttpStatus.OK);
	}
	
	@Test
	void replyMsg_Valid_Different_Rules() {
		String message = "12-kbzw9ru";
		String expected = "5a8973b3b1fafaeaadf10e195c6e1dd4";
		when(replyService.processWithRule(anyString(), anyString())).thenReturn("5a8973b3b1fafaeaadf10e195c6e1dd4");
		ResponseEntity<ReplyMessage> actual = controller.replyMsg(message);
		Assertions.assertEquals(actual.getBody().getMessage(), expected);
		Assertions.assertEquals(actual.getStatusCode(), HttpStatus.OK);

	}
	
	@Test
	void replyMsg_Valid_Same_Rule_Repeating_Encoding() {
		String message = "22-kbzw9ru";
		String expected = "e8501e64cf0a9fa45e3c25aa9e77ffd5";
		when(replyService.processWithRule(anyString(), anyString())).thenReturn("e8501e64cf0a9fa45e3c25aa9e77ffd5");
		ResponseEntity<ReplyMessage> actual = controller.replyMsg(message);
		Assertions.assertEquals(actual.getBody().getMessage(), expected);
		Assertions.assertEquals(actual.getStatusCode(), HttpStatus.OK);

	}
	
}
