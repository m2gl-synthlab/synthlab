package fr.istic.synthlab;

import java.io.File;
import java.util.List;

import fr.istic.synthlab.abstraction.module.IModule;
import fr.istic.synthlab.abstraction.module.out.IModuleOUT;
import fr.istic.synthlab.command.ICommand;
import fr.istic.synthlab.controller.synthesizer.CSynthesizer;
import fr.istic.synthlab.controller.synthesizer.ICSynthesizer;
import fr.istic.synthlab.factory.impl.PACFactory;
import fr.istic.synthlab.util.ReadXMLFile;
import fr.istic.synthlab.util.WriteXMLFile;

//github.com/m2gl-synthlab/synthlab.git/

/**
 * Application
 */
public class SynthApp implements ISynthApp {

	private ICSynthesizer synth;
	private ICommand displayCmd;
	private ICommand undisplayCmd;
	private String[] currentFile = { null, null };

	@Override
	public void startSynth() {
		if (synth == null) {
			newSynth();
		}
		displayCmd.execute();
	}

	@Override
	public void newSynth() {
		// Replace the current synthesizer with a new one
		if (this.synth != null)
			synth.stop();
		this.synth = (ICSynthesizer) PACFactory.getFactory().newSynthesizer();
		displayCmd.execute();

		// Add an OUT module
		IModuleOUT out = PACFactory.getFactory().newOUT();
		synth.add(out);

		currentFile[0] = null;
		currentFile[1] = null;

		synth.start();
	}

	@Override
	public void quitSynth() {
		this.synth = null;
		undisplayCmd.execute();
		System.exit(0);
	}

	@Override
	public void saveToXML(String fileDir, String filename) {
		List<IModule> modules = CSynthesizer.getInstance().getModules();

		WriteXMLFile writeToXML = new WriteXMLFile(new File(fileDir + filename));
		writeToXML.saveModules(modules);

		currentFile[0] = fileDir;
		currentFile[1] = filename;

	}

	@Override
	public void loadFromXML(String dir, String file) {
		synth.stop();
		this.synth = (ICSynthesizer) PACFactory.getFactory().newSynthesizer();
		displayCmd.execute();

		ReadXMLFile readXML = new ReadXMLFile(new File(dir + file));
		readXML.loadSynthesizer();

		currentFile[0] = dir;
		currentFile[1] = file;

		synth.start();
	}

	@Override
	public void setSynthesizer(ICSynthesizer syn) {
		this.synth = syn;
	}

	@Override
	public ICSynthesizer getSynthesizer() {
		return synth;
	}

	@Override
	public void setDisplaySynthCommand(ICommand displaySynthCommand) {
		this.displayCmd = displaySynthCommand;
	}

	@Override
	public void setUndisplaySynthCommand(ICommand undisplaySynthCommand) {
		this.undisplayCmd = undisplaySynthCommand;
	}

	@Override
	public String[] getCurrentFile() {
		return currentFile;
	}
}
