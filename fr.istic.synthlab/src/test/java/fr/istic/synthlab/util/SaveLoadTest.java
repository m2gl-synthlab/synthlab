package fr.istic.synthlab.util;
import junit.framework.TestCase;
import fr.istic.synthlab.ISynthApp;
import fr.istic.synthlab.ISynthFrame;
import fr.istic.synthlab.SynthApp;
import fr.istic.synthlab.SynthFrame;
import fr.istic.synthlab.command.app.DisplayCommand;
import fr.istic.synthlab.command.app.UndisplayCommand;
import fr.istic.synthlab.controller.module.eg.CModuleEG;
import fr.istic.synthlab.controller.module.eg.ICModuleEG;
import fr.istic.synthlab.controller.module.vca.CModuleVCA;
import fr.istic.synthlab.controller.module.vca.ICModuleVCA;
import fr.istic.synthlab.controller.module.vco.CModuleVCO;
import fr.istic.synthlab.controller.module.vco.ICModuleVCO;
import fr.istic.synthlab.controller.synthesizer.CSynthesizer;
import fr.istic.synthlab.controller.synthesizer.ICSynthesizer;
import fr.istic.synthlab.factory.impl.CFactory;
import fr.istic.synthlab.factory.impl.PACFactory;
import fr.istic.synthlab.factory.impl.PFactory;


public class SaveLoadTest extends TestCase {
	
	ISynthApp s;
	ICSynthesizer synth;
	ISynthFrame sf;
	public void setUp(){
		
		PACFactory.setCFactory(CFactory.getInstance());
		PACFactory.setPFactory(PFactory.getInstance());

		s=new SynthApp();
		synth=new CSynthesizer();
		s.setSynthesizer(synth);
		sf=new SynthFrame();
		

	}
	
	public void testSaveAndLoad(){
		
		s.setSynthesizer(synth);
		s.setDisplaySynthCommand(new DisplayCommand(s, sf));
		s.setUndisplaySynthCommand(new UndisplayCommand(sf));
		ICModuleVCO moduleVCO=new CModuleVCO(synth);
		ICModuleVCA moduleVCA=new CModuleVCA(synth);
		ICModuleEG moduleEG=new CModuleEG(synth);

		synth.add(moduleVCO);
		synth.add(moduleVCA);
		synth.add(moduleEG);
		
		moduleVCO.setFrequency(50.0);
		
		

		s.saveToXML();
		
		s.setSynthesizer(new CSynthesizer());
		s.loadFromXML();
		assertEquals(3,s.getSynthesizer().getModules().size());
		assertEquals("CModuleVCO",s.getSynthesizer().getModules().get(0).getClass().getSimpleName());
		assertEquals("CModuleVCA",s.getSynthesizer().getModules().get(1).getClass().getSimpleName());
		assertEquals("CModuleEG",s.getSynthesizer().getModules().get(2).getClass().getSimpleName());
		assertEquals(50.0,((CModuleVCO) s.getSynthesizer().getModules().get(0)).getFrequency());

		

		
	}

}
