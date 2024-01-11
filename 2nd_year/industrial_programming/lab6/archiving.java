package lab6666;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class archiving {

	public void archive(String filename, String inputfilename)
	{
		 try(ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(filename+".zip"));
	                FileInputStream fis= new FileInputStream(inputfilename);)
	        {
	            ZipEntry entry1=new ZipEntry(inputfilename);
	            zout.putNextEntry(entry1);
	            
	            byte[] buffer = new byte[fis.available()];
	            fis.read(buffer);
	            
	            zout.write(buffer);
	            
	            zout.closeEntry();
	        }
	        catch(Exception ex){
	              
	            System.out.println(ex.getMessage());
	        } 

	}
	
	public void read(String outputfilename, String name)
	 {try(ZipInputStream zin = new ZipInputStream(new FileInputStream(outputfilename+".zip")))
     {
         ZipEntry entry;
         while((entry=zin.getNextEntry())!=null){
              
              
             
             FileOutputStream fout = new FileOutputStream(name);
             for (int c = zin.read(); c != -1; c = zin.read()) {
                 fout.write(c);
             }
             fout.flush();
             zin.closeEntry();
             fout.close();
         }
     }
     catch(Exception ex){
           
         System.out.println(ex.getMessage());
     } 
}

	
}
