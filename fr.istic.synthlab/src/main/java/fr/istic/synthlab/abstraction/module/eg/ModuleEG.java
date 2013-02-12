package fr.istic.synthlab.abstraction.module.eg;

import java.util.ArrayList;
import java.util.List;

import com.jsyn.unitgen.EnvelopeDAHDSR;
import com.jsyn.unitgen.UnitGenerator;

import fr.istic.synthlab.abstraction.module.AModule;
import fr.istic.synthlab.abstraction.port.IInputPort;
import fr.istic.synthlab.abstraction.port.IOutputPort;
import fr.istic.synthlab.abstraction.synthesizer.ISynthesizer;
import fr.istic.synthlab.abstraction.wire.IWire;
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

	private EnvelopeDAHDSR adsr;

	public ModuleEG(ISynthesizer synth) {
		super(MODULE_NAME, synth);

		adsr = new EnvelopeDAHDSR();

		this.gate = PACFactory.getFactory().newInputPort(this, IN_NAME,	adsr.input);
		this.out = PACFactory.getFactory().newOutputPort(this, OUT_NAME, adsr.output);

		addPort(gate);
		addPort(out);
	}

	@Override
	public void setAttack(double attackTime) {
		setParameter("attackTime", attackTime);
		adsr.attack.set(attackTime);
	}

	@Override
	public double getAttack() {
		return adsr.attack.get();
	}

	@Override
	public void setDecay(double decayTime) {
		setParameter("decayTime", decayTime);
		adsr.decay.set(decayTime);
	}

	@Override
	public double getDecay() {
		return adsr.decay.get();
	}

	@Override
	public void setSustain(double substainTime) {
		setParameter("substainTime", substainTime);
		adsr.sustain.set(substainTime);
	}

	@Override
	public double getSustain() {
		return adsr.sustain.get();
	}

	@Override
	public void setRelease(double releaseTime) {
		setParameter("releaseTime", releaseTime);
		adsr.release.set(releaseTime);
	}

	@Override
	public double getRelease() {
		return adsr.release.get();
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