package fr.istic.synthlab.presentation.module.mix;

import fr.istic.synthlab.controller.module.mix.ICModuleMIX;
import fr.istic.synthlab.presentation.module.IPModule;

public interface IPModuleMIX extends IPModule{
	
	public ICModuleMIX getControl();
	
	/**
	 * Called by the controller to inform the presentation of the new gain 1 value
	 * 
	 * @param gain
	 */
	public void c2pSetGain1Value(double gain);
	
	/**
	 * Called by the controller to inform the presentation of the new gain 2 value
	 * 
	 * @param gain
	 */
	public void c2pSetGain2Value(double gain);
	
	/**
	 * Called by the controller to inform the presentation of the new gain 2 value
	 * 
	 * @param gain
	 */
	public void c2pSetGain3Value(double gain);
	
	/**
	 * Called by the controller to inform the presentation of the new gain 4 value
	 * 
	 * @param gain
	 */
	public void c2pSetGain4Value(double gain);
}
