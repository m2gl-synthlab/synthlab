package fr.istic.synthlab.presentation.module.noise;

import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.jsyn.swing.DoubleBoundedRangeModel;

import fr.istic.synthlab.abstraction.module.out.IModuleOUT;
import fr.istic.synthlab.controller.module.noise.ICModuleNOISE;
import fr.istic.synthlab.controller.port.ICOutputPort;
import fr.istic.synthlab.presentation.module.APModule;
import fr.istic.synthlab.presentation.port.POutputPort;
import fr.istic.synthlab.presentation.util.RotaryTextController;

/**
 * Presentation for a OUT module
 */
public class PModuleNOISE extends APModule implements IPModuleNOISE {

	private static final long serialVersionUID = -8519084219674310285L;

	private ICModuleNOISE ctrl;
	private DoubleBoundedRangeModel model;
	private POutputPort outputPort;

	/**
	 * @param control
	 */
	public PModuleNOISE(ICModuleNOISE control) {
		super(control);

		this.ctrl = control;
		configView();
		defineCallbacks();
	}

	private void configView() {
		JPanel panelGain = new JPanel();
		panelGain.setOpaque(false);
		JPanel panelInput = new JPanel();
		panelInput.setOpaque(false);
		JPanel panelMute = new JPanel();
		panelMute.setOpaque(false);

		model = new DoubleBoundedRangeModel(IModuleOUT.PARAM_NAME_GAIN, 7200, -60, 12, ctrl.getAttenuation());
		RotaryTextController gainRotary = new RotaryTextController(model, 1);
		panelGain.add(gainRotary);


		outputPort = (POutputPort) ((ICOutputPort) ctrl.getOutput()).getPresentation();
		panelInput.add(outputPort);

		this.setAutoscrolls(true);
		super.setWidth(100);
		super.setHeigth(220);

		Dimension size = new Dimension(super.getWidth(), super.getHeight());
		this.setSize(size);
		this.setPreferredSize(size);

		this.addTitleBar();
		this.addPanel(panelGain, 100, 100);
		this.addPanel(panelMute, 100, 30);
		this.addPanel(panelInput, 100, 60);

	}

	private void defineCallbacks() {

		// Slider change listener
		model.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				ctrl.p2cGainChanged(model.getDoubleValue());
			}
		});

	}

	@Override
	public void c2pSetGainValue(double gain) {
		model.setDoubleValue(gain);
	}

	@Override
	public ICModuleNOISE getControl() {
		return ctrl;
	}

}
