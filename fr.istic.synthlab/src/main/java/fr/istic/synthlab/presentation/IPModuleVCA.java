package fr.istic.synthlab.presentation;

import fr.istic.synthlab.controller.ICModuleVCA;

public interface IPModuleVCA extends IPModule {
	
	/* (non-Javadoc)
	 * @see fr.istic.synthlab.presentation.IPModule#getControl()
	 */
	public ICModuleVCA getControl();
	
	public void c2pDoSomething();
}
