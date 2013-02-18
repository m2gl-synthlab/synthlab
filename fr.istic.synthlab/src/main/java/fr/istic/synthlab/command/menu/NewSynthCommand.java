package fr.istic.synthlab.command.menu;

import javax.swing.JFrame;

import fr.istic.synthlab.ISynthApp;
import fr.istic.synthlab.ISynthFrame;
import fr.istic.synthlab.command.ICommand;

/**
 * Command that create a new synthesizer
 */
public class NewSynthCommand implements ICommand {

	private ISynthApp synthApp;
	private ISynthFrame frame;

	public NewSynthCommand(ISynthApp synthApp, ISynthFrame frame) {
		this.synthApp = synthApp;
		this.frame = frame;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.istic.synthlab.command.ICommand#execute()
	 */
	@Override
	public void execute() {
//		((JFrame) frame).setTitle("SynthlabG2 - untitled");
		synthApp.newSynth();
		frame.addToMenu(synthApp.getSynthesizer());
	}

}
