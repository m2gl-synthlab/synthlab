package fr.istic.synthlab.presentation.impl;

import javax.swing.BorderFactory;
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
		this.setSize(300, 400);
		this.setPreferredSize(this.getSize());
		this.setBorder(BorderFactory.createTitledBorder(getClass().getSimpleName()));
		
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

//	@Override
//	public void addPort(IPPort port) {
//		this.add((JPanel)port);
//	}
}
