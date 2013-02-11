package fr.istic.synthlab.abstraction.filter;

import com.jsyn.unitgen.UnitFilter;

public class FrequencyModulatorFilter extends UnitFilter {

	private double baseFrequency;
	
	public FrequencyModulatorFilter(double baseFrequency) {
		this.baseFrequency = baseFrequency;
	}
	
	@Override
	public void generate(int start, int limit) {
		// Get signal arrays from ports.
		double[] inputs = input.getValues();
		double[] outputs = output.getValues();

		for (int i = start; i < limit; i++) {
			// Valeur du port d'entrée
			double in = inputs[i]*5;
			// Calcul de la fréquence
			double out = baseFrequency * Math.pow(2, in);
			// Application de la fréquence sur les ports de sortie
			outputs[i] = out;
		}
	}
	
	public double getBaseFrequency(){
		return this.baseFrequency;
	}
	
	public void setBaseFrequency(double f){
		this.baseFrequency = f;
	}
}
