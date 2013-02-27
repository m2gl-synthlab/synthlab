package fr.istic.synthlab.command.toolbar;

import fr.istic.synthlab.ISynthApp;
import fr.istic.synthlab.command.ICommand;

public class ToolbarPauseCommand implements ICommand {

	private ISynthApp app;

	public ToolbarPauseCommand(ISynthApp app) {
		this.app = app;
	}

	@Override
	public void execute() {
		app.getSynthesizer().stop();
	}

}
