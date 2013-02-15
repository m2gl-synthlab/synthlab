package fr.istic.synthlab.controller.module.eg;

import fr.istic.synthlab.abstraction.module.eg.ModuleEG;
import fr.istic.synthlab.abstraction.synthesizer.ISynthesizer;
import fr.istic.synthlab.abstraction.wire.IWire;
import fr.istic.synthlab.controller.module.ICModule;
import fr.istic.synthlab.controller.synthesizer.ICSynthesizer;
import fr.istic.synthlab.factory.impl.PACFactory;
import fr.istic.synthlab.presentation.module.eg.IPModuleEG;
import fr.istic.synthlab.presentation.synthesizer.IPSynthesizer;

/**
 * Controller for the EG module
 */
public class CModuleEG extends ModuleEG implements ICModuleEG {

	private IPModuleEG pres;
	private ISynthesizer cSynthesizer;

	public CModuleEG(ISynthesizer cSynthesizer) {
		super(cSynthesizer);
		this.cSynthesizer = cSynthesizer;
		this.pres = PACFactory.getPFactory().newEG(this);
	}

	@Override
	public IPModuleEG getPresentation() {
		return this.pres;
	}

	private void changeAttack(double attack) {
		setAttack(attack);
		pres.c2pSetAttackValue(getAttack());
	}

	private void changeDecay(double decay) {
		setDecay(decay);
		pres.c2pSetDecayValue(getDecay());
	}

	private void changeSustain(double sustain) {
		setSustain(sustain);
		pres.c2pSetSustainValue(getSustain());
	}

	private void changeRelease(double release) {
		setRelease(release);
		pres.c2pSetReleaseValue(getRelease());
	}

	@Override
	public void p2cAttackChanged(double attack) {
		changeAttack(attack);
	}

	@Override
	public void p2cDecayChanged(double decay) {
		changeDecay(decay);
	}

	@Override
	public void p2cSustainChanged(double sustain) {
		changeSustain(sustain);
	}

	@Override
	public void p2cReleaseChanged(double release) {
		changeRelease(release);
	}

	@Override
	public void p2cClosing() {
		for (IWire w : this.getWires()) {
			w.disconnect();
		}
	}

	@Override
	public void setParameter(String key, Double value) {
		if (key.equals("attackTime")) {
			changeAttack(value);
		} else if (key.equals("decayTime")) {
			changeDecay(value);
		} else if (key.equals("substainTime")) {
			changeSustain(value);
		} else if (key.equals("releaseTime")) {
			changeRelease(value);
		}
	}
	
	@Override
	public IPSynthesizer getSynthesizerPresentation() {
		return ((ICSynthesizer)cSynthesizer).getPresentation();
	}
	

	@Override
	public void p2cRemoveModule(ICModule module) {
		((ICSynthesizer) cSynthesizer).p2cRemoveModule(module);
	}

}
