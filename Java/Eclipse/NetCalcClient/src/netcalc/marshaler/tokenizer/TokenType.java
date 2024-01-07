package netcalc.marshaler.tokenizer;

public enum TokenType {
	EOL('\n'), EOF('\0'), VALUE(), OPAREN('(', 4), CPAREN(')', 0), EXP('^', 3), MULT(
			'*', 2), DIV('/', 2), PLUS('+', 1), MINUS('-', 1);

	private char symbol;
	private int priority;

	private TokenType(char symbol) {
		this.symbol = symbol;
	}

	private TokenType(char symbol, int priority) {
		this.symbol = symbol;
		this.priority = priority;
	}

	private TokenType() {
	}

	public char getSymbol() {
		return symbol;
	}

	public int getPriority() {
		return priority;
	}

	public static TokenType getTokenType(char symbol) {
		for (int i = 0; i < values().length; i++) {
			if (values()[i].symbol == symbol) {
				return values()[i];
			}
		}
		return null;
	}

	public static TokenType getTokenType() {
		return TokenType.VALUE;
	}
}
