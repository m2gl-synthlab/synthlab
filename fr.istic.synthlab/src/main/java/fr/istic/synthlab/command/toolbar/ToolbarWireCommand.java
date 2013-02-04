package fr.istic.synthlab.command.toolbar;

import fr.istic.synthlab.abstraction.ISynthesizer;
import fr.istic.synthlab.abstraction.IWire;
import fr.istic.synthlab.command.ICommand;
import fr.istic.synthlab.factory.impl.PACFactory;

public class ToolbarWireCommand implements ICommand {

	private ISynthesizer synth;
	
	public ToolbarWireCommand(ISynthesizer synth) {
		this.synth = synth;
	}
	
	@Override
	public void execute() {
		IWire wire = PACFactory.getFactory().newWire();
		this.synth.setCurrentWire(wire);
	}

}
