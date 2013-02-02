package fr.istic.synthlab.abstraction.impl;

import java.util.ArrayList;
import java.util.List;

import com.jsyn.unitgen.FilterLowPass;
import com.jsyn.unitgen.UnitGenerator;

import fr.istic.synthlab.abstraction.IInputPort;
import fr.istic.synthlab.abstraction.IModule;
import fr.istic.synthlab.abstraction.IOutputPort;
import fr.istic.synthlab.factory.impl.PACFactory;

public class ModuleVCF implements IModule{

	public static final int OUTPUT_OUT = 0;
	
	public static final int INPUT_IN = 0;
	public static final int INPUT_AMPLITUDE = 1;
	public static final int INPUT_FREQUENCY = 2;

	private FilterLowPass vcf;	

	private List<IInputPort> inputs;
	private List<IOutputPort> outputs;
	
	
	public ModuleVCF() {
		this.vcf = new FilterLowPass();

		this.inputs = new ArrayList<IInputPort>();
		this.outputs = new ArrayList<IOutputPort>();
		
		this.outputs.add(ModuleVCF.OUTPUT_OUT, PACFactory.getFactory().newOutputPort(vcf.getOutput()));
		
		this.inputs.add(ModuleVCF.INPUT_IN, PACFactory.getFactory().newInputPort(vcf.getInput()));
		this.inputs.add(ModuleVCF.INPUT_AMPLITUDE, PACFactory.getFactory().newInputPort(vcf.amplitude));
		this.inputs.add(ModuleVCF.INPUT_FREQUENCY, PACFactory.getFactory().newInputPort(vcf.frequency));
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

}
