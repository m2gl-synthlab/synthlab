package fr.istic.synthlab.command.menu;

import java.awt.FileDialog;

import fr.istic.synthlab.ISynthApp;
import fr.istic.synthlab.ISynthFrame;
import fr.istic.synthlab.SynthFrame;
import fr.istic.synthlab.command.ICommand;

public class SaveSynthCommand implements ICommand {

	private ISynthApp synthApp;
	private ISynthFrame synthFrame;
	private FileDialog chooser;

	public SaveSynthCommand(ISynthApp synthApp, ISynthFrame synthFrame) {
		this.synthApp = synthApp;
		this.synthFrame = synthFrame;
		this.chooser = new FileDialog((SynthFrame) this.synthFrame, "Save as", FileDialog.SAVE);
	}

	@Override
	public void execute() {
		chooser.setVisible(true);
		
		String dir= chooser.getDirectory();
		String file = chooser.getFile();

		System.out.println("");
		System.out.println("Save to : "+ dir+""+file);
		if(file != null){
			synthApp.saveToXML(dir, file);
		}
	}

}
