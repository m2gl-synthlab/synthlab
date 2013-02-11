package fr.istic.synthlab.presentation.module.rep;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import fr.istic.synthlab.controller.module.rep.ICModuleREP;
import fr.istic.synthlab.controller.port.ICInputPort;
import fr.istic.synthlab.controller.port.ICOutputPort;
import fr.istic.synthlab.presentation.module.APModule;
import fr.istic.synthlab.presentation.port.PInputPort;
import fr.istic.synthlab.presentation.port.POutputPort;

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
		
		inputPort = (PInputPort) ((ICInputPort) ctrl.getInput()).getPresentation();
		panelInput.add(inputPort);
		
		out1 = (POutputPort) ((ICOutputPort) ctrl.getOutput1()).getPresentation();
		panelOutput.add(out1);
		
		out2 = (POutputPort) ((ICOutputPort) ctrl.getOutput2()).getPresentation();
		panelOutput.add(out2);
		
		out3 = (POutputPort) ((ICOutputPort) ctrl.getOutput3()).getPresentation();
		panelOutput.add(out3);
		
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		this.setAutoscrolls(true);
		this.add(panelInput, 0);
		this.add(panelOutput, 1);
		
		Dimension size = new Dimension(300, 250);
		this.setPreferredSize(size);

		// TODO : Not sure if it's the better way to define the size...
		this.width = size.width;
		this.height = size.height;
	}

}
