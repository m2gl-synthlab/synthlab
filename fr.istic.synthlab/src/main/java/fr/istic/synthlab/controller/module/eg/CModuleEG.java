package fr.istic.synthlab.controller.module.eg;

import fr.istic.synthlab.abstraction.module.eg.ModuleEG;
import fr.istic.synthlab.abstraction.synthesizer.ISynthesizer;
import fr.istic.synthlab.abstraction.wire.IWire;
import fr.istic.synthlab.factory.impl.PACFactory;
import fr.istic.synthlab.presentation.module.eg.IPModuleEG;

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
		setDecay(decay);
		pres.c2pSetDecayValue(getDecay());
	}

	@Override
	public void p2cSustainChanged(double sustain) {
		setSustain(sustain);
		pres.c2pSetSustainValue(getSustain());
	}

	@Override
	public void p2cReleaseChanged(double release) {
		setRelease(release);
		pres.c2pSetReleaseValue(getRelease());
	}

	@Override
	public void p2cClosing() {
		for(IWire w : this.getWires()){
			w.disconnect();
		}
	}
	
	
	@Override
	public void setParameter(String key, Double value){
		if(key.equals("attackTime")){
			p2cAttackChanged(value);
		} else if (key.equals("decayTime")){
			p2cDecayChanged(value);
		} else if (key.equals("substainTime")){
			p2cSustainChanged(value);
		}else if (key.equals("releaseTime")){
			p2cReleaseChanged(value);
		}
	}

}
