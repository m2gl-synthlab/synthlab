package fr.istic.synthlab.command.menu;

import fr.istic.synthlab.ISynthApp;
import fr.istic.synthlab.command.ICommand;
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
