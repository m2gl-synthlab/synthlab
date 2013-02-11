package fr.istic.synthlab.presentation.module.rep;

import java.awt.Dimension;
import java.awt.GridBagConstraints;

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
	
	private void configView() {
		JPanel panelInput = new JPanel();
		JPanel panelOutput = new JPanel();
		
		panelInput.setOpaque(false);
		panelOutput.setOpaque(false);
		
		inputPort = (PInputPort) ((ICInputPort) ctrl.getInput()).getPresentation();
		panelInput.add(inputPort);
		
		out1 = (POutputPort) ((ICOutputPort) ctrl.getOutput1()).getPresentation();
		panelOutput.add(out1);
		
		out2 = (POutputPort) ((ICOutputPort) ctrl.getOutput2()).getPresentation();
		panelOutput.add(out2);
		
		out3 = (POutputPort) ((ICOutputPort) ctrl.getOutput3()).getPresentation();
		panelOutput.add(out3);
		
		this.setAutoscrolls(true);
		super.setWidth(350);
		super.setHeigth(350);
		
		Dimension size = new Dimension(super.getWidth(), super.getHeigth());
		this.setSize(size);
		this.setPreferredSize(size);
		
		this.addTitleBar();
		this.addPanel(panelInput, 350, 100);
		this.addPanel(panelOutput, 350, 100);
	}

}
