package fr.istic.synthlab.presentation.module.rep;

import fr.istic.synthlab.controller.module.rep.ICModuleREP;
import fr.istic.synthlab.presentation.module.IPModule;

public interface IPModuleREP extends IPModule {

	public ICModuleREP getControl();

}
