package fr.istic.synthlab.presentation.impl;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import fr.istic.synthlab.controller.ICModule;
import fr.istic.synthlab.controller.ICSynthesizer;
import fr.istic.synthlab.controller.ICWire;
import fr.istic.synthlab.presentation.IPPort;
import fr.istic.synthlab.presentation.IPWire;

public class PWire extends JPanel implements IPWire {

	private ICWire ctrl;
	
	/**
	 * @param control
	 */
	public PWire(ICWire control) {
		this.ctrl = control;
		this.setSize(10, 60);
		this.setPreferredSize(this.getSize());
		this.setBorder(BorderFactory.createTitledBorder(getClass().getSimpleName()));
	}
	
	@Override
	public ICWire getControl() {
		return ctrl;
	}

	@Override
	public void connect(IPPort presentation) {
		this.add(new JLabel(presentation.getClass().getSimpleName()));
	}

}
