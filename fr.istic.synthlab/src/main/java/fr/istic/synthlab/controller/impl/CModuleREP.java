package fr.istic.synthlab.controller.impl;

import fr.istic.synthlab.abstraction.ISynthesizer;
import fr.istic.synthlab.abstraction.impl.ModuleREP;
import fr.istic.synthlab.controller.ICModuleREP;
import fr.istic.synthlab.presentation.IPModule;

public class CModuleREP  extends ModuleREP implements ICModuleREP {

	public CModuleREP(ISynthesizer synth) {
		super(synth);
		// TODO Auto-generated constructor stub
	}

	@Override
	public IPModule getPresentation() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void p2cClosing() {
		// TODO Auto-generated method stub
		
	}

}
