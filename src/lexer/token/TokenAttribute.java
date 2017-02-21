package lexer.token;

/*
This enum houses all of the possible kinds of tokens that can be found in our language.
*/
public enum TokenAttribute {
 // Keywords
 IF,
 ELSE,
 FOR,
 INT,
 CHAR,
 PRINT,

 // Delimeters
 OPAR,             // Open parenthesis
 CPAR,             // Close parenthesis
 OCBRA,            // Open curly brace
 CCBRA,            // Close curly brace
 SEMIC,            // Semi-colon
 COMMA,            // Comma

 // Literals
 INTLIT,           // Integer literals
 CHARLIT,          // Character literals

 // Identifiers
 IDENT,            // Identifier

 // Operators
 ADD,              // Addition (+)
 SUBT,             // Subtraction (-)
 MULT,             // Multiplication (*)
 DIV,              // Division (/)
 MOD,              // Modulus (%)

 LT,               // Less than (<)
 GT,               // Greater than (>)
 LEQ,              // Less than or equal to (<=)
 GEQ,              // Greater than or equal to (>=)
 LOR,              // Logical or (||)
 LAND,             // Logical and (&&)

 // EOF
 EOF
}
