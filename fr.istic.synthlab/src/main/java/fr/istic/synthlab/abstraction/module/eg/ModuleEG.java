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
		super(synth, MODULE_NAME);

		adsr = new EnvelopeDAHDSR();

		this.gate = PACFactory.getFactory().newInputPort(synth, this, IN_NAME,	adsr.input);
		this.out = PACFactory.getFactory().newOutputPort(synth, this, OUT_NAME, adsr.output);

		addPort(gate);
		addPort(out);
	}

	@Override
	public void setAttack(double attackTime) {
		if (attackTime < 0.0){
			attackTime = 0.0;
		}
		getParameters().put("attackTime", attackTime);
		adsr.attack.set(attackTime);
	}

	@Override
	public double getAttack() {
		return getParameter("attackTime");
	}

	@Override
	public void setDecay(double decayTime) {
		if (decayTime < 0.0){
			decayTime = 0.0;
		}
		getParameters().put("decayTime", decayTime);
		adsr.decay.set(decayTime);
	}

	@Override
	public double getDecay() {
		return getParameter("decayTime");
	}

	@Override
	public void setSustain(double sustainValue) {
		if (sustainValue < 0.0){
			sustainValue = 0.0;
		}
		getParameters().put("sustainValue", sustainValue);
		adsr.sustain.set(sustainValue/5);
	}

	@Override
	public double getSustain() {
		return getParameter("sustainValue");
	}

	@Override
	public void setRelease(double releaseTime) {
		if (releaseTime < 0.0){
			releaseTime = 0.0;
		}
		getParameters().put("releaseTime", releaseTime);
		adsr.release.set(releaseTime);
	}

	@Override
	public double getRelease() {
		return getParameter("releaseTime");
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
			if (!wires.contains(gate.getWire()))
				wires.add(gate.getWire());
		}
		if (out.getWire() != null) {
			if (!wires.contains(out.getWire()))
				wires.add(out.getWire());
		}
		return wires;
	}

}