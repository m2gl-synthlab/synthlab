package fr.istic.synthlab.controler;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fr.istic.synthlab.controller.module.ICModule;
import fr.istic.synthlab.controller.module.out.CModuleOUT;
import fr.istic.synthlab.controller.synthesizer.CSynthesizer;
import fr.istic.synthlab.controller.synthesizer.ICSynthesizer;
import fr.istic.synthlab.factory.impl.AFactory;
import fr.istic.synthlab.factory.impl.CFactory;
import fr.istic.synthlab.factory.impl.PACFactory;
import fr.istic.synthlab.factory.impl.PFactory;

//TODO A finir !!
public class CSynthesizerTest {

	private ICSynthesizer synth;
	private ICModule iTest;

	@Before
	public void setUp() throws Exception {
		PACFactory.setAFactory(AFactory.getInstance());
		PACFactory.setCFactory(CFactory.getInstance());
		PACFactory.setPFactory(PFactory.getInstance());
		synth = new CSynthesizer();
		iTest = new CModuleOUT(synth);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testAddIModule() {
		assertFalse(synth.getModules().contains(iTest));
		synth.add(iTest);
		assertTrue(synth.getModules().contains(iTest));
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
		assertFalse(synth.getModules().contains(iTest));
		synth.p2cAddModule(iTest);
		assertTrue(synth.getModules().contains(iTest));
	}

	@Test
	public void testP2cRemoveModule() {
		synth.add(iTest);
		assertTrue(synth.getModules().contains(iTest));
		synth.p2cRemoveModule(iTest);
		assertFalse(synth.getModules().contains(iTest));
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
