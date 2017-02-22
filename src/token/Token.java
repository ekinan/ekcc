package token;

import utils.FileLocation;

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

    // The token's location in its file
    private FileLocation fileLoc;

    public Token(TokenAttribute attr, FileLocation fileLoc) {
        this.attr = attr;
        this.fileLoc = fileLoc;
    }

    public TokenAttribute getAttr() {
        return attr;
    }

    public FileLocation getFileLoc() {
        return fileLoc;
    }
}
