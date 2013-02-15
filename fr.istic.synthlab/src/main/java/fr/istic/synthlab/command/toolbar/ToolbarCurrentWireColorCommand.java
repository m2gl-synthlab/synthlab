package fr.istic.synthlab.command.toolbar;

import fr.istic.synthlab.SynthFrame;
import fr.istic.synthlab.command.ICommand;
import fr.istic.synthlab.controller.synthesizer.CSynthesizer;

public class ToolbarCurrentWireColorCommand implements ICommand {

	private SynthFrame frame;

	public ToolbarCurrentWireColorCommand(SynthFrame frame) {
		this.frame = frame;
	}

	@Override
	public void execute() {
		CSynthesizer.getInstance().setCurrentWireColor(frame.getCurrentWireColor());
	}
}
