package fr.istic.synthlab.command;

import fr.istic.synthlab.ISynthApp;
import fr.istic.synthlab.ISynthFrame;

/**
 * Command that display the synthesizer
 */
public class DisplayCommand implements ICommand {

	private ISynthApp synthApp;
	private ISynthFrame synthFrame;

	public DisplayCommand(ISynthApp synthApp, ISynthFrame synthFrame) {
		this.synthApp = synthApp;
		this.synthFrame = synthFrame;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.istic.synthlab.command.ICommand#execute()
	 */
	@Override
	public void execute() {
		synthFrame.displaySynth(synthApp.getSynthesizer().getPresentation());
	}
}