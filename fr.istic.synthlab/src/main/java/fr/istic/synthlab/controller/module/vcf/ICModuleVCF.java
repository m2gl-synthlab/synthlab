package fr.istic.synthlab.controller.module.vcf;

import fr.istic.synthlab.abstraction.module.vcf.IModuleVCF;
import fr.istic.synthlab.controller.module.ICModule;
import fr.istic.synthlab.presentation.module.vcf.IPModuleVCF;

public interface ICModuleVCF extends ICModule, IModuleVCF {

	/**
	 * Called by the presentation when the cut frequency parameter change
	 * 
	 * @param cut
	 *            frequency
	 */
	public void p2cCutFrequencyChanged(int cutFrequency);

	/**
	 * Called by the presentation when the resonance parameter changed
	 * 
	 * @param resonance
	 */
	public void p2cResonanceChanged(double resonance);

	public IPModuleVCF getPresentation();

}
