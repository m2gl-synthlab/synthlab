package fr.istic.synthlab.abstraction.impl;

import java.util.ArrayList;
import java.util.List;

import com.jsyn.unitgen.FilterLowPass;
import com.jsyn.unitgen.UnitGenerator;

import fr.istic.synthlab.abstraction.IModule;

public class ModuleVCF implements IModule{

	public static final int OUTPUT_OUT = 0;
	
	public static final int INPUT_IN = 0;
	public static final int INPUT_AMPLITUDE = 1;
	public static final int INPUT_FREQUENCY = 2;

	private FilterLowPass vcf;	

	private List<InputPort> inputs;
	private List<OutputPort> outputs;
	
	
	public ModuleVCF() {
		this.vcf = new FilterLowPass();

		this.inputs = new ArrayList<InputPort>();
		this.outputs = new ArrayList<OutputPort>();
		
		this.outputs.add(ModuleVCF.OUTPUT_OUT, new OutputPort(vcf.getOutput()));
		
		this.inputs.add(ModuleVCF.INPUT_IN, new InputPort(vcf.getInput()));
		this.inputs.add(ModuleVCF.INPUT_AMPLITUDE, new InputPort(vcf.amplitude));
		this.inputs.add(ModuleVCF.INPUT_FREQUENCY, new InputPort(vcf.frequency));
	}

	@Override
	public UnitGenerator getJSyn() {
		return vcf;
	}
	
	@Override
	public void start() {
		this.vcf.start();
		System.out.println("Synth : JSyn vcf started " + this.vcf.isEnabled());
	}

	@Override
	public void stop() {
		this.vcf.stop();
	}


	@Override
	public InputPort getInput(int identifier) {
		return inputs.get(identifier);
	}

	@Override
	public OutputPort getOutput(int identifier) {
		return outputs.get(identifier);
	}

}
