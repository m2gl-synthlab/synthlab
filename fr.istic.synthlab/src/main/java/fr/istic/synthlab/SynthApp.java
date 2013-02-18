package fr.istic.synthlab;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import fr.istic.synthlab.abstraction.module.out.IModuleOUT;
import fr.istic.synthlab.abstraction.synthesizer.ISynthesizer;
import fr.istic.synthlab.command.ICommand;
import fr.istic.synthlab.controller.synthesizer.ICSynthesizer;
import fr.istic.synthlab.factory.impl.PACFactory;
import fr.istic.synthlab.util.ReadXMLFile;
import fr.istic.synthlab.util.WriteXMLFile;

/**
 * Application
 */
public class SynthApp implements ISynthApp {

	private List<ICSynthesizer> synths;
	private ICSynthesizer currentSynth;
	private ISynthFrame frame;
	private ICommand displayCmd;
	private ICommand undisplayCmd;
	private String[] currentFile = { null, null };
	private int untitledIndex = 1;

	public SynthApp(ISynthFrame frame) {
		this.frame = frame;
		synths = new ArrayList<ICSynthesizer>();

		// Set the current directory file and path file to null
		currentFile[0] = null;
		currentFile[1] = null;

		// Create a new synthesizer instance
		newSynthInstance();
	}

	// Create a new synthesizer instance, set its path and frame, and add it to
	// the list
	private void newSynthInstance() {
		if (synths.size() > 0)
			currentSynth.stop();
		ICSynthesizer newSynth = (ICSynthesizer) (PACFactory.getFactory()).newSynthesizer();
		this.synths.add(newSynth);
		newSynth.setPath("untitled" + untitledIndex);
		newSynth.setFrame(frame);
		setSynthesizer(newSynth.getPath());
		untitledIndex++;
	}

	@Override
	public void newSynth() {
		// Create a new instance
		newSynthInstance();

		// Display the new instance and add an OUT module
		displayNewDefaultSynth();

		// Add this new instance to the menu
		frame.addToMenu(currentSynth);
	}

	@Override
	public void startSynth() {
		currentSynth.start();
	}

	@Override
	public void quitSynth() {
		currentSynth.stop();
		this.currentSynth = null;
		undisplayCmd.execute();
		System.exit(0);
	}

	@Override
	public void saveToXML(String fileDir, String filename) {
		// Create a new writter
		WriteXMLFile writeToXML = new WriteXMLFile(new File(fileDir + filename));

		// Save all modules
		writeToXML.saveModules(currentSynth.getModules());

		// Set the path of this instance
		currentFile[0] = fileDir;
		currentFile[1] = filename;
		currentSynth.setPath(fileDir + filename);

		// Reload this instance in the menu
		frame.removeInMenu(currentSynth);
		frame.addToMenu(currentSynth);
	}

	@Override
	public void loadFromXML(String dir, String file) {
		// If this file is already open, return
		for (ICSynthesizer s : synths) {
			if (s.getPath().equals(dir + file)) {
				return;
			}
		}

		// Stop the current instance
		currentSynth.stop();

		// Create a new instance
		newSynthInstance();

		// Display the new instance
		displayCmd.execute();

		// Create a new reader
		ReadXMLFile readXML = new ReadXMLFile(currentSynth, new File(dir + file));

		// Populate the current synthesizer with the file
		readXML.loadSynthesizer();

		// Set the current path
		currentFile[0] = dir;
		currentFile[1] = file;
		currentSynth.setPath(dir + file);

		// Add this instance to the menu and set the title bar
		frame.addToMenu(currentSynth);
	}

	@Override
	public void displayNewDefaultSynth() {
		// Display the current instance
		displayCmd.execute();

		// Add an OUT module
		IModuleOUT out = (PACFactory.getFactory()).newOUT(currentSynth);
		currentSynth.add(out);
	}

	@Override
	public void remove(ISynthesizer cSynth) {
		// Stop the synthesizer
		cSynth.stop();

		// Remove the synthesizer from the list
		this.synths.remove(cSynth);

		// Remove the synthesizer in the menu
		frame.removeInMenu((ICSynthesizer) cSynth);

		// If we have another synthesizer loaded, we display the last one, else
		// we create a new one
		if (this.synths.size() > 0) {
			setSynthesizer(this.synths.get(this.synths.size() - 1).getPath());
			frame.displaySynth();
		} else {
			newSynth();
		}
		
		frame.selectInMenu(currentSynth);
	}

	@Override
	public void setSynthesizer(String synthS) {
		// If the current synthesizer is running, stop it
		if (currentSynth != null)
			currentSynth.stop();
		
		// Set the current synthesizer instance fron its path
		for (ISynthesizer synth : this.synths) {
			if (((ICSynthesizer) synth).getPath().equals(synthS)) {
				currentSynth = (ICSynthesizer) synth;
				currentFile[0] = null;
				currentFile[1] = currentSynth.getPath();
				return;
			}
		}
	}

	@Override
	public ICSynthesizer getSynthesizer() {
		if (currentSynth == null)
			newSynthInstance();
		return currentSynth;
	}

	@Override
	public String[] getCurrentFile() {
		return currentFile;
	}

	public void setFrame(SynthFrame frame2) {
		this.frame = frame2;
	}

	@Override
	public void setDisplaySynthCommand(ICommand displaySynthCommand) {
		this.displayCmd = displaySynthCommand;
	}

	@Override
	public void setUndisplaySynthCommand(ICommand undisplaySynthCommand) {
		this.undisplayCmd = undisplaySynthCommand;
	}
}
