package lab5;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
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


public class Work_with_xml_files implements Work_with_files
{

	private String inputfilename;
	private String outputfilename;
	
	public Work_with_xml_files(String inputfilename, String outputfilename)
	{
		this.inputfilename = inputfilename;
		this.outputfilename = outputfilename;
	}
	
	@Override
	public List_of_multicookers read() {
		List_of_multicookers mult = new List_of_multicookers();

	        try {
	            File file = new File(inputfilename);
	            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	            Document doc = dBuilder.parse(file);
	            doc.getDocumentElement().normalize();

	            NodeList fabricList = doc.getElementsByTagName("Multicooker");

	            for (int temp = 0; temp < fabricList.getLength(); temp++) {
	                Node fabricNode = fabricList.item(temp);
	                if (fabricNode.getNodeType() == Node.ELEMENT_NODE) {
	                    Element fabricElement = (Element) fabricNode;
	                    String name = fabricElement.getElementsByTagName("Name").item(0).getTextContent();
	                    String color = fabricElement.getElementsByTagName("color").item(0).getTextContent();
	                    String cost = fabricElement.getElementsByTagName("cost").item(0).getTextContent();
	                    String volume = fabricElement.getElementsByTagName("volume").item(0).getTextContent();
	                    mult.insert_object(new multicooker.Builder(name, Integer.parseInt(volume), Integer.parseInt(cost)).Color(color).build());
	                }
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }

	        return mult;
	}

	@Override
	public void write(List_of_multicookers multicookers) {

		 try {
	            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
	            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

	            // Root elements
	            Document doc = docBuilder.newDocument();
	            Element rootElement = doc.createElement("Multicookers");
	            doc.appendChild(rootElement);

	            for (multicooker cooker : multicookers.GetList()) {
	                // Fabric elements
	                Element cookerElement = doc.createElement("Multicooker");
	                rootElement.appendChild(cookerElement);

	                // Type element
	                Element name = doc.createElement("Name");
	                name.appendChild(doc.createTextNode(cooker.GetName()));
	                cookerElement.appendChild(name);
	                
	                Element color = doc.createElement("color");
	                color.appendChild(doc.createTextNode(cooker.GetColor()));
	                cookerElement.appendChild(color);

	                // Place element
	                Element cost = doc.createElement("cost");
	                cost.appendChild(doc.createTextNode(String.valueOf(cooker.GetCost())));
	                cookerElement.appendChild(cost);

	                // Amount element
	                Element volume = doc.createElement("volume");
	                volume.appendChild(doc.createTextNode(String.valueOf(cooker.GetVolume())));
	                cookerElement.appendChild(volume);
	            }

	            // Write the content into XML file
	            FileOutputStream outputStream = new FileOutputStream(outputfilename);
	            javax.xml.transform.TransformerFactory.newInstance().newTransformer().transform(new javax.xml.transform.dom.DOMSource(doc), new javax.xml.transform.stream.StreamResult(outputStream));
	        } catch (Exception e) {
	            e.printStackTrace();
	        }

		
		
	}

}
