package fr.istic.synthlab.presentation.impl;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.jsyn.swing.DoubleBoundedRangeModel;
import com.jsyn.swing.RotaryTextController;

import fr.istic.synthlab.controller.ICInputPort;
import fr.istic.synthlab.controller.ICModuleEG;
import fr.istic.synthlab.controller.ICOutputPort;
import fr.istic.synthlab.presentation.IPModuleEG;

/**
 * Presentation for an EG module
 */
public class PModuleEG extends APModule implements IPModuleEG {

	private static final long serialVersionUID = 2223267559839600440L;

	private ICModuleEG ctrl;
	private DoubleBoundedRangeModel attackModel;
	private DoubleBoundedRangeModel decayModel;
	private DoubleBoundedRangeModel sustainModel;
	private DoubleBoundedRangeModel releaseModel;
	
	private PInputPort gatePort;
	private POutputPort outputPort;

	private int width;
	private int height;
	
	public PModuleEG(ICModuleEG control) {
		super(control);
		ctrl = control;
		
		configView();
		registerCallbacks();
	}
	
	private void configView() {

		this.setBackground(Color.GRAY);
		JPanel panelRotary = new JPanel();
		JPanel panelInput = new JPanel();
		JPanel panelOutput = new JPanel();

		attackModel = new DoubleBoundedRangeModel("attack", 100, 0, 10, ctrl.getAttack());
		decayModel = new DoubleBoundedRangeModel("decay", 100, 0, 10, ctrl.getDecay());
		sustainModel = new DoubleBoundedRangeModel("sustain", 100, 0, 10, ctrl.getSustain());
		releaseModel = new DoubleBoundedRangeModel("release", 100, 0, 10, ctrl.getRelease());
		
		RotaryTextController attackRotary = new RotaryTextController(attackModel, 2);
		RotaryTextController decayRotary = new RotaryTextController(decayModel, 2);
		RotaryTextController sustainRotary = new RotaryTextController(sustainModel, 2);
		RotaryTextController releaseRotary = new RotaryTextController(releaseModel, 2);

		attackRotary.setBorder(new TitledBorder("Attack"));
		decayRotary.setBorder(new TitledBorder("Decay"));
		sustainRotary.setBorder(new TitledBorder("Sustain"));
		releaseRotary.setBorder(new TitledBorder("Release"));
		
		panelRotary.add(attackRotary);
		panelRotary.add(decayRotary);
		panelRotary.add(sustainRotary);
		panelRotary.add(releaseRotary);
		
		panelRotary.setPreferredSize(new Dimension(150, 200));
		
		gatePort = (PInputPort) ((ICInputPort) ctrl.getGateInput()).getPresentation();
		panelInput.add(gatePort);
		
		outputPort = (POutputPort) ((ICOutputPort) ctrl.getOutput()).getPresentation();
		panelOutput.add(outputPort);
		
		this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.PAGE_AXIS));
		this.setAutoscrolls(true);
		this.getContentPane().add(panelRotary, 0);
		this.getContentPane().add(panelInput, 1);
		this.getContentPane().add(panelOutput, 2);
		
		Dimension size = new Dimension(150, 450);
		this.setPreferredSize(size);

		// TODO : Not sure if it's the better way to define the size...
		this.width = size.width;
		this.height = size.height;
		
	}
	
	private void registerCallbacks() {

		attackModel.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				ctrl.p2cAttackChanged(attackModel.getDoubleValue());
			}
		});
		decayModel.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				ctrl.p2cDecayChanged(decayModel.getDoubleValue());
			}
		});
		sustainModel.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				ctrl.p2cSustainChanged(sustainModel.getDoubleValue());
			}
		});
		releaseModel.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				ctrl.p2cReleaseChanged(releaseModel.getDoubleValue());
			}
		});
		
	}
	
	@Override
	public int getHeight() {
		return this.height;
	}


	@Override
	public int getWidth() {
		return this.width;
	}
	
	
	@Override
	public ICModuleEG getControl() {
		return ctrl;
	}


	@Override
	public void c2pSetAttackValue(double attack) {
		// TODO Auto-generated method stub

	}

	@Override
	public void c2pSetDecayValue(double decay) {
		// TODO Auto-generated method stub

	}

	@Override
	public void c2pSetSustainValue(double sustain) {
		// TODO Auto-generated method stub

	}

	@Override
	public void c2pSetReleaseValue(double release) {
		// TODO Auto-generated method stub

	}

}
