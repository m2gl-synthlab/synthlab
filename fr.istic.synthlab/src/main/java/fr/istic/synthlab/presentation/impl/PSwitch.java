package fr.istic.synthlab.presentation.impl;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import fr.istic.synthlab.controller.ICParameter;
import fr.istic.synthlab.presentation.IPParameter;

public class PSwitch extends JPanel implements IPParameter {

	private static final long serialVersionUID = 7359022086561577171L;

	private ICParameter ctrl;

	private JToggleButton toggle;

	public PSwitch(ICParameter control) {
		ctrl = control;

		configView();
		defineCallbacks();
	}

	private void configView() {
		this.setSize(100, 100);
		this.setPreferredSize(this.getSize());
		this.setBorder(BorderFactory.createTitledBorder(ctrl.getName()));

		toggle = new JToggleButton();

		JPanel knobPanel = new JPanel();
		knobPanel.add(toggle);
		this.add(knobPanel);

	}

	private void defineCallbacks() {
		toggle.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				double value = ctrl.getMin(); 
				if (toggle.getModel().isSelected())
					value = ctrl.getMax();
				ctrl.p2cSetValue(value);
			}
		});
	}

	@Override
	public ICParameter getControl() {
		return ctrl;
	}

	public void setValue(double val) {
		if (val >= (ctrl.getMin() + ctrl.getMax()) / 2)
			toggle.getModel().setSelected(true);
		else
			toggle.getModel().setSelected(false);
	}

	@Override
	public void c2pSetValue(double val) {
		setValue(val);
	}

	@Override
	public void c2pInvalidValue() {
	}

}
