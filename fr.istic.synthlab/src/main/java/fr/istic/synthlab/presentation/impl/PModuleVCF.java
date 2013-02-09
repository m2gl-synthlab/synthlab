package fr.istic.synthlab.presentation.impl;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.jsyn.swing.DoubleBoundedRangeModel;
import com.jsyn.swing.RotaryTextController;

import fr.istic.synthlab.abstraction.impl.ModuleVCF;
import fr.istic.synthlab.controller.ICInputPort;
import fr.istic.synthlab.controller.ICModuleVCF;
import fr.istic.synthlab.controller.ICOutputPort;
import fr.istic.synthlab.controller.ICSynthesizer;
import fr.istic.synthlab.presentation.IPModuleVCF;

/**
 * Presentation of a module
 */
public class PModuleVCF extends APModule implements IPModuleVCF {

	private static final long serialVersionUID = -8519084219674310285L;

	private ICModuleVCF ctrl;

	private DoubleBoundedRangeModel cutFrequencyModel;
	private DoubleBoundedRangeModel resonanceModel;

	private PInputPort input;
	private PInputPort fm;
	private POutputPort output;

	private int width;
	private int height;

	/**
	 * @param control
	 */
	public PModuleVCF(ICModuleVCF control) {
		super(control);
		System.out.println("PModuleVCF initialized");

		this.ctrl = control;
		configView();
		defineCallbacks();
	}

	private void configView() {
		this.setBackground(Color.GRAY);
		JPanel panelParams = new JPanel();
		JPanel panelInput = new JPanel();
		JPanel panelOutput = new JPanel();

		cutFrequencyModel = new DoubleBoundedRangeModel(
				ModuleVCF.PARAM_CUT_FREQUENCY_NAME, 100, 0, 22000,
				ctrl.getCutFrequency());
		RotaryTextController cutRotary = new RotaryTextController(
				cutFrequencyModel, 4);
		cutRotary.setBorder(new TitledBorder(ModuleVCF.PARAM_CUT_FREQUENCY_NAME));
		panelParams.add(cutRotary);

		resonanceModel = new DoubleBoundedRangeModel(
				ModuleVCF.PARAM_RESONANCE_NAME, 100, 0, 100,
				ctrl.getResonance());
		RotaryTextController resonanceRotary = new RotaryTextController(
				resonanceModel, 4);
		resonanceRotary.setBorder(new TitledBorder(ModuleVCF.PARAM_RESONANCE_NAME));
		panelParams.add(resonanceRotary);

		input = (PInputPort) ((ICInputPort) ctrl.getInput()).getPresentation();
		panelInput.add(input);

		fm = (PInputPort) ((ICInputPort) ctrl.getInputFm()).getPresentation();
		panelInput.add(fm);

		output = (POutputPort) ((ICOutputPort) ctrl.getOutput())
				.getPresentation();
		panelOutput.add(output);

		this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.PAGE_AXIS));
		this.setAutoscrolls(true);
		this.getContentPane().add(panelParams, 0);
		this.getContentPane().add(panelInput, 1);
		this.getContentPane().add(panelOutput, 2);

		Dimension size = new Dimension(350, 350);
		this.setPreferredSize(size);

		// TODO : Not sure if it's the better way to define the size...
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
		cutFrequencyModel.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				ctrl.p2cCutFrequencyChanged(cutFrequencyModel.getDoubleValue());
			}
		});

		resonanceModel.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				ctrl.p2cResonanceChanged(resonanceModel.getDoubleValue());
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
	public ICModuleVCF getControl() {
		return ctrl;
	}

	@Override
	public void c2pSetCutFrequencyValue(double cutFrequency) {
		cutFrequencyModel.setDoubleValue(cutFrequency);
	}

	@Override
	public void c2pSetResonanceValue(double resonance) {
		resonanceModel.setDoubleValue(resonance);
	}

}
