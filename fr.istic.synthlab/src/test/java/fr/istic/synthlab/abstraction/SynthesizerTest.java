package fr.istic.synthlab.abstraction;

import junit.framework.TestCase;
import fr.istic.synthlab.abstraction.module.IModule;
import fr.istic.synthlab.abstraction.synthesizer.ISynthesizer;
import fr.istic.synthlab.abstraction.synthesizer.Synthesizer;
import fr.istic.synthlab.abstraction.wire.IWire;
import fr.istic.synthlab.controller.module.audioscope.CModuleAudioScope;
import fr.istic.synthlab.controller.module.eg.CModuleEG;
import fr.istic.synthlab.controller.module.out.CModuleOUT;
import fr.istic.synthlab.controller.wire.CWire;
import fr.istic.synthlab.factory.impl.AFactory;
import fr.istic.synthlab.factory.impl.CFactory;
import fr.istic.synthlab.factory.impl.PACFactory;
import fr.istic.synthlab.factory.impl.PFactory;

public class SynthesizerTest extends TestCase {
	
	private ISynthesizer synth;
	private IModule module;

	
	public void setUp(){
		PACFactory.setFactory(AFactory.getInstance());
		PACFactory.setCFactory(CFactory.getInstance());
		PACFactory.setPFactory(PFactory.getInstance());
		synth=PACFactory.getAFactory().newSynthesizer();
		module=new CModuleOUT();;

		

	}
	
	public void testGetInstance() {
		assertEquals(synth,Synthesizer.getInstance());
	}



	public void testGetJSyn() {
		boolean jsyn=false;
		for(int i=0;i<synth.getJSyn().getClass().getInterfaces().length;i++)
			if(
		synth.getJSyn().getClass().getInterfaces()[i].equals(com.jsyn.Synthesizer.class)
		
		)
				jsyn=true;
	
	assertTrue(jsyn);
	
	}
	


	public void testAddIModule() {
		synth.add(module);
		assertEquals(1,synth.getModules().size());

		assertEquals(module,synth.getModules().get(0));
	}
	

	public void testRemoveIModule() {
		
		synth.remove(synth.getModules().get(0));
		assertEquals(0,synth.getModules().size());	}

	public void testAddIWire() {
		IWire w=new CWire();
		synth.add(w);
		assertEquals(1,((Synthesizer) synth).getWires().size());
		assertEquals(w,((Synthesizer) synth).getWires().get(0));

	}

	public void testIsRunning() {
		assertFalse(synth.isRunning());
	}

	public void testStart() {
		synth.start();
		synth.add(new CModuleOUT());
		synth.add(new CModuleEG());
		synth.add(new CModuleAudioScope());
		assertTrue(synth.isRunning());

	
	}

	
	public void testStop() {
		synth.start();
		synth.stop();
		assertFalse(synth.isRunning());
	}
	
	public void testStartModule() {
		synth.stop();
		
		synth.add(new CModuleAudioScope());
		synth.startModule(synth.getModules().get(0));
		assertFalse(synth.isRunning());
	}




	public void testStopModule() {
		synth.start();
		synth.add(new CModuleAudioScope());
		synth.stopModule(synth.getModules().get(0));
		assertTrue(synth.isRunning());
	}




	public void testSetGetCurrentWire() {
		IWire w=new CWire();
		synth.setCurrentWire(w);
		assertEquals(w, synth.getCurrentWire());
	}

	public void testRemoveIWire() {
		IWire w=new CWire();
		synth.add(w);
		synth.remove(((Synthesizer) synth).getWires().get(0));
		assertEquals(0,((Synthesizer) synth).getWires().size());

}
}
