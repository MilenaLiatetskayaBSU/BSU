package lab6666;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;


public class list_of_objects {
	
	private ArrayList<multicookers> cookers;
	
	
	public list_of_objects()
	{

	}
	
	public int Size()
	{
		return cookers.size();
	}
	
	public multicookers get(int i)
	{
		return cookers.get(i);
	}
	
	public void insert_object(multicookers cooker)
	{
		cookers.add(cooker);
	}
	
	public void Sort_by_cost()
	{
		Collections.sort(cookers, multicookers.COMPARE_BY_COST);
		
		for(int i = 0; i < cookers.size(); i++)
		{
			System.out.println(cookers.get(i).toString());
		}
	}
	
	public void Sort_by_volume()
	{
		Collections.sort(cookers, multicookers.COMPARE_BY_VOLUME);
		for(int i = 0; i < cookers.size(); i++)
		{
			System.out.println(cookers.get(i).toString());
		}
	}
	
	public void Read(Work_with_files t)
	{
		cookers = t.Read();
		
	}
	
	}
    

