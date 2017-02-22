package token;

import utils.FileLocation;

import static token.TokenAttribute.INTLIT;

/*

Represents tokens that hold integer values

*/
public class IntToken extends ValueToken<Integer> {
    public IntToken(FileLocation fileLoc, Integer value) {
        super(INTLIT, fileLoc, value);
    }
}
