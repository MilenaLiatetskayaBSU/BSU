package final_task;

import java.io.IOException;
import java.util.ArrayList;

public class final_task {
	public static void main(String[] args) throws IOException
	{
		
		archiving arch = new archiving();
		arch.archive("archieve", "input.txt");
		arch.read("archieve");
	}
}
