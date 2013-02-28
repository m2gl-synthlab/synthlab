package fr.istic.synthlab.controler;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fr.istic.synthlab.controller.synthesizer.CSynthesizer;
import fr.istic.synthlab.controller.synthesizer.ICSynthesizer;
import fr.istic.synthlab.controller.wire.CWire;
import fr.istic.synthlab.controller.wire.ICWire;
import fr.istic.synthlab.factory.impl.AFactory;
import fr.istic.synthlab.factory.impl.CFactory;
import fr.istic.synthlab.factory.impl.PACFactory;
import fr.istic.synthlab.factory.impl.PFactory;

//TODO A finir !!
public class CWireTest {

	private ICSynthesizer synth;
	private ICWire iTest;

	@Before
	public void setUp() throws Exception {
		PACFactory.setAFactory(AFactory.getInstance());
		PACFactory.setCFactory(CFactory.getInstance());
		PACFactory.setPFactory(PFactory.getInstance());
		synth = new CSynthesizer();
		iTest = new CWire(synth);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testConnectIInputPort() {
		fail("Not yet implemented");
	}

	@Test
	public void testConnectIOutputPort() {
		fail("Not yet implemented");
	}

	@Test
	public void testDisconnect() {
		iTest.disconnect();
		assertFalse(iTest.isConnected());
	}

	@Test
	public void testGetPresentation() {
		assertNotNull(iTest.getPresentation());
	}

	@Test
	public void testGetCurrentWireColor() {
		assertNotNull(iTest.getCurrentWireColor());
	}

	@Test
	public void testGetSynthesizerPresentation() {
		assertNotNull(iTest.getSynthesizerPresentation());
	}

	@Test
	public void testSetCurrentWire() {
		fail("Not yet implemented");
	}

}
