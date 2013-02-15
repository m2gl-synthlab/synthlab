package fr.istic.synthlab.abstraction.module.keyboard;

import fr.istic.synthlab.abstraction.module.IModule;
import fr.istic.synthlab.abstraction.port.IOutputPort;

/**
 * Interface of a piano keyboard
 */
public interface IModuleKeyboard extends IModule {

	/**
	 * Play a note with the given frequency
	 * 
	 * @param frequency
	 */
	public void play(double frequency);

	/**
	 * Stop playing the note
	 */
	public void stop();

	/**
	 * Return the output port of the EG module
	 * 
	 * @return output
	 */
	public IOutputPort getOutput();
}
