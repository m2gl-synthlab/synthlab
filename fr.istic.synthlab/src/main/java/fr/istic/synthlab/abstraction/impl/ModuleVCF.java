package fr.istic.synthlab.abstraction.impl;

import java.util.HashMap;
import java.util.Map;

import com.jsyn.unitgen.FilterLowPass;
import com.jsyn.unitgen.PulseOscillator;
import com.jsyn.unitgen.UnitGenerator;

import fr.istic.synthlab.abstraction.IInputPort;
import fr.istic.synthlab.abstraction.IModule;
import fr.istic.synthlab.abstraction.IOutputPort;
import fr.istic.synthlab.abstraction.IParameter;
import fr.istic.synthlab.factory.impl.PACFactory;

public class ModuleVCF implements IModule{

	public static final int OUTPUT_OUT = 0;

	public static final int INPUT_IN = 0;
	public static final int INPUT_AMPLITUDE = 1;
	public static final int INPUT_FREQUENCY = 2;


	private FilterLowPass vcf;	

	private Map<Integer, IInputPort> inputs;
	private Map<Integer, IOutputPort> outputs;
	private Map<Integer, IParameter> params;

	
	
	public ModuleVCF(String name) {
		this.vcf = new FilterLowPass();

		this.inputs = new HashMap<Integer, IInputPort>();
		this.outputs = new HashMap<Integer, IOutputPort>();
		this.params = new HashMap<Integer, IParameter>();
		
		this.outputs.put(ModuleVCF.OUTPUT_OUT, PACFactory.getFactory().newOutputPort(vcf.getOutput()));
		
		this.inputs.put(ModuleVCF.INPUT_AMPLITUDE, PACFactory.getFactory().newInputPort(vcf.amplitude));
		this.inputs.put(ModuleVCF.INPUT_FREQUENCY, PACFactory.getFactory().newInputPort(vcf.frequency));
		this.inputs.put(ModuleVCF.INPUT_IN, PACFactory.getFactory().newInputPort(vcf.getInput()));
		
		
		IParameter amplitude = PACFactory.getFactory().newParameter(0, 1, 0.5);
		amplitude.connect(inputs.get(ModuleVCF.INPUT_AMPLITUDE));
		this.params.put(ModuleVCF.INPUT_AMPLITUDE, amplitude);
		System.out.println("Default amplitude : " + PulseOscillator.DEFAULT_AMPLITUDE);
		
		IParameter frequency = PACFactory.getFactory().newParameter(0,1000, 440);
		frequency.connect(inputs.get(ModuleVCF.INPUT_FREQUENCY));
		this.params.put(ModuleVCF.INPUT_FREQUENCY, frequency);
	}

	@Override
	public UnitGenerator getJSyn() {
		return vcf;
	}
	
	@Override
	public void start() {
		this.vcf.start();
	}

	@Override
	public void stop() {
		this.vcf.stop();
	}


	@Override
	public IInputPort getInput(int identifier) {
		return inputs.get(identifier);
	}

	@Override
	public IOutputPort getOutput(int identifier) {
		return outputs.get(identifier);
	}

	@Override
	public IParameter getParameter(int identifier) {
		return params.get(identifier);
	}

}
