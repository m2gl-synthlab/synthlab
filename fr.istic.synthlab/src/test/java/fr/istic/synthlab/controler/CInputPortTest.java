package fr.istic.synthlab.controler;

import com.jsyn.ports.UnitInputPort;

import fr.istic.synthlab.controller.port.CInputPort;
import fr.istic.synthlab.controller.port.ICInputPort;
import fr.istic.synthlab.factory.impl.PACFactory;
import fr.istic.synthlab.factory.impl.PFactory;
import junit.framework.TestCase;

public class CInputPortTest extends TestCase {

	ICInputPort iop;

	public void setUp() {
		PACFactory.setPFactory(PFactory.getInstance());

	}

	public void testCInputPortString() {
		iop = new CInputPort("out1", null, null);
		assertEquals("out1", iop.getName());
		assertNotNull(iop.getPresentation());

	}

	public void testCInputPortUnitInputPort() {
		UnitInputPort iop2 = new UnitInputPort("test");
		iop = new CInputPort("iop", iop2, null);
		assertEquals(iop2, iop.getJSyn());

		assertNotNull(iop.getPresentation());
	}

}
