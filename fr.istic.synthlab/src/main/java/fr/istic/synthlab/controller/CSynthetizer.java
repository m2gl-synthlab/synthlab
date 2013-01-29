package fr.istic.synthlab.controller;

import fr.istic.synthlab.abstraction.impl.InputPort;
import fr.istic.synthlab.abstraction.impl.OutputPort;
import fr.istic.synthlab.abstraction.impl.Synthetizer;
import fr.istic.synthlab.factory.IFactory;
import fr.istic.synthlab.factory.PACFactory;
import fr.istic.synthlab.presentation.PSynthetizer;

public class CSynthetizer extends Synthetizer {

	private PSynthetizer pSynthetizer;
	
	public CSynthetizer(){
		this.pSynthetizer = new PSynthetizer();
		
		IFactory factory = PACFactory.getFactory();
		
		// Ajout des modules
		CModule vco = (CModule)factory.newVCO(factory);
		CModule vca = (CModule)factory.newVCA(factory);
		this.addModule(vco);
		this.addModule(vca);

		pSynthetizer.addPModule(vco.getPresentation());
		pSynthetizer.addPModule(vca.getPresentation());
		
		// Ajout des fils
		CWire wire = (CWire)factory.newWire(factory);
		wire.connect((OutputPort) vco.getPort(0));
		wire.connect((InputPort) vca.getPort(0));
	}

	public PSynthetizer getPresentation(){
		return pSynthetizer;
	}

}
