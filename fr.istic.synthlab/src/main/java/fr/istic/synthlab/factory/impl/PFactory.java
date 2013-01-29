package fr.istic.synthlab.factory.impl;

import fr.istic.synthlab.controller.ICModule;
import fr.istic.synthlab.controller.ICSynthesizer;
import fr.istic.synthlab.factory.IPFactory;
import fr.istic.synthlab.presentation.IPModule;
import fr.istic.synthlab.presentation.IPSynthesizer;
import fr.istic.synthlab.presentation.impl.PModule;
import fr.istic.synthlab.presentation.impl.PSynthesizer;

public class PFactory implements IPFactory{

	private static final PFactory instance = new PFactory();

	private PFactory() {}

	public static PFactory getInstance() {
		return instance;
	}

	@Override
	public IPSynthesizer newSynthesizer(ICSynthesizer control) {
		return new PSynthesizer(control);
	}

	@Override
	public IPModule newModule(ICModule control) {
		return new PModule(control);
	}

	@Override
	public IPModule newVCO(ICModule control) {
		return new PModule(control); //TODO
	}

	@Override
	public IPModule newVCF(ICModule control) {
		return new PModule(control); //TODO
	}

	@Override
	public IPModule newVCA(ICModule control) {
		return new PModule(control); //TODO
	}

}