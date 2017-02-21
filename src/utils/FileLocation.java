package utils;

/*

A class to store some location in a file. Can be used
to store where a token was found in the file, or to
store the current location that's being processed in
the file. Class is immutable.

 */
public class FileLocation {
    // The line number that we're at
    protected Integer line;

    // The column number in the line
    protected Integer col;

    public FileLocation(Integer line, Integer col) {
        this.line = line;
        this.col = col;
    }

    public Integer getLine() {
        return line;
    }

    public Integer getCol() {
        return col;
    }
}
