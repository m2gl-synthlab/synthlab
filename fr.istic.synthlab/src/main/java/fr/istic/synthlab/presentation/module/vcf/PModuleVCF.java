package fr.istic.synthlab.presentation.module.vcf;

import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.jsyn.swing.DoubleBoundedRangeModel;

import fr.istic.synthlab.abstraction.module.vcf.IModuleVCF;
import fr.istic.synthlab.controller.module.vcf.ICModuleVCF;
import fr.istic.synthlab.controller.port.ICInputPort;
import fr.istic.synthlab.controller.port.ICOutputPort;
import fr.istic.synthlab.presentation.module.APModule;
import fr.istic.synthlab.presentation.port.PInputPort;
import fr.istic.synthlab.presentation.port.POutputPort;
import fr.istic.synthlab.presentation.util.RotaryTextController;

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
		JPanel panelParams = new JPanel();
		JPanel panelInput = new JPanel();
		JPanel panelOutput = new JPanel();

		panelParams.setOpaque(false);
		panelInput.setOpaque(false);
		panelOutput.setOpaque(false);

		cutFrequencyModel = new DoubleBoundedRangeModel(
				IModuleVCF.PARAM_CUT_FREQUENCY_NAME, 100, 0, 22000,
				ctrl.getCutFrequency());
		RotaryTextController cutRotary = new RotaryTextController(
				cutFrequencyModel, 4);
		panelParams.add(cutRotary);

		resonanceModel = new DoubleBoundedRangeModel(
				IModuleVCF.PARAM_RESONANCE_NAME, 490, 1, 50,
				ctrl.getResonance());
		RotaryTextController resonanceRotary = new RotaryTextController(
				resonanceModel, 4);
		panelParams.add(resonanceRotary);

		input = (PInputPort) ((ICInputPort) ctrl.getInput()).getPresentation();
		panelInput.add(input);

		fm = (PInputPort) ((ICInputPort) ctrl.getInputFm()).getPresentation();
		panelInput.add(fm);

		output = (POutputPort) ((ICOutputPort) ctrl.getOutput())
				.getPresentation();
		panelOutput.add(output);

		this.setAutoscrolls(true);

		super.setWidth(350);
		super.setHeigth(350);

		Dimension size = new Dimension(super.getWidth(), super.getHeight());
		this.setSize(size);
		this.setPreferredSize(size);

		this.addTitleBar();
		this.addPanel(panelParams, 350, 100);
		this.addPanel(panelInput, 350, 100);
		this.addPanel(panelOutput, 350, 100);
	}

	private void defineCallbacks() {
		// Slider change listener
		cutFrequencyModel.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				ctrl.p2cCutFrequencyChanged((int) cutFrequencyModel
						.getDoubleValue());
			}
		});

		resonanceModel.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				ctrl.p2cResonanceChanged(resonanceModel.getDoubleValue());
			}
		});
	}

	@Override
	public ICModuleVCF getControl() {
		return ctrl;
	}

	@Override
	public void c2pSetCutFrequencyValue(int cutFrequency) {
		cutFrequencyModel.setDoubleValue(cutFrequency);
	}

	@Override
	public void c2pSetResonanceValue(double resonance) {
		resonanceModel.setDoubleValue(resonance);
	}

}
