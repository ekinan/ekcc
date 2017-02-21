package lexer.token;

import static lexer.token.TokenAttribute.INTLIT;

/*

Represents tokens that hold integer values

*/
public class IntToken extends ValueToken<Integer> {
  public IntToken(Integer value) {
    super(INTLIT, value);
  }
}
