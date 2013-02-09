package fr.istic.synthlab.abstraction.module.vca;

import fr.istic.synthlab.abstraction.module.IModule;
import fr.istic.synthlab.abstraction.port.IInputPort;
import fr.istic.synthlab.abstraction.port.IOutputPort;

public interface IModuleVCA extends IModule {

	IInputPort getInput();

	IInputPort getInputAM();

	IOutputPort getOutput();

	/**
	 * Set the attenuation value (between -30 and 12)
	 * 
	 * @param Attenuatuin value
	 */
	public void setAttenuationValue(double value);

	/**
	 * Return the attenuation value (between -30 and 12)
	 * 
	 * @param Attenuation value
	 */
	public double getAttenuationValue();

}
