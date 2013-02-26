package fr.istic.synthlab.abstraction.module.vca;

import java.util.ArrayList;
import java.util.List;

import com.jsyn.unitgen.PassThrough;
import com.jsyn.unitgen.UnitGenerator;

import fr.istic.synthlab.abstraction.filter.AmplitudeModulatorFilter;
import fr.istic.synthlab.abstraction.filter.AttenuationFilter;
import fr.istic.synthlab.abstraction.module.AModule;
import fr.istic.synthlab.abstraction.observer.Observer;
import fr.istic.synthlab.abstraction.port.IInputPort;
import fr.istic.synthlab.abstraction.port.IOutputPort;
import fr.istic.synthlab.abstraction.port.Port;
import fr.istic.synthlab.abstraction.synthesizer.ISynthesizer;
import fr.istic.synthlab.abstraction.util.Convert;
import fr.istic.synthlab.abstraction.wire.IWire;
import fr.istic.synthlab.factory.impl.PACFactory;

public class ModuleVCA extends AModule implements IModuleVCA, Observer<Port> {

	private static final String MODULE_NAME = "VCA";
	private static final String INPUT_NAME = "Input";
	private static final String INPUT_AM_NAME = "AM";
	private static final String OUTPUT_NAME = "Output";

	// Input & Output du module
	private IInputPort input, inputAm;
	private IOutputPort output;

	// Modulateur d'amplitude
	private AttenuationFilter attenuator;
	private AmplitudeModulatorFilter inputAmplitudeModulator;
	private PassThrough passThroughA, passThroughB;
	
	public ModuleVCA(ISynthesizer synth) {
		super(synth, MODULE_NAME);

		passThroughA = new PassThrough();
		passThroughB = new PassThrough();

		// Attenuateur de base
		this.attenuator = new AttenuationFilter();

		// Modulateur sur entrée Am
		this.inputAmplitudeModulator = new AmplitudeModulatorFilter(0);

		this.setAttenuation(0);
		
		this.input = PACFactory.getFactory().newInputPort(synth, this, INPUT_NAME, attenuator.input); // Ajout du port INPUT

		this.passThroughA.input.connect(attenuator.output);
		this.passThroughB.input.connect(passThroughA.output);
		
		this.inputAm = PACFactory.getFactory().newInputPort(synth, this, INPUT_AM_NAME, inputAmplitudeModulator.inputAm); // Ajout du port AM
		this.inputAm.addObserver(this);
		this.output = PACFactory.getFactory().newOutputPort(synth, this, OUTPUT_NAME, passThroughB.output); // Ajout du port OUTPUT
		addPort(input);
		addPort(inputAm);
		addPort(output);
	}

	@Override
	public List<UnitGenerator> getJSyn() {
		List<UnitGenerator> generators = new ArrayList<UnitGenerator>();
		generators.add(attenuator);
		generators.add(inputAmplitudeModulator);
		return generators;
	}

	@Override
	public void start() {
		this.attenuator.start();
		this.inputAmplitudeModulator.start();
	}

	@Override
	public void stop() {
		this.attenuator.stop();
		this.inputAmplitudeModulator.stop();
	}

	@Override
	public IInputPort getInput() {
		return input;
	}

	@Override
	public IInputPort getInputAM() {
		return inputAm;
	}

	@Override
	public IOutputPort getOutput() {
		return output;
	}

	@Override
	public List<IWire> getWires() {
		List<IWire> wires = new ArrayList<IWire>();
		if (input.isInUse())
			if (!wires.contains(input.getWire()))
				wires.add(input.getWire());

		if (inputAm.isInUse())
			if (!wires.contains(inputAm.getWire()))
				wires.add(inputAm.getWire());

		if (output.isInUse())
			if (!wires.contains(output.getWire()))
				wires.add(output.getWire());
		return wires;
	}

	@Override
	public void setAttenuation(double value) {
		getParameters().put("attenuation", (double) value);
		this.attenuator.setAttenuation(Convert.dB2V(value));
	}

	@Override
	public double getAttenuation() {
		return getParameter("attenuation");
	}

	@Override
	public void update(Port subject) {
		// If the FM input port send a connection event
		if (subject == inputAm) {
			// If it is in use
			if (inputAm.isInUse()) {
				passThroughB.input.disconnectAll();
				passThroughA.output.disconnectAll();
				passThroughA.output.connect(inputAmplitudeModulator.input);
				passThroughB.input.connect(inputAmplitudeModulator.output);
			} else {
				passThroughB.input.disconnectAll();
				passThroughA.output.disconnectAll();
				passThroughA.output.connect(passThroughB.input);
			}

		}
	}

}
