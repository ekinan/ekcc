package lexer.filter;

import utils.InputFileReader;

/*

An abstract bas class representing the classes that are meant to filter out
the input. Here, it would be one to filter out whitespace and another to filter
out comments, single line and multi-line.

 */
public abstract class InputFilter {
    /*
    Filters the input.
     */
    public abstract void filter(InputFileReader input);

}
