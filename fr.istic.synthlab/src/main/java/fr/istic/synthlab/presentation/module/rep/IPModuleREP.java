package fr.istic.synthlab.presentation.module.rep;

import fr.istic.synthlab.controller.module.rep.ICModuleREP;
import fr.istic.synthlab.presentation.module.IPModule;

/**
 * Interface for an REP module presentation
 */
public interface IPModuleREP extends IPModule {

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.istic.synthlab.presentation.module.IPModule#getControl()
	 */
	public ICModuleREP getControl();

}
