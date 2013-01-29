package fr.istic.synthlab.presentation;

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class PSynthetizer extends JPanel {

	public PSynthetizer(){

		//this.setLayout(new BorderLayout());
		JLabel label = new JLabel("PSynthetizer!");
		this.add(label);
	}

	public void addPModule(IPModule ipModule) {
		this.add((Component)ipModule);
	}
}
