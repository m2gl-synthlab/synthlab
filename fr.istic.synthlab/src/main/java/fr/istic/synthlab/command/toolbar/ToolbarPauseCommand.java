package fr.istic.synthlab.command.toolbar;

import fr.istic.synthlab.command.ICommand;
import fr.istic.synthlab.presentation.IPSynthesizer;

public class ToolbarPauseCommand implements ICommand {

	private IPSynthesizer synth;

	public ToolbarPauseCommand(IPSynthesizer synth) {
		this.synth = synth;
	}
	
	@Override
	public void execute() {
		synth.stop();
		System.out.println("Pause");
	}

}
