import java.util.ArrayList;
import java.util.Iterator;


public class Splicer {
	String input;
	int count = 0;
	ArrayList<String> stream = new ArrayList<String>();
	public Splicer()
	{
		
	}
	public Splicer(String s)
	{
		this.input = s;
		iterate(input);
		
	}
	
	public void iterate(String input)
	{
		for (int i = 0; i < this.input.length(); i++) {
			char nextChar = this.input.charAt(i);
			getTextChain(nextChar);
		}
		
	}
	
	public void getTextChain(char nextChar){
		if (nextChar == 0) return;
		else if(nextChar == 10 )
			stream.add("EOL");
		else if(nextChar == 32)
			stream.add("BL");
		else if(nextChar >= 33 && nextChar <= 126)
			stream.add(nextChar+"");
  
	}
	public String textOut()
	{
		String spit = "";
		stream.add("EOF");
		for(String out : this.stream){
			spit = spit + (String.format("%-5s", out));
			count++;
			if (count == 20)
			{
				spit = spit + ("\n");
				count = 0;
			}
		}
		return spit;
	}
	
}
