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
	calculator_with_regular calc;
	
	public Work_with_txt()
	{
		result = new ArrayList<>();
		calculations = new calculator1();
		calc = new calculator_with_regular();
	}

	public void addArray(ArrayList<Double>list)
	{
		for(int i = 0; i < list.size(); i++)
		{
			result.add(list.get(i));
		}
	}
	
	@Override
	public ArrayList<Double> Read(String inputname, int answer) {
		ArrayList<Double>result2=new ArrayList<>();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(inputname));
			String str="";
			while(str!=null)
			{
     			str = reader.readLine();
				if(str!=null)
				{
					if(answer==1)
					{
						encryption encr = new encryption();
						str = encr.decrypt(str);
						
					}
				   result2.add(calc.evaluate(str));
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
      return result2;
	}

	@Override
	public void Write(String outputname, int answer) {
		try {
			PrintStream fileOut = new PrintStream(outputname);
			for(int i = 0; i < result.size(); i++)
			{
				if(answer == 1)
				{
					encryption encr = new encryption();
					fileOut.println(encr.encrypt(String.valueOf(result.get(i))));
				}
				else
				{
				fileOut.println(result.get(i));
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
