package fr.istic.synthlab.presentation.impl;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

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

	private Color color;


	/**
	 * @param control
	 */
	public PWire(ICWire control) {
		this.ctrl = control;
		configView();
		defineCallbacks();
	}

	private void configView() {
		posInput = new Point();
		posOutput = new Point();
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

		posOutput = ((JPanel) outputPort).getLocation();
		Point offsetModule = ((JPanel) outputPort).getParent().getLocation();

		posOutput.x += offsetModule.x;
		posOutput.y += offsetModule.y;

		System.out.println("output : " + posOutput.x + "," + posOutput.y);

		updateDisplay();
	}

	@Override
	public void c2pConnectIn(IPInputPort inputPort) {
		System.out.println("Connecting to In");

		posInput = ((JPanel) inputPort).getLocation();
		Point offsetModule = ((JPanel) inputPort).getParent().getLocation();

		posInput.x += offsetModule.x;
		posInput.y += offsetModule.y;

//		System.out.println("input : " + posInput.x + "," + posInput.y);

		updateDisplay();
	}

	@Override
	public void updateDisplay() {
		System.out.println("Updating wire " + posInput + " " + posOutput);

		Rectangle r = new Rectangle(posInput);
		r.add(posOutput);

		setPreferredSize(r.getSize());
		setBounds(r);

		setPreferredSize(new Dimension(r.width, r.height));
		repaint();
		validate();

	}
	
	@Override
	public void paint(Graphics g) {
		System.out.println("painting");
		
		g.setColor(Color.BLACK);
		g.drawLine(posInput.x, posInput.y, posOutput.x, posOutput.y);
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
