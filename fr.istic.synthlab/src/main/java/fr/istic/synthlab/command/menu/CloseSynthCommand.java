package fr.istic.synthlab.command.menu;

import fr.istic.synthlab.ISynthApp;
import fr.istic.synthlab.command.ICommand;

public class CloseSynthCommand implements ICommand {

	private ISynthApp synthApp;

	public CloseSynthCommand(ISynthApp synthApp) {
		this.synthApp = synthApp;
	}

	@Override
	public void execute() {
		synthApp.remove(synthApp.getSynthesizer());
	}

}
