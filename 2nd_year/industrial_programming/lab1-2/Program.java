package yyy;
import java.util.Scanner;
import java.util.Formatter;
import java.text.*;
import java.math.BigDecimal;

public class Program {
	
	private static int t = 0;
	
	public static BigDecimal getResultt(double x, int k)
	{
		BigDecimal big_x = new BigDecimal(x);
	       big_x =big_x.negate();//-x
	       BigDecimal big_u = new BigDecimal(1);
	       BigDecimal big_n = new BigDecimal(1);
	       BigDecimal big_fx = new BigDecimal(1);
	       BigDecimal big_2n = new BigDecimal(1);
	       BigDecimal big_2n2 = new BigDecimal(1);
	       while(t!=-1)
	       {
	    	   big_2n = big_n;
	    	   big_2n=big_2n.multiply(new BigDecimal(2)); //2*n
	    	   big_2n2 = big_2n;
	    	   big_2n2 = big_2n2.add(new BigDecimal(-3));//2*n-3
	    	   big_u = big_u.multiply(big_x);
	    	   big_u = big_u.multiply(big_2n2);
	    	   big_u = big_u.divide(big_2n);
	    	   big_fx = big_fx.add(big_u);
	    	   big_n = big_n.add(new BigDecimal(1));
	    	   t--;
	       }
	       return big_fx;
	}
	
	public static double getResult(double x, int k)
	{
		double b = 1;
        for (int i = 1; i <= k; i++)
         {
        	b = b/10;
        }
        double u = 1, fx = 1;
        int n = 0;
        int t = 0;
        while (Math.abs(u) > b)
        {
            n++;
            u *= -x * (2.0 * n - 3) / (2.0 * n);
            fx += u;
            t++;
        }
        
        Formatter fmt = new Formatter(); 
        fmt.format("| result = %010f|", fx);
        return fx;
	}
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		double x;
		do {
		System.out.println("Enter x");
         x = in.nextDouble();
		}while((x<=-1)||(x>=1));
        System.out.println("Enter k");
        int k = in.nextInt();
        
       Formatter fmt = new Formatter(); 
       fmt.format("| result = %010f|", getResult(x,k));
       System.out.println(fmt);
      // System.out.println(getResult(x,k));
       Formatter fmt2 = new Formatter();
       fmt2.format("| result by calculator = %."+(k+1)+"f|", Math.sqrt(x+1));
       System.out.println(fmt2);
       
       System.out.println("Result by using BigDecimal class "+getResultt(x,k));
       System.out.println("Enter integer number ");
       int numb = in.nextInt();
       Formatter formatter = new Formatter(System.out);
       formatter.format("Octal(8): %o%n", numb);
       formatter.format("Hexadecimal(16): %x%n", numb);
       in.close();
}
}
