package fr.istic.synthlab.abstraction;

public interface IModuleVCA extends IModule {

	IInputPort getInput();

	IInputPort getInputAM();

	IOutputPort getOutput();

	IParameter getParameter();

}
