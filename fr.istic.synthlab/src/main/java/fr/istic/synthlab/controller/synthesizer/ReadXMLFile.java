package fr.istic.synthlab.controller.synthesizer;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;

public class ReadXMLFile {

	private Document doc;
	private Element rootElement;

	public ReadXMLFile() {
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

	public static void main(String argv[]) {

		try {

			File fXmlFile = new File("savedInstance.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);

			// optional, but recommended
			// read this -
			// http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
			doc.getDocumentElement().normalize();
			NodeList nList = doc.getElementsByTagName("module");

			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node nNode = nList.item(temp);
				System.out.println("\nCurrent Element :" + nNode.getNodeName());

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					System.out.println("name : "
							+ eElement.getAttribute("name"));
					System.out.println("x : " + eElement.getAttribute("x"));
					System.out.println("y : " + eElement.getAttribute("y"));
					for (int i = 0; i < eElement.getElementsByTagName("port")
							.getLength(); i++) {
						System.out.println("name : "
								+ ((Element) eElement.getElementsByTagName(
										"port").item(i)).getAttribute("name"));
						System.out.println("module port : "
								+ ((Element) eElement.getElementsByTagName(
										"port").item(i))
										.getAttribute("connectedToModulePort"));
						System.out.println("module name : "
								+ ((Element) eElement.getElementsByTagName(
										"port").item(i))
										.getAttribute("connectedToModuleName"));
					}
					for (int i = 0; i < eElement.getElementsByTagName("parameter")
							.getLength(); i++) {
						System.out.println("key : "
								+ ((Element) eElement.getElementsByTagName(
										"parameter").item(i)).getAttribute("key"));
						System.out.println("value : "
								+ ((Element) eElement.getElementsByTagName(
										"parameter").item(i))
										.getAttribute("value"));
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}