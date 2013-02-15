package fr.istic.synthlab.abstraction;

import junit.framework.TestCase;

import com.jsyn.ports.UnitInputPort;
import com.jsyn.ports.UnitOutputPort;

import fr.istic.synthlab.abstraction.exception.BadConnectionException;
import fr.istic.synthlab.abstraction.exception.PortAlreadyInUseException;
import fr.istic.synthlab.abstraction.module.out.ModuleOUT;
import fr.istic.synthlab.abstraction.port.IInputPort;
import fr.istic.synthlab.abstraction.port.IOutputPort;
import fr.istic.synthlab.abstraction.synthesizer.ISynthesizer;
import fr.istic.synthlab.abstraction.wire.IWire;
import fr.istic.synthlab.factory.impl.AFactory;
import fr.istic.synthlab.factory.impl.CFactory;
import fr.istic.synthlab.factory.impl.PACFactory;
import fr.istic.synthlab.factory.impl.PFactory;

public class WireTest extends TestCase {

	private IWire wire;
	private IInputPort ip;
	private IOutputPort op;
	private ISynthesizer synth;
	public void setUp(){
		PACFactory.setFactory(AFactory.getInstance());
		PACFactory.setCFactory(CFactory.getInstance());
		PACFactory.setPFactory(PFactory.getInstance());

		wire=PACFactory.getAFactory().newWire(synth);
			ip=PACFactory.getAFactory().newInputPort(synth, new ModuleOUT(synth),"port", new UnitInputPort("port"));
		 op=PACFactory.getAFactory().newOutputPort(synth, new ModuleOUT(synth),"port", new UnitOutputPort("port"));

	}

	public void testGetInput() {
		System.out.println(wire);
		assertEquals(null, wire.getInput());
	}

	public void testGetOutput() {
		assertEquals(null, wire.getOutput());
	}

	public void testIsConnected() {
		assertEquals(false, wire.isConnected());
	}

	public void testConnectIInputPort() {
		try {
			wire.connect(ip);
		} catch (PortAlreadyInUseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertEquals(ip, wire.getInput());
		assertFalse(ip.isInUse());
		assertFalse(wire.isConnected());

	}

	public void testConnectIOutputPort() {
		try {
			wire.connect(op);
			wire.connect(ip);

		} catch (PortAlreadyInUseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertEquals(op, wire.getOutput());
		;
		assertTrue(op.isInUse());
		assertTrue(ip.isInUse());
		assertTrue(wire.isConnected());

	}

	public void testDisconnect() {
		wire.disconnect();
		assertFalse(op.isInUse());
		assertFalse(ip.isInUse());
		assertFalse(wire.isConnected());
	}

}
