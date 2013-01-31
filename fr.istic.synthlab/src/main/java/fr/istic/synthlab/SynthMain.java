package fr.istic.synthlab;

import fr.istic.synthlab.command.app.DisplayCommand;
import fr.istic.synthlab.command.app.UndisplayCommand;
import fr.istic.synthlab.command.menu.AboutCommand;
import fr.istic.synthlab.command.menu.DocumentationCommand;
import fr.istic.synthlab.command.menu.NewSynthCommand;
import fr.istic.synthlab.command.menu.OpenSynthCommand;
import fr.istic.synthlab.command.menu.QuitSynthCommand;
import fr.istic.synthlab.command.menu.SaveSynthCommand;
import fr.istic.synthlab.command.toolbar.ToolbarDefaultCommand;
import fr.istic.synthlab.command.toolbar.ToolbarModuleCommand;
import fr.istic.synthlab.command.toolbar.ToolbarWireCommand;
import fr.istic.synthlab.controller.ICSynthesizer;
import fr.istic.synthlab.controller.impl.CSynthesizer;
import fr.istic.synthlab.factory.IFactory;
import fr.istic.synthlab.factory.impl.AFactory;
import fr.istic.synthlab.factory.impl.CFactory;
import fr.istic.synthlab.factory.impl.PACFactory;
import fr.istic.synthlab.factory.impl.PFactory;

public class SynthMain {
	public static void main(String[] args) {
		
		// Initialize factories
		PACFactory.setAFactory(AFactory.getInstance());
		PACFactory.setCFactory(CFactory.getInstance());
		PACFactory.setPFactory(PFactory.getInstance());
		IFactory factory = PACFactory.getFactory();
		
		// Create the application
		SynthApp app = new SynthApp();
		
		// Create the main frame
		SynthFrame frame = new SynthFrame();
		frame.setTitle("Synthlab - G");
		
		// Create a synthesizer controller
		ICSynthesizer syn = (CSynthesizer)factory.newSynthesizer(factory);

		// Configure the application
		app.setSynthesizer(syn);
		app.setDisplaySynthCommand(new DisplayCommand(app, frame));
		app.setUndisplaySynthCommand(new UndisplayCommand(app, frame));

		// Configure the frame
		frame.setNewSynthCommand(new NewSynthCommand(app));
		frame.setOpenSynthCommand(new OpenSynthCommand());
		frame.setSaveSynthCommand(new SaveSynthCommand());
		frame.setQuitSynthCommand(new QuitSynthCommand(app));
		frame.setDocSynthCommand(new DocumentationCommand());
		frame.setAboutSynthCommand(new AboutCommand());
		frame.setToolbarDefaultCommand(new ToolbarDefaultCommand());
		frame.setToolbarWireCommand(new ToolbarWireCommand());
		frame.setToolbarModuleCommand(new ToolbarModuleCommand());
		
		// Start the application
		app.startSynth();
	}
}
