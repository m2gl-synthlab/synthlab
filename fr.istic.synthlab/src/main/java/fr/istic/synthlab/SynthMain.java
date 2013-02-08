package fr.istic.synthlab;

import com.alee.laf.WebLookAndFeel;

import fr.istic.synthlab.command.app.DisplayCommand;
import fr.istic.synthlab.command.app.UndisplayCommand;
import fr.istic.synthlab.command.menu.AboutCommand;
import fr.istic.synthlab.command.menu.AddModuleAudioScopeCommand;
import fr.istic.synthlab.command.menu.AddModuleEGCommand;
import fr.istic.synthlab.command.menu.AddModuleOUTCommand;
import fr.istic.synthlab.command.menu.AddModuleVCFCommand;
import fr.istic.synthlab.command.menu.AddModuleVCOCommand;
import fr.istic.synthlab.command.menu.DocumentationCommand;
import fr.istic.synthlab.command.menu.NewSynthCommand;
import fr.istic.synthlab.command.menu.OpenSynthCommand;
import fr.istic.synthlab.command.menu.QuitSynthCommand;
import fr.istic.synthlab.command.menu.SaveSynthCommand;
import fr.istic.synthlab.command.toolbar.ToolbarPauseCommand;
import fr.istic.synthlab.command.toolbar.ToolbarPlayCommand;
import fr.istic.synthlab.factory.impl.AFactory;
import fr.istic.synthlab.factory.impl.CFactory;
import fr.istic.synthlab.factory.impl.PACFactory;
import fr.istic.synthlab.factory.impl.PFactory;

public class SynthMain {
	public static void main(String[] args) {
		WebLookAndFeel.install();
		// Initialize factories
		PACFactory.setAFactory(AFactory.getInstance());
		PACFactory.setCFactory(CFactory.getInstance());
		PACFactory.setPFactory(PFactory.getInstance());
		
		// Create the application
		SynthApp app = new SynthApp();
		
		// Create the main frame
		SynthFrame frame = new SynthFrame();
		frame.setTitle("Synthlab - G2");
		
		// Configure the application
		app.setDisplaySynthCommand(new DisplayCommand(app, frame));
		app.setUndisplaySynthCommand(new UndisplayCommand(frame));

		// Create a default synthesizer
		app.newSynth();
  
		// Configure the frame
		frame.setNewSynthCommand(new NewSynthCommand(app));
		frame.setOpenSynthCommand(new OpenSynthCommand());
		frame.setSaveSynthCommand(new SaveSynthCommand());
		frame.setQuitSynthCommand(new QuitSynthCommand(app));
		frame.setDocSynthCommand(new DocumentationCommand());
		frame.setAboutSynthCommand(new AboutCommand());
		frame.setToolbarPlayCommand(new ToolbarPlayCommand(app.getSynthesizer().getPresentation()));
		frame.setToolbarPauseCommand(new ToolbarPauseCommand(app.getSynthesizer().getPresentation()));

		frame.setAddModuleOUTCommand(new AddModuleOUTCommand(app.getSynthesizer()));
		frame.setAddModuleVCOCommand(new AddModuleVCOCommand(app.getSynthesizer()));
		frame.setAddModuleVCFCommand(new AddModuleVCFCommand(app.getSynthesizer()));
		frame.setAddModuleEGCommand(new AddModuleEGCommand(app.getSynthesizer()));
		frame.setAddModuleAudioScopeCommand(new AddModuleAudioScopeCommand(app.getSynthesizer()));
		
		// Start the application
		app.startSynth();
	}
}
