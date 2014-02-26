/*
 * Copyright 2014 Cody Gildea.
 *
 * You may not use this file except in compliance with the License.
 *
 *      http://www.codygildea.com
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package pkg444.pkg3;

import com.sun.org.apache.xalan.internal.xsltc.runtime.BasisLibrary;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javax.swing.JTextArea;
import java.io.IOException;


/**
 *
 * @author Cody
 */
public class Scanner_Class {
        
    private int current_read;
    private int state;
    private String token_under_construction;
    
    Scanner_Class()
    {
        token_under_construction = "";
        state = 0;
        current_read = 0; 
    }

    public void read_characters(String filename, JTextArea outputArea) 
            throws FileNotFoundException, IOException
    {
        char current_char = ' ';
        boolean buffered = false;
        Scanner scan = null;
        File inFile = new File(filename);
        scan = new Scanner(inFile);
        

        while(scan.hasNext())
        {
            char [] char_array = scan.nextLine().toCharArray();
            for(int i = 0; i < char_array.length; i++)
            {
                if ((!buffered) || current_char==' ' || 
                        (current_char == '\n'))
                    current_char = char_array[i];
            
                System.out.print("Current char = "+current_char);             
                System.out.print("Status of EOF = "+scan.hasNext()+"\n");

                if(Character.isLetter(current_char))
                    current_read = 1;
                else if(Character.isDigit(current_char))
                    current_read = 0;
//                else if (Character.isWhitespace(current_char))
//                    current_read = 10;
                else
                {
                    switch(current_char)
                    {
                            case '_':
                                current_read = 3;
                                break;
                            case '.':
                                current_read = 2;
                                break;
                            
                            case '=':
                                current_read = 4;
                                break;
                            case '+':
                                current_read = 5;
                                break;
                            case ';':
                                current_read = 6;
                                break;
                            case '*':
                                current_read = 7;
                                break;
                            case '(':
                                current_read = 8;
                                break;
                            case ')':
                                current_read = 9;
                                break;
                            case '\n':
                                current_read = 10;
                                break;
                            case ' ':
                                current_read = 10;
                                break;
                            default:
                                current_read = 10;
                                break;
                    }
                }
                System.out.print("current state = "+state+
                        " current_char = "+current_char+
                        " token status = "+token_under_construction+
                        " \n");

                if(next_state(state, current_read) !=-1 && 
                        (action(state, current_read)==1))
                {
                    buffered = false;
                    token_under_construction = 
                            token_under_construction + current_char;
                    state = next_state(state, current_read);
                }
                else if(next_state(state, current_read)==-1 && 
                        (action(state, current_read)==2))
                {
                    System.out.print("inside switch with state = "+state+
                        " and char "+current_read+
                        " \nWe have a buffered character ="+current_char+
                        " \n");
                    buffered = true;
                    switch(look_up(state, current_read))
                    {
                            case 1:
                                outputArea.append("Token discovered is an "
                                        + "INTEGER: " + 
                                        token_under_construction + "\n");
                                break;
                            case 2:
                                outputArea.append("Token discovered is an "
                                        + "IDENTIFIER: " + 
                                        token_under_construction +"\n");
                                break;
                            case 3:
                                outputArea.append("Token discovered is an "
                                        + "ASSIGNMENT OPERATOR: " + 
                                        token_under_construction + "\n");
                                break;
                            case 4:
                                outputArea.append("Token discovered is an "
                                        + "ADDITION OPERATOR: " + 
                                        token_under_construction + "\n");
                                break;
                            case 5:
                                outputArea.append("Token discovered is a "
                                        + "SEMICOLON: " + 
                                        token_under_construction + "\n");
                                break;
                            case 6:
                                outputArea.append("Token discovered is a "
                                        + "MULTIPLY OPERATOR: " + 
                                        token_under_construction + "\n");
                                break;
                            case 7:
                                outputArea.append("Token discovered is an "
                                        + "OPEN PARENTHESES: " + 
                                        token_under_construction + "\n");
                                break;
                            case 8:
                                outputArea.append("Token discovered is a "
                                        + "CLOSE PARENTHESES: " + 
                                        token_under_construction +  "\n");
                                break;
                            case 9:
                                outputArea.append("Token discovered is a "
                                        + "REAL NUMBER: " + 
                                        token_under_construction + "\n");
                            default:
                                System.out.println("error");
                                break;  
                    }
                    state = 0;
                    token_under_construction = "";
                    --i;
                }
            }

	}
        
        outputArea.append("Done scanning!\n");
        scan.close();
    }
    public static int next_state(int new_state, int new_char)
    {
        int state_table[][]= {  {1, 2, 3, -1, 4, 5, 6, 7, 8, 9},
                                {1, -1, 10, -1, -1, -1, -1, -1, -1, -1},
                                {2, 2, -1, 11, -1, -1, -1, -1, -1, -1},
                                {10, -1, -1, -1, -1, -1, -1, -1, -1, -1},
                                {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
                                {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
                                {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
                                {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
                                {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
                                {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
                                {10, -1, -1, -1, -1, -1, -1, -1, -1, -1},
                                {2, 2, -1, -1, -1, -1, -1, -1, -1, -1} };
        return state_table[new_state][new_char];
    }
    public static int action(int new_state, int new_char)
    {
        int action_table[][]= { {1, 1, 1, 0, 1, 1, 1, 1, 1, 1},
                                {1, 2, 1, 2, 2, 2, 2, 2, 2, 2},
                                {1, 1, 2, 1, 2, 2, 2, 2, 2, 2},
                                {1, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                {2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
                                {2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
                                {2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
                                {2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
                                {2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
                                {2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
                                {1, 2, 2, 2, 2, 2, 2, 2, 2, 2},
                                {1, 1, 0, 0, 0, 0, 0, 0, 0, 0} };
        return action_table[new_state][new_char];
    }
    public static int look_up(int new_state, int new_char)
    {
        int look_up_table[][]= { {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                 {0, 1, 0, 1, 1, 1, 1, 1, 1, 1},
                                 {0, 0, 2, 0, 2, 2, 2, 2, 2, 2},
                                 {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                 {3, 3, 3, 3, 3, 3, 3, 3, 3, 3},
                                 {4, 4, 4, 4, 4, 4, 4, 4, 4, 4},
                                 {5, 5, 5, 5, 5, 5, 5, 5, 5, 5},
                                 {6, 6, 6, 6, 6, 6, 6, 6, 6, 6},
                                 {7, 7, 7, 7, 7, 7, 7, 7, 7, 7},
                                 {8, 8, 8, 8, 8, 8, 8, 8, 8, 8},
                                 {0, 9, 9, 9, 9, 9, 9, 9, 9, 9},
                                 {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}};
        return look_up_table[new_state][new_char];
    }

}
