package pkg444.parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.Stack;

/**
 *
 * @author Cody Gildea <cbgildea@gmail.com>
 */
public class Parser {

    /**
     * @param args the command line arguments
     */
//    public static void main(String[] args) {
//        // TODO code application logic here
//    }
    public Parser() {

    }
    private String current_token;
    private int table_entry, token_number, stacktop, wait;
//    private int next_table_entry(int non_terminal, int token);

    public void parse_from_file(String filename) throws FileNotFoundException, IOException {
        char current_char = ' ';
        Scanner scan = null;
        File inFile = new File(filename);
        scan = new Scanner(inFile);
        
        Stack<String> s = new Stack<>();

        while (scan.hasNext()) {
            char[] char_array = scan.nextLine().toCharArray();
            for (int i = 0; i < char_array.length; i++) {
                if (current_char == ' '
                        || (current_char == '\n')) {
                    current_char = char_array[i];
                }
            }

            if (current_char == '+') {
                token_number = -1;
            } else if (current_char == '-') {
                token_number = -2;
            } else if (current_char == '*') {
                token_number = -3;
            } else if (current_char == '/') {
                token_number = -4;
            } else if (current_char == '(') {
                token_number = -5;
            } else if (current_char == ')') {
                token_number = -6;
            } else if (current_char == 'i') {
                token_number = -7;
            } else {
                token_number = -8;
            }
        }
    }

}
