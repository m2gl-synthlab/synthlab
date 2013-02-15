package fr.istic.synthlab.presentation.module.vcf;

import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.jsyn.swing.DoubleBoundedRangeModel;
import com.jsyn.swing.ExponentialRangeModel;

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
		JPanel panelInputOutput = new JPanel();

		panelParams.setOpaque(false);
		panelInputOutput.setOpaque(false);

		cutFrequencyModel = new ExponentialRangeModel(IModuleVCF.PARAM_NAME_CUT_FREQUENCY, 100, 10, 10000, ctrl.getCutFrequency());
		RotaryTextController cutRotary = new RotaryTextController(cutFrequencyModel, 4);
		panelParams.add(cutRotary);

		resonanceModel = new DoubleBoundedRangeModel(IModuleVCF.PARAM_NAME_RESONANCE, 490, 1, 50, ctrl.getResonance());
		RotaryTextController resonanceRotary = new RotaryTextController(resonanceModel, 4);
		panelParams.add(resonanceRotary);

		input = (PInputPort) ((ICInputPort) ctrl.getInput()).getPresentation();
		panelInputOutput.add(input);

		fm = (PInputPort) ((ICInputPort) ctrl.getInputFm()).getPresentation();
		panelInputOutput.add(fm);

		output = (POutputPort) ((ICOutputPort) ctrl.getOutput()).getPresentation();
		panelInputOutput.add(output);

		super.setWidth(200);
		super.setHeigth(200);

		Dimension size = new Dimension(super.getWidth(), super.getHeight());
		this.setSize(size);
		this.setPreferredSize(size);

		this.addTitleBar();
		this.addPanel(panelParams, 200, 100);
		this.addPanel(panelInputOutput, 200, 100);
	}

	private void defineCallbacks() {
		// Slider change listener
		cutFrequencyModel.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				ctrl.p2cCutFrequencyChanged((int) cutFrequencyModel.getDoubleValue());
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
