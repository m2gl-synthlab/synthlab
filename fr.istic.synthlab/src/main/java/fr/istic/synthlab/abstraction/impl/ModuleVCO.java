package fr.istic.synthlab.abstraction.impl;

import java.util.ArrayList;
import java.util.List;

import com.jsyn.unitgen.PulseOscillator;
import com.jsyn.unitgen.UnitGenerator;

import fr.istic.synthlab.abstraction.IInputPort;
import fr.istic.synthlab.abstraction.IModule;
import fr.istic.synthlab.abstraction.IOutputPort;
import fr.istic.synthlab.factory.impl.PACFactory;

public class ModuleVCO implements IModule {

	public static final int OUTPUT_OUT = 0;

	public static final int INPUT_AMPLITUDE = 0;
	public static final int INPUT_FREQUENCY = 1;
	public static final int INPUT_WIDTH = 2;

	private PulseOscillator vco;

	private List<IInputPort> inputs;
	private List<IOutputPort> outputs;

	public ModuleVCO() {
		this.vco = new PulseOscillator();

		this.outputs = new ArrayList<IOutputPort>();
		this.inputs = new ArrayList<IInputPort>();

		this.outputs.add(ModuleVCO.OUTPUT_OUT,PACFactory.getFactory().newOutputPort(vco.getOutput()));

		this.inputs.add(ModuleVCO.INPUT_AMPLITUDE, PACFactory.getFactory().newInputPort(vco.amplitude));
		this.inputs.add(ModuleVCO.INPUT_FREQUENCY, PACFactory.getFactory().newInputPort(vco.frequency));
		this.inputs.add(ModuleVCO.INPUT_WIDTH, PACFactory.getFactory().newInputPort(vco.width));
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

}
