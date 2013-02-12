package fr.istic.synthlab.abstraction.module.vca;

import java.util.ArrayList;
import java.util.List;

import com.jsyn.unitgen.PassThrough;
import com.jsyn.unitgen.UnitGenerator;

import fr.istic.synthlab.abstraction.filter.AmplitudeModulatorFilter;
import fr.istic.synthlab.abstraction.filter.AttenuationFilter;
import fr.istic.synthlab.abstraction.module.AModule;
import fr.istic.synthlab.abstraction.port.IInputPort;
import fr.istic.synthlab.abstraction.port.IOutputPort;
import fr.istic.synthlab.abstraction.synthesizer.ISynthesizer;
import fr.istic.synthlab.abstraction.util.Convert;
import fr.istic.synthlab.abstraction.wire.IWire;
import fr.istic.synthlab.factory.impl.PACFactory;

public class ModuleVCA extends AModule implements IModuleVCA {

	private static final String MODULE_NAME = "VCA";
	private static final String INPUT_NAME = "Input";
	private static final String INPUT_AM_NAME = "AM";
	private static final String OUTPUT_NAME = "Output";
	
	public static final String PARAM_AMPLITUDE_NAME = "Gain";

	// Amplitude de base
//	private double baseAmplitude;
	
	// Input & Output du module
	private IInputPort input, inputAm;
	private IOutputPort output;
	
	// Modulateur d'amplitude 
	private AttenuationFilter attenuator;
	private AmplitudeModulatorFilter inputAmplitudeModulator;
//	private PassThrough passThrough;
	
	public ModuleVCA(ISynthesizer synth) {
		super(MODULE_NAME, synth);

		
//		this.passThrough = new PassThrough();
//		this.baseAmplitude = 0;
		
		// Attenuateur de base
		this.attenuator = new AttenuationFilter();
		this.attenuator.setAttenuation(0);
		
		// Modulateur sur entrée Am
		this.inputAmplitudeModulator = new AmplitudeModulatorFilter(0);
		
		this.attenuator.setAttenuation(getAttenuation());
		this.input = PACFactory.getFactory().newInputPort(this, INPUT_NAME, attenuator.input); // Ajout du port INPUT

		this.inputAmplitudeModulator.input.connect(attenuator.output);
		this.inputAm = PACFactory.getFactory().newInputPort(this, INPUT_AM_NAME, inputAmplitudeModulator.inputAm); // Ajout du port AM
		this.output = PACFactory.getFactory().newOutputPort(this, OUTPUT_NAME, inputAmplitudeModulator.output); // Ajout du port OUTPUT
		
	}

	@Override
	public List<UnitGenerator> getJSyn() {
		List<UnitGenerator> generators = new ArrayList<UnitGenerator>();
		generators.add(attenuator);
		generators.add(inputAmplitudeModulator);
		return generators;
	}

	@Override
	public String getName() {
		return MODULE_NAME;
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
		if(input.isInUse())
			wires.add(input.getWire());

		if(inputAm.isInUse())
			wires.add(inputAm.getWire());

		if(output.isInUse())
			wires.add(output.getWire());
		return wires;
	}

	@Override
	public void setAttenuation(double dbValue) { // dB
//		this.baseAmplitude = dbValue;
		this.attenuator.setAttenuation(Convert.dB2V(dbValue));
	}

	@Override
	public double getAttenuation() {
		return Convert.v2Db(attenuator.getAttenuation());
	}

}
