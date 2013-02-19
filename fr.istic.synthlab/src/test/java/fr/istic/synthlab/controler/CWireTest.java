package fr.istic.synthlab.controler;

import static org.junit.Assert.*;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.jsyn.ports.ConnectableInput;
import com.jsyn.ports.UnitInputPort;
import com.jsyn.ports.UnitOutputPort;

import fr.istic.synthlab.abstraction.exception.BadConnectionException;
import fr.istic.synthlab.abstraction.exception.PortAlreadyInUseException;
import fr.istic.synthlab.abstraction.module.out.ModuleOUT;
import fr.istic.synthlab.abstraction.port.IInputPort;
import fr.istic.synthlab.abstraction.port.IOutputPort;
import fr.istic.synthlab.abstraction.port.InputPort;
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
	private IInputPort ip;
	private IOutputPort op;

	@Before
	public void setUp() throws Exception {
		PACFactory.setAFactory(AFactory.getInstance());
		PACFactory.setCFactory(CFactory.getInstance());
		PACFactory.setPFactory(PFactory.getInstance());
		synth = new CSynthesizer();
		iTest = new CWire(synth);
		
		ip = PACFactory.getCFactory().newInputPort(synth, new ModuleOUT(synth), "port", new UnitInputPort("port"));
		op = PACFactory.getCFactory().newOutputPort(synth, new ModuleOUT(synth), "port", new UnitOutputPort("port"));
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
