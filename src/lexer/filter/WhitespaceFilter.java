package lexer.filter;

import utils.InputFileReader;

import java.util.function.Predicate;

import static utils.CharClass.isWhitespace;
import static utils.InputFileReader.EOF;

/*
Class that filters out whitespace from the given input.
 */
public class WhitespaceFilter extends InputFilter {
    @Override
    public void filter(InputFileReader input) {
        input.skipWhile(isWhiteSpaceP);
    }

    // Some common predicates that will be needed for this class (to avoid
    // recreating them each time the methods in this class are invoked).
    private static final Predicate<Integer> isWhiteSpaceP = (c) -> isWhitespace(c);
}
