package fr.istic.synthlab.abstraction.filter;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class FrequencyModulatorFilterTest {

	private FrequencyModulatorFilter fmf;

	@Before
	public void setUp() throws Exception {
		fmf = new FrequencyModulatorFilter(2080.0);
	}

	@Test
	public void testFrequencyModulatorFilter() {
		assertEquals(2080.0, fmf.getBaseFrequency());
	}

	@Test
	public void testSetGetBaseFrequency() {
		fmf.setBaseFrequency(2128.4);
		assertEquals(2128.4, fmf.getBaseFrequency());
	}

	@Test
	public void testGenerateIntInt() {
		fmf.setBaseFrequency(3029.7);

		fmf.generate(5, 7);
		for (int i = 5; i < 7; i++) {
			assertEquals(3029.7 * Math.pow(2, fmf.input.getValues()[i] * 5), fmf.output.getValues()[i]);
		}
	}

}
