package fr.istic.synthlab.presentation.impl;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import fr.istic.synthlab.controller.ICModule;
import fr.istic.synthlab.presentation.IPInputPort;
import fr.istic.synthlab.presentation.IPModule;
import fr.istic.synthlab.presentation.IPOutputPort;

/**
 * Presentation of a module
 */
public class PModule extends JPanel implements IPModule {

	private static final long serialVersionUID = -8519084219674310285L;
	private ICModule ctrl;

	/**
	 * @param control
	 */
	public PModule(ICModule control) {
		this.ctrl = control;

		configView();
		defineCallbacks();
	}

	private void configView() {
		this.setSize(300, 400);
		this.setPreferredSize(this.getSize());
		this.setBorder(BorderFactory.createTitledBorder(getClass()
				.getSimpleName()));
	}

	private void defineCallbacks() {
	}

	@Override
	public ICModule getControl() {
		return ctrl;
	}

	@Override
	public void addInputPort(IPInputPort presentation) {
		add((PInputPort)presentation);

	}

	@Override
	public void addOutputPort(IPOutputPort presentation) {
		remove((POutputPort)presentation);
	}

}
