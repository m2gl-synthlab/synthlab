package fr.istic.synthlab.abstraction.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jsyn.unitgen.PulseOscillator;
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

	public static final int OUTPUT_PULSE = 2;
	public static final int OUTPUT_TRIANGLE = 1;
	public static final int OUTPUT_SQUARE = 0;

	public static final int PARAM_AMPLITUDE = 0;
	public static final int PARAM_FREQUENCY = 1;
	public static final int PARAM_WIDTH = 2;

	private static final String MODULE_NAME = "VCO";

	public static final String OUT_PULSE_NAME = "Out Pulse";
	public static final String OUT_SQUARE_NAME = "Out Square";
	public static final String OUT_TRIANGLE_NAME = "Out Triangle";
	
	public static final String AMPLITUDE_NAME = "Ampl";
	public static final String FREQUENCY_NAME = "Fn";

	private SquareOscillator vcoSquare;
	private TriangleOscillator vcoTriangle;
	private PulseOscillator vcoPulse;

	private Map<Integer, IInputPort> inputs;
	private Map<Integer, IOutputPort> outputs;
	private Map<Integer, IParameter> params;

	public ModuleVCO(String name) {
		this.vcoSquare =  new SquareOscillator();
		this.vcoTriangle =  new TriangleOscillator();
		this.vcoPulse =  new PulseOscillator();

		this.outputs = new HashMap<Integer, IOutputPort>();
		this.inputs = new HashMap<Integer, IInputPort>();
		this.params = new HashMap<Integer, IParameter>();

		this.outputs.put(ModuleVCO.OUTPUT_SQUARE, PACFactory.getFactory().newOutputPort(OUT_SQUARE_NAME, vcoSquare.getOutput()));
		this.outputs.put(ModuleVCO.OUTPUT_TRIANGLE, PACFactory.getFactory().newOutputPort(OUT_TRIANGLE_NAME, vcoTriangle.getOutput()));
		this.outputs.put(ModuleVCO.OUTPUT_PULSE, PACFactory.getFactory().newOutputPort(OUT_PULSE_NAME, vcoPulse.getOutput()));

		IParameter amplitude = PACFactory.getFactory().newParameter(AMPLITUDE_NAME, 0, 1, SineOscillator.DEFAULT_AMPLITUDE);
		amplitude.connect(vcoSquare.amplitude);
		amplitude.connect(vcoTriangle.amplitude);
		amplitude.connect(vcoPulse.amplitude);
		this.params.put(ModuleVCO.PARAM_AMPLITUDE, amplitude);

		IParameter frequency = PACFactory.getFactory().newParameter( FREQUENCY_NAME,
				vcoSquare.frequency.getMinimum(),
				vcoSquare.frequency.getMaximum(),
				SineOscillator.DEFAULT_FREQUENCY);
		
		frequency.connect(vcoSquare.frequency);
		frequency.connect(vcoTriangle.frequency);
		frequency.connect(vcoPulse.frequency);
		this.params.put(ModuleVCO.PARAM_FREQUENCY, frequency);
	}

	@Override
	public List<UnitGenerator> getJSyn() {
		List<UnitGenerator> generators = new ArrayList<UnitGenerator>();
		generators.add(vcoSquare);
		generators.add(vcoTriangle);
		generators.add(vcoPulse);
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
		this.vcoPulse.start();
	}

	@Override
	public void stop() {
		this.vcoSquare.stop();
		this.vcoTriangle.stop();
		this.vcoPulse.stop();
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
