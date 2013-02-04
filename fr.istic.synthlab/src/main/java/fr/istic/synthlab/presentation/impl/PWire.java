package fr.istic.synthlab.presentation.impl;

import java.awt.Dimension;

import javax.swing.JPanel;

import fr.istic.synthlab.controller.ICWire;
import fr.istic.synthlab.presentation.IPInputPort;
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

	private int inputx;
	private int inputy;
	private int outputx;
	private int outputy;
	@Override
	public void connect(IPInputPort inputPortPresentation,
			IPOutputPort outputPortPresentation) {
		inputx = (((JPanel)inputPortPresentation).getParent().getX())
				+(((JPanel)inputPortPresentation).getX());
		inputy = ((JPanel)inputPortPresentation).getParent().getY()
				+((JPanel)inputPortPresentation).getY();
		outputx = ((JPanel)outputPortPresentation).getParent().getX()
				+((JPanel)outputPortPresentation).getX();
		outputy = ((JPanel)outputPortPresentation).getParent().getY()
				+((JPanel)outputPortPresentation).getY();
		
		int diffx;
		if(inputx<outputx) diffx =  outputx-inputx;
		else diffx = inputx-outputx;
		System.out.println("diffx=" + diffx);
		
		int diffy;
		if(inputy<outputy) diffy =  outputy-inputy;
		else {
			diffy = inputy-outputy;
			diffy=-diffy;
		}
		
		this.setPreferredSize(new Dimension(diffx, diffy));
		
		if(inputx<outputx){
		this.addLine(0, 0, diffx, diffy);
		} else {
			this.addLine(diffx,0, 0, diffy);
		}
	
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
	
}
