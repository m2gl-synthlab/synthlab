package fr.istic.synthlab.presentation.module.keyboard;

import fr.istic.synthlab.controller.module.keyboard.ICModuleKeyboard;
import fr.istic.synthlab.presentation.module.IPModule;

public interface IPModuleKeyboard extends IPModule {

	@Override
	public ICModuleKeyboard getControl();

}
