package fr.istic.synthlab.presentation.module.mix;

import java.awt.Dimension;

import javax.swing.JPanel;

import fr.istic.synthlab.controller.module.mix.ICModuleMIX;
import fr.istic.synthlab.controller.port.ICInputPort;
import fr.istic.synthlab.controller.port.ICOutputPort;
import fr.istic.synthlab.presentation.module.APModule;
import fr.istic.synthlab.presentation.port.PInputPort;
import fr.istic.synthlab.presentation.port.POutputPort;

public class PModuleMIX extends APModule implements IPModuleMIX{

	private ICModuleMIX ctrl;
	private PInputPort inputPort1;
	private PInputPort inputPort2;
	private PInputPort inputPort3;
	private PInputPort inputPort4;
	private POutputPort output;
	
	public PModuleMIX(ICModuleMIX control) {
		super(control);
		System.out.println("PModuleMIX initialized");

		this.ctrl = control;
		configView();
	}

	@Override
	public ICModuleMIX getControl() {
		return this.ctrl;
	}
	
	private void configView() {
		JPanel panelInput = new JPanel();
		JPanel panelOutput = new JPanel();
		
		panelInput.setOpaque(false);
		panelOutput.setOpaque(false);
		
		inputPort1 = (PInputPort) ((ICInputPort) ctrl.getInput(1)).getPresentation();
		panelInput.add(inputPort1);
		
		inputPort2 = (PInputPort) ((ICInputPort) ctrl.getInput(2)).getPresentation();
		panelInput.add(inputPort2);
		
		inputPort3 = (PInputPort) ((ICInputPort) ctrl.getInput(3)).getPresentation();
		panelInput.add(inputPort3);
		
		inputPort4 = (PInputPort) ((ICInputPort) ctrl.getInput(4)).getPresentation();
		panelInput.add(inputPort4);
		
		output = (POutputPort) ((ICOutputPort) ctrl.getOutput()).getPresentation();
		panelOutput.add(output);
		
		this.setAutoscrolls(true);
		super.setWidth(350);
		super.setHeigth(230);
		
		Dimension size = new Dimension(super.getWidth(), super.getHeight());
		this.setSize(size);
		this.setPreferredSize(size);
		
		this.addTitleBar();
		this.addPanel(panelInput, 350, 100);
		this.addPanel(panelOutput, 350, 100);
	}

}
