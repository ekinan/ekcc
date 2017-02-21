package lexer.token;

/*

This is the main token class. Every token has an attribute that distinguishes what
type of token it is from other tokens (e.g. you want to distinguish between an
integer literal vs. a character literal).

*/
public class Token {
  // The attribute of this token.
  public TokenAttribute attr;

  public Token(TokenAttribute attr) {
    this.attr = attr;
  }

  public TokenAttribute getAttr() {
    return attr;
  }
}
