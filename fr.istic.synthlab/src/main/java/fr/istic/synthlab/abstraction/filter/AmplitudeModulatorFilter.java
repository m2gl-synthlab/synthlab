package fr.istic.synthlab.abstraction.filter;

import com.jsyn.ports.UnitInputPort;
import com.jsyn.unitgen.UnitFilter;

import fr.istic.synthlab.abstraction.util.Convert;

public class AmplitudeModulatorFilter extends UnitFilter {

	public static String INPUT_AM_NAME = "Am";
	
	public UnitInputPort inputAm = new UnitInputPort(INPUT_AM_NAME);
	
	public AmplitudeModulatorFilter(double baseAmplitude) {
		addPort(inputAm);
	}
	
	@Override
	public void generate(int start, int limit) {

		double[] inputs = input.getValues();	
		double[] inputsAm = inputAm.getValues();	
		double[] outputs = output.getValues();

		for (int i = start; i < limit; i++) {
			double in = inputs[i];
			double mod = inputsAm[i] * 5 * 12;
			outputs[i] = in * Convert.dB2V(mod) ; 
		}
	}
}