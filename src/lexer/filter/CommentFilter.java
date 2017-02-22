package lexer.filter;

import utils.InputFileReader;

import java.util.function.Predicate;

import static utils.InputFileReader.EOF;

/*
Filters out comments from the program. Comments are described by the
following reg-ex:
    Comment -> SingleLine | MultiLine
    SingleLine -> "/" "/" * "\n"
    MultiLine -> "/" "*" * "*" "/"

where the * denotes any character.
 */
public class CommentFilter extends InputFilter {
    @Override
    public void filter(InputFileReader input) {
        if (input.readIf(isForwardSlash) < 0) {
            return;
        }

        if (input.readIf(isForwardSlash) > 0) {
            filterSingleLine(input);
            return;
        }

        if (input.readIf(isAsterisk) > 0) {
            filterMultiLine(input);
        }

        //TODO: Log error! Means we encountered an isolated forward slash.
    }

    /*
    Filters a single line comment.
     */
    private void filterSingleLine(InputFileReader input) {
        input.skipWhile(isNotNewline);
        input.skip();
    }

    /*
    Filters a multi-line comment.
    TODO: Log error if the comment is not terminated!
     */
    private void filterMultiLine(InputFileReader input) {
        do {
            input.skipWhile(isNotAsterisk);
        } while (input.skip() && input.readIf(isForwardSlash) < 0);
    }

    // Some common predicates that will be needed for this class (to avoid
    // recreating them each time the methods in this class are invoked).
    private static final Predicate<Integer> isForwardSlash = (c) -> c == '/';
    private static final Predicate<Integer> isAsterisk = (c) -> c == '*';
    private static final Predicate<Integer> isNotAsterisk = (c) -> c != '*';
    private static final Predicate<Integer> isNotNewline = (c) -> c != '\n';
}
