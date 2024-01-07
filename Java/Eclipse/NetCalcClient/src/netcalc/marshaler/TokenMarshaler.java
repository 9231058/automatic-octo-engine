package netcalc.marshaler;

import java.util.Stack;

import netcalc.domain.Command;
import netcalc.marshaler.tokenizer.Token;
import netcalc.marshaler.tokenizer.TokenType;
import netcalc.marshaler.tokenizer.Tokeninzer;
import netcalc.net.TCPNetwork;

public class TokenMarshaler implements Marshaler {

	private String toCalculate;
	private Stack<Token> tokenStack = new Stack<>();
	private Stack<Integer> valueStack = new Stack<>();

	@Override
	public Integer request(String string) {
		toCalculate = string;
		Tokeninzer tokeninzer = new Tokeninzer(toCalculate);
		while (true) {
			Token token = tokeninzer.getToken();
			if (token.getTokenType() == TokenType.EOF
					|| token.getTokenType() == TokenType.EOL) {
				break;
			} else if (token.getTokenType() == TokenType.VALUE) {
				valueStack.add(token.getValue());
			} else {
				addTokenToStack(token);
			}
		}
		while (!tokenStack.isEmpty()) {
			Token token = tokenStack.pop();
			Integer value1 = valueStack.pop();
			Integer value2 = valueStack.pop();
			valueStack.add(evaluate(token, value1, value2));
		}
		return valueStack.peek();
	}

	private void addTokenToStack(Token token) {
		while (!tokenStack.isEmpty()
				&& token.getTokenType().getPriority() < tokenStack.peek()
						.getTokenType().getPriority()) {
			if (tokenStack.peek().getTokenType() == TokenType.OPAREN
					&& token.getTokenType() == TokenType.CPAREN) {
				tokenStack.pop();
				return;
			} else if (tokenStack.peek().getTokenType() == TokenType.OPAREN
					&& !(token.getTokenType() == TokenType.CPAREN)) {
				break;
			}
			valueStack.add(evaluate(tokenStack.pop(), valueStack.pop(),
					valueStack.pop()));
		}
		tokenStack.add(token);
	}

	private Integer evaluate(Token token, Integer value1, Integer value2) {
		TCPNetwork network = new TCPNetwork();
		if (token.getTokenType() == TokenType.PLUS) {
			return network.evaluate(Command.PLUS, value1, value2);
		}
		if (token.getTokenType() == TokenType.MINUS) {
			return network.evaluate(Command.MINUS, value1, value2);
		}
		if (token.getTokenType() == TokenType.DIV) {
			return network.evaluate(Command.DIV, value1, value2);
		}
		if (token.getTokenType() == TokenType.MULT) {
			return network.evaluate(Command.MULT, value1, value2);
		}
		if (token.getTokenType() == TokenType.EXP) {
			return network.evaluate(Command.EXP, value1, value2);
		}
		return null;
	}

}
