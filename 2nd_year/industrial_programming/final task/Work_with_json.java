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
	
	public Work_with_json()
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
	
	public ArrayList<Double> Read_without_API(String inputname) throws IOException
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
			result2.add(calculations.c(task));
		}
		 
		return result2;
		
	}
	
	@Override
	public ArrayList<Double> Read(String inputname) {
	
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
        		result2.add(calculations.c(object.getString("task")));
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

	
	
	public void Write_without_API(String outputname) throws FileNotFoundException
	{
		PrintStream fileOut = new PrintStream(outputname);
		
		for(int i = 0; i < result.size(); i++)
		{
			fileOut.println("{\"result\":"+result.get(i)+"}");
		}
	}
	
	@Override
	public void Write(String outputname) {
		try {
			PrintStream fileOut = new PrintStream(outputname);
			JSONObject resultJson = new JSONObject();
			
			
			for(int i = 0; i < result.size(); i++)
			{
				resultJson.put("result", result.get(i));
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
