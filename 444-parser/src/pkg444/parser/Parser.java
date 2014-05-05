package pkg444.parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import static java.lang.Math.abs;
import java.util.Scanner;
import java.util.Stack;
import javax.swing.JTextArea;

/**
 *
 * @author Cody Gildea <cbgildea@gmail.com>
 */
public class Parser {

    public Parser() {

    }
    private String current_token;
    private int table_entry, token_number, stacktop, wait;

    public void parse_from_file(String filename, JTextArea outputArea) throws FileNotFoundException, IOException {
        char current_char = ' ';
        Scanner scan = null;
        File inFile = new File(filename);
        scan = new Scanner(inFile);

        Stack<Integer> s = new Stack<>();
        s.push(1);
        current_token = scan.next();
        while (scan.hasNext()) {
            switch (current_token) {
                case "+":
                    token_number = -1;
                    break;
                case "-":
                    token_number = -2;
                    break;
                case "*":
                    token_number = -3;
                    break;
                case "/":
                    token_number = -4;
                    break;
                case "(":
                    token_number = -5;
                    break;
                case ")":
                    token_number = -6;
                    break;
                case "^":
                    token_number = -7;
                    break;
                case "i":
                    token_number = -8;
                    break;
                default:
                    token_number = -9;
                    break;
            }

            stacktop = s.peek();
            if (stacktop > 0) {
                table_entry = next_table_entry(stacktop, abs(token_number));
                switch (table_entry) {
                    case 1:
                        System.out.println("Fire 1");
                        outputArea.append("Fire 1\n");
                        s.pop();
                        s.push(2);
                        s.push(3);
                        break;
                    case 2:
                        System.out.println("Fire 2");
                        outputArea.append("Fire 2\n");
                        s.pop();
                        s.push(2);
                        s.push(3);
                        s.push(-1);
                        break;
                    case 3:
                        System.out.println("Fire 3");
                        outputArea.append("Fire 3\n");
                        s.pop();
                        s.push(2);
                        s.push(3);
                        s.push(-2);
                        break;
                    case 4:
                        System.out.println("Fire 4");
                        outputArea.append("Fire 4\n");
                        s.pop();
                        break;
                    case 5:
                        System.out.println("Fire 5");
                        outputArea.append("Fire 5\n");
                        s.pop();
                        s.push(4);
                        s.push(5);
                        break;
                    case 6:
                        System.out.println("Fire 6");
                        outputArea.append("Fire 6\n");
                        s.pop();
                        s.push(4);
                        s.push(5);
                        s.push(-3);
                        break;
                    case 7:
                        System.out.println("Fire 7");
                        outputArea.append("Fire 7\n");
                        s.pop();
                        s.push(4);
                        s.push(5);
                        s.push(-4);
                        break;
                    case 8:
                        System.out.println("Fire 8");
                        outputArea.append("Fire 8\n");
                        s.pop();
                        break;
                    case 9:
                        System.out.println("Fire 9");
                        outputArea.append("Fire 9\n");
                        s.pop();
                        s.push(6);
                        s.push(7);
                        break;
                    case 10:
                        System.out.println("Fire 10");
                        outputArea.append("Fire 10\n");
                        s.pop();
                        s.push(6);
                        s.push(7);
                        s.push(-7);
                        break;
                    case 11:
                        System.out.println("Fire 11");
                        outputArea.append("Fire 11\n");
                        s.pop();
                        break;
                    case 12:
                        System.out.println("Fire 12");
                        outputArea.append("Fire 12\n");
                        s.pop();
                        s.push(-6);
                        s.push(1);
                        s.push(-5);
                        break;
                    case 13:
                        System.out.println("Fire 13");
                        outputArea.append("Fire 13\n");
                        s.pop();
                        s.push(-8);
                        break;
                    case 98:
                        System.out.println("Scan Error");
                        outputArea.append("Scan Error\n");
                        break;
                    case 99:
                        System.out.println("Pop Error");
                        outputArea.append("Pop Error\n");
                        //s.pop();
                        break;
                    default:
                        System.out.println("Error");
                        outputArea.append("Error\n");
                        break;
                }//end swtich
            }//end if
            else {
                System.out.println("Match and pop " + current_token);
                outputArea.append("Match and pop " + current_token + "\n");
                s.pop();
                current_token = scan.next();
            }// else {
//                System.out.println("Error");
//            }

        }//end while

        //Something
    }

    public int next_table_entry(int non_terminal, int token) {
        int parse_table[][] = {
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 98, 98, 1, 98, 1, 99, 99, 1, 98},
            {0, 2, 3, 98, 98, 98, 4, 98, 98, 4},
            {0, 5, 99, 98, 98, 5, 99, 99, 5, 98},
            {0, 8, 8, 6, 7, 98, 8, 98, 98, 8},
            {0, 9, 99, 99, 99, 9, 99, 99, 9, 98},
            {0, 11, 11, 11, 11, 98, 11, 10, 98, 11},
            {0, 99, 99, 99, 99, 12, 99, 99, 13, 99}
        };

        return parse_table[non_terminal][token];
    }

}
