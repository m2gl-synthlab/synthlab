package fr.istic.synthlab.abstraction.impl;

import java.util.ArrayList;
import java.util.List;

import com.jsyn.unitgen.FilterLowPass;
import com.jsyn.unitgen.UnitGenerator;

import fr.istic.synthlab.abstraction.IInputPort;
import fr.istic.synthlab.abstraction.IModuleVCF;
import fr.istic.synthlab.abstraction.IOutputPort;
import fr.istic.synthlab.abstraction.ISynthesizer;
import fr.istic.synthlab.abstraction.IWire;
import fr.istic.synthlab.factory.impl.PACFactory;

public class ModuleVCF extends AModule implements IModuleVCF {

	private static final String MODULE_NAME = "VCF";
	private static final String IN_NAME = "Input";
	private static final String IN_MOD_FREQ_NAME = "FM";
	private static final String OUT_NAME = "Output";

	public static final String PARAM_CUT_FREQUENCY_NAME = "Cut Frequency";
	public static final String PARAM_RESONANCE_NAME = "Resonance";

	// Filtre Perso
//	private Filter filter;

	// Input & Output du module
	private IInputPort input;
	private IInputPort fm;
	private IOutputPort output;

	// Filtre de fréquence JSyn
	private FilterLowPass filterJSyn;

	public ModuleVCF(ISynthesizer synth) {
		super(MODULE_NAME, synth);
		System.out.println("ModuleVCF initialized");

		// Création du filtre JSyn
		this.filterJSyn = new FilterLowPass();

		// Création du générateur perso
//		this.filter = new Filter();

		// Création des ports d'entrée sur le filtre perso
		this.input = PACFactory.getFactory().newInputPort(this, IN_NAME,
				filterJSyn.input);
		this.fm = PACFactory.getFactory().newInputPort(this, IN_MOD_FREQ_NAME,
				filterJSyn.Q);

		// Création du port de sortie sur le filtre perso
		this.output = PACFactory.getFactory().newOutputPort(this, OUT_NAME,
				filterJSyn.output);
	}

	@Override
	public List<UnitGenerator> getJSyn() {
		List<UnitGenerator> generators = new ArrayList<UnitGenerator>();
//		generators.add(filter);
		generators.add(filterJSyn);
		return generators;
	}

	@Override
	public void start() {
//		this.filter.start();
		this.filterJSyn.start();
	}

	@Override
	public void stop() {
//		this.filter.stop();
		this.filterJSyn.start();
	}

	@Override
	public void setCutFrequency(double value) {
		this.filterJSyn.frequency.set(value);
	}

	@Override
	public double getCutFrequency() {
		return this.filterJSyn.frequency.get();
	}

	@Override
	public void setResonance(double value) {
//		this.filter.resonance = value;
	}

	@Override
	public double getResonance() {
//		return this.filter.resonance;
		return 0;
	}

	@Override
	public IInputPort getInput() {
		return input;
	}

	@Override
	public IInputPort getInputFm() {
		return fm;
	}

	@Override
	public IOutputPort getOutput() {
		return output;
	}

//	private class Filter extends UnitFilter {
//		double cutFrequency = 0.0; // Value between 0 and 32768
//		double resonance = 0.0; // Value between 0 and 100
//
//		// Input & Output du filtre perso
//		UnitInputPort input = new UnitInputPort(IN_NAME);
//		UnitInputPort fm = new UnitInputPort(IN_MOD_FREQ_NAME);
//		UnitOutputPort output = new UnitOutputPort(OUT_NAME);
//
//		public Filter() {
//			this.addPort(input);
//			this.addPort(fm);
//			this.addPort(output);
//		}
//
//		@Override
//		public void generate(int start, int limit) {
//			// Get signal arrays from ports.
//			double[] inputs = this.input.getValues();
//			double[] fm = this.fm.getValues();
//			double[] outputs = this.output.getValues();
//
//			for (int i = start; i < limit; i++) {
//				// Valeur du port d'entrée
//				double x = inputs[i];
//				// Calcul de la fréquence de coupure
//				double frequency = Math.pow(2, cutFrequency + x);
//				// Application du filtre
//				System.out.println(x);
//				if (x >= frequency) {
//					outputs[i] = 0;// Math.pow((24/20) + Math.log(x), 2);
//				} else {
//					outputs[i] = x;
//				}
//
//			}
//		}
//
//	}

	@Override
	public List<IWire> getWires() {
		List<IWire> wires = new ArrayList<IWire>();
		if (input.getWire() != null) {
			wires.add(input.getWire());
		}
		if (fm.getWire() != null) {
			wires.add(fm.getWire());
		}
		if (output.getWire() != null) {
			wires.add(output.getWire());
		}
		return wires;

	}
}
