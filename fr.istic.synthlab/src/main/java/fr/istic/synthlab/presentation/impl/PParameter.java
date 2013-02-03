package fr.istic.synthlab.presentation.impl;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.jsyn.swing.DoubleBoundedRangeModel;
import com.jsyn.swing.RotaryTextController;

import fr.istic.synthlab.controller.ICParameter;
import fr.istic.synthlab.presentation.IPParameter;

public class PParameter extends JPanel implements IPParameter {

	private static final long serialVersionUID = 7359022086561577171L;
	
	private ICParameter ctrl;
	
	private RotaryTextController knob;
	private DoubleBoundedRangeModel model;
	
	public PParameter(ICParameter control) {
		ctrl = control;
		
		configView();
		defineCallbacks();
	}

	private void configView() {
		this.setSize(100, 100);
		this.setPreferredSize(this.getSize());
		this.setBorder(BorderFactory.createTitledBorder(ctrl.getName()));
		
		model = new DoubleBoundedRangeModel("model", 10000 , ctrl.getMin(), ctrl.getMax(), ctrl.getValue());
		
		knob = new RotaryTextController(model, 2);
		
		JPanel knobPanel = new JPanel();
		knobPanel.add( knob );
		this.add(knobPanel);
		
	}

	private void defineCallbacks() {
		model.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				DoubleBoundedRangeModel model = (DoubleBoundedRangeModel) e.getSource();
				ctrl.p2cSetValue(model.getDoubleValue());
			}
		});
	}
	
	@Override
	public ICParameter getControl() {
		return ctrl;
	}

	public void setValue(double val) {
		model.setDoubleValue(val);
	}

	@Override
	public void c2pSetValue(double val) {
		setValue(val);
	}

	@Override
	public void c2pInvalidValue() {
	}
	
}
