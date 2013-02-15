package fr.istic.synthlab.command.menu;

import fr.istic.synthlab.ISynthApp;
import fr.istic.synthlab.command.ICommand;
import fr.istic.synthlab.controller.synthesizer.ICSynthesizer;
import fr.istic.synthlab.factory.IFactory;
import fr.istic.synthlab.factory.impl.PACFactory;

public class AddModuleAudioScopeCommand implements ICommand {

	private ISynthApp app;
	public AddModuleAudioScopeCommand(ISynthApp app) {
		this.app = app;
	}

	@Override
	public void execute() {
		IFactory fact = PACFactory.getFactory();
		app.getSynthesizer().add(fact.newAudioScope(app.getSynthesizer()));
	}
}