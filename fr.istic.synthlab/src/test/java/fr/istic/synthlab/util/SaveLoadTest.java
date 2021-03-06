package fr.istic.synthlab.util;

import java.awt.Component;

import javax.swing.JPanel;

import junit.framework.TestCase;
import fr.istic.synthlab.ISynthApp;
import fr.istic.synthlab.ISynthFrame;
import fr.istic.synthlab.SynthApp;
import fr.istic.synthlab.SynthFrame;
import fr.istic.synthlab.abstraction.exception.BadConnectionException;
import fr.istic.synthlab.abstraction.exception.PortAlreadyInUseException;
import fr.istic.synthlab.command.app.DisplayCommand;
import fr.istic.synthlab.command.app.UndisplayCommand;
import fr.istic.synthlab.controller.module.eg.CModuleEG;
import fr.istic.synthlab.controller.module.eg.ICModuleEG;
import fr.istic.synthlab.controller.module.vca.CModuleVCA;
import fr.istic.synthlab.controller.module.vca.ICModuleVCA;
import fr.istic.synthlab.controller.module.vcf.CModuleVCF_LP;
import fr.istic.synthlab.controller.module.vcf.ICModuleVCF;
import fr.istic.synthlab.controller.module.vco.CModuleVCO;
import fr.istic.synthlab.controller.module.vco.ICModuleVCO;
import fr.istic.synthlab.controller.synthesizer.CSynthesizer;
import fr.istic.synthlab.controller.synthesizer.ICSynthesizer;
import fr.istic.synthlab.controller.wire.CWire;
import fr.istic.synthlab.controller.wire.ICWire;
import fr.istic.synthlab.factory.impl.CFactory;
import fr.istic.synthlab.factory.impl.PACFactory;
import fr.istic.synthlab.factory.impl.PFactory;

public class SaveLoadTest extends TestCase {

	private ISynthApp s;
	private ICSynthesizer synth;
	private ISynthFrame sf;
	private String chemin=System.getProperty("user.dir");


	public void setUp() {
		PACFactory.setCFactory(CFactory.getInstance());
		PACFactory.setPFactory(PFactory.getInstance());

		synth = new CSynthesizer();
		s = new SynthApp(sf);
		sf = new SynthFrame(s);
		((SynthApp) s).setFrame((SynthFrame) sf);
		s.getSynthesizer().setFrame(sf);
		s.setSynthesizer(s.getSynthesizer().getPath());
		
	}

	public void testSaveAndLoadSetTone() {

		s.setSynthesizer(s.getSynthesizer().getPath());
		s.setDisplaySynthCommand(new DisplayCommand(sf));
		s.setUndisplaySynthCommand(new UndisplayCommand(sf));
		ICModuleVCO moduleVCO = new CModuleVCO(synth);
		ICModuleVCA moduleVCA = new CModuleVCA(synth);
		ICModuleEG moduleEG = new CModuleEG(synth);

		s.getSynthesizer().add(moduleVCO);
		s.getSynthesizer().add(moduleVCA);
		s.getSynthesizer().add(moduleEG);
		

		moduleVCO.setTone(0.4);
		s.saveToXML(chemin+"/", "test.synthlab");

		s.setSynthesizer(new String[2]);
		s.loadFromXML(chemin+"/", "test.synthlab");
		assertEquals(3, s.getSynthesizer().getModules().size());
		assertEquals("CModuleVCO", s.getSynthesizer().getModules().get(0).getClass().getSimpleName());
		assertEquals("CModuleVCA", s.getSynthesizer().getModules().get(1).getClass().getSimpleName());
		assertEquals("CModuleEG", s.getSynthesizer().getModules().get(2).getClass().getSimpleName());

		assertEquals(168.0, Math.floor(((CModuleVCO) s.getSynthesizer().getModules().get(0)).getFrequency()));

	}

	public void testSaveAndLoadSetAttenuation() {
		s.setSynthesizer(s.getSynthesizer().getPath());
		s.setDisplaySynthCommand(new DisplayCommand(sf));
		s.setUndisplaySynthCommand(new UndisplayCommand(sf));
		ICModuleVCO moduleVCO = new CModuleVCO(synth);
		ICModuleVCA moduleVCA = new CModuleVCA(synth);
		ICModuleEG moduleEG = new CModuleEG(synth);

		s.getSynthesizer().add(moduleVCO);
		s.getSynthesizer().add(moduleVCA);
		s.getSynthesizer().add(moduleEG);

		moduleVCA.setAttenuation(7.0);

		s.saveToXML(chemin+"/", "test.synthlab");

		 s.setSynthesizer(new String[2]);
		s.loadFromXML(chemin+"/", "test.synthlab");
		assertEquals(3, s.getSynthesizer().getModules().size());
		assertEquals("CModuleVCO", s.getSynthesizer().getModules().get(0).getClass().getSimpleName());
		assertEquals("CModuleVCA", s.getSynthesizer().getModules().get(1).getClass().getSimpleName());
		assertEquals("CModuleEG", s.getSynthesizer().getModules().get(2).getClass().getSimpleName());

		assertEquals(7.0, ((CModuleVCA) s.getSynthesizer().getModules().get(1)).getAttenuation());

	}

	public void testSaveAndLoadWire() {

		s.setSynthesizer(s.getSynthesizer().getPath());
		s.setDisplaySynthCommand(new DisplayCommand(sf));
		s.setUndisplaySynthCommand(new UndisplayCommand(sf));
		ICModuleVCO moduleVCO = new CModuleVCO(synth);
		ICModuleVCA moduleVCA = new CModuleVCA(synth);
		ICModuleEG moduleEG = new CModuleEG(synth);

		s.getSynthesizer().add(moduleVCO);
		s.getSynthesizer().add(moduleVCA);
		s.getSynthesizer().add(moduleEG);
		ICWire wire = new CWire(synth);
		JPanel panelToAdd = new JPanel();
		panelToAdd.add((Component) wire.getPresentation());

		try {
			wire.connect(moduleVCO.getOutputSquare());

		} catch (PortAlreadyInUseException e) {
			e.printStackTrace();
		} catch (BadConnectionException e) {
			e.printStackTrace();
		}
		try {
			wire.connect(moduleVCA.getInput());
		} catch (PortAlreadyInUseException e) {
			e.printStackTrace();
		} catch (BadConnectionException e) {
			e.printStackTrace();
		}

		s.saveToXML(chemin+"/", "test.synthlab");

		((SynthApp)s).setSynthesizer(new String[2]);
		s.loadFromXML(chemin+"/", "test.synthlab");
		assertEquals(3, s.getSynthesizer().getModules().size());
		assertEquals("CModuleVCO", s.getSynthesizer().getModules().get(0).getClass().getSimpleName());
		assertEquals("CModuleVCA", s.getSynthesizer().getModules().get(1).getClass().getSimpleName());
		assertEquals("CModuleEG", s.getSynthesizer().getModules().get(2).getClass().getSimpleName());
		assertTrue(((CModuleVCA) s.getSynthesizer().getModules().get(1)).getInput().isInUse());
		assertTrue(((CModuleVCO) s.getSynthesizer().getModules().get(0)).getOutputSquare().isInUse());

	}

	public void testSaveAndLoadWireVCF() {

		s.setSynthesizer(s.getSynthesizer().getPath());
		s.setDisplaySynthCommand(new DisplayCommand(sf));
		s.setUndisplaySynthCommand(new UndisplayCommand(sf));
		ICModuleVCF moduleVCF = new CModuleVCF_LP(synth);
		ICModuleVCA moduleVCA = new CModuleVCA(synth);
		ICModuleEG moduleEG = new CModuleEG(synth);

		s.getSynthesizer().add(moduleVCF);
		s.getSynthesizer().add(moduleVCA);
		s.getSynthesizer().add(moduleEG);
		ICWire wire = new CWire(synth);
		JPanel panelToAdd = new JPanel();
		panelToAdd.add((Component) wire.getPresentation());

		try {
			wire.connect(moduleVCF.getOutput());

		} catch (PortAlreadyInUseException e) {
			e.printStackTrace();
		} catch (BadConnectionException e) {
			e.printStackTrace();
		}
		try {
			wire.connect(moduleVCA.getInput());
		} catch (PortAlreadyInUseException e) {
			e.printStackTrace();
		} catch (BadConnectionException e) {
			e.printStackTrace();
		}

		s.saveToXML(chemin+"/", "test.synthlab");

		s.setSynthesizer(new String[2]);
		s.loadFromXML(chemin+"/", "test.synthlab");
		assertEquals(3, s.getSynthesizer().getModules().size());
		assertEquals("CModuleVCF_LP", s.getSynthesizer().getModules().get(0).getClass().getSimpleName());
		assertEquals("CModuleVCA", s.getSynthesizer().getModules().get(1).getClass().getSimpleName());
		assertEquals("CModuleEG", s.getSynthesizer().getModules().get(2).getClass().getSimpleName());
		assertTrue(((CModuleVCA) s.getSynthesizer().getModules().get(1)).getInput().isInUse());
		assertTrue(((CModuleVCF_LP) s.getSynthesizer().getModules().get(0)).getOutput().isInUse());

	}
}