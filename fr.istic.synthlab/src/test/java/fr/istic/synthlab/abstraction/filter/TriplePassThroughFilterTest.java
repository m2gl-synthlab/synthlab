package fr.istic.synthlab.abstraction.filter;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TriplePassThroughFilterTest {
	
	private TriplePassThroughFilter tptf;

	@Before
	public void setUp() throws Exception {
		tptf=new TriplePassThroughFilter();
	}

	@Test
	public void testGenerateIntInt() {
		tptf.generate(5, 7);
		
		for(int i=5;i<7;i++){
			assertEquals(tptf.getInput().getValues()[i], tptf.getOutput1().getValues()[i]);
			assertEquals(tptf.getInput().getValues()[i], tptf.getOutput2().getValues()[i]);
			assertEquals(tptf.getInput().getValues()[i], tptf.getOutput3().getValues()[i]);

		}
	}

}
