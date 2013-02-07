package fr.istic.synthlab.presentation.impl;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.jsyn.swing.DoubleBoundedRangeModel;
import com.jsyn.swing.RotaryTextController;

import fr.istic.synthlab.abstraction.impl.ModuleVCO;
import fr.istic.synthlab.controller.ICInputPort;
import fr.istic.synthlab.controller.ICModuleVCO;
import fr.istic.synthlab.controller.ICOutputPort;
import fr.istic.synthlab.controller.ICSynthesizer;
import fr.istic.synthlab.presentation.IPModuleVCO;

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
	private POutputPort outputSine;
	private POutputPort outputSawtooth;

	private int width;
	private int height;

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
		this.setBackground(Color.GRAY);
		JPanel panelParams = new JPanel();
		JPanel panelInput = new JPanel();
		JPanel panelOutput = new JPanel();

		octaveModel = new DoubleBoundedRangeModel(ModuleVCO.PARAM_OCTAVE_NAME,
				10, -5, 5, ctrl.getOctave());
		panelParams.add(new RotaryTextController(octaveModel, 4));

		toneModel = new DoubleBoundedRangeModel(ModuleVCO.PARAM_TONE_NAME, 100,
				-1, 1, ctrl.getTone());
		panelParams.add(new RotaryTextController(toneModel, 4));

		fm = (PInputPort) ((ICInputPort) ctrl.getInputFm()).getPresentation();
		panelInput.add(fm);

		outputSquare = (POutputPort) ((ICOutputPort) ctrl.getOutputSquare())
				.getPresentation();
		panelOutput.add(outputSquare);

		outputTriangle = (POutputPort) ((ICOutputPort) ctrl.getOutputTriangle())
				.getPresentation();
		panelOutput.add(outputTriangle);

		outputSine = (POutputPort) ((ICOutputPort) ctrl.getOutputSine())
				.getPresentation();
		panelOutput.add(outputSine);

		outputSawtooth = (POutputPort) ((ICOutputPort) ctrl.getOutputSawtooth())
				.getPresentation();
		panelOutput.add(outputSawtooth);

		this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.PAGE_AXIS));
		this.setAutoscrolls(true);
		this.getContentPane().add(panelParams, 0);
		this.getContentPane().add(panelInput, 1);
		this.getContentPane().add(panelOutput, 2);

		Dimension size = new Dimension(350, 350);
		this.setPreferredSize(size);

		// FIXME : Not sure if it's the better way to define the size...
		width = 350;
		height = 350;
	}

	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
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

		this.addMouseMotionListener(new MouseMotionListener() {
			@Override
			public void mouseMoved(MouseEvent e) {
				((PSynthesizer) ((ICSynthesizer) getControl().getSynthesizer())
						.getPresentation()).dispatchEvent(e);
			}

			@Override
			public void mouseDragged(MouseEvent e) {
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

}
