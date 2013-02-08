package fr.istic.synthlab.abstraction;

public interface IModuleREP extends IModule {

	/**
	 * Return the input port of the REP module
	 * 
	 * @return in
	 */
	public IInputPort getIn();
	
	/**
	 * Return out1 port of the REP module
	 * 
	 * @return out1
	 */
	public IOutputPort getOut1();
	
	/**
	 * Return out1 port of the REP module
	 * 
	 * @return out2
	 */
	public IOutputPort getOut2();
	
	/**
	 * Return out1 port of the REP module
	 * 
	 * @return out3
	 */
	public IOutputPort getOut3();

}
