package fr.istic.synthlab.abstraction.filter;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class AmplitudeModulatorFilterTest {
	
	private AmplitudeModulatorFilter amf;

	@Before
	public void setUp() throws Exception {
		amf=new AmplitudeModulatorFilter(0);

	}
	


	@Test
	public void testGenerateIntInt() {
		amf.generate(5, 7);
		
		for (int i=5;i<7;i++){
			assertEquals(amf.input.getValues()[i]*amf.inputAm.getValues()[i],amf.output.getValues()[i]);
		}
	}


}
