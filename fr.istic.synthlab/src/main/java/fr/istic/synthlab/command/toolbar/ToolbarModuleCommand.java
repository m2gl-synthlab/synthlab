package fr.istic.synthlab.command.toolbar;

import fr.istic.synthlab.command.ICommand;
import fr.istic.synthlab.controller.ICSynthesizer;
import fr.istic.synthlab.factory.impl.PACFactory;

public class ToolbarModuleCommand implements ICommand {

	private ICSynthesizer synth;
	
	public ToolbarModuleCommand(ICSynthesizer synthesizer) {
		synth = synthesizer;
	}

	@Override
	public void execute() {
		synth.add(PACFactory.getFactory().newVCO(synth));
		System.out.println("Command "+this.getClass().getSimpleName()+" not implemented...");
	}

}
