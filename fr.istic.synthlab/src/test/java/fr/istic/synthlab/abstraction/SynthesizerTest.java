package fr.istic.synthlab.abstraction;

import java.lang.reflect.Field;
import java.util.List;

import junit.framework.TestCase;
import fr.istic.synthlab.abstraction.module.IModule;
import fr.istic.synthlab.abstraction.module.out.ModuleOUT;
import fr.istic.synthlab.abstraction.wire.IWire;
import fr.istic.synthlab.abstraction.wire.Wire;
import fr.istic.synthlab.controller.module.audioscope.CModuleAudioScope;
import fr.istic.synthlab.controller.module.eg.CModuleEG;
import fr.istic.synthlab.controller.module.out.CModuleOUT;
import fr.istic.synthlab.controller.synthesizer.CSynthesizer;
import fr.istic.synthlab.controller.synthesizer.ICSynthesizer;
import fr.istic.synthlab.controller.wire.CWire;
import fr.istic.synthlab.controller.wire.ICWire;
import fr.istic.synthlab.factory.impl.AFactory;
import fr.istic.synthlab.factory.impl.CFactory;
import fr.istic.synthlab.factory.impl.PACFactory;
import fr.istic.synthlab.factory.impl.PFactory;

public class SynthesizerTest extends TestCase {

	private ICSynthesizer synth;
	private IModule module;

	public void setUp() {
		PACFactory.setFactory(AFactory.getInstance());

		PACFactory.setCFactory(CFactory.getInstance());
		PACFactory.setPFactory(PFactory.getInstance());
		synth=new CSynthesizer();
		module=new CModuleOUT(synth);

		


	}

	public void testGetInstance() {
		assertEquals(synth,synth);
	}

	public void testGetJSyn() {
		boolean jsyn = false;
		for (int i = 0; i < synth.getJSyn().getClass().getInterfaces().length; i++)
			if (synth.getJSyn().getClass().getInterfaces()[i].equals(com.jsyn.Synthesizer.class)

			)
				jsyn = true;

		assertTrue(jsyn);

	}

	public void testAddIModule() {
		synth.add(module);
		assertEquals(1, synth.getModules().size());

		assertEquals(module, synth.getModules().get(0));
	}

	public void testRemoveIModule() {
		synth.add(new CModuleOUT(synth));
		synth.remove(synth.getModules().get(0));
		assertEquals(0, synth.getModules().size());
	}

	@SuppressWarnings("unchecked")
	public void testAddIWire() {
		ICWire w=new CWire(synth);
		synth.add(w);

		Field f = null;
		try {
			for (int i = 0; i < synth.getClass().getDeclaredFields().length; i++)
				System.out.println(synth.getClass().getDeclaredFields()[i]);
			;
			f = synth.getClass().getDeclaredField("wires");
			f.setAccessible(true);
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<Wire> p = null;
		try {
			p = (List<Wire>) f.get(synth);
			System.out.println(p);
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertEquals(1, p.size());
		assertEquals(w, p.get(0));

	}

	public void testIsRunning() {
		assertFalse(synth.isRunning());
	}

	public void testStart() {
		synth.start();
		synth.add(new CModuleOUT(synth));
		synth.add(new CModuleEG(synth));
		synth.add(new CModuleAudioScope(synth));
		assertTrue(synth.isRunning());

	}

	public void testStop() {
		synth.start();
		synth.stop();
		assertFalse(synth.isRunning());
	}

	public void testStartModule() {
		synth.stop();
		
		synth.add(new CModuleAudioScope(synth));
		synth.startModule(synth.getModules().get(0));
		assertFalse(synth.isRunning());
	}

	public void testStopModule() {
		synth.start();
		synth.add(new CModuleAudioScope(synth));
		synth.stopModule(synth.getModules().get(0));
		assertTrue(synth.isRunning());
	}

	public void testSetGetCurrentWire() {
		ICWire w=new CWire(synth);
		synth.setCurrentWire(w);
		assertEquals(w, synth.getCurrentWire());
	}

	public void testRemoveIWire() {
		IWire w=new Wire(synth);
		synth.add(w);
		Field f = null;
		try {
			f = synth.getClass().getDeclaredField("wires");
			f.setAccessible(true);
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<Wire> p = null;
		try {
			p = (List<Wire>) f.get(synth);
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		synth.remove(p.get(0));
		assertEquals(0, p.size());

	}
}
