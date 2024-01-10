package final_task;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;

public class Work_with_txt implements FileBuilder {

	private ArrayList<Double>result;
	calculator1 calculations;
	
	public Work_with_txt()
	{
		result = new ArrayList<>();
		calculations = new calculator1();
	}

	public void addArray(ArrayList<Double>list)
	{
		for(int i = 0; i < list.size(); i++)
		{
			result.add(list.get(i));
		}
	}
	
	@Override
	public ArrayList<Double> Read(String inputname) {
		ArrayList<Double>result2=new ArrayList<>();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(inputname));
			String str="";
			while(str!=null)
			{
     			str = reader.readLine();
				if(str!=null)
				{
				   result.add(calculations.c(str));
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
      return result2;
	}

	@Override
	public void Write(String outputname) {
		try {
			PrintStream fileOut = new PrintStream(outputname);
			for(int i = 0; i < result.size(); i++)
			{
				fileOut.println(result.get(i));
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
