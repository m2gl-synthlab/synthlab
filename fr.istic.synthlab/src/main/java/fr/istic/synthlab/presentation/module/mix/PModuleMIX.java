package fr.istic.synthlab.presentation.module.mix;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.jsyn.swing.DoubleBoundedRangeModel;

import fr.istic.synthlab.abstraction.module.mix.IModuleMIX;
import fr.istic.synthlab.controller.module.mix.ICModuleMIX;
import fr.istic.synthlab.controller.port.ICInputPort;
import fr.istic.synthlab.controller.port.ICOutputPort;
import fr.istic.synthlab.presentation.module.APModule;
import fr.istic.synthlab.presentation.port.PInputPort;
import fr.istic.synthlab.presentation.port.POutputPort;
import fr.istic.synthlab.presentation.util.RotaryTextController;

public class PModuleMIX extends APModule implements IPModuleMIX {

	private static final long serialVersionUID = -1813495434910266229L;

	private ICModuleMIX ctrl;
	private PInputPort inputPort1;
	private PInputPort inputPort2;
	private PInputPort inputPort3;
	private PInputPort inputPort4;
	private POutputPort output;

	// Gains params
	private DoubleBoundedRangeModel modelGain1;
	private DoubleBoundedRangeModel modelGain2;
	private DoubleBoundedRangeModel modelGain3;
	private DoubleBoundedRangeModel modelGain4;

	public PModuleMIX(ICModuleMIX control) {
		super(control);

		this.ctrl = control;
		configView();
		defineCallbacks();
	}

	@Override
	public ICModuleMIX getControl() {
		return this.ctrl;
	}

	private void configView() {
		JPanel panelComponent = new JPanel();
		GridLayout layout = new GridLayout(2, 6);
		layout.setVgap(20);
		panelComponent.setLayout(layout);
		panelComponent.setOpaque(false);

		panelComponent.add(new JLabel());

		modelGain1 = new DoubleBoundedRangeModel(IModuleMIX.PARAM_NAME_GAIN1, 7200, -60, 12, ctrl.getAttenuation1());
		RotaryTextController gainRotary1 = new RotaryTextController(modelGain1, 1);
		panelComponent.add(gainRotary1);

		modelGain2 = new DoubleBoundedRangeModel(IModuleMIX.PARAM_NAME_GAIN2, 7200, -60, 12, ctrl.getAttenuation2());
		RotaryTextController gainRotary2 = new RotaryTextController(modelGain2, 1);
		panelComponent.add(gainRotary2);

		modelGain3 = new DoubleBoundedRangeModel(IModuleMIX.PARAM_NAME_GAIN3, 7200, -60, 12, ctrl.getAttenuation3());
		RotaryTextController gainRotary3 = new RotaryTextController(modelGain3, 1);
		panelComponent.add(gainRotary3);

		modelGain4 = new DoubleBoundedRangeModel(IModuleMIX.PARAM_NAME_GAIN4, 7200, -60, 12, ctrl.getAttenuation4());
		RotaryTextController gainRotary4 = new RotaryTextController(modelGain4, 1);
		panelComponent.add(gainRotary4);

		panelComponent.add(new JLabel());
		panelComponent.add(new JLabel());

		inputPort1 = (PInputPort) ((ICInputPort) ctrl.getInput(1)).getPresentation();
		panelComponent.add(inputPort1);

		inputPort2 = (PInputPort) ((ICInputPort) ctrl.getInput(2)).getPresentation();
		panelComponent.add(inputPort2);

		inputPort3 = (PInputPort) ((ICInputPort) ctrl.getInput(3)).getPresentation();
		panelComponent.add(inputPort3);

		inputPort4 = (PInputPort) ((ICInputPort) ctrl.getInput(4)).getPresentation();
		panelComponent.add(inputPort4);

		output = (POutputPort) ((ICOutputPort) ctrl.getOutput()).getPresentation();
		panelComponent.add(output);

		this.setAutoscrolls(true);
		super.setWidth(350);
		super.setHeigth(200);

		Dimension size = new Dimension(super.getWidth(), super.getHeight());
		this.setSize(size);
		this.setPreferredSize(size);

		this.addTitleBar();
		this.addPanel(panelComponent, 330, 200);
	}

	private void defineCallbacks() {

		// Slider change listener
		modelGain1.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				ctrl.p2cGain1Changed(modelGain1.getDoubleValue());
			}
		});

		modelGain2.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				ctrl.p2cGain2Changed(modelGain2.getDoubleValue());
			}
		});

		modelGain3.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				ctrl.p2cGain3Changed(modelGain3.getDoubleValue());
			}
		});

		modelGain4.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				ctrl.p2cGain4Changed(modelGain4.getDoubleValue());
			}
		});

	}

	@Override
	public void c2pSetGain1Value(double gain) {
		modelGain1.setDoubleValue(gain);
	}

	@Override
	public void c2pSetGain2Value(double gain) {
		modelGain2.setDoubleValue(gain);
	}

	@Override
	public void c2pSetGain3Value(double gain) {
		modelGain3.setDoubleValue(gain);
	}

	@Override
	public void c2pSetGain4Value(double gain) {
		modelGain4.setDoubleValue(gain);
	}

}
