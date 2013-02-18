package fr.istic.synthlab.command.menu;

import java.awt.FileDialog;

import fr.istic.synthlab.ISynthApp;
import fr.istic.synthlab.ISynthFrame;
import fr.istic.synthlab.SynthFrame;
import fr.istic.synthlab.command.ICommand;

public class SaveAsSynthCommand implements ICommand {

	private ISynthApp synthApp;
	private ISynthFrame synthFrame;
	private FileDialog chooser;

	public SaveAsSynthCommand(ISynthApp synthApp, ISynthFrame synthFrame) {
		this.synthApp = synthApp;
		this.synthFrame = synthFrame;
		this.chooser = new FileDialog((SynthFrame) this.synthFrame, "Save this instance as", FileDialog.SAVE);

	}

	@Override
	public void execute() {
		String[] currentFile = synthApp.getCurrentFile();

		if (currentFile[0] != null) {
			chooser.setDirectory(currentFile[0]);
			chooser.setFile(currentFile[1]);
		} else {
			chooser.setFile(currentFile[1]+".synthlab");
		}

		chooser.setVisible(true);

		String dir = chooser.getDirectory();
		String file = chooser.getFile();

		if (file != null) {
			if (!file.endsWith(".synthlab"))
				file = file + ".synthlab";
			synthApp.saveToXML(dir, file);
		}
	}

}
