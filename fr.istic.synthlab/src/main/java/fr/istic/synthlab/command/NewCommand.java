package fr.istic.synthlab.command;

import fr.istic.synthlab.ISynthApp;
/**
 * Command that create a new synthesizer
 */
public class NewCommand implements ICommand{

	private ISynthApp synthApp;

	public NewCommand(ISynthApp synthApp) {
		this.synthApp = synthApp;
	}

	/* (non-Javadoc)
	 * @see fr.istic.synthlab.command.ICommand#execute()
	 */
	@Override
	public void execute() {
		synthApp.newSynth();
	}

}
