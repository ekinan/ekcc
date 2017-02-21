package lexer.token;

import static lexer.token.TokenAttribute.CHARLIT;

/*

Represents tokens that hold character values

*/
public class CharToken extends ValueToken<Character> {
  public CharToken(Character value) {
    super(CHARLIT, value);
  }
}
