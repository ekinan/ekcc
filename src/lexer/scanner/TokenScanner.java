package lexer.scanner;

import token.Token;
import token.TokenAttribute;
import utils.FileLocation;
import utils.InputFileReader;

/*
An abstract class representing a TokenScanner. Used to separate out lexer code
for each kinds of tokens we might have.
 */
public abstract class TokenScanner {
    /*
    This method returns the next valid token that the scanner
    can find. If no token exists, it returns null;
     */
    public abstract Token scanToken(InputFileReader input);

    /*
    Shared method amongst the classes. It takes the input,
    reads it, and then creates the token with the passed in
    attribute tokAttr. Used to reduce duplicate code in the
    subclasses.
     */
    protected Token readToken(
            InputFileReader input,
            TokenAttribute tokAttr
    ) {
        FileLocation tokLoc = new FileLocation(input.getCurLoc());
        input.read();

        return new Token(tokAttr, tokLoc);
    }
}
