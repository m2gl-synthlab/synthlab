package fr.istic.synthlab.command.menu;

import fr.istic.synthlab.command.ICommand;

public class OpenSynthCommand implements ICommand {

	@Override
	public void execute() {
		System.out.println("Command "+this.getClass().getSimpleName()+" not implemented...");
		
	}

}
