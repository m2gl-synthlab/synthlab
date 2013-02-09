package fr.istic.synthlab.presentation.impl;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import fr.istic.synthlab.controller.ICInputPort;
import fr.istic.synthlab.controller.ICModuleREP;
import fr.istic.synthlab.controller.ICOutputPort;
import fr.istic.synthlab.presentation.IPModuleREP;

/**
 * Presentation for a REP module
 */
public class PModuleREP extends APModule implements IPModuleREP {

	private static final long serialVersionUID = 1L;
	
	private ICModuleREP ctrl;
	private PInputPort inputPort;
	private POutputPort out1;
	private POutputPort out2;
	private POutputPort out3;
	
	private int width;
	private int height;

	public PModuleREP(ICModuleREP control) {
		// TODO Auto-generated constructor stub
		super(control);
		System.out.println("PModuleREP initialized");

		this.ctrl = control;
		configView();
	}

	@Override
	public ICModuleREP getControl() {
		return this.ctrl;
	}

	@Override
	public int getHeight() {
		return this.height;
	}

	@Override
	public int getWidth() {
		return this.width;
	}
	
	private void configView() {

		this.setBackground(Color.GRAY);
		JPanel panelInput = new JPanel();
		JPanel panelOutput = new JPanel();
		
		inputPort = (PInputPort) ((ICInputPort) ctrl.getIn()).getPresentation();
		panelInput.add(inputPort);
		
		out1 = (POutputPort) ((ICOutputPort) ctrl.getOut1()).getPresentation();
		panelOutput.add(out1);
		
		out2 = (POutputPort) ((ICOutputPort) ctrl.getOut2()).getPresentation();
		panelOutput.add(out2);
		
		out3 = (POutputPort) ((ICOutputPort) ctrl.getOut3()).getPresentation();
		panelOutput.add(out3);
		
		this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.PAGE_AXIS));
		this.setAutoscrolls(true);
		this.getContentPane().add(panelInput, 0);
		this.getContentPane().add(panelOutput, 1);
		
		Dimension size = new Dimension(300, 250);
		this.setPreferredSize(size);

		// TODO : Not sure if it's the better way to define the size...
		this.width = size.width;
		this.height = size.height;
	}

}
