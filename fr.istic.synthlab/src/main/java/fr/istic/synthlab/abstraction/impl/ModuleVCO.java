package fr.istic.synthlab.abstraction.impl;

import java.util.ArrayList;
import java.util.List;

import com.jsyn.ports.UnitInputPort;
import com.jsyn.ports.UnitOutputPort;
import com.jsyn.unitgen.SawtoothOscillator;
import com.jsyn.unitgen.SineOscillator;
import com.jsyn.unitgen.SquareOscillator;
import com.jsyn.unitgen.TriangleOscillator;
import com.jsyn.unitgen.UnitGenerator;

import fr.istic.synthlab.abstraction.IInputPort;
import fr.istic.synthlab.abstraction.IModuleVCO;
import fr.istic.synthlab.abstraction.IOutputPort;
import fr.istic.synthlab.abstraction.ISynthesizer;
import fr.istic.synthlab.abstraction.IWire;
import fr.istic.synthlab.command.ICommand;
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
	
	// Générateur Perso
	private FrequencyGenerator frequencyGen;

	// Input & Output du module
	private IInputPort fm;
	private IOutputPort outputSquare;
	private IOutputPort outputTriangle;
	private IOutputPort outputSine;
	private IOutputPort outputSawtooth;

	// Générateurs de fréquence JSyn
	private SquareOscillator vcoSquare;
	private TriangleOscillator vcoTriangle;
	private SineOscillator vcoSine;
	private SawtoothOscillator vcoSawtooth;
	
	// Valeur de la fréquence
	private double frequency;
	private ICommand displayFrequencyCmd;

	public ModuleVCO(ISynthesizer synth) {
		super(MODULE_NAME, synth);
		System.out.println("ModuleVCO initialized");
		
		// Création des générateurs JSyn
		this.vcoSquare = new SquareOscillator();
		this.vcoTriangle = new TriangleOscillator();
		this.vcoSine = new SineOscillator();
		this.vcoSawtooth = new SawtoothOscillator();

		// Création du générateur perso
		this.frequencyGen = new FrequencyGenerator();

		// Création d'un port d'entrée sur le générateur perso
		this.fm = PACFactory.getFactory().newInputPort(this, IN_MOD_FREQ_NAME,
				frequencyGen.input);

		// Création des ports de sortie des générateurs JSsyn
		this.outputSquare = PACFactory.getFactory().newOutputPort(this,
				OUT_SQUARE_NAME, vcoSquare.output);
		this.outputTriangle = PACFactory.getFactory().newOutputPort(this,
				OUT_TRIANGLE_NAME, vcoTriangle.output);
		this.outputSine = PACFactory.getFactory().newOutputPort(this,
				OUT_SINE_NAME, vcoSine.output);
		this.outputSawtooth = PACFactory.getFactory().newOutputPort(this,
				OUT_SAWTOOTH_NAME, vcoSawtooth.output);

		// Connection du générateur perso aux générateurs JSyn
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
	public double getFrequency() {
		return frequency;
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

	private class FrequencyGenerator extends UnitGenerator {
		int octave = 0; // Value between 0 and 14
		double tone = 0.0; // Value between -1.0 and 1.0

		// Input & Output du générateur perso
		UnitInputPort input = new UnitInputPort(IN_MOD_FREQ_NAME);
		UnitOutputPort outputSquare = new UnitOutputPort(OUT_SQUARE_NAME);
		UnitOutputPort outputTriangle = new UnitOutputPort(OUT_TRIANGLE_NAME);
		UnitOutputPort outputSine = new UnitOutputPort(OUT_SINE_NAME);
		UnitOutputPort outputSawtooth = new UnitOutputPort(OUT_SAWTOOTH_NAME);

		public FrequencyGenerator() {
			this.addPort(input);
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
				// Valeur du port d'entrée
				double x = inputs[i];
				// Calcul de la fréquence
				frequency = Math.pow(2,octave+tone+x);
				// Application de la fréquence sur les ports de sortie
				outputsSquare[i] = frequency;
				outputsTriangle[i] = frequency;
				outputsSine[i] = frequency;
				outputsSawtooth[i] = frequency;
				System.out.println(x);
			}
			
			if (displayFrequencyCmd != null)
				displayFrequencyCmd.execute();
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

	@Override
	public void setDisplayFrequencyCmd(ICommand cmd) {
		this.displayFrequencyCmd = cmd;
	}
}
