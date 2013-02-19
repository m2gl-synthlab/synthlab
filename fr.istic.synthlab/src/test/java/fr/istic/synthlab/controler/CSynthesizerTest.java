package fr.istic.synthlab.controler;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fr.istic.synthlab.abstraction.module.out.ModuleOUT;
import fr.istic.synthlab.controller.synthesizer.CSynthesizer;
import fr.istic.synthlab.controller.synthesizer.ICSynthesizer;
import fr.istic.synthlab.factory.impl.AFactory;
import fr.istic.synthlab.factory.impl.CFactory;
import fr.istic.synthlab.factory.impl.PACFactory;
import fr.istic.synthlab.factory.impl.PFactory;

public class CSynthesizerTest {

	private ICSynthesizer synth;

	@Before
	public void setUp() throws Exception {
		PACFactory.setAFactory(AFactory.getInstance());
		PACFactory.setCFactory(CFactory.getInstance());
		PACFactory.setPFactory(PFactory.getInstance());
		synth = new CSynthesizer();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testAddIModule() {
		assertEquals(0,synth.getModules().size());
		synth.add(new ModuleOUT(synth));
		assertEquals(1,synth.getModules().size());
	}

	@Test
	public void testAddIWire() {
		fail("Not yet implemented");
	}

	@Test
	public void testStart() {
		fail("Not yet implemented");
	}

	@Test
	public void testStop() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetCurrentWire() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetPresentation() {
		assertNotNull(synth.getPresentation());
	}

	@Test
	public void testP2cStart() {
		fail("Not yet implemented");
	}

	@Test
	public void testP2cStop() {
		fail("Not yet implemented");
	}

	@Test
	public void testP2cAddModule() {
		fail("Not yet implemented");
	}

	@Test
	public void testP2cRemoveModule() {
		fail("Not yet implemented");
	}

	@Test
	public void testP2cDisconnectCurrentWire() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetCurrentWireColor() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetCurrentWireColor() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetPath() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetPath() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetFrame() {
		fail("Not yet implemented");
	}

}
