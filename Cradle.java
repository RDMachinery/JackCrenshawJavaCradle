import java.util.*;
import java.io.*;

public class Cradle {

    static int TAB = '\t';
    int look = 0;    // Lookahead character

    FileInputStream fis;

    public Cradle(String filename) throws IOException {
        fis = new FileInputStream(new File(filename));
        // Prime the pump
        getChar();
    }
    
    private void getChar() {
        try {
            look = fis.read();
        } catch(IOException ioe) {
            abort("I/O error: " + ioe.getMessage());
        }
    }

    private void error(String error) {
        System.out.println("Error: " + error);
    }

    // Report error and halt
    private void abort(String s) {
        error(s);
        System.exit(-1);
    }

    // Report what was expected
    private void expected(String s) {
        abort(s + " expected.");
    }

    // Match a character
    private void match(int c) {
        if( look == c ) 
            getChar();
        else {
            expected( look + "" );
        }
    }

    // Is the character alphabetic
    private boolean isAlpha(int c) {
        return Character.isAlphabetic((char)c);
    }
   
    // Is it a digit 
    private boolean isDigit(int c) {
        return Character.isDigit((char)c);
    }

    // Return identifier
    private int getName() {
        if( ! isAlpha(look) ) {
            expected("Name");
        }
        int name = look;
        getChar();
        return name;
    }

    // Return a number
    private int getNum() {
        if ( ! isDigit(look ) )
            expected("Integer");
        int num = (int)look;
        getChar();
        return num;
    }
    
    // Output a String with TAB
    private void emit(String s) {
        System.out.print(((char)TAB) + s );
    }
    // Output a String with newline
    private void emitln(String s) {
        emit(s);
        System.out.println();
    }
    
    public static void main(String[] args) {
        if( args.length != 1 ) {
            System.out.println("Usage: java Cradle <filename>");
            System.exit(0);
        }
        try {
            Cradle c = new Cradle(args[0]);
        } catch(IOException ioe) {
            System.err.println("IOException: "+ioe.getMessage());
        }

    }
}
