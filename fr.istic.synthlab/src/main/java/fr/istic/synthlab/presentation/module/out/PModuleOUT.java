package fr.istic.synthlab.presentation.module.out;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.alee.extended.button.WebSwitch;
import com.jsyn.swing.DoubleBoundedRangeModel;
import com.jsyn.swing.RotaryTextController;

import fr.istic.synthlab.controller.module.out.ICModuleOUT;
import fr.istic.synthlab.controller.port.ICInputPort;
import fr.istic.synthlab.presentation.module.APModule;
import fr.istic.synthlab.presentation.port.PInputPort;

/**
 * Presentation for a OUT module
 */
public class PModuleOUT extends APModule implements IPModuleOUT {

	private static final long serialVersionUID = -8519084219674310285L;

	private ICModuleOUT ctrl;
	private DoubleBoundedRangeModel model;
	private WebSwitch muteSwitch;
	private PInputPort inputPort;

	private int width;
	private int height;
	
	/**
	 * @param control
	 */
	public PModuleOUT(ICModuleOUT control) {
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
		
		model = new DoubleBoundedRangeModel("model", 7200, -60, 12, ctrl.getAttenuation());
		RotaryTextController gainRotary = new RotaryTextController(model, 1);
		gainRotary.setBorder(new TitledBorder("Gain"));
		panelGain.add(gainRotary);

		muteSwitch = new WebSwitch();
		muteSwitch.setRound(4);
		muteSwitch.setSelected(!ctrl.isMute());
		panelMute.add(muteSwitch);

		inputPort = (PInputPort) ((ICInputPort) ctrl.getInput()).getPresentation();
		panelInput.add(inputPort);

		this.setAutoscrolls(true);
		super.setWidth(150);
		super.setHeigth(270);
		
		Dimension size = new Dimension(super.getWidth(), super.getHeigth());
		this.setSize(size);
		this.setPreferredSize(size);
		
		this.addTitleBar();
		this.addPanel(panelGain, 150, 100);
		this.addPanel(panelInput, 150, 100);
		this.addPanel(panelMute, 150, 100);

	}

	private void defineCallbacks() {

		// Slider change listener
		model.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				ctrl.p2cGainChanged(model.getDoubleValue());
			}
		});

		muteSwitch.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ctrl.p2cMute();
			}
		});
	}

	@Override
	public void c2pMute() {
		this.muteSwitch.setSelected(false);
	}

	@Override
	public void c2pUnmute() {
		this.muteSwitch.setSelected(true);
	}

	@Override
	public void c2pSetGainValue(double gain) {
	}

	@Override
	public ICModuleOUT getControl() {
		return ctrl;
	}

}