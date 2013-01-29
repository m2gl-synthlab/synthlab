package fr.istic.synthlab.controller.impl;

import java.util.List;

import fr.istic.synthlab.abstraction.IModule;
import fr.istic.synthlab.abstraction.ISynthesizer;
import fr.istic.synthlab.abstraction.impl.InputPort;
import fr.istic.synthlab.abstraction.impl.OutputPort;
import fr.istic.synthlab.controller.ICModule;
import fr.istic.synthlab.controller.ICSynthesizer;
import fr.istic.synthlab.factory.IFactory;
import fr.istic.synthlab.factory.PACFactory;
import fr.istic.synthlab.presentation.IPSynthesizer;
import fr.istic.synthlab.presentation.impl.PSynthesizer;

public class CSynthesizer implements ICSynthesizer {
	
	private IPSynthesizer pres;
	private ISynthesizer abs;

	public CSynthesizer() {
		this.pres = PACFactory.getPFactory().newSynthesizer(this);
		this.abs = PACFactory.getAFactory().newSynthesizer(PACFactory.getAFactory());
	}
	
	@Override
	public void start() {
		abs.start();
		if(isRunning()){
//			pres.start();
		}

	}

	@Override
	public void stop() {
		abs.stop();
		if(!isRunning()){
//			pres.stop();
		}
	}

	@Override
	public boolean isRunning() {
		return abs.isRunning();
	}

	@Override
	public void addModule(IModule module) {
		abs.addModule(module);
		ICModule ctrl = (ICModule) module;
		pres.addModule(ctrl.getPresentation());
	}

	@Override
	public void removeModule(IModule module) {
		abs.removeModule(module);
		ICModule ctrl = (ICModule) module;
		pres.removeModule(ctrl.getPresentation());
	}

	@Override
	public IPSynthesizer getPresentation() {
		return pres;
	}

	@Override
	public IModule getModule(int i) {
		return abs.getModule(i);
	}

	@Override
	public List<IModule> getModules() {
		return abs.getModules();
	}

}
