import java.io.*;
import java.util.*;
import java.util.stream.*;
import java.awt.*;
import java.lang.*;
import java.math.*;
import javax.swing.*;
import java.time.*;
import java.time.format.*;

public class Main {
    static StreamTokenizer in;
    static DataOutputStream dos = new DataOutputStream(System.out);
    static StringBuilder sb = new StringBuilder();
    static int nextInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
	// in.nval returns a double value, so it has to be casted to the desired
	// data type. Because of that, other methods will be implemented
	// similarily. For example, to get a long, rename the method to "nextLong",
	// change the return type to "long", and do "return (long) in.nval;".
	// Note that for doubles you don't have to cast to anything, you can just
	// do "return in.nval".
    }
    static String next() throws IOException {
        in.nextToken();
        return in.sval;
    }
    static String nextLine() throws IOException {
        in.wordChars(' ', ' ');
        in.nextToken();
        in.whitespaceChars(' ', ' ');
        return in.sval;
    }

    // Note that unless there is a faster way to implement a nextChar() method,
    // the tactic would just to read in either the entire line using nextLine()
    // or just read in a token using next(), storing that in a String variable,
    // and then iterating through it.


// This is a very fast way to quickly read in the entire input and store
// it in a string. It was made using example #8 from the link:
// https://stackoverflow.com/questions/309424/how-do-i-read-convert-an-inputstream-into-a-string-in-java
    static String StreamToString (InputStream is) throws IOException {
        ByteArrayOutputStream result = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        for (int length; (length = is.read(buffer)) != -1; ) {
            result.write(buffer, 0, length);
        }
        // StandardCharsets.UTF_8.name() > JDK 7
        return result.toString("UTF-8");
    }
    public static void main (String [] args) throws IOException {
        in = new StreamTokenizer(new BufferedInputStream(new DataInputStream(System.in)));
        in.wordChars('!', '~');
        
        int n = nextInt();
        for (int i =0; i < n; i++) sb.append(nextLine()).append("\n");
        dos.write((sb.toString()).getBytes());
        
        dos.flush();
        dos.close();
    }
}


/*
Types of methods in StreamTokenizer:

For the examples, assume there is a StreamTokenizer initialized in the form of:
StreamTokenizer streamTokenizer = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

wordChars(int low, int high) takes the characters in the ASCII range low <= char <= high
and then includes them as part of the word tokens. For example, if I have the word "Mad!",
StreamTokenizer will usually split it apart to become "Mad" and "!", but by saying
"streamTokenizer.wordChars('!', '!')", then it will leave the two connected as "Mad!".

whitespaceChars(int low, int high) takes the characters in the ASCII range
low <= char <= high and then makes them characters to split the tokens by. By default,
spaces (' '), as well as newlines ('\n') are set as whitespaceChars, but you can add
other ones. For example, you can split CSV (comma-separated values) by saying
"streamTokenizer.whitespaceChars(',' , ',')", so "1,2,3,4,5" becomes "1", "2", "3", "4", "5".

ordinaryChars(int low, int high) and ordinaryChars(int ch) take the specified 
character(s) (either the ASCII range of low <= char <= high or just char ch)
and then allow them to stand alone. This is the same reason why "Mad!" splits apart
into "Mad" and "!", because '!' is an ordinary character by default. So, in order to
counteract this, you can set all values to wordChars by doing
"streamTokenizer.wordChars('!', '~');". Making the space (' ') character an ordinary
character would make strings like this -> "Jimmy Wimmy" become "Jimmy", " ", "Wimmy".

quoteChar(int ch) takes the character and, I believe, gets rid of this. This would allow
you to take a string such as -> "'Jeffery'" and then using the code:
"streamTokenizer.quoteChar('\'');" would allow you to get "Jeffery" as you have removed the
single quote characters.

The other methods are either self-explanatory, explained well enough in the docs
(https://docs.oracle.com/javase/7/docs/api/java/io/StreamTokenizer.html), or not needed.
*/