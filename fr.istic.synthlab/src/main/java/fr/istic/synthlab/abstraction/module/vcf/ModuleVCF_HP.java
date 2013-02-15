package fr.istic.synthlab.abstraction.module.vcf;

import java.util.ArrayList;
import java.util.List;

import com.jsyn.unitgen.FilterHighPass;
import com.jsyn.unitgen.PassThrough;
import com.jsyn.unitgen.UnitGenerator;

import fr.istic.synthlab.abstraction.filter.FrequencyModulatorFilter;
import fr.istic.synthlab.abstraction.module.AModule;
import fr.istic.synthlab.abstraction.observer.Observer;
import fr.istic.synthlab.abstraction.port.IInputPort;
import fr.istic.synthlab.abstraction.port.IOutputPort;
import fr.istic.synthlab.abstraction.port.Port;
import fr.istic.synthlab.abstraction.synthesizer.ISynthesizer;
import fr.istic.synthlab.abstraction.wire.IWire;
import fr.istic.synthlab.factory.IFactory;
import fr.istic.synthlab.factory.impl.PACFactory;

public class ModuleVCF_HP extends AModule implements IModuleVCF, Observer<Port> {

	private static final String MODULE_NAME = "VCF HP24";
	private static final String IN_NAME = "Input";
	private static final String IN_MOD_FREQ_NAME = "FM";
	private static final String OUT_NAME = "Output";

	// FM Perso
	private FrequencyModulatorFilter frequencyModulator;

	// PassThrough
	private PassThrough passThrough;

	// Input & Output du module
	private IInputPort input;
	private IInputPort fm;
	private IOutputPort output;

	// Filtres de fréquence JSyn
	private FilterHighPass filterJSyn1;
	private FilterHighPass filterJSyn2;

	public ModuleVCF_HP(ISynthesizer synth) {
		super(synth, MODULE_NAME);
		System.out.println("ModuleVCFA HP24 initialized");

		// Création des filtres JSyn
		this.filterJSyn1 = new FilterHighPass();
		this.filterJSyn2 = new FilterHighPass();

		// Conection des filtres JSyn
		this.filterJSyn1.output.connect(this.filterJSyn2.input);

		// Création du modulateur de fréquence
		this.frequencyModulator = new FrequencyModulatorFilter(1000);

		// Création d'un module distribuant la nouvelle fréquence
		this.passThrough = new PassThrough();

		// Connexion du modulateur au PassThrough
		frequencyModulator.output.connect(passThrough.input);

		// Création d'un port d'entrée sur le générateur perso
		this.fm = PACFactory.getFactory().newInputPort(synth, this, IN_MOD_FREQ_NAME,
				frequencyModulator.input);
		this.fm.addObserver(this);

		// Création des ports d'entrée sur le filtre JSyn 1
		this.input = PACFactory.getFactory().newInputPort(synth, this, IN_NAME,
				filterJSyn1.input);

		// Création du port de sortie sur le filtre JSyn 2
		this.output = PACFactory.getFactory().newOutputPort(synth, this, OUT_NAME,
				filterJSyn2.output);

		// Valeur par defaut
		this.setCutFrequency(1000);
		this.setResonance(1);

		// Set de la frequence de coupure de base
		filterJSyn1.frequency.set(getCutFrequency());
		filterJSyn2.frequency.set(getCutFrequency());
		addPort(input);
		addPort(fm);
		addPort(output);
	}

	@Override
	public List<UnitGenerator> getJSyn() {
		List<UnitGenerator> generators = new ArrayList<UnitGenerator>();
		generators.add(filterJSyn1);
		generators.add(filterJSyn2);
		generators.add(frequencyModulator);
		generators.add(passThrough);
		return generators;
	}

	@Override
	public void start() {
		this.filterJSyn1.start();
		this.filterJSyn2.start();
		this.frequencyModulator.start();
		this.passThrough.start();
	}

	@Override
	public void stop() {
		this.filterJSyn1.stop();
		this.filterJSyn2.stop();
		this.frequencyModulator.stop();
		this.passThrough.stop();
	}

	@Override
	public void setCutFrequency(int value) {
		getParameters().put("cutFrequency", (double) value);
		updateFrequency();
	}

	@Override
	public int getCutFrequency() {
		return getParameter("cutFrequency").intValue();
	}

	@Override
	public void setResonance(double value) {
		getParameters().put("resonance", (double) value);
		this.filterJSyn1.Q.set(value);
		this.filterJSyn2.Q.set(value);
	}

	@Override
	public double getResonance() {
		return getParameter("resonance");
	}

	private void updateFrequency() {
		this.frequencyModulator.setBaseFrequency(getCutFrequency());
		if (!getInputFm().isInUse()) {
			filterJSyn1.frequency.set(getCutFrequency());
			filterJSyn2.frequency.set(getCutFrequency());
		}
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

	@Override
	public List<IWire> getWires() {
		List<IWire> wires = new ArrayList<IWire>();
		if (input.getWire() != null) {
			if (!wires.contains(input.getWire()))
				wires.add(input.getWire());
		}
		if (fm.getWire() != null) {
			if (!wires.contains(fm.getWire()))
				wires.add(fm.getWire());
		}
		if (output.getWire() != null) {
			if (!wires.contains(output.getWire()))
				wires.add(output.getWire());
		}
		return wires;

	}

	@Override
	public void update(Port subject) {
		// Lors d'un event sur le port d'entrée
		if (subject == fm) {
			// En cas de connection
			if (fm.isInUse()) {
				// Connect the frequency modulator to the output
				passThrough.output.connect(filterJSyn1.frequency);
				passThrough.output.connect(filterJSyn2.frequency);

			} else {
				// En cas de déconnection
				passThrough.output.disconnectAll();
				filterJSyn1.frequency.set(getCutFrequency());
				filterJSyn2.frequency.set(getCutFrequency());
			}

		}
	}
}
