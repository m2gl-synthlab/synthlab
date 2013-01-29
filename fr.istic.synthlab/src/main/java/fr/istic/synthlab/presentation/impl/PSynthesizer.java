package fr.istic.synthlab.presentation.impl;

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JPanel;

import fr.istic.synthlab.controller.ICSynthesizer;
import fr.istic.synthlab.presentation.IPModule;
import fr.istic.synthlab.presentation.IPSynthesizer;

public class PSynthesizer extends JPanel implements IPSynthesizer {

	ICSynthesizer ctrl;
	
	public PSynthesizer(ICSynthesizer control){
		ctrl = control;
		//this.setLayout(new BorderLayout());
		JLabel label = new JLabel("PSynthetizer!");
		this.add(label);
	}

	public void addPModule(IPModule ipModule) {
		this.add((Component)ipModule);
	}

	@Override
	public ICSynthesizer getControl() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addModule(IPModule module) {
		this.add((Component) module);
	}

	@Override
	public void removeModule(IPModule module) {
		// TODO Auto-generated method stub
		
	}
}