package fr.istic.synthlab.controller.port;

import com.jsyn.ports.UnitOutputPort;

import fr.istic.synthlab.abstraction.port.OutputPort;
import fr.istic.synthlab.abstraction.wire.IWire;
import fr.istic.synthlab.factory.impl.PACFactory;
import fr.istic.synthlab.presentation.port.IPOutputPort;

public class COutputPort extends OutputPort implements ICOutputPort {

	private IPOutputPort pres;

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
		// this.pres.c2pSetName();
	}

	@Override
	public void p2cConnect() {
		if (getWire() == null) {
			IWire currentWire = getModule().getSynthesizer().getCurrentWire();
			if(currentWire == null){
				getModule().getSynthesizer().setCurrentWire(PACFactory.getFactory().newWire());
				currentWire = getModule().getSynthesizer().getCurrentWire();
			}
			if (currentWire.getOutput() == null) {
				this.setWire(currentWire);
				currentWire.connect(this);
				if(currentWire.getInput() != null){
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
