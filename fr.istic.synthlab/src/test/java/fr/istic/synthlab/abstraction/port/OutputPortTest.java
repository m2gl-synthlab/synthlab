package fr.istic.synthlab.abstraction.port;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.jsyn.ports.UnitOutputPort;

import fr.istic.synthlab.abstraction.module.vco.ModuleVCO;
import fr.istic.synthlab.abstraction.synthesizer.Synthesizer;
import fr.istic.synthlab.factory.impl.AFactory;
import fr.istic.synthlab.factory.impl.PACFactory;

public class OutputPortTest {
	
	IOutputPort op;

	@Before
	public void setUp() throws Exception {
		PACFactory.setAFactory(AFactory.getInstance());
		PACFactory.setCFactory(null);

		op=new OutputPort("", new UnitOutputPort(""), new ModuleVCO(new Synthesizer()));
		
	}



	@Test
	public void testGetJSyn() {
		assertNotNull(op.getJSyn());
	}

}
