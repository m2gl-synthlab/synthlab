package fr.istic.synthlab.util;

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
import fr.istic.synthlab.controller.wire.ICWire;

/**
 * Class that is used to save a list of modules into an xml file
 */
public class WriteXMLFile {

	private Document doc;
	private Element rootElement;
	private File directory;

	public WriteXMLFile(File directory) {
		this.directory = directory;
		try {
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

			// root elements
			doc = docBuilder.newDocument();
			rootElement = doc.createElement("modules");
			doc.appendChild(rootElement);
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Write the current document content into an xml file
	 */
	public void saveToXML() {
		try {
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();

			DOMSource source = new DOMSource(doc);

			StreamResult result = new StreamResult(directory);

			// Output to console for testing
			StreamResult res = new StreamResult(System.out);

			transformer.transform(source, result);
			transformer.transform(source, res);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the xml containing the modules description
	 * 
	 * @param modules
	 */
	public void saveModules(List<IModule> modules) {
		for (IModule module : modules) {
			Element moduleName = doc.createElement("module");
			rootElement.appendChild(moduleName);

			Point p = ((ICModule) module).getPresentation().getPosition();
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
			for (IWire wire : module.getWires()) {

				if (module.containsPort(wire.getOutput())) {

					// set le port
					Element port = doc.createElement("wire");
					moduleName.appendChild(port);

					// Nom port
					Attr attrPortName = doc.createAttribute("outputPort");
					attrPortName.setValue(wire.getOutput().getName());
					port.setAttributeNode(attrPortName);

					// Connected to Module name
					Attr attrPortConnectedToModuleName = doc.createAttribute("inputPortModuleName");
					attrPortConnectedToModuleName.setValue(wire.getInput().getModule().getName());
					port.setAttributeNode(attrPortConnectedToModuleName);

					// Connected to Module port
					Attr attrPortConnectedToModulePort = doc.createAttribute("inputPort");
					attrPortConnectedToModulePort.setValue(wire.getInput().getName());
					port.setAttributeNode(attrPortConnectedToModulePort);

					// Cable Color R
					Attr attrPortColorR = doc.createAttribute("colorR");

					System.out.println(((ICWire) wire).getPresentation().getColor());

					attrPortColorR.setValue(((ICWire) wire).getPresentation().getColor().getRed() + "");
					port.setAttributeNode(attrPortColorR);

					// Cable Color G
					Attr attrPortColorG = doc.createAttribute("colorG");
					attrPortColorG.setValue(((ICWire) wire).getPresentation().getColor().getGreen() + "");
					port.setAttributeNode(attrPortColorG);

					// Cable Color B
					Attr attrPortColorB = doc.createAttribute("colorB");
					attrPortColorB.setValue(((ICWire) wire).getPresentation().getColor().getBlue() + "");
					port.setAttributeNode(attrPortColorB);

				}
			}

			for (String key : module.getParameters().keySet()) {
				// set le parametre
				Element param = doc.createElement("parameter");
				moduleName.appendChild(param);

				// Nom parametre
				Attr attrParamName = doc.createAttribute("key");
				attrParamName.setValue(key);
				param.setAttributeNode(attrParamName);

				// Valeur parametre
				Attr attrParamValue = doc.createAttribute("value");
				attrParamValue.setValue(module.getParameters().get(key).toString());
				param.setAttributeNode(attrParamValue);
			}
		}
		saveToXML();
	}
}