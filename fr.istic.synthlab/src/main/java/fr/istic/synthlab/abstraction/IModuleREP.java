package fr.istic.synthlab.abstraction;

public interface IModuleREP extends IModule {

	/**
	 * Return the input port of the REP module
	 * 
	 * @return input
	 */
	public IInputPort getInput();
	
	/**
	 * Return one output port of the REP module
	 * 
	 * @return outputs[ref]
	 */
	public IOutputPort getOutput(int ref);

}
