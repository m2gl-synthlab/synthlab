package fr.istic.synthlab.abstraction.impl;

import java.util.ArrayList;

import com.jsyn.unitgen.PulseOscillator;


//import com.jsyn.ports.UnitGenerator;
public class ModuleVCO extends AModuleVCO{

	PulseOscillator myOscillo = new PulseOscillator();

	public ModuleVCO(String name) {
		super(name);

	}
	
	public double[] generateOscillo()  {

		double[] values =myOscillo.output.getValues();
		for (int i =0; i<values.length; i++){
			System.err.println(values[i]);
		}
		
		return values;
	}

	
	
}
