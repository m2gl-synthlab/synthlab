package fr.istic.synthlab.abstraction.filter;

import com.jsyn.unitgen.UnitFilter;

/**
 * Attenuation Filter
 */
public class AttenuationFilter extends UnitFilter {

	private double attenuation = 0; // Attenuation value in volts

	@Override
	public void generate(int start, int limit) {
		// Get signal arrays from ports.
		double[] inputs = input.getValues();
		double[] outputs = output.getValues();

		for (int i = start; i < limit; i++) {
			double in = inputs[i];
			double out = attenuation * in;
			outputs[i] = out;
			// see : http://fr.wikipedia.org/wiki/Niveau_(audio)
		}
	}

	/**
	 * Set the attenuation value in volts
	 * 
	 * @param attenuation
	 */
	public void setAttenuation(double attenuation) {
		this.attenuation = attenuation;
	}

	/**
	 * Get the attenuation value in volts
	 * 
	 * @param attenuation
	 */
	public double getAttenuation() {
		return this.attenuation;
	}

}