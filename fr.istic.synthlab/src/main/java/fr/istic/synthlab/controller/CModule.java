package fr.istic.synthlab.controller;

import fr.istic.synthlab.abstraction.impl.Module;
import fr.istic.synthlab.presentation.PModule;

public class CModule extends Module {

	private PModule pModule;

	public CModule(String name){
		super(name);
		this.pModule = new PModule(name);
	}

	public PModule getPresentation(){
		return pModule;
	}
	

}
