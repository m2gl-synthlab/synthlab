package fr.istic.synthlab.abstraction;

import junit.framework.TestCase;
import fr.istic.synthlab.abstraction.util.Convert;

public class ConvertTest extends TestCase {

	/**
	 * Test the conversion volt/dB
	 */
	public void testConvertVolt2DB() {
		double voltValue = 3.14;
		assertEquals(voltValue, Convert.dB2V(Convert.v2Db(voltValue)));
	}

	/**
	 * Test the conversion dB/volt
	 */
	public void testConvertDB2Volt() {
		double dbValue = -12;
		assertEquals(dbValue, Convert.v2Db(Convert.dB2V(dbValue)));
	}
}
