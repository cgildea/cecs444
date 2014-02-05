import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Tester {

	/**
	 * @param args
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException {
		String scan = new Scanner(new File("test.txt")).useDelimiter("\\Z").next();
		Splicer splice = new Splicer(scan);
		
		//System.out.printf("%s", splice.getTextBlock());
		
	}

}
