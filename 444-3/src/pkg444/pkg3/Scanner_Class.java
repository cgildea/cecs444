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

/**
 *
 * @author Cody
 */
public class Scanner_Class {
        
    public static void main(String args[]) throws FileNotFoundException{
        
        String filename = "source.txt";
        read_characters(filename);
    }
        
    static int current_read;
    static int state;
    static String token_under_construction;
    /*
    Scanner_Class()
    {
        
    }*/

    public static void read_characters(String filename) throws FileNotFoundException
    {
        String current_char = "";
        boolean buffered = false;
        //System.out.println("Enter a text file: ");
        Scanner scan = new Scanner(new File(filename));
        while(scan.hasNext())
        {
            String char_list = scan.nextLine();
            
            if ((!buffered) || current_char==" " || (current_char == "\n"))
               current_char = scan.nextLine();
          
            System.out.print("Current char = "+current_char);             
            System.out.print("Status of EOF = "+scan.hasNext()+"\n");

            if(current_char.matches("^.*[^a-zA-Z ].*$"))
                current_read = 0;
            else if(current_char.matches("^.*[^0-9 ].*$"))
                current_read = 1;
            else if (current_char.matches(".*\\w.*"))
                current_read = 4;
            else
            {
                switch(current_char)
                {
                    case "_":
                        current_read = 2;
                        break;
                    case ".":
                        current_read = 3;
                        break;
                    case "\n":
                        current_read = 4;
                    default:
                        current_read = 4;
                        break;
                }
            }
            System.out.print("current state = "+state+
                    " current_char = "+current_char+
                    " token status = "+token_under_construction+
                    " \n");
                    
            if(next_state(state, current_read)==-1 && (action(state, current_read)==1))
            {
                buffered = false;
                token_under_construction = token_under_construction + current_char;
                state = next_state(state, current_read);
            }
            else if(next_state(state, current_read)==-1 && (action(state, current_read)==2))
            {
                System.out.print("inside switch with state = "+state+
                    " and char "+current_read+
                    " \nWe have a buffered character ="+current_char+
                    " \n");
                buffered = true;
                switch(look_up(state, current_read))
                {
                    case 1:
                        System.out.print("TOKEN DISCOVERED is IDENTIFIER-> "+
                                token_under_construction+"\n");
                        break;
                    case 2:
                        System.out.print("TOKEN DISCOVERED is INTEGER-> "+
                                token_under_construction+"\n");
                        break;
                    case 3:
                        System.out.print("TOKEN DISCOVERED is REALLIT-> "+
                                token_under_construction+"\n");
                        break;
                    default:
                        System.out.print("error\n");
                        break;  
                }
                state = 0;
                token_under_construction = "";
                
            }
            if(scan.hasNext() == false)
                break;
	}
        System.out.print("Done Scanning!\n");
        scan.close();
    }
    public static int next_state(int new_state, int new_char)
    {
        int state_table[][]= {  {1, 2, -1, 3, -1},
                                {1, 1, 4, -1, -1},
                                {-1, 2, -1, 5, -1},
                                {-1, 5, -1, -1, -1},
                                {1, 1, -1, -1, -1},
                                {-1, 5, -1, 5, -1} };
        return state_table[new_state][new_char];
    }
    public static int action(int new_state, int new_char)
    {
        int action_table[][]= { {1, 1, 0, 1, 0},
                                {1, 1, 1, 2, 2},
                                {2, 1, 2, 1, 2},
                                {0, 1, 0, 0, 0},
                                {1, 1, 0, 0, 0},
                                {2, 1, 2, 1, 2} };
        return action_table[new_state][new_char];
    }
    public static int look_up(int new_state, int new_char)
    {
        int look_up_table[][]= {    {0, 0, 0, 0, 0},
                                    {0, 0, 0, 1, 1},
                                    {2, 0, 2, 0, 2},
                                    {0, 0, 0, 0, 0},
                                    {0, 0, 0, 0, 0},
                                    {3, 0, 3, 3, 3} };
        return look_up_table[new_state][new_char];
    }

}
