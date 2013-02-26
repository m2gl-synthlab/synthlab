package fr.istic.synthlab.abstraction;

import java.lang.reflect.Field;
import java.util.List;

import junit.framework.TestCase;
import fr.istic.synthlab.abstraction.module.IModule;
import fr.istic.synthlab.abstraction.module.audioscope.ModuleAudioScope;
import fr.istic.synthlab.abstraction.module.eg.ModuleEG;
import fr.istic.synthlab.abstraction.module.out.ModuleOUT;
import fr.istic.synthlab.abstraction.synthesizer.ISynthesizer;
import fr.istic.synthlab.abstraction.synthesizer.Synthesizer;
import fr.istic.synthlab.abstraction.wire.IWire;
import fr.istic.synthlab.abstraction.wire.Wire;
import fr.istic.synthlab.factory.impl.AFactory;
import fr.istic.synthlab.factory.impl.PACFactory;

public class SynthesizerTest extends TestCase {

	private ISynthesizer synth;
	private IModule module;

	public void setUp() {
		PACFactory.setFactory(AFactory.getInstance());
		PACFactory.setCFactory(null);


		synth = new Synthesizer();
		module = new ModuleOUT(synth);

	}

	public void testGetInstance() {
		assertEquals(synth, synth);
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
		synth.add(new ModuleOUT(synth));
		synth.remove(synth.getModules().get(0));
		assertEquals(0, synth.getModules().size());
	}

	@SuppressWarnings("unchecked")
	public void testAddIWire() {
		IWire w = new Wire(synth);
		synth.add(w);

		Field f = null;
		try {
			for (int i = 0; i < synth.getClass().getDeclaredFields().length; i++)
				System.out.println(synth.getClass().getDeclaredFields()[i]);
			;
			f = synth.getClass().getDeclaredField("wires");
			f.setAccessible(true);
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		List<Wire> p = null;
		try {
			p = (List<Wire>) f.get(synth);
			System.out.println(p);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
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
		synth.add(new ModuleOUT(synth));
		synth.add(new ModuleEG(synth));
		synth.add(new ModuleAudioScope(synth));
		assertTrue(synth.isRunning());

	}

	public void testStop() {
		synth.start();
		synth.stop();
		assertFalse(synth.isRunning());
	}

	public void testStartModule() {
		synth.stop();

		synth.add(new ModuleAudioScope(synth));
		synth.startModule(synth.getModules().get(0));
		assertFalse(synth.isRunning());
	}

	public void testStopModule() {
		synth.start();
		synth.add(new ModuleAudioScope(synth));
		synth.stopModule(synth.getModules().get(0));
		assertTrue(synth.isRunning());
	}

	public void testSetGetCurrentWire() {
		IWire w = new Wire(synth);
		synth.setCurrentWire(w);
		assertEquals(w, synth.getCurrentWire());
	}

	@SuppressWarnings("unchecked")
	public void testRemoveIWire() {
		IWire w = new Wire(synth);
		synth.add(w);
		Field f = null;
		try {
			f = synth.getClass().getDeclaredField("wires");
			f.setAccessible(true);
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		List<Wire> p = null;
		try {
			p = (List<Wire>) f.get(synth);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}

		synth.remove(p.get(0));
		assertEquals(0, p.size());

	}
}
