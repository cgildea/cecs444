import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.util.*;
import java.awt.Font;
import java.io.*;

public class OpenTextFile {
	static ArrayList<String> stream = new ArrayList<String>();
	static boolean b = false;

	public static void main(String[] args) throws FileNotFoundException, IOException {
		
		String fileName, search;
		JTextArea outputTextArea = new JTextArea(10, 30);
		Font font = new Font("Monospaced", Font.PLAIN, 14);
		outputTextArea.setFont(font);
		JScrollPane scroller = new JScrollPane(outputTextArea);
		
		fileName = JOptionPane.showInputDialog("Enter External File to Open");
		Scanner scanner = new Scanner(new File(fileName));
		JTextArea outputOriginalFile = new JTextArea(10, 30);
		JScrollPane scroller2 = new JScrollPane(outputOriginalFile);
		outputOriginalFile.setText("The original File was: \n");
		
		while(scanner.hasNext())
		{
			String origFile = scanner.nextLine();
			outputOriginalFile.append(origFile+"\n");
		}
		JOptionPane.showMessageDialog(null, scroller2, "Opened file...", JOptionPane.INFORMATION_MESSAGE);
		PrintWriter pw = new PrintWriter(new File("outputOfFile.txt"));
		String scan = new Scanner(new File(fileName)).useDelimiter("\\Z").next();
		String [] alpha = scan.split("\\r?\\n");
		iterate(alpha, null);
		outputTextArea.setText("The Modified Text is: \n"  + textOut());

		JOptionPane.showMessageDialog(null, scroller, "Results...", JOptionPane.INFORMATION_MESSAGE);
		
		search = JOptionPane.showInputDialog("Search for a word:");
		
		iterate(alpha, search);
		if (b == true)
		{
			outputTextArea.setText("TRUE");
			JOptionPane.showMessageDialog(null,outputTextArea, "Results...", JOptionPane.INFORMATION_MESSAGE);
		}
		else 
		{
			outputTextArea.setText("FALSE");
			JOptionPane.showMessageDialog(null,outputTextArea, "Results...", JOptionPane.INFORMATION_MESSAGE);
		}
		pw.close();
	}
	public static void iterate(String[] input2, String search)
	{
		String tmp;
		for (int i = 0;i < input2.length;i++)
		{
			tmp = input2[i];
			for (int j = 0;j < input2.length;j++)
			{
				if (i == j) continue; 
				int x = tmp.compareTo(input2[j]); 
				if (x < 0) 
				{
					tmp = input2[j];
					input2[j] = input2[i];
					input2[i] = tmp;
				}
			}
		}
		for (String tmp2: input2){
			stream.add(tmp2);
		}
		if(search != null)
		{
			for (int i = 0; i < input2.length; i++) {
				b = input2[i].matches("(?i).*"+search+".*");

				if (b == true)
					break;
			}
		}
	}
	public static String textOut()
	{
		String out = "";
		for (String tmp3 : stream) {
			out = out + tmp3 + "\n";
		}
		return out;
	}
}
