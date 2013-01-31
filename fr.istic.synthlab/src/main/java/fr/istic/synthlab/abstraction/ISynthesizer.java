package fr.istic.synthlab.abstraction;

import com.jsyn.Synthesizer;
import com.jsyn.devices.AudioDeviceManager;
import com.jsyn.unitgen.UnitGenerator;
import com.softsynth.shared.time.ScheduledCommand;
import com.softsynth.shared.time.TimeStamp;


public interface ISynthesizer{

	public void add(IModule module);
	public Synthesizer getJSyn();

	public TimeStamp createTimeStamp();
	public AudioDeviceManager getAudioDeviceManager();
	public double getCurrentTime();
	public double getFramePeriod();
	public int getFrameRate();
	public double getUsage();
	public String getVersion();
	public int getVersionCode();
	public boolean isRealTime();
	public boolean isRunning();
	public void queueCommand(ScheduledCommand arg0);
	public void remove(UnitGenerator arg0);
	public void scheduleCommand(TimeStamp arg0, ScheduledCommand arg1);
	public void scheduleCommand(double arg0, ScheduledCommand arg1);
	public void setRealTime(boolean arg0);
	public void sleepFor(double arg0) throws InterruptedException;
	public void sleepUntil(double arg0) throws InterruptedException;
	public void start();
	public void start(int arg0);
	public void start(int arg0, int arg1, int arg2, int arg3, int arg4);
	public void startUnit(UnitGenerator arg0);
	public void startUnit(UnitGenerator arg0, double arg1);
	public void startUnit(UnitGenerator arg0, TimeStamp arg1);
	public void stop();
	public void stopUnit(UnitGenerator arg0);
	public void stopUnit(UnitGenerator arg0, double arg1);
	public void stopUnit(UnitGenerator arg0, TimeStamp arg1);
	
	
	
//	/**
//	 * Start the synthesizer
//	 */
//	public void start();
//	
//	/**
//	 * Stop the synthesizer
//	 */
//	public void stop();
//
//	/**
//	 * Is the synthesizer running ?
//	 * @return boolean
//	 */
//	public boolean isRunning();
//	
//	/**
//	 * Add the given module to the synthesizer
//	 * @param module
//	 */
//	public void addModule(IModule module);
//	
//	/**
//	 * Return the given module from the synthesizer
//	 * @param module
//	 */
//	public IModule getModule(int i);
//	
//	/**
//	 * Return all modules from the synthesizer
//	 * @param module
//	 */
//	public List<IModule> getModules();
//	
//	/**
//	 * Remove the given module from the synthesizer
//	 * @param module
//	 */
//	public void removeModule(IModule module);
//	
	
}
