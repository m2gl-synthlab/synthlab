package fr.istic.synthlab.abstraction.module.audioscope;

import com.jsyn.scope.AudioScopeModel;

import fr.istic.synthlab.abstraction.module.IModule;
import fr.istic.synthlab.abstraction.port.IInputPort;
import fr.istic.synthlab.abstraction.port.IOutputPort;

/**
 * Interface for an AudioScope Module
 */
public interface IModuleAudioScope extends IModule {

	/**
	 * Return the input port of the AudioScope  module
	 * 
	 * @return input
	 */
	public IInputPort getInput();

	/**
	 * Return the output port of the AudioScope module
	 * 
	 * @return output
	 */
	public IOutputPort getOutput();
	

	/**
	 * Return the AudioScope model
	 * @return
	 */
	public AudioScopeModel getModel();

}
