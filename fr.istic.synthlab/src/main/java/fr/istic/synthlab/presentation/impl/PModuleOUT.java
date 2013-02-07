package fr.istic.synthlab.presentation.impl;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.alee.extended.button.WebSwitch;
import com.jsyn.swing.DoubleBoundedRangeModel;
import com.jsyn.swing.RotaryTextController;

import fr.istic.synthlab.controller.ICInputPort;
import fr.istic.synthlab.controller.ICModuleOUT;
import fr.istic.synthlab.presentation.IPModuleOUT;

/**
 * Presentation for a OUT module
 */
public class PModuleOUT extends APModule implements IPModuleOUT {

	private static final long serialVersionUID = -8519084219674310285L;

	private ICModuleOUT ctrl;
	private DoubleBoundedRangeModel model;
	private RotaryTextController gainRotary;
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
		this.setBackground(Color.GRAY);
		JPanel panelGain = new JPanel(new BorderLayout());
		JPanel panelInput = new JPanel();
		JPanel panelMute = new JPanel();

		model = new DoubleBoundedRangeModel("model", 4200, -30, 12, ctrl.getAttenuation());
		gainRotary = new RotaryTextController(model, 1);
		panelGain.add(gainRotary,BorderLayout.CENTER);
		panelGain.add(new JLabel("Gain"), BorderLayout.NORTH);

		muteSwitch = new WebSwitch();
		muteSwitch.setRound(4);
		muteSwitch.setSelected(!ctrl.isMute());
		panelMute.add(muteSwitch);

		inputPort = (PInputPort) ((ICInputPort) ctrl.getInput()).getPresentation();
		panelInput.add(inputPort);

		this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.PAGE_AXIS));
		this.setAutoscrolls(true);
		this.getContentPane().add(panelGain, 0);
		this.getContentPane().add(panelInput, 1);
		this.getContentPane().add(panelMute, 2);

		Dimension size = new Dimension(150, 250);
		this.setPreferredSize(size);

		// FIXME : Not sure if it's the better way to define the size...
		width = size.width;
		height = size.height;

	}

	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
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
