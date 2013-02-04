package fr.istic.synthlab.presentation.impl;

import java.awt.BasicStroke;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

import javax.swing.JPanel;

import fr.istic.synthlab.controller.ICWire;
import fr.istic.synthlab.presentation.IPInputPort;
import fr.istic.synthlab.presentation.IPOutputPort;
import fr.istic.synthlab.presentation.IPWire;

public class PWire extends JPanel implements IPWire {

	private static final long serialVersionUID = 433233331577188149L;

	private ICWire ctrl;

	private Point posInput;
	private Point posOutput;
	private int height = 0;
	private int width = 0;
	private IPInputPort inputPort;
	private IPOutputPort outputPort;
	

	/**
	 * @param control
	 */
	public PWire(ICWire control) {
		this.ctrl = control;
		configView();
		defineCallbacks();
	}

	private void configView() {
		setOpaque(false);
	}

	private void defineCallbacks() {

	}

	@Override
	public ICWire getControl() {
		return ctrl;
	}

	@Override
	public void c2pConnectOut(IPOutputPort outputPort) {
		System.out.println("Connecting to out");
		this.outputPort = outputPort;
		if(inputPort != null)
			updateDisplay();
	}

	@Override
	public void c2pConnectIn(IPInputPort inputPort) {
		System.out.println("Connecting to In");
		this.inputPort = inputPort;
		if(outputPort != null)
			updateDisplay();
	}

	@Override
	public void updateDisplay() {

		posOutput = new Point(((JPanel) outputPort).getX(), ((JPanel) outputPort).getY());
		
		posOutput.x += ((JPanel) outputPort).getParent().getX();
		posOutput.y += ((JPanel) outputPort).getParent().getY();

		System.out.println("output : " + posOutput.x + "," + posOutput.y);

		
		posInput = new Point(((JPanel) inputPort).getX(), ((JPanel) inputPort).getY());

		posInput.x += ((JPanel) inputPort).getParent().getX();
		posInput.y += ((JPanel) inputPort).getParent().getY();

		System.out.println("input : " + posInput.x + "," + posInput.y);
		
		int x = 0;
		int y = 0;

		if (posInput.x < posOutput.x) {
			x = posInput.x;
			width = posOutput.x - posInput.x;
		} else {
			x = posOutput.x;
			width = posInput.x - posOutput.x;
		}

		if (posInput.y < posOutput.y) {
			y = posInput.y;
			height = posOutput.y - posInput.y;
		} else {
			y = posOutput.y;
			height = posInput.y - posOutput.y;
		}

		setPreferredSize(new Dimension(width, height));

		setBounds(x+55, y+53, width, height);
		repaint();
		validate();
	}

	@Override
	public void paint(Graphics g) {
		System.out.println("painting");

		Graphics2D g2 = (Graphics2D) g;
	    g2.setStroke(new BasicStroke(3));
	    
		if (posInput.x > posOutput.x) {
		     g2.drawLine(0, height, width, 0);  
		} else {
		     g2.drawLine(0, 0, width, height);  
		}
	}

	@Override
	public void c2pDisconnectIn(IPInputPort pInputPort) {
		// TODO Auto-generated method stub

	}

	@Override
	public void c2pDisconnectOut(IPOutputPort pOutputPort) {
		// TODO Auto-generated method stub

	}

}
