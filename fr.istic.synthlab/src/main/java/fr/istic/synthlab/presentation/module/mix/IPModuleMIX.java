package fr.istic.synthlab.presentation.module.mix;

import fr.istic.synthlab.controller.module.mix.ICModuleMIX;
import fr.istic.synthlab.presentation.module.IPModule;

public interface IPModuleMIX extends IPModule{
	
	public ICModuleMIX getControl();
}
