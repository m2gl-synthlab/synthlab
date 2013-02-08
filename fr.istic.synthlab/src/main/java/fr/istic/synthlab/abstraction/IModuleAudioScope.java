package fr.istic.synthlab.abstraction;

import com.jsyn.scope.AudioScopeModel;

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
