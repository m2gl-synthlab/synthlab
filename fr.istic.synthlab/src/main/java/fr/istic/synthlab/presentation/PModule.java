package fr.istic.synthlab.presentation;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class PModule extends JPanel{

	public PModule(String name){
		JLabel nameLbl = new JLabel(name);
		this.add(nameLbl);
	}
}
