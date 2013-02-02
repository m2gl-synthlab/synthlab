package fr.istic.synthlab.command.toolbar;

import fr.istic.synthlab.command.ICommand;
import fr.istic.synthlab.presentation.IPSynthesizer;

public class ToolbarPlayCommand implements ICommand {

	private IPSynthesizer synth;

	public ToolbarPlayCommand(IPSynthesizer synth) {
		this.synth = synth;
	}
	
	@Override
	public void execute() {
		synth.start();
	}

}
