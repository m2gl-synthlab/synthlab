
package fr.istic.synthlab;

import com.alee.laf.WebLookAndFeel;

import fr.istic.synthlab.command.app.DisplayCommand;
import fr.istic.synthlab.command.app.UndisplayCommand;
import fr.istic.synthlab.command.menu.AboutCommand;
import fr.istic.synthlab.command.menu.AddModuleAudioScopeCommand;
import fr.istic.synthlab.command.menu.AddModuleEGCommand;
import fr.istic.synthlab.command.menu.AddModuleMIXCommand;
import fr.istic.synthlab.command.menu.AddModuleOUTCommand;
import fr.istic.synthlab.command.menu.AddModuleREPCommand;
import fr.istic.synthlab.command.menu.AddModuleVCACommand;
import fr.istic.synthlab.command.menu.AddModuleVCFHPCommand;
import fr.istic.synthlab.command.menu.AddModuleVCFLPCommand;
import fr.istic.synthlab.command.menu.AddModuleVCOCommand;
import fr.istic.synthlab.command.menu.DocumentationCommand;
import fr.istic.synthlab.command.menu.NewSynthCommand;
import fr.istic.synthlab.command.menu.OpenSynthCommand;
import fr.istic.synthlab.command.menu.QuitSynthCommand;
import fr.istic.synthlab.command.menu.SaveSynthCommand;
import fr.istic.synthlab.command.toolbar.ToolbarCurrentWireColorCommand;
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
		app.setDisplaySynthCommand(new DisplayCommand(frame));
		app.setUndisplaySynthCommand(new UndisplayCommand(frame));

		// Create a default synthesizer
		app.newSynth();
  
		// Configure the frame
		frame.setNewSynthCommand(new NewSynthCommand(app));
		frame.setOpenSynthCommand(new OpenSynthCommand(app, frame));
		frame.setSaveSynthCommand(new SaveSynthCommand(app, frame));
		frame.setQuitSynthCommand(new QuitSynthCommand(app));
		frame.setDocSynthCommand(new DocumentationCommand());
		frame.setAboutSynthCommand(new AboutCommand());
		frame.setToolbarPlayCommand(new ToolbarPlayCommand(app.getSynthesizer().getPresentation()));
		frame.setToolbarPauseCommand(new ToolbarPauseCommand(app.getSynthesizer().getPresentation()));
		frame.setCurrentWireColorCommand(new ToolbarCurrentWireColorCommand(frame));
		
		frame.setAddModuleOUTCommand(new AddModuleOUTCommand());
		frame.setAddModuleVCOCommand(new AddModuleVCOCommand());
		frame.setAddModuleVCACommand(new AddModuleVCACommand());
		frame.setAddModuleVCFLPCommand(new AddModuleVCFLPCommand());
		frame.setAddModuleVCFHPCommand(new AddModuleVCFHPCommand());
		frame.setAddModuleEGCommand(new AddModuleEGCommand());
		frame.setAddModuleAudioScopeCommand(new AddModuleAudioScopeCommand());
		frame.setAddModuleREPCommand(new AddModuleREPCommand());
		frame.setAddModuleMIXCommand(new AddModuleMIXCommand());
		
		// Start the application
		app.startSynth();
		
		// Chargement du fichier ouvert
		String filename = null;
		if(args.length > 0){
			filename = args[0];
			app.loadFromXML("", filename);
		}
		
	}
}
