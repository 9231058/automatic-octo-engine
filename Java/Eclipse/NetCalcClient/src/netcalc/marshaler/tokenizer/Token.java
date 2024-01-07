package netcalc.marshaler.tokenizer;

public class Token {

	private TokenType tokenType;
	private Integer value;

	public Token(TokenType tokenType) {
		this.tokenType = tokenType;
	}

	public Token(TokenType tokenType, Integer value) {
		this.tokenType = tokenType;
		this.value = value;
	}

	public TokenType getTokenType() {
		return tokenType;
	}

	public Integer getValue() {
		return value;
	}

}
