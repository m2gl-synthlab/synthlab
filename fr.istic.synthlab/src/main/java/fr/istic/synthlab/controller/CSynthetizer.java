package fr.istic.synthlab.controller;

import fr.istic.synthlab.abstraction.impl.Synthetizer;
import fr.istic.synthlab.presentation.PSynthetizer;

public class CSynthetizer extends Synthetizer {

	private PSynthetizer pSynthetizer;
	
	public CSynthetizer(){
		this.pSynthetizer = new PSynthetizer();
		
		System.out.println("tante");
	}

	public PSynthetizer getPresentation(){
		return pSynthetizer;
	}

}
