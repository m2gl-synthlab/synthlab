package fr.istic.synthlab.command.toolbar;

import fr.istic.synthlab.ISynthApp;
import fr.istic.synthlab.SynthFrame;
import fr.istic.synthlab.command.ICommand;

public class ToolbarCurrentWireColorCommand implements ICommand {

	private SynthFrame frame;
	private ISynthApp app;
	
	public ToolbarCurrentWireColorCommand(ISynthApp app, SynthFrame frame){
		this.frame = frame;
		this.app = app;
	}

	@Override
	public void execute() {
		app.getSynthesizer().setCurrentWireColor(frame.getCurrentWireColor());
	}
}
