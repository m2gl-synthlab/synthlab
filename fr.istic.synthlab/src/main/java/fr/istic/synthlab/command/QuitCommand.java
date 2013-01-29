package fr.istic.synthlab.command;

import fr.istic.synthlab.ISynthApp;
/**
 * Command that quit the application
 */
public class QuitCommand implements ICommand {

	private ISynthApp synthApp;

	public QuitCommand(ISynthApp synthApp) {
		this.synthApp = synthApp;
	}

	@Override
	public void execute() {
		synthApp.quitSynth();
	}

}
