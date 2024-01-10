package final_task;

import java.util.Stack;

public class calculator1 {

	public double perform_calculations(Stack<Double> numbers, Stack<String> operators )
	{
		double res = 0;
		 int size = operators.size();
	        for(int i = 0; i < size; i++)
	        {
	        	if(operators.get(i).equals( "*"))
	        	{
	        	    res = numbers.get(i)*numbers.get(i+1);
	        	    numbers.remove(i+1);
	        	    numbers.remove(i);
	        	    numbers.add(i,res);
	        		operators.remove(i);
	        		i = i-1;
	        		size = operators.size();
	        		//System.out.println(res);
	        	}
	        	
	        }
	        
	        operators.remove("*");
	        
	        for(int i = 0; i < size; i++)
	        {
	        	if(operators.get(i).equals( "/"))
	        	{
	        	    res = numbers.get(i)/numbers.get(i+1);
	        	    
	        	    numbers.remove(i+1);
	        	    numbers.remove(i);
	        	    numbers.add(i,res);

	        		operators.remove(i);
	        		i = i-1;
	        		size--;
	        		//System.out.println(res);
	        	}
	        	
	        }
	        
	        operators.remove("/");
	        
	        for(int i = 0; i < operators.size(); i++)
	        {
	        	if(operators.get(i).equals( "-"))
	        	{
	        	    res = numbers.get(i)-numbers.get(i+1);
	        	    numbers.remove(i+1);
	        	    numbers.remove(i);
	        	    numbers.add(i,res);
	        		operators.remove(i);
	        		i = i-1;
	        		size = operators.size();
	        		//System.out.println(res);
	        	}
	        	
	        }
	        
	        operators.remove("-");
	        
	        for(int i = 0; i < operators.size(); i++)
	        {
	        	if(operators.get(i).equals( "+"))
	        	{
	        	    res = numbers.get(i)+numbers.get(i+1);
	        	    numbers.remove(i+1);
	        	    numbers.remove(i);
	        	    numbers.add(i,res);
	        		operators.remove(i);
	        		i = i-1;
	        		size = operators.size();
	        		//System.out.println(res);
	        	}
	        	
	        }
	        
	        operators.remove("+");
		return res;
	}
	
	
	
	
	public double c(String expression)
	{
		String stringwithoutbrackets="";
		for(int i = 0; i < expression.length(); i++)
		{
			if (expression.charAt(i)=='(')
			{
				String expressioninbrackets="";
				i++;
				for( ; ; i++)
				{
					if(expression.charAt(i)==')')
					{
						break;
					}
					expressioninbrackets+=String.valueOf(expression.charAt(i));
					//System.out.println(expression.charAt(i));
					
				}
				
				
				String number=Double.toString(calculate(expressioninbrackets));
				String resnumber = "";
				
				for(int j = 0; j < number.length(); j++)
				{
					if(number.charAt(j)=='.')
					{
						break;
					}
					
					resnumber+=String.valueOf(number.charAt(j));
				}
				
				stringwithoutbrackets+=resnumber;
			}
			else
			{
				stringwithoutbrackets+=String.valueOf(expression.charAt(i));
			}
		}
		
		//System.out.println(stringwithoutbrackets);
		
		return calculate(stringwithoutbrackets);
	}
	
	
	public double calculate(String expression)
	{
		Stack<Double> numbers = new Stack<>();
        Stack<String> operators = new Stack<>();
        
        int len = 0;
        int ind = 0;
        
        for(int i = 0; i < expression.length(); i++)
        {
        	//System.out.println(expression.charAt(i));
        	if(expression.charAt(i)=='+' || expression.charAt(i)=='-'|| expression.charAt(i)=='*'||expression.charAt(i)=='/')
        	{
        		operators.add(String.valueOf(expression.charAt(i)));
        		len = 0;
        	}
        	else
        	{
        		if(len==0)
        		{
        			double number =Double.valueOf(String.valueOf(expression.charAt(i)));
        		    numbers.add(number);
       
        		    len++;
        		    ind++;
        		}
        		
        		else
        		{
        			double number = numbers.get(ind-1)*10+Double.valueOf(String.valueOf(expression.charAt(i)));
        			numbers.remove(ind-1);
        			numbers.add(number);
        			len++;
        		}
        	}
        }
        
       
        
		return perform_calculations(numbers, operators);
	}
	
}
