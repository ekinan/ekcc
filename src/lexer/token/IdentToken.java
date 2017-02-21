package lexer.token;

import static lexer.token.TokenAttribute.IDENT;

/*

Represents tokens that hold identifier names

*/
public class IdentToken extends ValueToken<String> {
  public IdentToken(String value) {
    super(IDENT, value);
  }
}
