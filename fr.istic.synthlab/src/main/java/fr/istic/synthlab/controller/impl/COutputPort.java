package fr.istic.synthlab.controller.impl;

import com.jsyn.ports.UnitOutputPort;

import fr.istic.synthlab.abstraction.IWire;
import fr.istic.synthlab.abstraction.impl.OutputPort;
import fr.istic.synthlab.controller.ICOutputPort;
import fr.istic.synthlab.factory.impl.PACFactory;
import fr.istic.synthlab.presentation.IPOutputPort;

public class COutputPort extends OutputPort implements ICOutputPort{

	private IPOutputPort pres;
	private IWire wire;
	
	public COutputPort(String name) {
		super(name);
		this.pres = PACFactory.getPFactory().newOutputPort(this);
	}
	
	public COutputPort(UnitOutputPort output, String name) {
		super(output, name);
		this.pres = PACFactory.getPFactory().newOutputPort(this);
	}

	public COutputPort(UnitOutputPort output, int part, String name) {
		super(output, part, name);
		this.pres = PACFactory.getPFactory().newOutputPort(this);
	}

	@Override
	public IPOutputPort getPresentation() {
		return pres;
	}
	
	@Override
	public void setName(String name) {
		super.setName(name);
		//this.pres.c2pSetName();
	}
	
	@Override
	public void p2cConnect() {
		if(wire == null){
			wire = getModule().getSynthesizer().getCurrentWire();
			wire.connect(this);
		}
	}

}
