package fr.istic.synthlab.abstraction.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jsyn.unitgen.SineOscillator;
import com.jsyn.unitgen.SquareOscillator;
import com.jsyn.unitgen.TriangleOscillator;
import com.jsyn.unitgen.UnitGenerator;

import fr.istic.synthlab.abstraction.IInputPort;
import fr.istic.synthlab.abstraction.IModule;
import fr.istic.synthlab.abstraction.IOutputPort;
import fr.istic.synthlab.abstraction.IParameter;
import fr.istic.synthlab.factory.impl.PACFactory;

public class ModuleVCO implements IModule {

	public static final int OUTPUT_OUT1 = 1;
	public static final int OUTPUT_OUT = 0;

	public static final int PARAM_AMPLITUDE = 0;
	public static final int PARAM_FREQUENCY = 1;
	public static final int PARAM_WIDTH = 2;

	private static final String MODULE_NAME = "VCO";

	public static final String OUT_NAME = "Out";
	public static final String OUT_NAME1 = "Out1";
	public static final String AMPLITUDE_NAME = "Ampl";
	public static final String FREQUENCY_NAME = "Fn";

	private SquareOscillator vcoSquare;
	private TriangleOscillator vcoTriangle;

	private Map<Integer, IInputPort> inputs;
	private Map<Integer, IOutputPort> outputs;
	private Map<Integer, IParameter> params;

	public ModuleVCO(String name) {
		this.vcoSquare =  new SquareOscillator();
		this.vcoTriangle =  new TriangleOscillator();

		this.outputs = new HashMap<Integer, IOutputPort>();
		this.inputs = new HashMap<Integer, IInputPort>();
		this.params = new HashMap<Integer, IParameter>();

		this.outputs.put(ModuleVCO.OUTPUT_OUT, PACFactory.getFactory().newOutputPort(OUT_NAME, vcoSquare.getOutput()));
		this.outputs.put(ModuleVCO.OUTPUT_OUT1, PACFactory.getFactory().newOutputPort(OUT_NAME1, vcoTriangle.getOutput()));

		IParameter amplitude = PACFactory.getFactory().newParameter(AMPLITUDE_NAME, 0, 1, SineOscillator.DEFAULT_AMPLITUDE);
		amplitude.connect(vcoSquare.amplitude);
		amplitude.connect(vcoTriangle.amplitude);
		this.params.put(ModuleVCO.PARAM_AMPLITUDE, amplitude);

		IParameter frequency = PACFactory.getFactory().newParameter( FREQUENCY_NAME,
				vcoSquare.frequency.getMinimum(),
				vcoSquare.frequency.getMaximum(),
				SineOscillator.DEFAULT_FREQUENCY);
		
		frequency.connect(vcoSquare.frequency);
		frequency.connect(vcoTriangle.frequency);
		this.params.put(ModuleVCO.PARAM_FREQUENCY, frequency);
	}

	@Override
	public List<UnitGenerator> getJSyn() {
		List<UnitGenerator> generators = new ArrayList<UnitGenerator>();
		generators.add(vcoSquare);
		generators.add(vcoTriangle);
		return generators;
	}

	@Override
	public String getName() {
		return MODULE_NAME;
	}

	@Override
	public void start() {
		this.vcoSquare.start();
		this.vcoTriangle.start();
	}

	@Override
	public void stop() {
		this.vcoSquare.stop();
		this.vcoTriangle.stop();
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
