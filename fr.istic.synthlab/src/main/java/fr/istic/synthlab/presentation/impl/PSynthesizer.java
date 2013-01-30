package fr.istic.synthlab.presentation.impl;

import javax.swing.JLabel;
import javax.swing.JPanel;

import fr.istic.synthlab.controller.ICSynthesizer;
import fr.istic.synthlab.presentation.IPModule;
import fr.istic.synthlab.presentation.IPSynthesizer;

public class PSynthesizer extends JPanel implements IPSynthesizer {

	private static final long serialVersionUID = -1444696064954307756L;
	private ICSynthesizer ctrl;

	public PSynthesizer(ICSynthesizer control) {
		ctrl = control;

		JLabel label = new JLabel(getClass().getSimpleName());
		this.add(label);
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