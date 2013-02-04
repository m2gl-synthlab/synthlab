package fr.istic.synthlab.presentation.impl;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;

import javax.swing.JPanel;

import fr.istic.synthlab.controller.ICWire;
import fr.istic.synthlab.controller.impl.CInputPort;
import fr.istic.synthlab.controller.impl.COutputPort;
import fr.istic.synthlab.presentation.IPInputPort;
import fr.istic.synthlab.presentation.IPOutputPort;
import fr.istic.synthlab.presentation.IPWire;

public class PWire extends JPanel implements IPWire {

	private static final long serialVersionUID = 433233331577188149L;

	private ICWire ctrl;

	private Point posInput;
	private Point posOutput;

	/**
	 * @param control
	 */
	public PWire(ICWire control) {
		this.ctrl = control;
		configView();
		defineCallbacks();
	}

	private void configView() {
		setOpaque(true);
		setBackground(Color.BLACK);
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

		updateDisplay();
	}

	@Override
	public void updateDisplay() {
		System.out.println("Updating wire " + posInput + " " + posOutput);

		if(ctrl.getInput() != null)
			posInput = ((PInputPort) ((CInputPort)ctrl.getInput()).getPresentation()).getLocation();
		if(ctrl.getOutput() != null)
			posOutput = ((POutputPort) ((COutputPort)ctrl.getOutput()).getPresentation()).getLocation();
		
		
		int x = 0;
		int y = 0;
		int height = 0;
		int width  = 0;
		
		if(posInput.x < posOutput.x){
			x = posInput.x;
			width = posOutput.x - posInput.x;
		}else{
			x = posOutput.x;
			width = posInput.x - posOutput.x;
		}
		
		if(posInput.y < posOutput.y){
			y = posInput.y;
			height = posOutput.y - posInput.y;
		}else{
			y = posOutput.y;
			height = posInput.y - posOutput.y;
		}
		
		setPreferredSize(new Dimension(width, height));
		setBounds(x, y, width, height);
		repaint();
		validate();

	}
	
	@Override
	public void paint(Graphics g) {
		System.out.println("painting");
		
		g.setColor(Color.BLACK);
		g.drawLine(posOutput.x, posOutput.y, posInput.x, posInput.y);
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
