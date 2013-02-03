package fr.istic.synthlab.abstraction.impl;

import java.util.HashMap;
import java.util.Map;

import com.jsyn.unitgen.PulseOscillator;
import com.jsyn.unitgen.UnitGenerator;
import com.jsyn.unitgen.SineOscillator;

import fr.istic.synthlab.abstraction.IInputPort;
import fr.istic.synthlab.abstraction.IModule;
import fr.istic.synthlab.abstraction.IOutputPort;
import fr.istic.synthlab.abstraction.IParameter;
import fr.istic.synthlab.factory.impl.PACFactory;

public class ModuleVCO implements IModule {

	public static final int OUTPUT_OUT = 0;

	public static final int INPUT_AMPLITUDE = 0;
	public static final int INPUT_FREQUENCY = 1;
	public static final int INPUT_WIDTH = 2;

	private SineOscillator vco;

	private Map<Integer, IInputPort> inputs;
	private Map<Integer, IOutputPort> outputs;
	private Map<Integer, IParameter> params;

	public ModuleVCO(String name) {
		this.vco = new SineOscillator();

		this.outputs = new HashMap<Integer, IOutputPort>();
		this.inputs = new HashMap<Integer, IInputPort>();
		this.params = new HashMap<Integer, IParameter>();

		this.outputs.put(ModuleVCO.OUTPUT_OUT, PACFactory.getFactory()
				.newOutputPort(vco.getOutput()));

		this.inputs.put(ModuleVCO.INPUT_AMPLITUDE, PACFactory.getFactory()
				.newInputPort(vco.amplitude));
		this.inputs.put(ModuleVCO.INPUT_FREQUENCY, PACFactory.getFactory()
				.newInputPort(vco.frequency));

		IParameter amplitude = PACFactory.getFactory().newParameter(0, 1,
				PulseOscillator.DEFAULT_AMPLITUDE);
		amplitude.connect(inputs.get(ModuleVCO.INPUT_AMPLITUDE));
		this.params.put(ModuleVCO.INPUT_AMPLITUDE, amplitude);

		IParameter frequency = PACFactory.getFactory().newParameter(
				inputs.get(ModuleVCO.INPUT_FREQUENCY).getJSyn().getMinimum(),
				inputs.get(ModuleVCO.INPUT_FREQUENCY).getJSyn().getMaximum(),
				SineOscillator.DEFAULT_FREQUENCY);
		frequency.connect(inputs.get(ModuleVCO.INPUT_FREQUENCY));
		this.params.put(ModuleVCO.INPUT_FREQUENCY, frequency);
	}

	@Override
	public UnitGenerator getJSyn() {
		return vco;
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
}
