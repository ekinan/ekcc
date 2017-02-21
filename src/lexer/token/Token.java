package lexer.token;

/*

This is the main token class. Every token has an attribute that distinguishes what
type of token it is from other tokens (e.g. you want to distinguish between an
integer literal vs. a character literal).

We also want to track the line and column # that this token was found on for easier
error reporting.

*/
public class Token {
  // The token's attribute
  private TokenAttribute attr;

  // The token's line number
  private Integer line;

  // The token's column number
  private Integer col;


  public Token(TokenAttribute attr, Integer line, Integer col) {
    this.attr = attr;
    this.line = line;
    this.col = col;
  }

  public TokenAttribute getAttr() {
    return attr;
  }

  public Integer getLine() {
    return line;

  }

  public Integer getCol() {
    return col;
  }
}
