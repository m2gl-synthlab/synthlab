package fr.istic.synthlab.command;

import fr.istic.synthlab.ISynthApp;
/**
 * Command that quit the application
 */
public class QuitSynthCommand implements ICommand {

	private ISynthApp synthApp;

	public QuitSynthCommand(ISynthApp synthApp) {
		this.synthApp = synthApp;
	}

	@Override
	public void execute() {
		synthApp.quitSynth();
	}

}
