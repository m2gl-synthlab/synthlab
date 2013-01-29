package fr.istic.synthlab.controller;

import fr.istic.synthlab.abstraction.IModule;
import fr.istic.synthlab.abstraction.ISynthetizer;
import fr.istic.synthlab.presentation.PSynthetizer;

public class CSynthetizer implements ISynthetizer {

	private PSynthetizer pSynthetizer;
	
	public CSynthetizer(){
		this.pSynthetizer = new PSynthetizer();
		
		System.out.println("tante");
	}

	public PSynthetizer getPresentation(){
		return pSynthetizer;
	}
	
	public void start() {
		// TODO Auto-generated method stub
		
	}

	public void stop() {
		// TODO Auto-generated method stub
		
	}

	public boolean isRunning() {
		// TODO Auto-generated method stub
		return false;
	}

	public void addModule(IModule module) {
		// TODO Auto-generated method stub
		
	}

	public void removeModule(IModule module) {
		// TODO Auto-generated method stub
		
	}
}
