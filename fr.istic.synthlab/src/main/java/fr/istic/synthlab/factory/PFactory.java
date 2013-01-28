package fr.istic.synthlab.factory;

public class PFactory {

	private static final PFactory instance = new PFactory();

	private PFactory() {}

	public static PFactory getInstance() {
		return instance;
	}

}