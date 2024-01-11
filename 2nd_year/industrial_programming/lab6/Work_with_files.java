package lab6666;

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



public class Work_with_files {

	private String inputfilename;
	private String name;
	private String format;
	
	private String outputfilename;
	private String outputname;
	private String outputformat;
	
	public Work_with_files()
	{
		
	}
	
	public void SetInputName(String str)
	{
		name = str;
	}
	
	public void SetOutputName(String str)
	{
		outputname = str;
	}
	
	public void SetOutputTxtFormat()
	{
		outputfilename = outputname+".txt";
		outputformat = ".txt";
		System.out.println(outputfilename);
	}
	
	public void SetOutputXMLFormat()
	{
		outputfilename = outputname+".xml";
		outputformat = ".xml";
		System.out.println(outputfilename);
	}
	
	public void SetTxtFormat()
	{
		inputfilename = name+".txt";
		format = ".txt";
		System.out.println(inputfilename);
	}
	
	public void SetXMLFormat()
	{
		format = ".xml";
		inputfilename = name+format;
		System.out.println(inputfilename);
	}
	
	public void Write(list_of_objects mult)
	{
		if(outputformat==".txt")
		{
			try {
				PrintStream fileOut = new PrintStream(outputfilename);
				for(int i = 0; i < mult.Size(); i++)
				{
					fileOut.println(mult.get(i).toString());
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(outputformat==".xml")
		{
			 try {

		            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

		            // root elements
		            Document doc = docBuilder.newDocument();
		            Element rootElement = doc.createElement("multicookers");
		            doc.appendChild(rootElement);
		            DOMSource source = new DOMSource(doc);
		            StreamResult result = new StreamResult(outputfilename);
		            
		            for(int i = 0; i < mult.Size(); i++)
		            {
		            	multicookers cooker = mult.get(i);
		            // staff elements
		            Element multicooker = doc.createElement("multicooker");
		            rootElement.appendChild(multicooker);

		            // set attribute to staff element
		            Element name = doc.createElement("name");
		            name.appendChild(doc.createTextNode(cooker.name));
		            multicooker.appendChild(name);

		            // shorten way
		            // staff.setAttribute("id", "1");

		         // nickname elements
		            Element volume = doc.createElement("volume");
		            volume.appendChild(doc.createTextNode(Integer.toString(cooker.volume)));
		            multicooker.appendChild(volume);
		            // firstname elements
		            Element color = doc.createElement("color");
		            color.appendChild(doc.createTextNode(cooker.color));
		            multicooker.appendChild(color);

		            // lastname elements
		            Element cost = doc.createElement("cost");
		            cost.appendChild(doc.createTextNode(Integer.toString(cooker.cost)));
		            multicooker.appendChild(cost);

		 

		            // write the content into xml file
		            TransformerFactory transformerFactory = TransformerFactory.newInstance();
		            Transformer transformer = null;
		            try {
		                transformer = transformerFactory.newTransformer();
		            } catch (TransformerConfigurationException e) {
		                e.printStackTrace();
		            }

		            // Output to console for testing
		            // StreamResult result = new StreamResult(System.out);

		            transformer.transform(source, result);
		            }


		        } catch (ParserConfigurationException pce) {
		            pce.printStackTrace();
		        } catch (TransformerException tfe) {
		            tfe.printStackTrace();
		        }
		}
	}
	
	public ArrayList<multicookers> Read()
	{
		ArrayList<multicookers> mult = new ArrayList<multicookers>();
		
		if(format == ".txt")
		{
			try {
				BufferedReader reader = new BufferedReader(new FileReader(inputfilename));
				String str = " ";
				
				while(str!=null)
				{
					
						str = reader.readLine();
						if(str == null)
						{
							break;
						}
						
						//System.out.println(str);
						String[]parts = str.split(" ");
						multicookers cooker = new multicookers(parts[2], Integer.parseInt( parts[3]), parts[0], Integer.parseInt( parts[1]));
				        mult.add(cooker);
				}
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			
		}
		
		if(format==".xml")
		{
			 try {
		            // Создается построитель документа
		            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		            // Создается дерево DOM документа из файла
		            Document document = documentBuilder.parse(inputfilename);
		 
		            // Получаем корневой элемент
		            Node root = document.getDocumentElement();
		            
		            System.out.println("List of books:");
		            System.out.println();
		            // Просматриваем все подэлементы корневого - т.е. книги
		            NodeList books = root.getChildNodes();
		            
		            for (int i = 0; i < books.getLength(); i++) {
		                Node book = books.item(i);
		                multicookers cooker = new multicookers();
		                // Если нода не текст, то это книга - заходим внутрь
		                if (book.getNodeType() != Node.TEXT_NODE) {
		                    NodeList bookProps = book.getChildNodes();
		                    String str = " ";
		                   
		                    for(int j = 0; j < bookProps.getLength(); j++) {
		                        Node bookProp = bookProps.item(j);
		                        
		                        if (bookProp.getNodeType() != Node.TEXT_NODE) {
		                        
		                        	if(bookProp.getNodeName()=="name")
		                        	{
		                        		cooker.SetColor(bookProp.getChildNodes().item(0).getTextContent());
		                        	}
		                        	
		                        	if(bookProp.getNodeName()=="volume")
		                        	{
		                        		cooker.SetCost(Integer.parseInt(bookProp.getChildNodes().item(0).getTextContent()));
		                        	}
		                           
		                        	if(bookProp.getNodeName()=="cost")
		                        	{
		                        		cooker.SetVolume(Integer.parseInt(bookProp.getChildNodes().item(0).getTextContent()));
		                        	}
		                        	
		                        	if(bookProp.getNodeName()=="color")
		                        	{
		                        		cooker.SetName(bookProp.getChildNodes().item(0).getTextContent());
		                        	}
		                        	
		                        }
		                        
		                    }mult.add(cooker);
		                   
							
		                }
		            }
		 
		        } catch (ParserConfigurationException ex) {
		            ex.printStackTrace(System.out);
		        } catch (SAXException ex) {
		            ex.printStackTrace(System.out);
		        } catch (IOException ex) {
		            ex.printStackTrace(System.out);
		        }
		}
		return mult;
	}
}
