package fr.istic.synthlab.abstraction;

import com.jsyn.ports.ConnectableInput;
import com.jsyn.ports.ConnectableOutput;
import com.jsyn.ports.PortBlockPart;
import com.jsyn.ports.UnitInputPort;
import com.jsyn.ports.UnitOutputPort;
import com.jsyn.unitgen.UnitGenerator;
import com.softsynth.shared.time.TimeStamp;

/**
 * Interface for a module's port
 * 
 * @author Clément Hardouin
 * 
 */
public interface IPort {// extends ConnectableInput, GettablePort, SettablePort, ConnectableOutput{


	public UnitOutputPort getJSynOut();
	public UnitInputPort getJSynIn();
	
	/**
	 * Return the port's name
	 * @return the name
	 */
	public String getName();
	
	public void connect(ConnectableOutput arg0);
	
	public void disconnect(ConnectableOutput arg0);
	
	public PortBlockPart getPortBlockPart();
	
	public int getNumParts();
	
	public UnitGenerator getUnitGenerator();

	public double getValue(int arg0);

	public void set(int arg0, double arg1, TimeStamp arg2);

	public void connect(ConnectableInput arg0);

	public void disconnect(ConnectableInput arg0);

	
}
