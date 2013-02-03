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
	private DoubleBoundedRangeModel amplitudeModel;
	
	public PParameter(ICParameter control) {
		ctrl = control;
		
		configView();
		defineCallbacks();
	}

	private void configView() {
		this.setSize(400, 100);
		this.setPreferredSize(this.getSize());
		this.setBorder(BorderFactory.createTitledBorder(getClass().getSimpleName()));
		
		amplitudeModel = new DoubleBoundedRangeModel("amplitude", 10000 , ctrl.getMin(), ctrl.getMax(), ctrl.getValue());
		
		knob = new RotaryTextController(amplitudeModel, 2);
		
		JPanel knobPanel = new JPanel();
		knobPanel.add( knob );
		this.add(knobPanel);
		
	}

	private void defineCallbacks() {
		amplitudeModel.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				DoubleBoundedRangeModel model = (DoubleBoundedRangeModel) e.getSource();
				System.out.println(" changed " +amplitudeModel.getValue() + " " + amplitudeModel.getDoubleValue() +" "+amplitudeModel.getExtent());
				ctrl.p2cSetValue(model.getDoubleValue());
			}
		});
		
	}
	
	@Override
	public ICParameter getControl() {
		return ctrl;
	}

	public void setValue(double val) {
		amplitudeModel.setDoubleValue(val);
	}

	@Override
	public void c2pSetValue(double val) {
		setValue(val);
	}

	@Override
	public void c2pInvalidValue() {
	}
//
//	@Override
//	public void c2pSetRangeModel(DoubleBoundedRangeModel model) {
//		defineCallbacks();
//		knob = new RotaryTextController(model, 2);
//		this.add(knob);
//	}
	
}
