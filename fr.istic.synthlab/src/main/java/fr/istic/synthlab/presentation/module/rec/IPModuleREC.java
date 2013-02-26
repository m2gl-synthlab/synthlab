package fr.istic.synthlab.presentation.module.rec;

import fr.istic.synthlab.controller.module.rec.ICModuleREC;
import fr.istic.synthlab.presentation.module.IPModule;

/**
 * Interface for an REC module presentation
 */
public interface IPModuleREC extends IPModule {

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.istic.synthlab.presentation.IPModule#getControl()
	 */
	public ICModuleREC getControl();

	/**
	 * Called by the controller to inform the presentation of the new mute value
	 */
	public void c2pRecord(boolean value);

	/**
	 * Called by the controller to inform the presentation of the new gain value
	 * 
	 * @param gain
	 */
	public void c2pSetGainValue(double gain);

}
