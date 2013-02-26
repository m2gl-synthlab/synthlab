package fr.istic.synthlab.abstraction.util;

public class Convert {

	public static double vRef = 1;

	/**
	 * Convert dB to volt
	 * 
	 * @param dB
	 * @return
	 */
	public static double dB2V(double dB) {
		return Math.pow(10.0, new Double(dB) / 20.0) * vRef;
	}

	/**
	 * Convert volt to dB
	 * 
	 * @param v
	 * @return
	 */
	public static double v2Db(double v) {
		return 20 * Math.log10(v / vRef);
	}

}
