package fr.istic.synthlab.presentation.impl;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;

import fr.istic.synthlab.LinesComponent;
import fr.istic.synthlab.controller.ICWire;
import fr.istic.synthlab.presentation.IPWire;

public class PWire extends JPanel implements IPWire {

	private static final long serialVersionUID = 433233331577188149L;
	
	private ICWire ctrl;
	private LinesComponent comp;
	int x, y;
	
	/**
	 * @param control
	 */
	public PWire(ICWire control) {
		this.ctrl = control;
		
		x=y=-1;
		
		this.setSize(60, 60);
		this.setPreferredSize(this.getSize());
		this.add(new JLabel("Wire"));
		
		comp = new LinesComponent();
		comp.setPreferredSize(new Dimension(1000, 1000));
		this.add(comp, BorderLayout.CENTER);
	}
	
	@Override
	public ICWire getControl() {
		return ctrl;
	}

	@Override
	public void setXY(int x, int y) {
		System.out.println("x="+x+" y="+y);
		if((x==-1)&&(y==-1)) {
			this.x=x;
			this.y=y;
			return;
		}
		comp.addLine(x, y, this.x, this.y);
		this.repaint();
	}
}
