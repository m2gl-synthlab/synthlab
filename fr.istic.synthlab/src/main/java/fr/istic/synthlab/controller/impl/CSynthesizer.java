package fr.istic.synthlab.controller.impl;

import fr.istic.synthlab.abstraction.IModule;
import fr.istic.synthlab.abstraction.IWire;
import fr.istic.synthlab.abstraction.impl.Synthesizer;
import fr.istic.synthlab.controller.ICModule;
import fr.istic.synthlab.controller.ICSynthesizer;
import fr.istic.synthlab.controller.ICWire;
import fr.istic.synthlab.factory.impl.PACFactory;
import fr.istic.synthlab.presentation.IPSynthesizer;

public class CSynthesizer extends Synthesizer implements ICSynthesizer {

	private IPSynthesizer pres;

	public CSynthesizer() {
		super();
		this.pres = PACFactory.getPFactory().newSynthesizer(this);

		stop();
	}

	@Override
	public void start() {
		super.start();
		if (isRunning()) {
			pres.c2pStart();
		}

	}

	@Override
	public void stop() {
		super.stop();
		if (!isRunning()) {
			pres.c2pStop();
		}
	}

	@Override
	public IPSynthesizer getPresentation() {
		return pres;
	}

	@Override
	public void add(IModule module) {
		super.add(module);
		pres.c2pAddModule(((ICModule) module).getPresentation());
	}

	@Override
	public void add(IWire wire) {
		super.add(wire);
		pres.c2pAddWire(((ICWire) wire).getPresentation());
	}
	
	@Override
	public void p2cStart() {
		this.start();
	}

	@Override
	public void p2cStop() {
		this.stop();
	}

	@Override
	public void p2cAddModule(ICModule module) {
		// TODO : Do some check here
		this.add(module);
		
		//Inform the presentation
		pres.c2pAddModuleOk(module.getPresentation());
	}

	@Override
	public void p2cRemoveModule(ICModule module) {
		// TODO : Do some check here
		this.remove(module);
		pres.c2pRemoveModuleOk(module.getPresentation());
	}

	/**
	 * p2cStart p2cStop
	 * 
	 * p2cAddModule(ICModule module) p2cRemoveModule(ICModule module) p2cStart
	 * p2cStart p2cStart
	 */

}
