package fr.istic.synthlab.command.abstraction;

import fr.istic.synthlab.abstraction.IModuleVCO;
import fr.istic.synthlab.command.ICommand;
import fr.istic.synthlab.presentation.IPModuleVCO;

/**
 * Command that display the synthesizer
 */
public class DisplayFrequencyCommand implements ICommand {

	private IModuleVCO moduleVCO;
	private IPModuleVCO presentationVCO;

	public DisplayFrequencyCommand(IModuleVCO moduleVCO, IPModuleVCO presentationVCO) {
		this.moduleVCO = moduleVCO;
		this.presentationVCO = presentationVCO;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.istic.synthlab.command.ICommand#execute()
	 */
	@Override
	public void execute() {
		presentationVCO.c2pSetFrequencyValue(moduleVCO.getFrequency());
	}
}