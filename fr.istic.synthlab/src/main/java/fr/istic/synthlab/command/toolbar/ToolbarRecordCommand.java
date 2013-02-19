package fr.istic.synthlab.command.toolbar;

import java.awt.FileDialog;

import fr.istic.synthlab.ISynthApp;
import fr.istic.synthlab.ISynthFrame;
import fr.istic.synthlab.SynthFrame;
import fr.istic.synthlab.abstraction.module.rec.IModuleREC;
import fr.istic.synthlab.command.ICommand;

public class ToolbarRecordCommand implements ICommand {

	private ISynthApp synthApp;
	private ISynthFrame synthFrame;
	private FileDialog chooser;
	private IModuleREC moduleRec;

	public ToolbarRecordCommand(ISynthApp synthApp, ISynthFrame synthFrame, IModuleREC moduleRec) {
		this.synthApp = synthApp;
		this.synthFrame = synthFrame;
		this.moduleRec = moduleRec;
		this.chooser = new FileDialog((SynthFrame) this.synthFrame,
				"Save wave file as", FileDialog.SAVE);

	}

	@Override
	public void execute() {
		if (synthApp.getSynthesizer().getPath()[0] != null) {
			chooser.setDirectory(synthApp.getSynthesizer().getPath()[0]);
			chooser.setFile(synthApp.getSynthesizer().getPath()[1]);
		} else {
			chooser.setFile(synthApp.getSynthesizer().getPath()[1]
					+ ".wav");
		}

		chooser.setVisible(true);

		String dir = chooser.getDirectory();
		String file = chooser.getFile();

		if (file != null) {
			if (!file.endsWith(".wav"))
				file = file + ".wav";
			moduleRec.saveRecordToFile(dir, file);
		}
	}

}
