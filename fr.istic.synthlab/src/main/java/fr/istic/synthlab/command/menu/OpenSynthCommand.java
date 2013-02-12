package fr.istic.synthlab.command.menu;

import fr.istic.synthlab.ISynthApp;
import fr.istic.synthlab.command.ICommand;

public class OpenSynthCommand implements ICommand {

	private ISynthApp app;
	
	public OpenSynthCommand(ISynthApp app){
		this.app = app;
	}
	
	@Override
	public void execute() {
		app.loadFromXML();
	}

}