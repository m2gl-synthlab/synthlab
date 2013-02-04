package fr.istic.synthlab.presentation.impl;

import java.awt.Dimension;

import javax.swing.JPanel;

import fr.istic.synthlab.controller.ICModule;
import fr.istic.synthlab.controller.ICWire;
import fr.istic.synthlab.presentation.IPInputPort;
import fr.istic.synthlab.presentation.IPModule;
import fr.istic.synthlab.presentation.IPOutputPort;
import fr.istic.synthlab.presentation.IPWire;
import fr.istic.synthlab.presentation.util.LinesComponent;

public class PWire extends LinesComponent implements IPWire {

	private static final long serialVersionUID = 433233331577188149L;
	
	private ICWire ctrl;
	
	/**
	 * @param control
	 */
	public PWire(ICWire control) {
		super();
		this.ctrl = control;
		configView();
		defineCallbacks();
		
	}

	private void configView() {
	}

	private void defineCallbacks() {
	
	}
	
	@Override
	public ICWire getControl() {
		return ctrl;
	}

	private int inputx = 0;
	private int inputy = 0;
	private int outputx = 0;
	private int outputy = 0;
	@Override
	public void connect(IPInputPort inputPortPresentation, IPOutputPort outputPortPresentation) {
		
		
		inputx = (((JPanel)inputPortPresentation).getParent().getX())
				+(((JPanel)inputPortPresentation).getX());
		inputy = ((JPanel)inputPortPresentation).getParent().getY()
				+((JPanel)inputPortPresentation).getY();
		outputx = ((JPanel)outputPortPresentation).getParent().getX()
				+((JPanel)outputPortPresentation).getX();
		outputy = ((JPanel)outputPortPresentation).getParent().getY()
				+((JPanel)outputPortPresentation).getY();
		

		drawWire(inputx, inputy, outputx, outputy);

	
	}

	public int getx() {
		if(inputx < outputx)
			return inputx;
		else
			return outputx;
	}

	public int gety() {
		if(inputy < outputy)
			return inputy;
		else
			return outputy;
	}

	@Override
	public void c2pConnectOut(IPOutputPort outputPort) {
		System.out.println("Connecting to out");
		
		PModule pMod = (PModule)((ICModule)outputPort.getControl().getModule()).getPresentation();
		
		outputx = pMod.getX() + ((POutputPort)outputPort).getX();
		outputy = pMod.getY() + ((POutputPort)outputPort).getY();
		
		System.out.println("output : "+ outputx +","+outputx);
		drawWire(inputx, inputy, outputx, outputy);
	}

	@Override
	public void c2pConnectIn(IPInputPort inputPort) {
		System.out.println("Connecting to In");

		PModule pMod = (PModule)((ICModule)inputPort.getControl().getModule()).getPresentation();
		
		
		inputx = pMod.getX()+((PInputPort)inputPort).getX();
		inputy = pMod.getY()+((PInputPort)inputPort).getY();

		System.out.println("output : "+ inputx +","+inputy);
		drawWire(inputx, inputy, outputx, outputy);
	}
	
	
	private void drawWire(int x1, int y1, int x2, int y2){
		clearLines();
		int dx;
		if(x1<x2) dx =  x2-x1;
		else dx = inputx-outputx;
		
		int dy;
		if(y1<y2) dy =  y2-y1;
		else {
			dy = y1-y2;
			dy=-dy;
		}
		
		this.setPreferredSize(new Dimension(dx, dy));
		
		if(x1<x2){
		this.addLine(0, 0, dx, dy);
		} else {
			this.addLine(dx,0, 0, dy);
		}
		validate();
		repaint();
	}
	
}
