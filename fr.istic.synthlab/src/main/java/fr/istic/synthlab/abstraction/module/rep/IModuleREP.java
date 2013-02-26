package fr.istic.synthlab.abstraction.module.rep;

import fr.istic.synthlab.abstraction.module.IModule;
import fr.istic.synthlab.abstraction.port.IInputPort;
import fr.istic.synthlab.abstraction.port.IOutputPort;

/**
 * A module that replicate the input on the 3 output
 */
public interface IModuleREP extends IModule {

	/**
	 * Return the input port of the REP module
	 * 
	 * @return in
	 */
	public IInputPort getInput();

	/**
	 * Return output1 port of the REP module
	 * 
	 * @return output1
	 */
	public IOutputPort getOutput1();

	/**
	 * Return output2 port of the REP module
	 * 
	 * @return output2
	 */
	public IOutputPort getOutput2();

	/**
	 * Return output3 port of the REP module
	 * 
	 * @return output3
	 */
	public IOutputPort getOutput3();

}
