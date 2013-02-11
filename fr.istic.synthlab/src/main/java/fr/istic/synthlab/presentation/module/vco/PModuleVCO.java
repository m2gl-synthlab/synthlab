package fr.istic.synthlab.presentation.module.vco;

import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.jsyn.swing.DoubleBoundedRangeModel;
import com.jsyn.swing.RotaryTextController;

import fr.istic.synthlab.abstraction.module.vco.ModuleVCO;
import fr.istic.synthlab.controller.module.vco.ICModuleVCO;
import fr.istic.synthlab.controller.port.ICInputPort;
import fr.istic.synthlab.controller.port.ICOutputPort;
import fr.istic.synthlab.controller.synthesizer.ICSynthesizer;
import fr.istic.synthlab.presentation.module.APModule;
import fr.istic.synthlab.presentation.port.PInputPort;
import fr.istic.synthlab.presentation.port.POutputPort;
import fr.istic.synthlab.presentation.synthesizer.PSynthesizer;

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
		JPanel panelInput = new JPanel();
		JPanel panelOutput = new JPanel();
		
		panelParams.setOpaque(false);
		panelInput.setOpaque(false);
		panelOutput.setOpaque(false);

		octaveModel = new DoubleBoundedRangeModel(ModuleVCO.PARAM_OCTAVE_NAME,
				14, 0, 14, ctrl.getOctave());
		RotaryTextController octaveRotary = new RotaryTextController(
				octaveModel, 4);
		octaveRotary.setBorder(new TitledBorder(ModuleVCO.PARAM_OCTAVE_NAME));
		panelParams.add(octaveRotary);

		toneModel = new DoubleBoundedRangeModel(ModuleVCO.PARAM_TONE_NAME, 100,
				-1.0, 1.0, ctrl.getTone());
		RotaryTextController toneRotary = new RotaryTextController(toneModel, 4);
		toneRotary.setBorder(new TitledBorder(ModuleVCO.PARAM_TONE_NAME));
		panelParams.add(toneRotary);

		frequencyLabel = new JLabel();
		frequencyLabel.setText("" + ctrl.getFrequency());
		panelParams.add(frequencyLabel);

		fm = (PInputPort) ((ICInputPort) ctrl.getInputFm()).getPresentation();
		panelInput.add(fm);

		outputSquare = (POutputPort) ((ICOutputPort) ctrl.getOutputSquare())
				.getPresentation();
		panelOutput.add(outputSquare);

		outputTriangle = (POutputPort) ((ICOutputPort) ctrl.getOutputTriangle())
				.getPresentation();
		panelOutput.add(outputTriangle);

		outputSawtooth = (POutputPort) ((ICOutputPort) ctrl.getOutputSawtooth()).getPresentation();
		panelOutput.add(outputSawtooth);

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
//
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
		frequencyLabel.setText(String.format("%.2f", frequency));
	}

}
