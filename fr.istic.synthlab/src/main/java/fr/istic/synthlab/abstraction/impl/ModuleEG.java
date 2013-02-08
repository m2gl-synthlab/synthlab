package fr.istic.synthlab.abstraction.impl;

import java.util.ArrayList;
import java.util.List;

import com.jsyn.unitgen.EnvelopeDAHDSR;
import com.jsyn.unitgen.UnitGenerator;

import fr.istic.synthlab.abstraction.IInputPort;
import fr.istic.synthlab.abstraction.IModuleEG;
import fr.istic.synthlab.abstraction.IOutputPort;
import fr.istic.synthlab.abstraction.ISynthesizer;
import fr.istic.synthlab.abstraction.IWire;
import fr.istic.synthlab.factory.impl.PACFactory;

/**
 * EG Module
 */
public class ModuleEG extends AModule implements IModuleEG {

	private static final String MODULE_NAME = "EG";
	private static final String IN_NAME = "Gate";
	private static final String OUT_NAME = "Out";

	private IInputPort gate;
	private IOutputPort out;

	private double attack; // Time in seconds for the rising stage of the
							// envelope to go from 0.0 to 1.0.
	private double decay; // Time in seconds for the falling stage to go from 0
							// dB to -96 dB.
	private double sustain; // Level for the sustain stage.
	private double release; // Time in seconds to go from 0 dB to -96 dB.

	private EnvelopeDAHDSR adsr;

	public ModuleEG(ISynthesizer synth) {
		super(MODULE_NAME, synth);

		adsr = new EnvelopeDAHDSR();

		this.gate = PACFactory.getFactory().newInputPort(this, IN_NAME,
				adsr.input);
		this.out = PACFactory.getFactory().newOutputPort(this, OUT_NAME,
				adsr.output);

		setAttack(adsr.attack.get());
		setDecay(adsr.decay.get());
		setRelease(adsr.release.get());
		setSustain(adsr.sustain.get());

	}

	@Override
	public void setAttack(double attackTime) {
		this.attack = attackTime;
		adsr.attack.set(attackTime);
	}

	@Override
	public double getAttack() {
		return this.attack;
	}

	@Override
	public void setDecay(double decayTime) {
		this.decay = decayTime;
		adsr.decay.set(decayTime);
	}

	@Override
	public double getDecay() {
		return this.decay;
	}

	@Override
	public void setSustain(double substainTime) {
		this.sustain = substainTime;
		adsr.sustain.set(substainTime);
	}

	@Override
	public double getSustain() {
		return this.sustain;
	}

	@Override
	public void setRelease(double releaseTime) {
		this.release = releaseTime;
		adsr.release.set(releaseTime);
	}

	@Override
	public double getRelease() {
		return this.release;
	}

	@Override
	public IInputPort getGateInput() {
		return this.gate;
	}

	@Override
	public IOutputPort getOutput() {
		return this.out;
	}

	@Override
	public List<UnitGenerator> getJSyn() {
		List<UnitGenerator> generators = new ArrayList<UnitGenerator>();
		generators.add(adsr);
		return generators;
	}

	@Override
	public void start() {
		adsr.start();
	}

	@Override
	public void stop() {
		adsr.stop();
	}

	@Override
	public List<IWire> getWires() {
		List<IWire> wires = new ArrayList<IWire>();
		if (gate.getWire() != null) {
			wires.add(gate.getWire());
		}
		if (out.getWire() != null) {
			wires.add(out.getWire());
		}
		return wires;
	}

}