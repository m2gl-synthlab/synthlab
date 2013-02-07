package fr.istic.synthlab.controller.impl;

import fr.istic.synthlab.abstraction.ISynthesizer;
import fr.istic.synthlab.abstraction.impl.ModuleEG;
import fr.istic.synthlab.controller.ICModuleEG;
import fr.istic.synthlab.factory.impl.PACFactory;
import fr.istic.synthlab.presentation.IPModuleEG;

/**
 * Controller for the EG module
 */
public class CModuleEG extends ModuleEG implements ICModuleEG {

	private IPModuleEG pres;


	public CModuleEG(ISynthesizer synth) {
		super(synth);
		this.pres = PACFactory.getPFactory().newEG(this);
	}

	@Override
	public IPModuleEG getPresentation() {
		return this.pres;
	}

	@Override
	public void p2cAttackChanged(double attack) {
		setAttack(attack);
		pres.c2pSetAttackValue(getAttack());
	}

	@Override
	public void p2cDecayChanged(double decay) {
		setAttack(decay);
		pres.c2pSetDecayValue(getDecay());
	}

	@Override
	public void p2cSustainChanged(double sustain) {
		setAttack(sustain);
		pres.c2pSetSustainValue(getSustain());
	}

	@Override
	public void p2cReleaseChanged(double release) {
		setAttack(release);
		pres.c2pSetReleaseValue(getRelease());
	}

	@Override
	public void p2cClosing() {
		System.out.println("Closing not implemented");
	}

}
