package fr.istic.synthlab.command.toolbar;

import fr.istic.synthlab.abstraction.port.IInputPort;
import fr.istic.synthlab.abstraction.port.IOutputPort;
import fr.istic.synthlab.abstraction.synthesizer.ISynthesizer;
import fr.istic.synthlab.abstraction.wire.IWire;
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
		
		
		// S'il éxiste un cable
		if(this.synth.getCurrentWire() != null){
			IInputPort in = this.synth.getCurrentWire().getInput();
			IOutputPort out = this.synth.getCurrentWire().getOutput();
			
			// Qu'il n'est connecté que d'un coté
			if(in == null){
				if(out != null){
					// On disconnect
					this.synth.getCurrentWire().disconnect();
					System.out.println("Disconnect out");
				}
			}
			if(out == null){
				if(in != null){
					// On disconnect
					this.synth.getCurrentWire().disconnect();
					System.out.println("Disconnect in");
				}
			}
		}
			
		this.synth.setCurrentWire(wire);
	}

}
