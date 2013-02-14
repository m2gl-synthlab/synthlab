package fr.istic.synthlab.presentation.module.vco;

import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.jsyn.swing.DoubleBoundedRangeModel;

import fr.istic.synthlab.abstraction.module.vco.ModuleVCO;
import fr.istic.synthlab.controller.module.vco.ICModuleVCO;
import fr.istic.synthlab.controller.port.ICInputPort;
import fr.istic.synthlab.controller.port.ICOutputPort;
import fr.istic.synthlab.presentation.module.APModule;
import fr.istic.synthlab.presentation.port.PInputPort;
import fr.istic.synthlab.presentation.port.POutputPort;
import fr.istic.synthlab.presentation.util.RotaryTextController;

/**
 * Presentation of a module
 */
public class PModuleVCO extends APModule implements IPModuleVCO {

	private static final long serialVersionUID = -8519084219674310285L;

	private ICModuleVCO ctrl;

	private DoubleBoundedRangeModel octaveModel;
	private DoubleBoundedRangeModel toneModel;
	private PInputPort fm;
	private POutputPort outputSquare;
	private POutputPort outputTriangle;
	private POutputPort outputSawtooth;

	JLabel frequencyLabel;

	/**
	 * @param control
	 */
	public PModuleVCO(ICModuleVCO control) {
		super(control);
		System.out.println("PModuleVCO initialized");

		this.ctrl = control;
		configView();
		defineCallbacks();
	}

	private void configView() {
		JPanel panelParams = new JPanel();
		JPanel panelFrequency = new JPanel();
		JPanel panelInputOutput = new JPanel();
		
		panelParams.setOpaque(false);
		panelFrequency.setOpaque(false);
		panelInputOutput.setOpaque(false);
		
		octaveModel = new DoubleBoundedRangeModel(ModuleVCO.PARAM_OCTAVE_NAME,
				14, 0, 14, ctrl.getOctave());
		RotaryTextController octaveRotary = new RotaryTextController(octaveModel, 4);
		panelParams.add(octaveRotary);

		toneModel = new DoubleBoundedRangeModel(ModuleVCO.PARAM_TONE_NAME, 100,	-1.0, 1.0, ctrl.getTone());
		RotaryTextController toneRotary = new RotaryTextController(toneModel, 4);
		panelParams.add(toneRotary);

		frequencyLabel = new JLabel();
		frequencyLabel.setText("" + ctrl.getFrequency());
		panelFrequency.add(frequencyLabel);

		fm = (PInputPort) ((ICInputPort) ctrl.getInputFm()).getPresentation();
		panelInputOutput.add(fm);

		outputSquare = (POutputPort) ((ICOutputPort) ctrl.getOutputSquare())
				.getPresentation();
		panelInputOutput.add(outputSquare);

		outputTriangle = (POutputPort) ((ICOutputPort) ctrl.getOutputTriangle())
				.getPresentation();
		panelInputOutput.add(outputTriangle);

		outputSawtooth = (POutputPort) ((ICOutputPort) ctrl.getOutputSawtooth()).getPresentation();
		panelInputOutput.add(outputSawtooth);

		super.setWidth(270);
		super.setHeigth(240);
		
		Dimension size = new Dimension(super.getWidth(), super.getHeight());
		this.setSize(size);
		this.setPreferredSize(size);
		
		this.addTitleBar();
		this.addPanel(panelParams, 270, 115);
		this.addPanel(panelFrequency, 270, 25);
		this.addPanel(panelInputOutput, 270, 60);
	}



	private void defineCallbacks() {
		// Slider change listener
		octaveModel.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				ctrl.p2cOctaveChanged((int) octaveModel.getDoubleValue());
			}
		});

		toneModel.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				ctrl.p2cToneChanged(toneModel.getDoubleValue());
			}
		});

	}

	@Override
	public ICModuleVCO getControl() {
		return ctrl;
	}

	@Override
	public void c2pSetOctaveValue(int octave) {
		octaveModel.setDoubleValue(octave);
	}

	@Override
	public void c2pSetToneValue(double tone) {
		toneModel.setDoubleValue(tone);
	}

	@Override
	public void c2pSetFrequencyValue(double frequency) {
		frequencyLabel.setText(""+String.format("%.2f", frequency)+" Hz");
	}

}
