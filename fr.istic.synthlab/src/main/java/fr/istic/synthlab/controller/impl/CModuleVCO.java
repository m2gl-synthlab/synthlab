package fr.istic.synthlab.controller.impl;

import java.util.ArrayList;
import java.util.List;

import fr.istic.synthlab.abstraction.IInputPort;
import fr.istic.synthlab.abstraction.IOutputPort;
import fr.istic.synthlab.abstraction.IParameter;
import fr.istic.synthlab.abstraction.impl.ModuleVCO;
import fr.istic.synthlab.controller.ICInputPort;
import fr.istic.synthlab.controller.ICModule;
import fr.istic.synthlab.controller.ICOutputPort;
import fr.istic.synthlab.controller.ICParameter;
import fr.istic.synthlab.controller.ICWire;
import fr.istic.synthlab.factory.impl.PACFactory;
import fr.istic.synthlab.presentation.IPModule;

public class CModuleVCO extends ModuleVCO implements ICModule {

	private IPModule pres;
	private List<ICWire> wires;

	public CModuleVCO(String name) {
		super(name);
		this.pres = PACFactory.getPFactory().newVCO(this);

		wires = new ArrayList<ICWire>();
		
		
		
		IParameter amplitude = this.getParameter(PARAM_AMPLITUDE);
		pres.addParameter(((ICParameter) amplitude).getPresentation());
		
		IParameter frequency = this.getParameter(PARAM_FREQUENCY);
		pres.addParameter(((ICParameter) frequency).getPresentation());
		
		IInputPort inputModulation = this.getInput(INPUT_MOD_FREQ);
		pres.addInputPort(((ICInputPort) inputModulation).getPresentation());
		
		IOutputPort outputSquare = this.getOutput(OUTPUT_SQUARE);
		pres.addOutputPort(((ICOutputPort) outputSquare).getPresentation());

		IOutputPort outputTriangle = this.getOutput(OUTPUT_TRIANGLE);
		pres.addOutputPort(((ICOutputPort) outputTriangle).getPresentation());
		
		IOutputPort outputSine = this.getOutput(OUTPUT_SINE);
		pres.addOutputPort(((ICOutputPort) outputSine).getPresentation());

		IOutputPort outputSawtooth = this.getOutput(OUTPUT_SAWTOOTH);
		pres.addOutputPort(((ICOutputPort) outputSawtooth).getPresentation());

	}

	@Override
	public IPModule getPresentation() {
		return pres;
	}

	@Override
	public List<ICWire> getWires() {
		return wires;
	}

	@Override
	public void addWire(ICWire cWire) {
		wires.add(cWire);
	}

}
