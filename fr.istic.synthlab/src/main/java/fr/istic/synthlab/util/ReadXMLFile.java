package fr.istic.synthlab.util;

import java.awt.Point;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import fr.istic.synthlab.abstraction.module.IModule;
import fr.istic.synthlab.controller.module.ICModule;
import fr.istic.synthlab.controller.module.audioscope.CModuleAudioScope;
import fr.istic.synthlab.controller.module.eg.CModuleEG;
import fr.istic.synthlab.controller.module.out.CModuleOUT;
import fr.istic.synthlab.controller.module.rep.CModuleREP;
import fr.istic.synthlab.controller.module.vca.CModuleVCA;
import fr.istic.synthlab.controller.module.vcf.CModuleVCFA_LP;
import fr.istic.synthlab.controller.module.vco.CModuleVCO;
import fr.istic.synthlab.controller.synthesizer.CSynthesizer;

public class ReadXMLFile {

	private Document doc;

	public ReadXMLFile() {
		try {

			File fXmlFile = new File("savedInstance.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			doc = dBuilder.parse(fXmlFile);

			// optional, but recommended
			// read this -
			// http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
			doc.getDocumentElement().normalize();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<IModule> getModules() {
		List<IModule> modules = new ArrayList<IModule>();

		try {
			NodeList nList = doc.getElementsByTagName("module");

			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node nNode = nList.item(temp);

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					ICModule module = null;
					
					if(eElement.getAttribute("name").startsWith("VCO")){
						module = new CModuleVCO(CSynthesizer.getInstance());
					} else if (eElement.getAttribute("name").startsWith("VCA")){
						module = new CModuleVCA(CSynthesizer.getInstance());
					} else if (eElement.getAttribute("name").startsWith("OUT")){
						module = new CModuleOUT(CSynthesizer.getInstance());
					} else if (eElement.getAttribute("name").startsWith("EG")){
						module = new CModuleEG(CSynthesizer.getInstance());
					} else if (eElement.getAttribute("name").startsWith("REP")){
						module = new CModuleREP(CSynthesizer.getInstance());
					} else if (eElement.getAttribute("name").startsWith("VCFA LP24")){
						module = new CModuleVCFA_LP(CSynthesizer.getInstance());
					} else if (eElement.getAttribute("name").startsWith("AudioScope")){
						module = new CModuleAudioScope(CSynthesizer.getInstance());
					} else {
						throw new Exception("Module not recognized in xml file");
					}
					
					int x = (int) Double.parseDouble(eElement.getAttribute("x"));
					int y = (int) Double.parseDouble(eElement.getAttribute("y"));
					Point p = new Point(x,y);
					module.getPresentation().setPosition(p);
					
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
					for (int i = 0; i < eElement.getElementsByTagName(
							"parameter").getLength(); i++) {
						System.out.println("key : "
								+ ((Element) eElement.getElementsByTagName(
										"parameter").item(i))
										.getAttribute("key"));
						System.out.println("value : "
								+ ((Element) eElement.getElementsByTagName(
										"parameter").item(i))
										.getAttribute("value"));
					}
					modules.add(module);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return modules;
	}

}