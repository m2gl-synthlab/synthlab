package fr.istic.synthlab.controller.impl;

import java.util.ArrayList;
import java.util.List;

import fr.istic.synthlab.abstraction.ISynthesizer;
import fr.istic.synthlab.abstraction.impl.ModuleOUT;
import fr.istic.synthlab.controller.ICModuleOUT;
import fr.istic.synthlab.controller.ICWire;
import fr.istic.synthlab.factory.impl.PACFactory;
import fr.istic.synthlab.presentation.IPModuleOUT;

public class CModuleOUT extends ModuleOUT implements ICModuleOUT {

	private IPModuleOUT pres;


	public CModuleOUT(ISynthesizer synth) {
		super(synth);
		System.out.println("CModuleOUT initialized");
		this.pres = PACFactory.getPFactory().newOUT(this);
		
		// IParameter gain = this.getParameter(PARAM_GAIN);
		// pres.addParameter(((ICParameter) gain).getPresentation());
		//
		// IParameter switchOnOff = this.getParameter(PARAM_SWITCH_ON_OFF);
		// pres.addParameter(((ICParameter) switchOnOff).getPresentation());
		//
		// IInputPort input = this.getInput(INPUT_IN);
		// pres.addInputPort(((ICInputPort) input).getPresentation());
	}

	@Override
	public void p2cMute() {
		setMute(!isMute());
		if (isMute()) {
			pres.c2pMute();
		} else {
			pres.c2pUnmute();
		}
	}

	@Override
	public void p2cGainChanged(double gain) {
		setAttenuation(gain);
		pres.c2pSetGainValue(getAttenuation());
	}

	@Override
	public IPModuleOUT getPresentation() {
		return this.pres;
	}
}
