package fr.istic.synthlab.presentation;

import fr.istic.synthlab.controller.ICModuleVCO;

public interface IPModuleVCO extends IPModule {
	
	/* (non-Javadoc)
	 * @see fr.istic.synthlab.presentation.IPModule#getControl()
	 */
	public ICModuleVCO getControl();
	
	public void c2pDoSomething();

}
