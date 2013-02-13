package fr.istic.synthlab.command.menu;

import javax.swing.JFileChooser;

import fr.istic.synthlab.ISynthApp;
import fr.istic.synthlab.ISynthFrame;
import fr.istic.synthlab.SynthFrame;
import fr.istic.synthlab.command.ICommand;

public class SaveSynthCommand implements ICommand {

	private ISynthApp synthApp;
	private ISynthFrame synthFrame;
	private JFileChooser chooser;

	public SaveSynthCommand(ISynthApp synthApp, ISynthFrame synthFrame) {
		this.synthApp = synthApp;
		this.synthFrame = synthFrame;
		this.chooser = new JFileChooser();
	}

	@Override
	public void execute() {
		int returnVal = this.chooser.showSaveDialog((SynthFrame) synthFrame);

		switch (returnVal) {
		case JFileChooser.APPROVE_OPTION:

			break;
		case JFileChooser.CANCEL_OPTION:

			break;
		case JFileChooser.ERROR_OPTION:

			break;

		default:
			break;
		}
		synthApp.saveToXML();
	}

}
