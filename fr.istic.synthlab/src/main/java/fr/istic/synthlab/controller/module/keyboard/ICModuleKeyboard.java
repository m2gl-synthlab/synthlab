package fr.istic.synthlab.controller.module.keyboard;

import fr.istic.synthlab.abstraction.module.keyboard.IModuleKeyboard;
import fr.istic.synthlab.controller.module.ICModule;
import fr.istic.synthlab.presentation.module.keyboard.IPModuleKeyboard;

public interface ICModuleKeyboard extends ICModule, IModuleKeyboard {

	@Override
	public IPModuleKeyboard getPresentation();

}
