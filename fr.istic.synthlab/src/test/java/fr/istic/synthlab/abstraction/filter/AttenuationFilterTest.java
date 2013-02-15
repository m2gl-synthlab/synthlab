package fr.istic.synthlab.abstraction.filter;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class AttenuationFilterTest {
	private AttenuationFilter af;

	@Before
	public void setUp() throws Exception {
		af=new AttenuationFilter();
	}

	@Test
	public void testSetGetAttenuation() {
		af.setAttenuation(3.0);
		assertEquals(3.0, af.getAttenuation());
	}

	
	@Test
	public void testGenerateIntInt() {
		af.setAttenuation(4.0);
		af.generate(5,7);
		
		for(int i=5;i<7;i++){
			assertEquals(4.0*af.input.getValues()[i],af.output.getValues()[i]);
		}
	}



}
