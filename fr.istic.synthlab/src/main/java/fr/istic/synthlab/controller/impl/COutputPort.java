package fr.istic.synthlab.controller.impl;

import com.jsyn.ports.UnitOutputPort;

import fr.istic.synthlab.abstraction.IWire;
import fr.istic.synthlab.abstraction.impl.OutputPort;
import fr.istic.synthlab.controller.ICOutputPort;
import fr.istic.synthlab.factory.impl.PACFactory;
import fr.istic.synthlab.presentation.IPOutputPort;

public class COutputPort extends OutputPort implements ICOutputPort{

	private IPOutputPort pres;
	
	public COutputPort(String name) {
		super(name);
		this.pres = PACFactory.getPFactory().newOutputPort(this);
	}
	
	public COutputPort(UnitOutputPort output, String name) {
		super(output, name);
		this.pres = PACFactory.getPFactory().newOutputPort(this);
	}

	public COutputPort(UnitOutputPort output, int part) {
		super(output, part);
		this.pres = PACFactory.getPFactory().newOutputPort(this);
	}

	@Override
	public IPOutputPort getPresentation() {
		return pres;
	}
	
	@Override
	public void p2cConnect() {
		IWire wire = getModule().getSynthesizer().getCurrentWire();
		if(wire != null){
			wire.connect(this);
		}
	}

}
