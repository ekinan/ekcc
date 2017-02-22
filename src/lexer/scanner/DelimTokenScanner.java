package lexer.scanner;

import token.Token;
import token.TokenAttribute;
import utils.InputFileReader;

import static token.TokenAttribute.*;
import static utils.InputFileReader.EOF;

/*
Lexer code that returns tokens representing delimiters
in this language.
 */
public class DelimTokenScanner extends TokenScanner {
    @Override
    public Token scanToken(InputFileReader input) {
        int ch = input.peek();
        if (ch == EOF) { // End of file
            return null;
        }

        switch (ch) {
            case '(':
                return readToken(input, OPAR);
            case ')':
                return readToken(input, CPAR);
            case '{':
                return readToken(input, OCBRA);
            case '}':
                return readToken(input, CCBRA);
            case ';':
                return readToken(input, SEMIC);
            case ',':
                return readToken(input, COMMA);
        }

        return null;
    }
}
