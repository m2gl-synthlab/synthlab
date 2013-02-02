package fr.istic.synthlab.abstraction.impl;

import java.util.ArrayList;
import java.util.List;

import com.jsyn.unitgen.PulseOscillator;
import com.jsyn.unitgen.UnitGenerator;

import fr.istic.synthlab.abstraction.IModule;

public class ModuleVCO implements IModule {

	public static final int OUTPUT_OUT = 0;

	public static final int INPUT_AMPLITUDE = 0;
	public static final int INPUT_FREQUENCY = 1;
	public static final int INPUT_WIDTH = 2;

	private PulseOscillator vco;

	private List<InputPort> inputs;
	private List<OutputPort> outputs;

	public ModuleVCO() {
		this.vco = new PulseOscillator();

		this.outputs = new ArrayList<OutputPort>();
		this.inputs = new ArrayList<InputPort>();

		this.outputs.add(ModuleVCO.OUTPUT_OUT, new OutputPort(vco.getOutput()));

		this.inputs.add(ModuleVCO.INPUT_AMPLITUDE, new InputPort(vco.amplitude));
		this.inputs.add(ModuleVCO.INPUT_FREQUENCY, new InputPort(vco.frequency));
		this.inputs.add(ModuleVCO.INPUT_WIDTH, new InputPort(vco.width));
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
	public InputPort getInput(int identifier) {
		System.err.println("No Input in " + getClass().getSimpleName());
		return null;
	}

	@Override
	public OutputPort getOutput(int identifier) {
		return outputs.get(identifier);
	}

}
