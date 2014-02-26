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

import java.awt.Font;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author Cody
 */
public class ScannerLoader {
       public static void main(String[] args) throws FileNotFoundException, 
               IOException{
        Scanner_Class scan_cl = new Scanner_Class();
        String file;
        JTextArea fileOutput = new JTextArea();
        JScrollPane scrolly1 = new JScrollPane(fileOutput);
        Font font = new Font("Monospaced", Font.PLAIN, 14);
        JTextArea outputTextArea = new JTextArea();
        outputTextArea.setRows(10);
        outputTextArea.setFont(font);
        JScrollPane scrolly2 = new JScrollPane(outputTextArea);
        file = JOptionPane.showInputDialog("Enter a text File: ");
        Scanner scan = new Scanner(new File(file));
        String orig;
        while(scan.hasNext()){
            orig = scan.nextLine();
            fileOutput.append(orig + "\n");
        }
        JOptionPane.showMessageDialog(null, scrolly1, "Oringial File: ", 
                JOptionPane.INFORMATION_MESSAGE);
        scan.close();
        scan_cl.read_characters(file, outputTextArea);
        JOptionPane.showMessageDialog(null, scrolly2, "Scanned File: ", 
                JOptionPane.INFORMATION_MESSAGE);
       }
}
