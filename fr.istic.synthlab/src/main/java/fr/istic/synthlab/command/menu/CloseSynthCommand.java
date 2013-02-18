package fr.istic.synthlab.command.menu;

import java.awt.FileDialog;

import javax.swing.JFrame;

import fr.istic.synthlab.ISynthApp;
import fr.istic.synthlab.ISynthFrame;
import fr.istic.synthlab.SynthFrame;
import fr.istic.synthlab.command.ICommand;

public class CloseSynthCommand implements ICommand {

	private ISynthApp synthApp;
	private ISynthFrame synthFrame;

	public CloseSynthCommand(ISynthApp synthApp, ISynthFrame synthFrame) {
		this.synthApp = synthApp;
		this.synthFrame = synthFrame;
	}

	@Override
	public void execute() {
		synthApp.remove(synthApp.getSynthesizer());
	}

}
