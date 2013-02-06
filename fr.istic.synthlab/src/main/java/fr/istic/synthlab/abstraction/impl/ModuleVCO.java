package fr.istic.synthlab.abstraction.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jsyn.unitgen.SawtoothOscillator;
import com.jsyn.unitgen.SineOscillator;
import com.jsyn.unitgen.SquareOscillator;
import com.jsyn.unitgen.TriangleOscillator;
import com.jsyn.unitgen.UnitGenerator;

import fr.istic.synthlab.abstraction.IInputPort;
import fr.istic.synthlab.abstraction.IModule;
import fr.istic.synthlab.abstraction.IOutputPort;
import fr.istic.synthlab.abstraction.IParameter;
import fr.istic.synthlab.abstraction.ISynthesizer;
import fr.istic.synthlab.abstraction.IWire;
import fr.istic.synthlab.factory.impl.PACFactory;

public class ModuleVCO implements IModule {

	public static final int INPUT_MOD_FREQ = 0;
	
	public static final int OUTPUT_SAWTOOTH = 3;
	public static final int OUTPUT_SINE = 2;
	public static final int OUTPUT_TRIANGLE = 1;
	public static final int OUTPUT_SQUARE = 0;

	public static final int PARAM_AMPLITUDE = 0;
	public static final int PARAM_FREQUENCY = 1;
	public static final int PARAM_WIDTH = 2;

	private static final String MODULE_NAME = "VCO";

	public static final String IN_MOD_FREQ = "Modulation";
	
	public static final String OUT_SINE_NAME = "Sine";
	public static final String OUT_SAWTOOTH_NAME = "SawTooth";
	public static final String OUT_SQUARE_NAME = "Square";
	public static final String OUT_TRIANGLE_NAME = "Triangle";
	
	public static final String AMPLITUDE_NAME = "Ampl";
	public static final String FREQUENCY_NAME = "Fn";

	private SquareOscillator vcoSquare;
	private TriangleOscillator vcoTriangle;
	private SineOscillator vcoSine;
	private SawtoothOscillator vcoSawtooth;

	private Map<Integer, IInputPort> inputs;
	private Map<Integer, IOutputPort> outputs;
	private Map<Integer, IParameter> params;

	private ISynthesizer parentSynth;

	public ModuleVCO(String name) {
		this.vcoSquare =  new SquareOscillator();
		this.vcoTriangle =  new TriangleOscillator();
		this.vcoSine =  new SineOscillator();
		this.vcoSawtooth =  new SawtoothOscillator();

		this.outputs = new HashMap<Integer, IOutputPort>();
		this.inputs = new HashMap<Integer, IInputPort>();
		this.params = new HashMap<Integer, IParameter>();
		
		this.inputs.put(INPUT_MOD_FREQ, PACFactory.getFactory().newInputPort(this, IN_MOD_FREQ, vcoTriangle.frequency));
		
		this.outputs.put(ModuleVCO.OUTPUT_SQUARE, PACFactory.getFactory().newOutputPort(this, OUT_SQUARE_NAME, vcoSquare.getOutput()));
		this.outputs.put(ModuleVCO.OUTPUT_TRIANGLE, PACFactory.getFactory().newOutputPort(this, OUT_TRIANGLE_NAME, vcoTriangle.getOutput()));
		this.outputs.put(ModuleVCO.OUTPUT_SINE, PACFactory.getFactory().newOutputPort(this, OUT_SINE_NAME, vcoSine.getOutput()));
		this.outputs.put(ModuleVCO.OUTPUT_SAWTOOTH, PACFactory.getFactory().newOutputPort(this, OUT_SAWTOOTH_NAME, vcoSawtooth.getOutput()));
		
		IParameter amplitude = PACFactory.getFactory().newParameter(this, AMPLITUDE_NAME, 0, 1, SineOscillator.DEFAULT_AMPLITUDE);
		amplitude.connect(vcoSquare.amplitude);
		amplitude.connect(vcoSine.amplitude);
		amplitude.connect(vcoSawtooth.amplitude);
		amplitude.connect(vcoTriangle.amplitude);
		this.params.put(ModuleVCO.PARAM_AMPLITUDE, amplitude);

		IParameter frequency = PACFactory.getFactory().newParameter(this, FREQUENCY_NAME,
				vcoSquare.frequency.getMinimum(),
				vcoSquare.frequency.getMaximum(),
				SineOscillator.DEFAULT_FREQUENCY);
		frequency.connect(vcoSquare.frequency);
		frequency.connect(vcoSine.frequency);
		frequency.connect(vcoSawtooth.frequency);
		frequency.connect(vcoTriangle.frequency);
		this.params.put(ModuleVCO.PARAM_FREQUENCY, frequency);
	}

	@Override
	public List<UnitGenerator> getJSyn() {
		List<UnitGenerator> generators = new ArrayList<UnitGenerator>();
		generators.add(vcoSquare);
		generators.add(vcoTriangle);
		generators.add(vcoSine);
		generators.add(vcoSawtooth);
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
		this.vcoSine.start();
		this.vcoSawtooth.start();
	}

	@Override
	public void stop() {
		this.vcoSquare.stop();
		this.vcoTriangle.stop();
		this.vcoSine.stop();
		this.vcoSawtooth.stop();
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

	@Override
	public List<IWire> getWires() {
		List<IWire> wires = new ArrayList<IWire>();
		for(IInputPort inputPort : inputs.values()){
			wires.add(inputPort.getWire());
		}
		for(IOutputPort outputPort : outputs.values()){
			wires.add(outputPort.getWire());
		}
		return wires;
	}

}
