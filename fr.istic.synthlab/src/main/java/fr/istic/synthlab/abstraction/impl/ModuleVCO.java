package fr.istic.synthlab.abstraction.impl;

import java.util.HashMap;
import java.util.Map;

import com.jsyn.unitgen.PulseOscillator;
import com.jsyn.unitgen.UnitGenerator;

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

	private PulseOscillator vco;

	private Map<Integer, IInputPort> inputs;
	private Map<Integer, IOutputPort> outputs;
	private Map<Integer, IParameter> params;

	public ModuleVCO(String name) {
		this.vco = new PulseOscillator();

		this.outputs = new HashMap<Integer, IOutputPort>();
		this.inputs = new HashMap<Integer, IInputPort>();
		this.params = new HashMap<Integer, IParameter>();

		this.outputs.put(ModuleVCO.OUTPUT_OUT,PACFactory.getFactory().newOutputPort(vco.getOutput()));

		this.inputs.put(ModuleVCO.INPUT_AMPLITUDE, PACFactory.getFactory().newInputPort(vco.amplitude));
		this.inputs.put(ModuleVCO.INPUT_FREQUENCY, PACFactory.getFactory().newInputPort(vco.frequency));
		this.inputs.put(ModuleVCO.INPUT_WIDTH, PACFactory.getFactory().newInputPort(vco.width));
		
		
		IParameter amplitude = PACFactory.getFactory().newParameter("Amplitude");
		amplitude.setValue(PulseOscillator.DEFAULT_AMPLITUDE);
		amplitude.connect(inputs.get(ModuleVCO.INPUT_AMPLITUDE));
		this.params.put(ModuleVCO.INPUT_AMPLITUDE, amplitude);
		
		IParameter frequency = PACFactory.getFactory().newParameter("Frequency");
		frequency.setValue(PulseOscillator.DEFAULT_FREQUENCY);
		frequency.connect(inputs.get(ModuleVCO.INPUT_FREQUENCY));
		this.params.put(ModuleVCO.INPUT_FREQUENCY, frequency);
		
		IParameter width = PACFactory.getFactory().newParameter("Width");
		width.setMax(1d);
		width.setMin(-1d);
		width.setValue(0);
		width.connect(inputs.get(ModuleVCO.INPUT_WIDTH));
		this.params.put(ModuleVCO.INPUT_WIDTH, width);
	}

	@Override
	public UnitGenerator getJSyn() {
		return vco;
	}

	@Override
	public void start() {
		this.vco.start();
		System.out.println("vco started");
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
