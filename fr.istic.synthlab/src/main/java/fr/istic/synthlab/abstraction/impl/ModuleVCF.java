package fr.istic.synthlab.abstraction.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jsyn.unitgen.FilterLowPass;
import com.jsyn.unitgen.PulseOscillator;
import com.jsyn.unitgen.UnitGenerator;

import fr.istic.synthlab.abstraction.IInputPort;
import fr.istic.synthlab.abstraction.IModule;
import fr.istic.synthlab.abstraction.IOutputPort;
import fr.istic.synthlab.abstraction.IParameter;
import fr.istic.synthlab.factory.impl.PACFactory;

public class ModuleVCF implements IModule {

	public static final int OUTPUT_OUT = 0;

	public static final int INPUT_IN = 0;
	
	public static final int PARAM_AMPLITUDE = 0;
	public static final int PARAM_FREQUENCY = 1;
	
	private static final String MODULE_NAME = "Filter Low Pass";

	public static final String OUT_NAME = "Out";
	public static final String IN_NAME = "In";
	public static final String AMPLITUDE_NAME = "Ampl";
	public static final String FREQUENCY_NAME = "Fn";
	
	
	private FilterLowPass vcf;

	private Map<Integer, IInputPort> inputs;
	private Map<Integer, IOutputPort> outputs;
	private Map<Integer, IParameter> params;

	public ModuleVCF(String name) {
		this.vcf = new FilterLowPass();

		this.inputs = new HashMap<Integer, IInputPort>();
		this.outputs = new HashMap<Integer, IOutputPort>();
		this.params = new HashMap<Integer, IParameter>();

		this.outputs.put(ModuleVCF.OUTPUT_OUT, PACFactory.getFactory()
				.newOutputPort(OUT_NAME, vcf.getOutput()));
		
		this.inputs.put(ModuleVCF.INPUT_IN, PACFactory.getFactory()
				.newInputPort(IN_NAME, vcf.getInput()));

		IParameter amplitude = PACFactory.getFactory().newParameter(AMPLITUDE_NAME, 0, 1, 0.5);
		amplitude.connect(vcf.amplitude);
		this.params.put(ModuleVCF.PARAM_AMPLITUDE, amplitude);
		System.out.println("Default amplitude : "
				+ PulseOscillator.DEFAULT_AMPLITUDE);

		IParameter frequency = PACFactory.getFactory().newParameter(FREQUENCY_NAME,
				vcf.frequency.getMinimum(),
				vcf.frequency.getMaximum(),
				440);
		frequency.connect(vcf.frequency);
		this.params.put(ModuleVCF.PARAM_FREQUENCY, frequency);
	}

	@Override
	public List<UnitGenerator> getJSyn() {
		List<UnitGenerator> generators = new ArrayList<UnitGenerator>();
		generators.add(vcf);
		return generators;
	}
	
	@Override
	public String getName() {
		return MODULE_NAME;
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
