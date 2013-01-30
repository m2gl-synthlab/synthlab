package fr.istic.synthlab.presentation.impl;

import javax.swing.JLabel;
import javax.swing.JPanel;

import fr.istic.synthlab.controller.ICModule;
import fr.istic.synthlab.presentation.IPModule;

/**
 * 
 *
 */
public class PModule extends JPanel implements IPModule {

	private static final long serialVersionUID = -8519084219674310285L;
	private ICModule ctrl;

	/**
	 * @param control
	 */
	public PModule(ICModule control) {
		this.ctrl = control;
		JLabel nameLbl = new JLabel(getClass().getSimpleName());
		this.add(nameLbl);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.istic.synthlab.presentation.IPModule#getControl()
	 */
	@Override
	public ICModule getControl() {
		return ctrl;
	}
}
