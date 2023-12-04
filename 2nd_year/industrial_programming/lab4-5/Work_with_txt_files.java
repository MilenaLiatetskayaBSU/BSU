package lab5;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;


public class Work_with_txt_files implements Work_with_files
{

	private String inputfilename;
	private String outputfilename;
	
	public Work_with_txt_files(String inputfilename, String outputfilename)
	{
		this.inputfilename = inputfilename;
		this.outputfilename = outputfilename;
	}
	
	@Override
	public List_of_multicookers read() {
		List_of_multicookers multicookers = new List_of_multicookers();
			BufferedReader reader;
			try {
				reader = new BufferedReader(new FileReader(inputfilename));
			 String str = reader.readLine();
	            while (str != null) 
	            {
	                String[]parts = str.split(" ");
	                multicooker cooker = new multicooker.Builder(parts[0], Integer.parseInt(parts[1]), Integer.parseInt(parts[2])).build();
	                multicookers.insert_object(cooker);
	                str = reader.readLine();
	            }
	            reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			return multicookers;
		
		
	}

	@Override
	public void write(List_of_multicookers multicookers2) {
		 try {
			PrintStream fileOut = new PrintStream(outputfilename);
			for(int i = 0; i < multicookers2.Size(); i++)
			{
				fileOut.println(multicookers2.get(i).toString());
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}

}
