package netcalc.marshaler.tokenizer;

public class Tokeninzer {

	private String valString;

	public Tokeninzer(String valString) {
		this.valString = valString;
	}

	public Token getToken() {
		if (valString.length() == 0) {
			return new Token(TokenType.EOF);
		}
		int index = 0;
		char ch = valString.charAt(index);
		index++;
		TokenType tokenType;
		Integer value;
		while (ch == ' ') {
			ch = valString.charAt(index);
			index++;
		}
		if (ch != '\n' && ch != '\0') {
			tokenType = TokenType.getTokenType(ch);
			if (tokenType != null) {
				valString = valString.substring(index);
				return new Token(tokenType);
			} else {
				try {
					valString = valString.substring(index - 1);
					String tempString = valString.split("[+-/*() ]+")[0];
					value = Integer.parseInt(tempString);
					valString = valString.substring(tempString.length());
					return new Token(TokenType.VALUE, value);
				} catch (NumberFormatException exception) {
					exception.printStackTrace();
					return new Token(TokenType.EOF);
				}
			}
		}
		return new Token(TokenType.EOF);
	}
}
