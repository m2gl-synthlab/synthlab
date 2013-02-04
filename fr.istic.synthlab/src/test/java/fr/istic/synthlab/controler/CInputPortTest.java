package fr.istic.synthlab.controler;

import com.jsyn.ports.UnitInputPort;

import fr.istic.synthlab.controller.ICInputPort;
import fr.istic.synthlab.controller.impl.CInputPort;
import fr.istic.synthlab.factory.impl.PACFactory;
import fr.istic.synthlab.factory.impl.PFactory;
import junit.framework.TestCase;

public class CInputPortTest extends TestCase {

	ICInputPort iop;
	
	
	public void setUp(){
		PACFactory.setPFactory(PFactory.getInstance());

	}
	public void testCInputPortString() {
		iop=new CInputPort("out1");
		assertEquals("out1", iop.getName());
		assertNotNull(iop.getPresentation());
		
	}

	public void testCInputPortUnitInputPort() {
		UnitInputPort iop2=new UnitInputPort("test");
		iop=new CInputPort(iop2, "iop");
		assertEquals(iop2, iop.getJSyn());

		assertNotNull(iop.getPresentation());	}

	public void testCInputPortUnitInputPortInt() {
		UnitInputPort iop2=new UnitInputPort("test");

		iop=new CInputPort(iop2, 4, "iop");
		assertEquals(iop2, iop.getJSyn());

		assertEquals(4, iop.getDefaultPart());
		assertNotNull(iop.getPresentation());	}


	
	
}


