package fr.istic.synthlab.controller.impl;

import com.jsyn.ports.UnitInputPort;

import fr.istic.synthlab.abstraction.IWire;
import fr.istic.synthlab.abstraction.impl.InputPort;
import fr.istic.synthlab.controller.ICInputPort;
import fr.istic.synthlab.factory.impl.PACFactory;
import fr.istic.synthlab.presentation.IPInputPort;

public class CInputPort extends InputPort implements ICInputPort {

	private IPInputPort pres;
	
	public CInputPort(String name) {
		super(name);
		this.pres = PACFactory.getPFactory().newInputPort(this);
	}
	
	public CInputPort(UnitInputPort input, String name) {
		super(input, name);
		this.pres = PACFactory.getPFactory().newInputPort(this);
	}

	public CInputPort(UnitInputPort input, int part, String name) {
		super(input, part, name);
		this.pres = PACFactory.getPFactory().newInputPort(this);
	}

	@Override
	public IPInputPort getPresentation() {
		return pres;
	}
	
	@Override
	public void setName(String name) {
		super.setName(name);
//		this.pres.c2pSetName();
	}

	@Override
	public void p2cConnect() {
		System.out.println("CInputPort p2cConnect");
		if (getWire() == null) {
			IWire currentWire = getModule().getSynthesizer().getCurrentWire();
			if(currentWire == null){
				getModule().getSynthesizer().setCurrentWire(PACFactory.getFactory().newWire());
				currentWire = getModule().getSynthesizer().getCurrentWire();
			}
			
			if (currentWire.getInput() == null) {
				this.setWire(currentWire);
				currentWire.connect(this);
				if(currentWire.getOutput() != null){
					getModule().getSynthesizer().setCurrentWire(null);
				}
			}
		}
	}
	

	
	@Override
	public void p2cDisconnect() {
		if(getWire() != null){
			
			getWire().disconnect();
		}
	}

	
}
