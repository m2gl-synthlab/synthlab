package fr.istic.synthlab.presentation.impl;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import com.jsyn.scope.AudioScopeModel;
import com.jsyn.scope.swing.AudioScopeView;

import fr.istic.synthlab.controller.ICInputPort;
import fr.istic.synthlab.controller.ICModuleAudioScope;
import fr.istic.synthlab.controller.ICOutputPort;
import fr.istic.synthlab.presentation.IPModuleAudioScope;

/**
 * Presentation for a OUT module
 */
public class PModuleAudioScope extends APModule implements IPModuleAudioScope {

	private static final long serialVersionUID = -8519084219674310285L;

	private ICModuleAudioScope ctrl;
	private AudioScopeModel model;
	
	private PInputPort inputPort;
	private POutputPort outputPort;
	
	

	private int width;
	private int height;

	/**
	 * @param control
	 */
	public PModuleAudioScope(ICModuleAudioScope control) {
		super(control);
		this.ctrl = control;
		configView();
		defineCallbacks();
	}

	private void configView() {
		
		this.setBackground(Color.GRAY);
		
		JPanel panelScope= new JPanel();
		JPanel panelPort = new JPanel();

		AudioScopeView scope = new AudioScopeView();
		model = ctrl.getModel();
		scope.setModel(model);
		scope.setControlsVisible(false);
		panelScope.add(scope);

		inputPort = (PInputPort) ((ICInputPort) ctrl.getInput()).getPresentation();
		outputPort = (POutputPort) ((ICOutputPort) ctrl.getOutput()).getPresentation();
		panelPort.add(inputPort);
		panelPort.add(outputPort);

		this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.PAGE_AXIS));
		this.setAutoscrolls(true);
		this.getContentPane().add(panelScope, 0);
		this.getContentPane().add(panelPort, 1);

		Dimension size = new Dimension(600, 400);
		this.setPreferredSize(size);

		// TODO : Not sure if it's the better way to define the size...
		width = size.width;
		height = size.height;

	}

	private void defineCallbacks() {
	}
	
	
	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}

	@Override
	public ICModuleAudioScope getControl() {
		return ctrl;
	}

}
