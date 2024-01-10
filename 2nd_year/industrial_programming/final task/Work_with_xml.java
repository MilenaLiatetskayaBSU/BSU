package final_task;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;




public class Work_with_xml implements FileBuilder{

	private ArrayList<Double>result;
	private calculator1 calculations;
	
	public Work_with_xml()
	{
		result = new ArrayList<>();
		calculations = new calculator1();
	}
	
	public void addArray(ArrayList<Double>list)
	{
		for(int i = 0; i < list.size(); i++)
		{
			result.add(list.get(i));
		}
	}
	
	public void Write_without_API(String outputname) throws FileNotFoundException
	{
		PrintStream fileOut = new PrintStream(outputname);
		fileOut.println("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>");
		fileOut.println("<Results>");
		for(int i = 0; i < result.size(); i++)
		{
			fileOut.println("<answers>"+result.get(i)+"</answers>");
		}
		
		fileOut.println("</Results>");
	}
	
	public ArrayList<Double>Read_without_API(String inputname)
	{
		 ArrayList<Double>result2=new ArrayList<>();
		 try {
				BufferedReader reader = new BufferedReader(new FileReader(inputname));
				String str="";
				while(str!=null)
				{
	     			str = reader.readLine();
	     			if(str == null)
	     			{
	     				break;
	     			}
	     			String task="";
	     			if(str.contains("<task>"))
	     			{
	     				for(int i = 6; i < str.length(); i++)
	     				{
	     					if(str.charAt(i)=='<')
	     					{
	     						break;
	     					}
	     					
	     					else
	     					{
	     						task+=String.valueOf(str.charAt(i));
	     					}
	     				}
	     				result2.add(calculations.c(task));
	     			}
				
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 return result2;
	}
	
	@Override
	public ArrayList<Double> Read(String inputname) {
		 ArrayList<Double>result2=new ArrayList<>();
		 try {
	   
	            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
	        
	            Document document = documentBuilder.parse(inputname);
	 
	            Node root = document.getDocumentElement();
	            
	            NodeList books = root.getChildNodes();
	            
	            for (int i = 0; i < books.getLength(); i++) {
	                Node book = books.item(i);

	                if (book.getNodeType() != Node.TEXT_NODE) {
	                    NodeList bookProps = book.getChildNodes();
	                    String str = "";
	                   
	                    for(int j = 0; j < bookProps.getLength(); j++) {
	                        Node bookProp = bookProps.item(j);
	                        
	                        if (bookProp.getNodeType() != Node.TEXT_NODE) {
	                        
	                        	if(bookProp.getNodeName()=="task")
	                        	{
	                        		result2.add(calculations.c(bookProp.getChildNodes().item(0).getTextContent()));
	                        		
	                        	}
	                        	
	                        	
	                        }
	                        
	                    }
	                   
						
	                }
	            }
	 
	        } catch (ParserConfigurationException ex) {
	            ex.printStackTrace(System.out);
	        } catch (SAXException ex) {
	            ex.printStackTrace(System.out);
	        } catch (IOException ex) {
	            ex.printStackTrace(System.out);
	        }
	
		return result2;
		
	}

	@Override
	public void Write(String outputname) {
		 try {

	            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
	            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

	            
	            
	            Document doc = docBuilder.newDocument();
	            Element rootElement = doc.createElement("Results");
	            doc.appendChild(rootElement);
	            DOMSource source = new DOMSource(doc);
	            StreamResult resultt = new StreamResult(outputname);
	            
	            for(int i = 0; i < result.size(); i++)
	            {
	            	
	            	
	            
	            Element res = doc.createElement("answers");
	            rootElement.appendChild(res);

	            res.appendChild(doc.createTextNode(Double.toString(result.get(i))));
	            

	           
	            TransformerFactory transformerFactory = TransformerFactory.newInstance();
	            Transformer transformer = null;
	            try {
	                transformer = transformerFactory.newTransformer();
	           } catch (TransformerConfigurationException e) {
	                e.printStackTrace();
	            }


	            transformer.transform(source, resultt);
	            }


	        } catch (ParserConfigurationException pce) {
	            pce.printStackTrace();
	        } catch (TransformerException tfe) {
	            tfe.printStackTrace();
	        }
		
	}

}
