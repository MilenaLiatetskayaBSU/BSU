package final_task;

import java.util.ArrayList;

public interface FileBuilder {
	
	public abstract ArrayList<Double> Read(String inputname);
	public abstract void Write(String outputname);

}
