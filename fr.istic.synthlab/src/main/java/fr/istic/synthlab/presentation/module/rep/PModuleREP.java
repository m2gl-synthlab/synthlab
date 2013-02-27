package fr.istic.synthlab.presentation.module.rep;

import java.awt.Dimension;

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

	public PModuleREP(ICModuleREP control) {
		super(control);
		this.ctrl = control;
		configView();
	}

	@Override
	public ICModuleREP getControl() {
		return this.ctrl;
	}

	private void configView() {
		JPanel panelInputOutput = new JPanel();

		panelInputOutput.setOpaque(false);

		inputPort = (PInputPort) ((ICInputPort) ctrl.getInput()).getPresentation();
		panelInputOutput.add(inputPort);

		out1 = (POutputPort) ((ICOutputPort) ctrl.getOutput1()).getPresentation();
		panelInputOutput.add(out1);

		out2 = (POutputPort) ((ICOutputPort) ctrl.getOutput2()).getPresentation();
		panelInputOutput.add(out2);

		out3 = (POutputPort) ((ICOutputPort) ctrl.getOutput3()).getPresentation();
		panelInputOutput.add(out3);

		super.setWidth(270);
		super.setHeigth(100);

		Dimension size = new Dimension(super.getWidth(), super.getHeight());
		this.setSize(size);
		this.setPreferredSize(size);

		this.addTitleBar();
		this.addPanel(panelInputOutput, 270, 60);
	}

}
