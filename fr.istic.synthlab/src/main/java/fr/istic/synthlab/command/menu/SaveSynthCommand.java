package fr.istic.synthlab.command.menu;

import java.awt.FileDialog;

import javax.swing.JFrame;

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
		String[] currentFile = synthApp.getCurrentFile();

		if (currentFile[1] != null) {
			synthApp.saveToXML(currentFile[0], currentFile[1]);
		} else {
			chooser.setFile("untitled.synthlab");

			chooser.setVisible(true);

			String dir = chooser.getDirectory();
			String file = chooser.getFile();

			if (file != null) {
				if (!file.endsWith(".synthlab"))
					file = file + ".synthlab";
				synthApp.saveToXML(dir, file);
				((JFrame) synthFrame).setTitle("SynthlabG2 - " + dir + file);
			}
		}
	}

}
