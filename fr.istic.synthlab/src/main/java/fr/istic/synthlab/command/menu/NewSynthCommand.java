package fr.istic.synthlab.command.menu;

import fr.istic.synthlab.ISynthApp;
import fr.istic.synthlab.command.ICommand;

/**
 * Command that create a new synthesizer
 */
public class NewSynthCommand implements ICommand {

	private ISynthApp synthApp;

	public NewSynthCommand(ISynthApp synthApp) {
		this.synthApp = synthApp;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.istic.synthlab.command.ICommand#execute()
	 */
	@Override
	public void execute() {
		synthApp.newSynth();
	}

}
