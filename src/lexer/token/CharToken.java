package lexer.token;

import utils.FileLocation;

import static lexer.token.TokenAttribute.CHARLIT;

/*

Represents tokens that hold character values

*/
public class CharToken extends ValueToken<Character> {
    public CharToken(FileLocation fileLoc, Character value) {
        super(CHARLIT, fileLoc, value);
    }
}
