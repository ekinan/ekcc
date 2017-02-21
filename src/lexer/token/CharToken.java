package lexer.token;

import static lexer.token.TokenAttribute.CHARLIT;

/*

Represents tokens that hold character values

*/
public class CharToken extends ValueToken<Character> {
  public CharToken(Integer line, Integer col, Character value) {
    super(CHARLIT, line, col, value);
  }
}
