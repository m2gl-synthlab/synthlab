package fr.istic.synthlab.controler;

import com.jsyn.ports.UnitOutputPort;

import junit.framework.TestCase;

import fr.istic.synthlab.controller.port.COutputPort;
import fr.istic.synthlab.controller.port.ICOutputPort;
import fr.istic.synthlab.factory.impl.PACFactory;
import fr.istic.synthlab.factory.impl.PFactory;

public class COutputPortTest extends TestCase {
	ICOutputPort iop;

	public void setUp() {
		PACFactory.setPFactory(PFactory.getInstance());

	}

	public void testCOutputPortString() {
		iop = new COutputPort("out1", null, null);
		assertEquals("out1", iop.getName());
		assertNotNull(iop.getPresentation());

	}

	public void testCOutputPortUnitOutputPortString() {
		UnitOutputPort iop2 = new UnitOutputPort("test");
		iop = new COutputPort("out2", iop2, null);
		assertEquals("out2", iop.getName());
		assertEquals(iop2, iop.getJSyn());

		assertNotNull(iop.getPresentation());
	}

}
