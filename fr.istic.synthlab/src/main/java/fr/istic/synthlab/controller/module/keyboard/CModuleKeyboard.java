package fr.istic.synthlab.controller.module.keyboard;

import fr.istic.synthlab.abstraction.module.keyboard.ModuleKeyboard;
import fr.istic.synthlab.abstraction.synthesizer.ISynthesizer;
import fr.istic.synthlab.controller.module.ICModule;
import fr.istic.synthlab.controller.synthesizer.ICSynthesizer;
import fr.istic.synthlab.presentation.module.keyboard.IPModuleKeyboard;
import fr.istic.synthlab.presentation.synthesizer.IPSynthesizer;

public class CModuleKeyboard extends ModuleKeyboard implements ICModuleKeyboard{

	private ISynthesizer cSynthesizer;


	public CModuleKeyboard(ICSynthesizer synth) {
		super(synth);
		this.cSynthesizer = synth;
	}

	@Override
	public IPModuleKeyboard getPresentation() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void p2cClosing() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setParameter(String key, Double value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public IPSynthesizer getSynthesizerPresentation() {
		return ((ICSynthesizer)cSynthesizer).getPresentation();
	}
	

	@Override
	public void p2cRemoveModule(ICModule module) {
		((ICSynthesizer) cSynthesizer).p2cRemoveModule(module);
	}

}
