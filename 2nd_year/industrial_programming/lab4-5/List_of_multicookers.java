package lab5;

import java.util.ArrayList;
import java.util.Collections;


public class List_of_multicookers
{
	
	private ArrayList<multicooker> cookers;
	
	public List_of_multicookers()
	{
		cookers = new ArrayList<multicooker>();
	}
	
	public ArrayList<multicooker> GetList()
	{
		return this.cookers;
	}
	
	public int Size()
	{
		return cookers.size();
	}
	
	public multicooker get(int i)
	{
		return cookers.get(i);
	}
	
	public void insert_object(multicooker cooker)
	{
		cookers.add(cooker);
	}
	
	void Sort_by_cost()
	{
		Collections.sort(cookers, multicooker.COMPARE_BY_COST);
	}
	
	void Sort_by_volume()
	{
		Collections.sort(cookers, multicooker.COMPARE_BY_VOLUME);
	}
}
