package fr.istic.synthlab.presentation.module.vca;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.jsyn.swing.DoubleBoundedRangeModel;
import com.jsyn.swing.RotaryTextController;

import fr.istic.synthlab.abstraction.module.vca.ModuleVCA;
import fr.istic.synthlab.controller.module.vca.ICModuleVCA;
import fr.istic.synthlab.controller.port.ICInputPort;
import fr.istic.synthlab.controller.port.ICOutputPort;
import fr.istic.synthlab.controller.synthesizer.ICSynthesizer;
import fr.istic.synthlab.presentation.module.APModule;
import fr.istic.synthlab.presentation.port.PInputPort;
import fr.istic.synthlab.presentation.port.POutputPort;
import fr.istic.synthlab.presentation.synthesizer.PSynthesizer;

public class PModuleVCA extends APModule implements IPModuleVCA {

	private static final long serialVersionUID = -8784306925093530549L;

	private ICModuleVCA ctrl;

	private DoubleBoundedRangeModel attenuationModel;

	private PInputPort input;
	private PInputPort inputAM;
	private POutputPort output;

	private int width;
	private int height;

	/**
	 * @param control
	 */
	public PModuleVCA(ICModuleVCA control) {
		super(control);
		System.out.println("PModuleVCA initialized");

		this.ctrl = control;
		configView();
		defineCallbacks();
	}

	private void configView() {
		JPanel panelParams = new JPanel();
		JPanel panelInput = new JPanel();
		JPanel panelOutput = new JPanel();
		
		panelParams.setOpaque(false);
		panelInput.setOpaque(false);
		panelOutput.setOpaque(false);

		attenuationModel = new DoubleBoundedRangeModel(ModuleVCA.PARAM_AMPLITUDE_NAME, 7200, -60, 12, ctrl.getBaseAmplitudeValue());
		RotaryTextController attenuationRotary = new RotaryTextController(attenuationModel, 4);
		attenuationRotary.setBorder(new TitledBorder(ModuleVCA.PARAM_AMPLITUDE_NAME));
		panelParams.add(attenuationRotary);

		input = (PInputPort) ((ICInputPort) ctrl.getInput()).getPresentation();
		panelInput.add(input);

		inputAM = (PInputPort) ((ICInputPort) ctrl.getInputAM())
				.getPresentation();
		panelInput.add(inputAM);

		output = (POutputPort) ((ICOutputPort) ctrl.getOutput())
				.getPresentation();
		panelOutput.add(output);

		this.setAutoscrolls(true);

		super.setWidth(350);
		super.setHeigth(350);
		
		Dimension size = new Dimension(super.getWidth(), super.getHeigth());
		this.setSize(size);
		this.setPreferredSize(size);
		
		this.addTitleBar();
		this.addPanel(panelParams, 350, 100);
		this.addPanel(panelInput, 350, 100);
		this.addPanel(panelOutput, 350, 100);
	}

	private void defineCallbacks() {
		// Slider change listener
		attenuationModel.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				ctrl.p2cAttenuationValueChanged(attenuationModel
						.getDoubleValue());
			}
		});

//		this.addMouseMotionListener(new MouseMotionListener() {
//			@Override
//			public void mouseMoved(MouseEvent e) {
//				((PSynthesizer) ((ICSynthesizer) getControl().getSynthesizer())
//						.getPresentation()).dispatchEvent(e);
//			}
//
//			@Override
//			public void mouseDragged(MouseEvent e) {
//			}
//		});
	}

	@Override
	public ICModuleVCA getControl() {
		return ctrl;
	}

	@Override
	public void c2pSetAttenuationValue(double attenuationValue) {
		attenuationModel.setDoubleValue(attenuationValue);
	}
}