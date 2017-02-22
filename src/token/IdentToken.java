package token;

import utils.FileLocation;

import static token.TokenAttribute.IDENT;

/*

Represents tokens that hold identifier names

*/
public class IdentToken extends ValueToken<String> {
    public IdentToken(FileLocation fileLoc, String value) {
        super(IDENT, fileLoc, value);
    }
}
