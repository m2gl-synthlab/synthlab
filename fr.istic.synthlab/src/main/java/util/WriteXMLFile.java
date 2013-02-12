package util;

import java.awt.Point;
import java.io.File;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import fr.istic.synthlab.abstraction.module.IModule;
import fr.istic.synthlab.abstraction.wire.IWire;
import fr.istic.synthlab.controller.module.ICModule;

public class WriteXMLFile {

	private Document doc;
	private Element rootElement;

	public WriteXMLFile() {
		try {
			DocumentBuilderFactory docFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder docBuilder;
			docBuilder = docFactory.newDocumentBuilder();

			// root elements
			doc = docBuilder.newDocument();
			rootElement = doc.createElement("modules");
			doc.appendChild(rootElement);
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
	}

	// write the content into xml file
	public void saveToXML() {
		try {
			TransformerFactory transformerFactory = TransformerFactory
					.newInstance();
			Transformer transformer;

			transformer = transformerFactory.newTransformer();

			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File("file2.xml"));

			// Output to console for testing
			 StreamResult res = new StreamResult(System.out);

			transformer.transform(source, res);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void addModules(List<IModule> modules) {
		for(IModule module : modules){
			
			System.out.println(module.getName());
			Element moduleName = doc.createElement(module.getName());
			rootElement.appendChild(moduleName);
			
			Point p = ((ICModule)module).getPresentation().getPosition();
			System.out.println(p);
			
			Attr attrX = doc.createAttribute("x");
			attrX.setValue(Double.toString(p.getX()));
			moduleName.setAttributeNode(attrX);
			
			Attr attrY = doc.createAttribute("y");
			attrY.setValue(Double.toString(p.getY()));
			moduleName.setAttributeNode(attrY);
			
			for(IWire wire : module.getWires()){
				System.out.println(wire.getInput());
				System.out.println(wire.getOutput());
			}
		}
		saveToXML();
	}
	
//	public void save() {
//		try {
//			// firstname elements
//			Element firstname = doc.createElement("firstname");
//			firstname.appendChild(doc.createTextNode("yong"));
//			staff.appendChild(firstname);
//
//			// lastname elements
//			Element lastname = doc.createElement("lastname");
//			lastname.appendChild(doc.createTextNode("mook kim"));
//			staff.appendChild(lastname);
//
//			// nickname elements
//			Element nickname = doc.createElement("nickname");
//			nickname.appendChild(doc.createTextNode("mkyong"));
//			staff.appendChild(nickname);
//
//			// salary elements
//			Element salary = doc.createElement("salary");
//			salary.appendChild(doc.createTextNode("100000"));
//			staff.appendChild(salary);
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
}