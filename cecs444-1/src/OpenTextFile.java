import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.text.*;
import java.util.*;
import java.awt.Font;
import java.io.*;

public class OpenTextFile {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws FileNotFoundException, IOException {
		
		JOptionPane myWindow;
		myWindow = new JOptionPane();
		String fileName;
		JTextArea outputTextArea = new JTextArea(10, 30);
		Font font = new Font("Monospaced", Font.PLAIN, 14);
		outputTextArea.setFont(font);
		JScrollPane scroller = new JScrollPane(outputTextArea);
		
		fileName = myWindow.showInputDialog("Enter External File to Open");
		Scanner scanner = new Scanner(new File(fileName));
		JTextArea outputOriginalFile = new JTextArea(10, 30);
		JScrollPane scroller2 = new JScrollPane(outputOriginalFile);
		outputOriginalFile.setText("The original File was: \n");
		while(scanner.hasNext())
		{
			String fullName = scanner.nextLine();
			outputOriginalFile.append(fullName+"\n");
		}
		myWindow.showMessageDialog(null, scroller2, "Opened file...", myWindow.INFORMATION_MESSAGE);
		PrintWriter pw = new PrintWriter(new File("outputOfFile.txt"));
		String scan = new Scanner(new File(fileName)).useDelimiter("\\Z").next();
		Splicer splice = new Splicer(scan);
		outputTextArea.setText("The Modified Text is: \n"  + splice.textOut());

		myWindow.showMessageDialog(null, scroller, "Results...", myWindow.INFORMATION_MESSAGE);
		
		System.out.println(splice.textOut());
		
		pw.close();
	}
}
