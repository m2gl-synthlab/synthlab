package fr.istic.synthlab.command.app;

import fr.istic.synthlab.ISynthFrame;
import fr.istic.synthlab.command.ICommand;
import fr.istic.synthlab.controller.synthesizer.CSynthesizer;

/**
 * Command that display the synthesizer
 */
public class DisplayCommand implements ICommand {

	private ISynthFrame synthFrame;

	public DisplayCommand(ISynthFrame synthFrame) {
		this.synthFrame = synthFrame;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.istic.synthlab.command.ICommand#execute()
	 */
	@Override
	public void execute() {
		synthFrame.displaySynth(CSynthesizer.getInstance().getPresentation());
	}
}