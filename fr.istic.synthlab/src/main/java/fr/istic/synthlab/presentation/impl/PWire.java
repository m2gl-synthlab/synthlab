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
	private int inputx;
	private int inputy;
	private int outputx;
	private int outputy;
	private int height;
	private int width;
	
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
		
		if(outputx > inputx){
			width = outputx-inputx;
		} else {
			width = inputx-outputx;
		}
		
		if(outputy > inputy){
			height = outputy-inputy;
		} else {
			height = inputy-outputy;
		}
		
		this.setPreferredSize(new Dimension(width, height));
				
		if(inputx<outputx){
			this.addLine(0, 0, width, height);
		} else {
			this.addLine(width,0, 0, height);
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

	@Override
	public int getHeight() {
		return height;
	}
	
	@Override
	public int getWidth() {
		return width;
	}
	
}
