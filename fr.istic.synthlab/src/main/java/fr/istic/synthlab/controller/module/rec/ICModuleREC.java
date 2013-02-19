package fr.istic.synthlab.controller.module.rec;

import fr.istic.synthlab.abstraction.module.rec.IModuleREC;
import fr.istic.synthlab.command.toolbar.ToolbarRecordCommand;
import fr.istic.synthlab.controller.module.ICModule;
import fr.istic.synthlab.presentation.module.rec.IPModuleREC;

/**
 * Interface of a REC module controller
 */
public interface ICModuleREC extends ICModule, IModuleREC {

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.istic.synthlab.controller.ICModule#getPresentation()
	 */
	public IPModuleREC getPresentation();

	/**
	 * Called by the presentation when the mute button is clicked
	 */
	public void p2cRecord();

	/**
	 * Called by the presentation when the gain changed
	 * 
	 * @param gain
	 */
	public void p2cGainChanged(double gain);
	
	/**
	 * Set the record command
	 * 
	 * @param toolbarRecordCommand
	 */
	public void setRecordCmd(ToolbarRecordCommand toolbarRecordCommand);
}
