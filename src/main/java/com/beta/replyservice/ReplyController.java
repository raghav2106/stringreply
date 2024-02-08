package com.beta.replyservice;

import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
public class ReplyController {

	@Autowired
	ReplyService replyService;

	private final static String SEPERATOR = "-";
	private final static String INVALID_INPUT = "Invalid input format";

	@GetMapping("/reply")
	public ReplyMessage replying() {
		return new ReplyMessage("Message is empty");
	}

	@GetMapping("/reply/{message}")
	public ReplyMessage replying(@PathVariable String message) {
		return new ReplyMessage(message);
	}

	@GetMapping("v2/reply/{message}")
	public ResponseEntity<ReplyMessage> replyMsg(@PathVariable String message) {

		String[] parts = message.split(SEPERATOR);

		if (parts.length != 2) {
			
			return new ResponseEntity<>(new ReplyMessage(INVALID_INPUT), HttpStatus.BAD_REQUEST);
		}

		String rule = parts[0];
		String actualMessage = parts[1];

		if (rule.length() > 2 || !Pattern.matches("^[1-2]+$", rule)) {
			return new ResponseEntity<>(new ReplyMessage(INVALID_INPUT), HttpStatus.BAD_REQUEST);
		}

		String result = replyService.processWithRule(actualMessage, rule);
		return new ResponseEntity<>(new ReplyMessage(result), HttpStatus.OK);
	}

}