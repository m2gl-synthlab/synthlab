package fr.istic.synthlab.command.menu;

import fr.istic.synthlab.command.ICommand;
import fr.istic.synthlab.controller.synthesizer.CSynthesizer;
import fr.istic.synthlab.factory.impl.PACFactory;

public class AddModuleMIXCommand implements ICommand {

	public AddModuleMIXCommand() {
	}

	@Override
	public void execute() {
		CSynthesizer.getInstance().add(PACFactory.getFactory().newMIX());
	}

}
