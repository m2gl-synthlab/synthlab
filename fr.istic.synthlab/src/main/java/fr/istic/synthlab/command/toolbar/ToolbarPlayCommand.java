package fr.istic.synthlab.command.toolbar;

import fr.istic.synthlab.ISynthApp;
import fr.istic.synthlab.SynthApp;
import fr.istic.synthlab.command.ICommand;

public class ToolbarPlayCommand implements ICommand {

	private ISynthApp app;

	public ToolbarPlayCommand(SynthApp app) {
		this.app = app;
	}

	@Override
	public void execute() {
		app.getSynthesizer().start();
	}

}
