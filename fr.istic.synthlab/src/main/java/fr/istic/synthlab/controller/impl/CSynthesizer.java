package fr.istic.synthlab.controller.impl;

import fr.istic.synthlab.abstraction.impl.Synthesizer;
import fr.istic.synthlab.controller.ICSynthesizer;
import fr.istic.synthlab.factory.impl.PACFactory;
import fr.istic.synthlab.presentation.IPSynthesizer;

public class CSynthesizer extends Synthesizer implements ICSynthesizer {
	
	private IPSynthesizer pres;

	public CSynthesizer() {
		this.pres = PACFactory.getPFactory().newSynthesizer(this);
	}
	
	@Override
	public void start() {
		super.start();
		if(isRunning()){
//			pres.start();
		}

	}

	@Override
	public void stop() {
		super.stop();
		if(!isRunning()){
//			pres.stop();
		}
	}

	@Override
	public IPSynthesizer getPresentation() {
		return pres;
	}



}
