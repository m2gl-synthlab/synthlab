package fr.istic.synthlab;

import java.io.File;
import java.util.List;

import fr.istic.synthlab.abstraction.module.IModule;
import fr.istic.synthlab.abstraction.module.out.IModuleOUT;
import fr.istic.synthlab.command.ICommand;
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
	private ISynthFrame frame;
	private ICommand displayCmd;
	private ICommand undisplayCmd;
	private String[] currentFile = { null, null };

	public SynthApp(ISynthFrame frame){
		this.frame = frame;
		currentFile[0]=null;
		currentFile[1]=null;
		newSynthInstance();
	}
	
	@Override
	public void newSynth() {
		newSynthInstance();
		frame.setSynthesizer(synth);
		displayNewSynth();
		startSynth();
	}

	@Override
	public void startSynth() {
		synth.start();
	}

	private void newSynthInstance() {
		if(synth != null)
			synth.stop();
		this.synth = (ICSynthesizer) (PACFactory.getFactory()).newSynthesizer();
	}

	@Override
	public void quitSynth() {
		this.synth = null;
		undisplayCmd.execute();
		System.exit(0);
	}

	@Override
	public void saveToXML(String fileDir, String filename) {
		List<IModule> modules = synth.getModules();

		WriteXMLFile writeToXML = new WriteXMLFile(new File(fileDir + filename));
		writeToXML.saveModules(modules);

		currentFile[0] = fileDir;
		currentFile[1] = filename;

	}

	@Override
	public void loadFromXML(String dir, String file) {
		synth.stop();
		newSynthInstance();
		frame.setSynthesizer(synth);
		displayCmd.execute();
		
		ReadXMLFile readXML = new ReadXMLFile(synth, new File(dir+file));
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
		if(synth==null)

			newSynthInstance();
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

	public void displayNewSynth() {
		displayCmd.execute();

		// Add an OUT module
		IModuleOUT out = (PACFactory.getFactory()).newOUT(synth);
		synth.add(out);
		
	}

	public void setFrame(SynthFrame frame2) {
		this.frame = frame2;
	}
}
