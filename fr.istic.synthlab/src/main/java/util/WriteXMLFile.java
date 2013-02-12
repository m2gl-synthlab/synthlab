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
			StreamResult result = new StreamResult(new File("savedInstance.xml"));

			// Output to console for testing
			StreamResult res = new StreamResult(System.out);

			transformer.transform(source, result);
			transformer.transform(source, res);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void addModules(List<IModule> modules) {
		for(IModule module : modules){
			
			Element moduleName = doc.createElement("module");
			rootElement.appendChild(moduleName);
			
			Point p = ((ICModule)module).getPresentation().getPosition();
			System.out.println(p);
			
			// Nom module
			Attr attrName = doc.createAttribute("name");
			attrName.setValue(module.getName());
			moduleName.setAttributeNode(attrName);
			
			// Position x
			Attr attrX = doc.createAttribute("x");
			attrX.setValue(Double.toString(p.getX()));
			moduleName.setAttributeNode(attrX);
			
			// Position y
			Attr attrY = doc.createAttribute("y");
			attrY.setValue(Double.toString(p.getY()));
			moduleName.setAttributeNode(attrY);
			
			// Ajout des ports utilises du module
			for(IWire wire : module.getWires()){
				if(module.havePort(wire.getInput())){
					// set le port
					Element port = doc.createElement("port");
					moduleName.appendChild(port);
					
					// Nom port
					Attr attrPortName = doc.createAttribute("name");
					attrPortName.setValue(wire.getInput().getName());
					port.setAttributeNode(attrPortName);
					
					// Type port
					Attr attrPortType = doc.createAttribute("type");
					attrPortType.setValue("input");
					port.setAttributeNode(attrPortType);
					
					// Connected to Module name
					Attr attrPortConnectedToModuleName = doc.createAttribute("connectedToModuleName");
					attrPortConnectedToModuleName.setValue(wire.getOutput().getModule().getName());
					port.setAttributeNode(attrPortConnectedToModuleName);
					
					// Connected to Module port
					Attr attrPortConnectedToModulePort = doc.createAttribute("connectedToModulePort");
					attrPortConnectedToModulePort.setValue(wire.getOutput().getName());
					port.setAttributeNode(attrPortConnectedToModulePort);
					
				}
			}
		}
		saveToXML();
	}
	
// STRUCTURE DU XML BATI :
//	<modules>
//		<module name="OUT" x="10" y="10">
//			<port name="out" type="output" connectedToModuleName="" connectedToModulePort=""/>
//			</module>
//	</modules>
}