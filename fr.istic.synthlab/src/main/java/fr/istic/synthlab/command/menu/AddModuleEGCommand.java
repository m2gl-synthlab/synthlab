package fr.istic.synthlab.command.menu;

import fr.istic.synthlab.command.ICommand;
import fr.istic.synthlab.controller.ICSynthesizer;
import fr.istic.synthlab.factory.impl.PACFactory;

public class AddModuleEGCommand implements ICommand {

	private ICSynthesizer synth;
	
	public AddModuleEGCommand(ICSynthesizer synthesizer) {
		synth = synthesizer;
	}

	@Override
	public void execute() {
		synth.add(PACFactory.getFactory().newEG(synth));
	}

}
