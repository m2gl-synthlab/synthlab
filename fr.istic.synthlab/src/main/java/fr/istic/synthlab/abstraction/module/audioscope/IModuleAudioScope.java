package fr.istic.synthlab.abstraction.module.audioscope;

import java.awt.Color;

import com.jsyn.scope.AudioScopeModel;

import fr.istic.synthlab.abstraction.module.IModule;
import fr.istic.synthlab.abstraction.port.IInputPort;
import fr.istic.synthlab.abstraction.port.IOutputPort;

/**
 * Interface for an AudioScope Module
 */
public interface IModuleAudioScope extends IModule {

	/**
	 * Default parameters
	 */
	static final double DEFAULT_VERTICAL_SCALE_Y = 2.0;
	static final Color DEFAULT_WAVE_TRACE_COLOR = Color.DARK_GRAY;
	static final double DEFAULT_TRIGGER_LEVEL = 0.01;
	
	/**
	 * Return the input port of the AudioScope module
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
	 * 
	 * @return
	 */
	public AudioScopeModel getModel();

}
