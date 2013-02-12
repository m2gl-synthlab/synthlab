package fr.istic.synthlab.command.menu;

import fr.istic.synthlab.abstraction.synthesizer.ISynthesizer;
import fr.istic.synthlab.command.ICommand;
import fr.istic.synthlab.controller.synthesizer.ICSynthesizer;

public class SaveSynthCommand implements ICommand {

	private ISynthesizer synth;
	
	public SaveSynthCommand(ISynthesizer synth){
		this.synth = synth;
	}
	
	@Override
	public void execute() {
		((ICSynthesizer)synth).saveToXML();
	}

}
