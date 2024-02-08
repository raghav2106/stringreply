package com.beta.replyservice;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;

@Service
public class ReplyServiceImpl implements ReplyService {

	private String reverseString(String input) {
		return new StringBuilder(input).reverse().toString();
	}

	private String encodeString(String input) {
		return DigestUtils.md5Hex(input);
	}

	@Override
	public String processWithRule(String message, String rule) {

		for (char ruleChar : rule.toCharArray()) {
			int operation = Character.getNumericValue(ruleChar);
			message = applyOperation(message, operation);
		}

		return message;
	}

	private String applyOperation(String message, int operation) {
		switch (operation) {
		case 1:
			// Reverse the string
			return reverseString(message);
		case 2:
			// Encode the string using MD5
			return encodeString(message);
		default:
			return message;
		}
	}
}
