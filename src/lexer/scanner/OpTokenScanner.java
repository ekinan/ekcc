package lexer.scanner;

import token.Token;
import token.TokenAttribute;
import utils.FileLocation;
import utils.InputFileReader;

import java.util.function.Function;

import static token.TokenAttribute.*;
import static utils.InputFileReader.EOF;

/*

Lexer code that returns tokens representing operators in the
language.

 */
public class OpTokenScanner extends TokenScanner {
    @Override
    public Token scanToken(InputFileReader input) {
        int ch = input.peek();
        if (ch == EOF) { // End of file
            return null;
        }

        switch (ch) {
            case '=':
                return lookahead(input, ASSIGN, EQ, '=');

            case '+':
                return readToken(input, ADD);
            case '-':
                return readToken(input, SUBT);
            case '*':
                return readToken(input, MULT);
            case '/':
                return readToken(input, DIV);
            case '%':
                return readToken(input, MOD);

            case '<':
                return lookahead(input, LT, LEQ, '=');
            case '>':
                return lookahead(input, GT, GEQ, '=');
            case '|':
                return lookahead(input, LOR, '|');
            case '&':
                return lookahead(input, LAND, '&');
        }

        return null;
    }

    /*
    Wrapper to lookahead where the generator function returns a token
    with the default attribute.
     */
    private Token lookahead(
            InputFileReader input,
            TokenAttribute defTokAttr,
            TokenAttribute lookaheadTokAttr,
            Character lookaheadChar
    ) {
        return lookahead(
                input,
                (fileLoc) -> new Token(defTokAttr, fileLoc),
                lookaheadTokAttr,
                lookaheadChar
        );
    }

    /*
    Wrapper to lookahead where the generator function returns null.
     */
    private Token lookahead(
            InputFileReader input,
            TokenAttribute lookaheadTokAttr,
            Character lookaheadChar
    ) {
        return lookahead(
                input,
                (_fileLoc) -> null,
                lookaheadTokAttr,
                lookaheadChar
        );
    }

    /*
    Reads the input, and then looks ahead at the next character to see
    if it is lookaheadChar. If it is, then it returns the token with
    the attribute lookaheadTokAttr and reads the input. Otherwise, it
    returns the default token contained in the generator function.
    */
    private Token lookahead(
            InputFileReader input,
            Function<FileLocation, Token> defToken,
            TokenAttribute lookaheadTokAttr,
            char lookaheadChar
    ) {
        FileLocation tokLoc = new FileLocation(input.getCurLoc());
        input.read();

        if (input.readIf((c) -> c == lookaheadChar) < 0) {
            return defToken.apply(tokLoc);
        }

        return new Token(lookaheadTokAttr, tokLoc);
    }
}
