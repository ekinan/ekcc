package lexer.token;

/*

This class represents a template for tokens that can hold values.
Examples include literals such as constant integers and characters,
identifiers (holds the name of the variable).

*/
public abstract class ValueToken<T> extends Token {
  private T value;

  public ValueToken(TokenAttribute attr, T value) {
    super(attr);
    this.value = value;
  }

  public T getValue() {
    return value;
  }
}
