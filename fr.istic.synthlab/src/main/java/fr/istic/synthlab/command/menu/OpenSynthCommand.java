package fr.istic.synthlab.command.menu;

import java.awt.FileDialog;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import fr.istic.synthlab.ISynthApp;
import fr.istic.synthlab.ISynthFrame;
import fr.istic.synthlab.SynthFrame;
import fr.istic.synthlab.command.ICommand;

public class OpenSynthCommand implements ICommand {

	private ISynthApp app;
	private ISynthFrame synthFrame;
	private FileDialog chooser;

	public OpenSynthCommand(ISynthApp app, ISynthFrame synthFrame) {
		this.app = app;
		this.synthFrame = synthFrame;
		this.chooser = new FileDialog((SynthFrame) this.synthFrame, "Open a Synthlab File", FileDialog.LOAD);
	}

	@Override
	public void execute() {

		chooser.setVisible(true);

		String dir = chooser.getDirectory();
		String file = chooser.getFile();
		if (file != null) {
			if (file.endsWith(".synthlab")) {
				((JFrame) synthFrame).setTitle("SynthlabG2 - " + dir + file);
				app.loadFromXML(dir, file);
			} else {
				JOptionPane.showMessageDialog(((JFrame) synthFrame), "Select a \'.synthlab\' file please.");
				this.execute();
			}
		}
	}
}