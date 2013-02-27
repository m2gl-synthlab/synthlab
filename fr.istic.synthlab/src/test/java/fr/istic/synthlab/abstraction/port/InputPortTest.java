package fr.istic.synthlab.abstraction.port;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import com.jsyn.ports.UnitInputPort;

import fr.istic.synthlab.abstraction.module.vco.ModuleVCO;
import fr.istic.synthlab.abstraction.synthesizer.Synthesizer;
import fr.istic.synthlab.factory.impl.AFactory;
import fr.istic.synthlab.factory.impl.PACFactory;

public class InputPortTest {
	
	IInputPort ip;

	@Before
	public void setUp() throws Exception {
		PACFactory.setAFactory(AFactory.getInstance());
		PACFactory.setCFactory(null);

		ip=new InputPort("", new UnitInputPort(""), new ModuleVCO(new Synthesizer()));
		
	}



	@Test
	public void testGetJSyn() {
		assertNotNull(ip.getJSyn());
	}

}
