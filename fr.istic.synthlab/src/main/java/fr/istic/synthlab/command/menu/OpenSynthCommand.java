package fr.istic.synthlab.command.menu;

import fr.istic.synthlab.abstraction.synthesizer.ISynthesizer;
import fr.istic.synthlab.command.ICommand;
import fr.istic.synthlab.controller.synthesizer.ICSynthesizer;

public class OpenSynthCommand implements ICommand {

	private ISynthesizer synth;
	
	public OpenSynthCommand(ISynthesizer synth){
		this.synth = synth;
	}
	
	@Override
	public void execute() {
		((ICSynthesizer)synth).loadFromXML();
	}

}