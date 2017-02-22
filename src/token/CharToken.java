package token;

import utils.FileLocation;

import static token.TokenAttribute.CHARLIT;

/*

Represents tokens that hold character values

*/
public class CharToken extends ValueToken<Character> {
    public CharToken(FileLocation fileLoc, Character value) {
        super(CHARLIT, fileLoc, value);
    }
}
