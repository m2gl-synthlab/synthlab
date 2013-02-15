package fr.istic.synthlab.abstraction;

import java.lang.reflect.Field;

import junit.framework.TestCase;

import com.jsyn.unitgen.PassThrough;

import fr.istic.synthlab.abstraction.exception.BadConnectionException;
import fr.istic.synthlab.abstraction.exception.PortAlreadyInUseException;
import fr.istic.synthlab.abstraction.module.vco.IModuleVCO;
import fr.istic.synthlab.abstraction.module.vco.ModuleVCO;
import fr.istic.synthlab.abstraction.port.Port;
import fr.istic.synthlab.abstraction.wire.IWire;
import fr.istic.synthlab.abstraction.wire.Wire;
import fr.istic.synthlab.factory.impl.AFactory;
import fr.istic.synthlab.factory.impl.CFactory;
import fr.istic.synthlab.factory.impl.PACFactory;
import fr.istic.synthlab.factory.impl.PFactory;

public class ModuleVCOTest extends TestCase {

	private IModuleVCO m;

	public void setUp() {
		PACFactory.setFactory(AFactory.getInstance());
		PACFactory.setCFactory(CFactory.getInstance());
		PACFactory.setPFactory(PFactory.getInstance());
		m = new ModuleVCO();

	}

	public void testStart() {
		fail("Not yet implemented");
	}

	public void testStop() {
		fail("Not yet implemented");
	}

	public void testSetGetOctave() {
		m.setOctave(5);
		m.setTone(0.4);
		assertEquals(5, m.getOctave());
		assertEquals((double) Math.pow(2, 5 + 0.4), m.getFrequency());

	}

	public void testSetGetTone() {
		m.setTone(0.24);
		m.setOctave(6);

		assertEquals(0.24, m.getTone());

		assertEquals((double) Math.pow(2, 6 + 0.24), m.getFrequency());

	}

	public void testSetGetFrequency() {
		m.setFrequency(140.0);
		assertEquals(140.0, m.getFrequency());
	}

	public void testGetWiresUpdate() {
		IWire w = new Wire();
		try {
			w.connect(m.getInputFm());
		} catch (PortAlreadyInUseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			w.connect(m.getOutputSawtooth());
		} catch (PortAlreadyInUseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		((ModuleVCO) m).update((Port) m.getInputFm());

		Field f = null;
		try {
			f = m.getClass().getDeclaredField("passThrough");
			f.setAccessible(true);
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PassThrough p = null;
		try {
			p = (PassThrough) f.get(m);
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertEquals(w, m.getWires().get(0));
		assertTrue(p.output.isConnected());

	}

	public void testUpdateWithoutConnect() {

		((ModuleVCO) m).update((Port) m.getInputFm());

		Field f = null;
		try {
			f = m.getClass().getDeclaredField("passThrough");
			f.setAccessible(true);
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PassThrough p = null;
		try {
			p = (PassThrough) f.get(m);
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertFalse(p.output.isConnected());

	}

}
