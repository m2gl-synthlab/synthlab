package fr.istic.synthlab.abstraction.impl;

import java.util.HashMap;
import java.util.Map;

import com.jsyn.unitgen.SineOscillator;
import com.jsyn.unitgen.UnitGenerator;

import fr.istic.synthlab.abstraction.IInputPort;
import fr.istic.synthlab.abstraction.IModule;
import fr.istic.synthlab.abstraction.IOutputPort;
import fr.istic.synthlab.abstraction.IParameter;
import fr.istic.synthlab.abstraction.ISynthesizer;
import fr.istic.synthlab.factory.impl.PACFactory;

public class ModuleVCO implements IModule {

	public static final int OUTPUT_OUT = 0;

	public static final int PARAM_AMPLITUDE = 0;
	public static final int PARAM_FREQUENCY = 1;
	public static final int PARAM_WIDTH = 2;

	private static final String MODULE_NAME = "VCO";
	
	public static final String OUT_NAME = "Out";
	public static final String AMPLITUDE_NAME = "Ampl";
	public static final String FREQUENCY_NAME = "Fn";
	
	private SineOscillator vco;

	private Map<Integer, IInputPort> inputs;
	private Map<Integer, IOutputPort> outputs;
	private Map<Integer, IParameter> params;

	private ISynthesizer parentSynth;

	public ModuleVCO(String name) {
		this.vco = new SineOscillator();

		this.outputs = new HashMap<Integer, IOutputPort>();
		this.inputs = new HashMap<Integer, IInputPort>();
		this.params = new HashMap<Integer, IParameter>();

		this.outputs.put(ModuleVCO.OUTPUT_OUT, PACFactory.getFactory().newOutputPort(this, OUT_NAME, vco.getOutput()));

		IParameter amplitude = PACFactory.getFactory().newParameter(this, AMPLITUDE_NAME, 0, 1, SineOscillator.DEFAULT_AMPLITUDE);
		amplitude.connect(vco.amplitude);
		this.params.put(ModuleVCO.PARAM_AMPLITUDE, amplitude);

		IParameter frequency = PACFactory.getFactory().newParameter(this, FREQUENCY_NAME,
				vco.frequency.getMinimum(),
				vco.frequency.getMaximum(),
				SineOscillator.DEFAULT_FREQUENCY);
		frequency.connect(vco.frequency);
		this.params.put(ModuleVCO.PARAM_FREQUENCY, frequency);
	}

	@Override
	public UnitGenerator getJSyn() {
		return vco;
	}

	@Override
	public String getName() {
		return MODULE_NAME;
	}

	@Override
	public void start() {
		this.vco.start();
	}

	@Override
	public void stop() {
		this.vco.stop();
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

	@Override
	public ISynthesizer getSynthesizer() {
		return parentSynth;
	}

	@Override
	public void setSynthesizer(ISynthesizer synth) {
		parentSynth = synth;
	}

}
