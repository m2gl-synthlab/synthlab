package fr.istic.synthlab.presentation.impl;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import fr.istic.synthlab.controller.ICSynthesizer;
import fr.istic.synthlab.presentation.IPModule;
import fr.istic.synthlab.presentation.IPSynthesizer;

public class PSynthesizer extends JPanel implements IPSynthesizer {

	private static final long serialVersionUID = -1444696064954307756L;
	private ICSynthesizer ctrl;

	public PSynthesizer(ICSynthesizer control) {
		ctrl = control;
		this.setBorder(BorderFactory.createTitledBorder(getClass().getSimpleName()));
	}

	@Override
	public void addModule(IPModule module) {
		this.add((PModule) module);
	}

	@Override
	public void removeModule(IPModule module) {
		this.remove((PModule) module);
	}

	@Override
	public ICSynthesizer getControl() {
		return ctrl;
	}

}