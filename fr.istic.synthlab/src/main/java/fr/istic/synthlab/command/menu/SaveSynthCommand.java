package fr.istic.synthlab.command.menu;

import fr.istic.synthlab.ISynthApp;
import fr.istic.synthlab.command.ICommand;

public class SaveSynthCommand implements ICommand {

	private ISynthApp synthApp;
	
	public SaveSynthCommand(ISynthApp synthApp){
		this.synthApp = synthApp;
	}
	
	@Override
	public void execute() {
		synthApp.saveToXML();
	}

}
