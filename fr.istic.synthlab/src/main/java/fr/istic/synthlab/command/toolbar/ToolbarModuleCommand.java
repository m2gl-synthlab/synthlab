package fr.istic.synthlab.command.toolbar;

import fr.istic.synthlab.command.ICommand;
import fr.istic.synthlab.controller.synthesizer.ICSynthesizer;
import fr.istic.synthlab.factory.impl.PACFactory;

public class ToolbarModuleCommand implements ICommand {

	private ICSynthesizer synth;
	
	public ToolbarModuleCommand(ICSynthesizer synthesizer) {
		synth = synthesizer;
	}

	@Override
	public void execute() {
		synth.add(PACFactory.getFactory().newVCO());
		System.out.println("Command "+this.getClass().getSimpleName()+" not implemented...");
	}

}
