package fr.istic.synthlab.command.app;

import fr.istic.synthlab.ISynthFrame;
import fr.istic.synthlab.command.ICommand;

/**
 * Command that hide the synthesizer
 */
public class UndisplayCommand implements ICommand {

	private ISynthFrame synthFrame;

	public UndisplayCommand(ISynthFrame synthFrame) {
		this.synthFrame = synthFrame;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.istic.synthlab.command.ICommand#execute()
	 */
	@Override
	public void execute() {
		synthFrame.quitSynth();
	}
}