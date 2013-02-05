package fr.istic.synthlab.command.toolbar;

import fr.istic.synthlab.command.ICommand;

public class ToolbarDefaultCommand implements ICommand {

	@Override
	public void execute() {
		System.out.println("Command "+this.getClass().getSimpleName()+" not implemented...");
	}

}
