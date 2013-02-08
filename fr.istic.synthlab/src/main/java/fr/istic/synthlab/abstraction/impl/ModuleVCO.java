package fr.istic.synthlab.abstraction.impl;

import java.util.ArrayList;
import java.util.List;

import com.jsyn.ports.UnitOutputPort;
import com.jsyn.unitgen.SawtoothOscillator;
import com.jsyn.unitgen.SineOscillator;
import com.jsyn.unitgen.SquareOscillator;
import com.jsyn.unitgen.TriangleOscillator;
import com.jsyn.unitgen.UnitFilter;
import com.jsyn.unitgen.UnitGenerator;

import fr.istic.synthlab.abstraction.IInputPort;
import fr.istic.synthlab.abstraction.IModuleVCO;
import fr.istic.synthlab.abstraction.IOutputPort;
import fr.istic.synthlab.abstraction.ISynthesizer;
import fr.istic.synthlab.abstraction.IWire;
import fr.istic.synthlab.factory.impl.PACFactory;

public class ModuleVCO extends AModule implements IModuleVCO {

	private static final String MODULE_NAME = "VCO";
	private static final String IN_MOD_FREQ_NAME = "fm";
	private static final String OUT_SINE_NAME = "Sine";
	private static final String OUT_SAWTOOTH_NAME = "SawTooth";
	private static final String OUT_SQUARE_NAME = "Square";
	private static final String OUT_TRIANGLE_NAME = "Triangle";
	
	public static final String PARAM_OCTAVE_NAME = "Octave";
	public static final String PARAM_TONE_NAME = "Tone";
	
	public static final double DEFAULT_FREQUENCY_MIN = 800; // 800 Hz

	private FrequencyGenerator frequencyGen;

	private IInputPort fm;
	private IOutputPort outputSquare;
	private IOutputPort outputTriangle;
	private IOutputPort outputSine;
	private IOutputPort outputSawtooth;

	private SquareOscillator vcoSquare;
	private TriangleOscillator vcoTriangle;
	private SineOscillator vcoSine;
	private SawtoothOscillator vcoSawtooth;

	public ModuleVCO(ISynthesizer synth) {
		super(MODULE_NAME, synth);
		System.out.println("ModuleVCO initialized");

		this.vcoSquare = new SquareOscillator();
		this.vcoTriangle = new TriangleOscillator();
		this.vcoSine = new SineOscillator();
		this.vcoSawtooth = new SawtoothOscillator();

		this.frequencyGen = new FrequencyGenerator();

		this.fm = PACFactory.getFactory().newInputPort(this, IN_MOD_FREQ_NAME,
				frequencyGen.input);

		this.outputSquare = PACFactory.getFactory().newOutputPort(this,
				OUT_SQUARE_NAME, vcoSquare.output);
		this.outputTriangle = PACFactory.getFactory().newOutputPort(this,
				OUT_TRIANGLE_NAME, vcoTriangle.output);
		this.outputSine = PACFactory.getFactory().newOutputPort(this,
				OUT_SINE_NAME, vcoSine.output);
		this.outputSawtooth = PACFactory.getFactory().newOutputPort(this,
				OUT_SAWTOOTH_NAME, vcoSawtooth.output);

		frequencyGen.outputSquare.connect(vcoSquare.frequency);
		frequencyGen.outputTriangle.connect(vcoTriangle.frequency);
		frequencyGen.outputSine.connect(vcoSine.frequency);
		frequencyGen.outputSawtooth.connect(vcoSawtooth.frequency);
	}

	@Override
	public List<UnitGenerator> getJSyn() {
		List<UnitGenerator> generators = new ArrayList<UnitGenerator>();
		generators.add(vcoSquare);
		generators.add(vcoTriangle);
		generators.add(vcoSine);
		generators.add(vcoSawtooth);
		generators.add(frequencyGen);
		return generators;
	}

	@Override
	public void start() {
		this.vcoSquare.start();
		this.vcoTriangle.start();
		this.vcoSine.start();
		this.vcoSawtooth.start();
		this.frequencyGen.start();
	}

	@Override
	public void stop() {
		this.vcoSquare.stop();
		this.vcoTriangle.stop();
		this.vcoSine.stop();
		this.vcoSawtooth.stop();
		this.frequencyGen.stop();
	}

	@Override
	public void setOctave(int value) {
		this.frequencyGen.octave = value;
	}

	@Override
	public int getOctave() {
		return this.frequencyGen.octave;
	}

	@Override
	public void setTone(double value) {
		this.frequencyGen.tone = value;
	}

	@Override
	public double getTone() {
		return this.frequencyGen.tone;
	}
	
	@Override
	public IInputPort getInputFm() {
		return fm;
	}

	@Override
	public IOutputPort getOutputSquare() {
		return outputSquare;
	}

	@Override
	public IOutputPort getOutputTriangle() {
		return outputTriangle;
	}

	@Override
	public IOutputPort getOutputSine() {
		return outputSine;
	}

	@Override
	public IOutputPort getOutputSawtooth() {
		return outputSawtooth;
	}

	private class FrequencyGenerator extends UnitFilter {
		int octave = 0; // Value between -5 and 5
		double tone = 0.0; // Value between -1.0 and 1.0

		UnitOutputPort outputSquare = new UnitOutputPort(OUT_SQUARE_NAME);
		UnitOutputPort outputTriangle = new UnitOutputPort(OUT_TRIANGLE_NAME);
		UnitOutputPort outputSine = new UnitOutputPort(OUT_SINE_NAME);
		UnitOutputPort outputSawtooth = new UnitOutputPort(OUT_SAWTOOTH_NAME);

		public FrequencyGenerator() {
			this.addPort(outputSquare);
			this.addPort(outputTriangle);
			this.addPort(outputSine);
			this.addPort(outputSawtooth);
		}
		
		@Override
		public void generate(int start, int limit) {
			// Get signal arrays from ports.
			double[] inputs = input.getValues();
			double[] outputsSquare = outputSquare.getValues();
			double[] outputsTriangle = outputTriangle.getValues();
			double[] outputsSine = outputSine.getValues();
			double[] outputsSawtooth = outputSawtooth.getValues();

			for (int i = start; i < limit; i++) {
				double x = inputs[i];
				double frequency = Math.pow(2,octave+tone+x) * DEFAULT_FREQUENCY_MIN;
				outputsSquare[i] = frequency;
				outputsTriangle[i] = frequency;
				outputsSine[i] = frequency;
				outputsSawtooth[i] = frequency;
			}
		}

	}

	@Override
	public List<IWire> getWires() {
		List<IWire> wires = new ArrayList<IWire>();
		if (fm.getWire() != null) {
			wires.add(fm.getWire());
		}
		if (outputSquare.getWire() != null) {
			wires.add(outputSquare.getWire());
		}
		if (outputTriangle.getWire() != null) {
			wires.add(outputTriangle.getWire());
		}
		if (outputSine.getWire() != null) {
			wires.add(outputSine.getWire());
		}
		if (outputSawtooth.getWire() != null) {
			wires.add(outputSawtooth.getWire());
		}
		return wires;
	}

}
