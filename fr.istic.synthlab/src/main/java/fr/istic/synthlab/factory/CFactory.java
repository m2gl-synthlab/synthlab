package fr.istic.synthlab.factory;

import fr.istic.synthlab.abstraction.IModule;
import fr.istic.synthlab.abstraction.IParameter;
import fr.istic.synthlab.abstraction.IPort;
import fr.istic.synthlab.abstraction.ISynthesizer;
import fr.istic.synthlab.abstraction.IWire;
import fr.istic.synthlab.controller.impl.CModule;
import fr.istic.synthlab.controller.impl.CSynthesizer;

public class CFactory implements IFactory{
	
	private static final CFactory instance = new CFactory();

	private CFactory() {}

	public static CFactory getInstance() {
		return instance;
	}
	
	@Override
	public ISynthesizer newSynthesizer(IFactory factory) {
		return new CSynthesizer();
	}

	@Override
	public IModule newModule(String name, IFactory factory) {
		return new CModule();
	}

	@Override
	public IModule newVCO(IFactory factory) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IModule newVCF(IFactory factory) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IModule newVCA(IFactory factory) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IParameter newParameter(String name, IFactory factory) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IPort newInputPort(String name, IFactory factory) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IPort newOutputPort(String name, IFactory factory) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IWire newWire(IFactory factory) {
		// TODO Auto-generated method stub
		return null;
	}

}
