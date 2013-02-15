package fr.istic.synthlab.controller.module.rep;

import fr.istic.synthlab.abstraction.module.rep.ModuleREP;
import fr.istic.synthlab.abstraction.synthesizer.ISynthesizer;
import fr.istic.synthlab.abstraction.wire.IWire;
import fr.istic.synthlab.controller.module.ICModule;
import fr.istic.synthlab.controller.synthesizer.ICSynthesizer;
import fr.istic.synthlab.factory.impl.PACFactory;
import fr.istic.synthlab.presentation.module.IPModule;
import fr.istic.synthlab.presentation.module.rep.IPModuleREP;
import fr.istic.synthlab.presentation.synthesizer.IPSynthesizer;

//TODO : need comments

public class CModuleREP extends ModuleREP implements ICModuleREP {

	private IPModuleREP pres;
	private ISynthesizer cSynthesizer;
	
	public CModuleREP(ISynthesizer cSynthesizer) {
		super(cSynthesizer);
		this.cSynthesizer = cSynthesizer;
		this.pres = PACFactory.getPFactory().newREP(this);	
	}

	@Override
	public IPModule getPresentation() {
		return this.pres;
	}

	@Override
	public void p2cClosing() {
		for (IWire w : this.getWires()) {
			w.disconnect();
		}
	}

	@Override
	public IPSynthesizer getSynthesizerPresentation() {
		return ((ICSynthesizer)cSynthesizer).getPresentation();
	}
	

	@Override
	public void p2cRemoveModule(ICModule module) {
		((ICSynthesizer) cSynthesizer).p2cRemoveModule(module);
	}

	@Override
	public void setParameter(String key, Double value) {}
}
