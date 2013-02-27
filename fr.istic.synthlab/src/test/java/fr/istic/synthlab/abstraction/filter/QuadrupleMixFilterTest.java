package fr.istic.synthlab.abstraction.filter;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class QuadrupleMixFilterTest {
	private QuadrupleMixFilter qmf;

	@Before
	public void setUp() throws Exception {
		qmf = new QuadrupleMixFilter();
	}

	@Test
	public void testGenerateIntInt() {
		qmf.generate(5, 7);
		for (int i = 5; i < 7; i++) {
			assertEquals(qmf.getInput1().getValues()[i] + qmf.getInput2().getValues()[i] + qmf.getInput3().getValues()[i] + qmf.getInput4().getValues()[i], qmf
					.getOutput().getValues()[i]);
		}

	}

}
