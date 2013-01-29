package fr.istic.synthlab.factory;

import fr.istic.synthlab.controller.ICModule;
import fr.istic.synthlab.controller.ICSynthesizer;
import fr.istic.synthlab.presentation.IPModule;
import fr.istic.synthlab.presentation.IPSynthesizer;

public class PFactory implements IPFactory{

	private static final PFactory instance = new PFactory();

	private PFactory() {}

	public static PFactory getInstance() {
		return instance;
	}

	@Override
	public IPSynthesizer newSynthesizer(ICSynthesizer control) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IPModule newModule(ICModule control) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IPModule newVCO(ICModule control) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IPModule newVCF(ICModule control) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IPModule newVCA(ICModule control) {
		// TODO Auto-generated method stub
		return null;
	}

}