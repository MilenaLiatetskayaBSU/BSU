package lab5;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

public class lab5 {
public static void main(String[] args) throws IOException {
		
		Scanner in = new Scanner(System.in);
		Work_with_txt_files read_and_write_txt = new Work_with_txt_files("input.txt", "output.txt");
		//List_of_multicookers multicookers = read_and_write_txt.read();
		//multicookers.insert_object(new multicooker.Builder("Name3", 2, 123).Color("Red").build());
		//read_and_write_txt.write(multicookers);
		Work_with_xml_files read_and_write_xml = new Work_with_xml_files("input.xml", "output.xml");
		List_of_multicookers multicookers=read_and_write_xml.read();
		multicookers.insert_object(new multicooker.Builder("Name4", 20, 1123).Color("R4ed").build());
		read_and_write_xml.write(multicookers);
		
		encryption encrypt = new encryption();
		PrintStream fileOut = new PrintStream("encryption.txt");
		for(int i = 0; i < multicookers.Size(); i++)
		{
			String str = encrypt.encrypt(multicookers.get(i).toString());
			fileOut.println(str);
		}
		
		BufferedReader reader;
		reader = new BufferedReader(new FileReader("encryption.txt"));
		PrintStream fileOut2 = new PrintStream("decryption.txt");
		 String str = reader.readLine();
           while (str != null) 
           {
             String str1 = encrypt.decrypt(str);
             fileOut2.println(str1);
             str = reader.readLine();
           }
           
           archiving archieve = new archiving();
           archieve.archive("arc", "input.txt");
           archieve.read("arc");
	}


}
