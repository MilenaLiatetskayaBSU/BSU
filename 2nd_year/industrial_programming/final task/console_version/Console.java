package final_task;

import java.util.ArrayList;
import java.util.Scanner;

public class Console {

	private Scanner in;
	private String inputfilename, outputfilename;
	private String type;
	private int answers;
	private ArrayList<Double>result;
	
	public Console()
	{
		 in = new Scanner(System.in);
		 result = new ArrayList<>();
		
	}
	public void dialog()
	{ 
		System.out.println("Enter inputfilename (without it's type)");
		inputfilename = in.nextLine();
		
		System.out.println("Enter file type (.txt, .json, .xml)");
		type = in.nextLine();
		
		System.out.println("Is your file archieved? (enter 1 if yes, 0 if no)");
		do {
		answers = in.nextInt();
		}while(answers!=0 && answers!=1);
		
		if(answers==1)
		{
			archiving arch = new archiving();
			inputfilename = arch.read(inputfilename);
		}
		
		System.out.println("Is your file encrypted? (enter 1 if yes, 0 if no)");
		do {
			answers = in.nextInt();
			}while(answers!=0 && answers!=1);
		
		if(type.equals(".txt"))
	     {
			Work_with_txt file = new Work_with_txt();
			result = file.Read(inputfilename+type, answers);
	     }
		
		if(type.equals(".json"))
		{
			Work_with_json file = new Work_with_json();
			result = file.Read(inputfilename+type, answers);
		}
		
		if(type.equals(".xml"))
		{
			Work_with_xml file = new Work_with_xml();
			result = file.Read( inputfilename+type, answers);
		}
		
		
		
		System.out.println("");
		type = in.nextLine();
		System.out.println(type);
		
		System.out.println("Enter outputfile name");
		outputfilename = in.nextLine();
		
		//System.out.println(outputfilename);
		
		System.out.println("Enter outputfile type (.txt, .json, .xml)");
		type = in.nextLine();
		//System.out.println(type);
		
		System.out.println("Do you want to encrypt data? (enter 1 if yes, 0 if no)");
		do {
			answers = in.nextInt();
			}while(answers!=0 && answers!=1);
		
		if(type.equals(".txt"))
	     {
			Work_with_txt file = new Work_with_txt();
			file.addArray(result);
			file.Write(outputfilename+type, answers);
	     }
		
		if(type.equals(".json"))
		{
			Work_with_json file = new Work_with_json();
			file.addArray(result);
			file.Write(outputfilename+type, answers);
		}
		
		if(type.equals(".xml"))
		{
			Work_with_xml file = new Work_with_xml();
			file.addArray(result);
			file.Write(outputfilename+type, answers);
		}
		
		System.out.println("Do you want to archieve? (enter 1 if yes, 0 if no)");
		do {
			answers = in.nextInt();
			}while(answers!=0 && answers!=1);
		
		if(answers==1)
		{
			archiving a = new archiving();
			a.archive(outputfilename, outputfilename+type);
		}
	}
}
