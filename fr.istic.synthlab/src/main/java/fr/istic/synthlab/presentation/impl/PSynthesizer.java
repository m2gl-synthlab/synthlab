package fr.istic.synthlab.presentation.impl;

import java.awt.Color;

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
	public ICSynthesizer getControl() {
		return ctrl;
	}

	@Override
	public void start() {
		this.getControl().p2cStart();
	}

	@Override
	public void stop() {
		this.getControl().p2cStop();
	}

	@Override
	public void addModule(IPModule module) {
		this.getControl().p2cAddModule(module.getControl());
	}

	@Override
	public void removeModule(IPModule module) {
		this.getControl().p2cRemoveModule(module.getControl());
	}

	@Override
	public void c2pStart() {
		this.setBackground(Color.GREEN);
	}

	@Override
	public void c2pStop() {
		this.setBackground(Color.RED);
	}

	@Override
	public void c2pAddModule(IPModule module) {
		this.add((PModule) module);
	}

	@Override
	public void c2pAddModuleOk(IPModule module) {
		this.add((PModule) module);
	}

	@Override
	public void c2pRemoveModuleOk(IPModule module) {
		this.remove((PModule) module);
	}

}