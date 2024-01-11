package final_task;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

public class Work_with_json implements FileBuilder{

	private ArrayList<Double>result;
	private calculator1 calculations;
	calculator_with_regular calc;
	
	public Work_with_json()
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
	
	public ArrayList<Double> Read_without_API(String inputname, int answer) throws IOException
	{
		BufferedReader reader= new BufferedReader(new FileReader(inputname));
		
		ArrayList<Double>result2=new ArrayList<>();
		
		String result="";
		while(true)
		{
			String task = "";
			result = reader.readLine();
			
			if(result == null)
			{
				break;
			}
			
			for(int i = 9; ; i++)
			{
				if(result.charAt(i)=='"')
					{
						break;	
					}
				task+=String.valueOf(result.charAt(i));
			}
			
			if(answer==1)
			{
				encryption enc = new encryption();
				result2.add(calc.evaluate(enc.decrypt( task)));
			}
			else
			{
				result2.add(calc.evaluate(task));
			}
			
			
		}
		 
		return result2;
		
	}
	
	@Override
	public ArrayList<Double> Read(String inputname, int answer) {
	
		BufferedReader reader;
		 ArrayList<Double>result2=new ArrayList<>();
		try {
			reader = new BufferedReader(new FileReader(inputname));
        String result="";
    
        try {
        	while(true)
       	 {
        		result = reader.readLine();
        		if(result == null)
        		{
        			break;
        		}
        		
        		JSONObject object = (JSONObject) new JSONTokener(result).nextValue();
        		
        		if(answer == 1)
        		{
        			encryption enc = new encryption();
        			result2.add(calc.evaluate(enc.decrypt(object.getString("task"))));
        		}
        		
        		else
        		{
        			result2.add(calc.evaluate(object.getString("task")));
        		}
        		
        		
         }
       	 
       	 
		} catch (IOException | JSONException e) {
			e.printStackTrace();
		}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
       
		return result2;
	}

	
	
	public void Write_without_API(String outputname, int answer) throws FileNotFoundException
	{
		PrintStream fileOut = new PrintStream(outputname);
		
		for(int i = 0; i < result.size(); i++)
		{
			if(answer==1)
			{
				encryption enc = new encryption();
				fileOut.println("{\"result\":"+enc.encrypt(String.valueOf(result.get(i))) +"}");
			}
			else
			{
				fileOut.println("{\"result\":"+result.get(i)+"}");
			}
			
			
		}
	}
	
	@Override
	public void Write(String outputname, int answer) {
		try {
			PrintStream fileOut = new PrintStream(outputname);
			JSONObject resultJson = new JSONObject();
			
			
			for(int i = 0; i < result.size(); i++)
			{
				if(answer==1)
				{
					encryption en = new encryption();
					resultJson.put("result", en.encrypt(String.valueOf(result.get(i)) ));
				}
				else
				{
					resultJson.put("result", result.get(i));
				}
				
				
				fileOut.print(resultJson.toString());
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
