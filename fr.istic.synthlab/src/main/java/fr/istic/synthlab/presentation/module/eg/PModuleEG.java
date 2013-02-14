package fr.istic.synthlab.presentation.module.eg;

import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.jsyn.swing.DoubleBoundedRangeModel;
import com.jsyn.swing.ExponentialRangeModel;
import com.jsyn.swing.RotaryTextController;

import fr.istic.synthlab.controller.module.eg.ICModuleEG;
import fr.istic.synthlab.controller.port.ICInputPort;
import fr.istic.synthlab.controller.port.ICOutputPort;
import fr.istic.synthlab.presentation.module.APModule;
import fr.istic.synthlab.presentation.port.PInputPort;
import fr.istic.synthlab.presentation.port.POutputPort;

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

	public PModuleEG(ICModuleEG control) {
		super(control);
		ctrl = control;
		
		configView();
		registerCallbacks();
	}

	private void configView() {
		JPanel panelRotary = new JPanel();
		JPanel panelPort = new JPanel();

		panelRotary.setOpaque(false);
		panelPort.setOpaque(false);
	
		attackModel = new ExponentialRangeModel("attack", 500, 0, 5, ctrl.getAttack());
		decayModel = new ExponentialRangeModel("decay", 500, 0, 5, ctrl.getDecay());
		sustainModel = new ExponentialRangeModel("sustain", 500, 0, 5, ctrl.getSustain());
		releaseModel = new ExponentialRangeModel("release", 500, 0, 5, ctrl.getRelease());
		
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
		outputPort = (POutputPort) ((ICOutputPort) ctrl.getOutput()).getPresentation();
		panelPort.add(gatePort);
		panelPort.add(outputPort);
		
		this.setAutoscrolls(true);
		
		super.setWidth(290);
		super.setHeigth(200);
		
		Dimension size = new Dimension(super.getWidth(), super.getHeight());
		this.setSize(size);
		this.setPreferredSize(size);
		
		this.addTitleBar();
		this.addPanel(panelRotary, 290, 100);
		this.addPanel(panelPort, 290, 60);
		
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
	public ICModuleEG getControl() {
		return ctrl;
	}

	@Override
	public void c2pSetAttackValue(double attack) {
		attackModel.setDoubleValue(attack);
	}

	@Override
	public void c2pSetDecayValue(double decay) {
		decayModel.setDoubleValue(decay);
	}

	@Override
	public void c2pSetSustainValue(double sustain) {
		sustainModel.setDoubleValue(sustain);
	}

	@Override
	public void c2pSetReleaseValue(double release) {
		releaseModel.setDoubleValue(release);
	}

}
