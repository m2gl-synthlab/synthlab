package fr.istic.synthlab.presentation.impl;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
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
		System.out.println("PModuleOUT initialized");

		this.ctrl = control;
		configView();
		defineCallbacks();
	}

	private void configView() {
		this.setBackground(Color.GRAY);
		JPanel panelGain = new JPanel();
		JPanel panelInput = new JPanel();
		JPanel panelMute = new JPanel();

		model = new DoubleBoundedRangeModel("model", 10000, -30, 12,
				ctrl.getAttenuation());
		gainRotary = new RotaryTextController(model, 2);
		panelGain.add(gainRotary);

		muteSwitch = new WebSwitch();
		muteSwitch.setRound(4);
		panelMute.add(muteSwitch);

		inputPort = (PInputPort) ((ICInputPort) ctrl.getInput())
				.getPresentation();
		panelInput.add(inputPort);

		this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.PAGE_AXIS));
		this.setAutoscrolls(true);
		this.getContentPane().add(panelGain, 0);
		this.getContentPane().add(panelMute, 1);
		this.getContentPane().add(panelInput, 2);

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
		model.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				ctrl.p2cGainChanged(model.getDoubleValue());
			}
		});

		muteSwitch.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("mute clicked");
				ctrl.p2cMute();
			}
		});
	}

	@Override
	public void c2pMute() {
		this.muteSwitch.setSelected(true);
	}

	@Override
	public void c2pUnmute() {
		this.muteSwitch.setSelected(false);
	}

	@Override
	public void c2pSetGainValue(double gain) {
	}

	@Override
	public ICModuleOUT getControl() {
		return ctrl;
	}

}
