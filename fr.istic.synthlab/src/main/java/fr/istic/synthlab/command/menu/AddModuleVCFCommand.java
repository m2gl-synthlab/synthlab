package fr.istic.synthlab.command.menu;

import fr.istic.synthlab.command.ICommand;
import fr.istic.synthlab.controller.synthesizer.ICSynthesizer;
import fr.istic.synthlab.factory.impl.PACFactory;

public class AddModuleVCFCommand implements ICommand {

	private ICSynthesizer synth;
	
	public AddModuleVCFCommand(ICSynthesizer synthesizer) {
		synth = synthesizer;
	}

	@Override
	public void execute() {
		synth.add(PACFactory.getFactory().newVCFA_LP(synth));
	}

}
