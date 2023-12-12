import java.util.*;
import java.text.*;
import java.io.*;
import java.lang.String;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.time.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.io.BufferedReader;
import java.io.FileReader;
import java.math.*;


public class Program {
	 
	    
	public static void main(String[] args) throws FileNotFoundException {
		BufferedReader reader = new BufferedReader(new FileReader("in.txt"));
	    PrintStream fileOut = new PrintStream( "out.txt");
	    Scanner in = new Scanner(System.in);
		
	    String str="";
		int n, m;
		
		  try {
		str = reader.readLine();
		n = Integer.parseInt(str);
		str = reader.readLine();
		m =Integer.parseInt(str);
		
		if(n > m)
		{
			int a = n;
			n = m;
			m = a;
		}
		
		if(n==0)
		{
			fileOut.print(0);
			return;
		}
		
		int size_ = (int) Math.pow(2, n);
		
		int [][] binnumb=new int[size_][n];
		for(int i = 0; i < n - 4 ;i+=5)
		{
			binnumb[0][i]=0;
	        binnumb[0][i+1] = 0;
	        binnumb[0][i+2]=0;
	        binnumb[0][i+3] = 0;
	        binnumb[0][i+4] = 0;
		}
		
		for(int i = 1; i < size_; i++)
		{
			 int b;   
			 int a = i;
			 int j = 0; 
	             while(a !=0){  
	                b = a%2;  
	                binnumb[i][n - 1-j] = b; 
	                j++;
	                a = a/2;  
	           } 
	           
		}
		
		/*for(int i = 0; i < (int) Math.pow(2, n); i++)
		{
			for(int j = 0; j < n; j++)
			{
				fileOut.print(binnumb[i][j]+" ");
			}
			fileOut.println("             "+i);
		}*/
		
		int[][] matrsmeznost = new int[size_][size_];
		for(int i = 0; i < size_; i++)
		{
			for(int j = 0; j < size_; j++)
			{
				matrsmeznost[i][j]=smeznost(binnumb[i], binnumb[j], n);
				
			}
		}
		
		
		/*for(int i = 0; i < (int) Math.pow(2, n); i++)
		{
			for(int j = 0; j < (int) Math.pow(2, n); j++)
			{
				fileOut.print(matrsmeznost[i][j]+" ");
			}
			fileOut.println("             "+i);
		}*/
		
		BigInteger [][]resultmatr = new BigInteger[size_][m+1];
		BigInteger res;
		for(int i = 0; i < size_; i++)
		{
			resultmatr[i][1] = BigInteger.ONE;
		}
		
		for(int j = 2; j < m+1; j++)
		{
			for(int i = 0; i < size_; i++)
			{
				res = BigInteger.ZERO;
				for(int k = 0; k < size_; k++)
				{
					if(matrsmeznost[i][k] == 1)
					{
						res=res.add(resultmatr[k][j-1]);
					}
				}
				
				resultmatr[i][j] = res;
			}
			
		}
		
		/*for(int i = 0; i < (int) Math.pow(2, n); i++)
		{
			for(int j = 1; j < m+1; j++)
			{
				fileOut.print(resultmatr[i][j]+" ");
			}
			fileOut.println(" ");
		}*/
		
		BigInteger k = BigInteger.valueOf(0);
		
		for(int i = 0; i < size_; i++)
		{
			
			k = k.add(resultmatr[i][m]);
		}
		
		fileOut.println(k);
		
		
		  } catch (IOException e) {
		        e.printStackTrace();
		    }	
	
	}
	
	public static int smeznost(int []mas1, int []mas2, int size)
	{
		for(int i = 0; i < size-1; i++)
		{
			if((mas1[i]==mas2[i])&&(mas1[i+1]==mas2[i+1])&&(mas1[i]==mas1[i+1]))
			{
				return 0;
			}
		}
		return 1;
	}
	}