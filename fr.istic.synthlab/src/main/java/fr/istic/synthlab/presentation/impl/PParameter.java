package fr.istic.synthlab.presentation.impl;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import com.jsyn.swing.ExponentialRangeModel;
import com.jsyn.swing.RotaryTextController;

import fr.istic.synthlab.controller.ICParameter;
import fr.istic.synthlab.presentation.IPParameter;

public class PParameter extends JPanel implements IPParameter {

	private static final long serialVersionUID = 7359022086561577171L;
	
	private ICParameter ctrl;
	
	public PParameter(ICParameter control) {
		ctrl = control;
		
		configView();
		defineCallbacks();
	}

	private void configView() {
		this.setSize(100, 100);
		this.setPreferredSize(this.getSize());
		this.setBorder(BorderFactory.createTitledBorder(getClass().getSimpleName()));
		
		ExponentialRangeModel amplitudeModel = new ExponentialRangeModel("amplitude", 100, ctrl.getMin(), ctrl.getMax(), ctrl.getValue());
		RotaryTextController knob = new RotaryTextController( amplitudeModel, 5 );
		JPanel knobPanel = new JPanel();
		knobPanel.add( knob );
		add( knobPanel );
		
	}

	private void defineCallbacks() {
	}
	
	@Override
	public ICParameter getControl() {
		return ctrl;
	}

}
