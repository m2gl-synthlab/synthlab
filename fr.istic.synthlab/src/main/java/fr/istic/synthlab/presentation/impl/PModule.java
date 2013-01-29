package fr.istic.synthlab.presentation.impl;

import javax.swing.JLabel;
import javax.swing.JPanel;

import fr.istic.synthlab.controller.ICModule;
import fr.istic.synthlab.presentation.IPModule;

public class PModule extends JPanel implements IPModule{

	public PModule(ICModule control){
		JLabel nameLbl = new JLabel(control.toString());
		this.add(nameLbl);
	}
}
