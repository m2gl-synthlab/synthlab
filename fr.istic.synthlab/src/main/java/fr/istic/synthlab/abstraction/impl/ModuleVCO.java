package fr.istic.synthlab.abstraction.impl;

import com.jsyn.unitgen.UnitFilter;

import fr.istic.synthlab.factory.IFactory;
import fr.istic.synthlab.factory.impl.AFactory;
//import com.jsyn.ports.UnitGenerator;
public class ModuleVCO extends AModuleVCO{



	public ModuleVCO(String name) {
		super(name);

	}

	/**
	 * Custom unit generator that can be used with other JSyn units.
	 * Cube the input value and write it to output port.
	 * 
	 * @author Phil Burk (C) 2010 Mobileer Inc
	 * 
	 */
	double[] outputs = null;
	public double[] getOutputs() {
		return outputs;
	}
	/** This is where the synthesis occurs.
	 * It is called in a high priority background thread so do not do
	 * anything crazy here like reading a file or doing network I/O.
	 * The start and limit allow us to do either block or single sample processing.
	 */
	@Override
	public void generate( int start, int limit )
	{
		// Get signal arrays from ports.
		double[] inputs = input.getValues();
		outputs = output.getValues();

		for( int i = start; i < limit; i++ )
		{
			double x = inputs[i];
			outputs[i] = x * x * x;  // x cubed
		}
	}

}
