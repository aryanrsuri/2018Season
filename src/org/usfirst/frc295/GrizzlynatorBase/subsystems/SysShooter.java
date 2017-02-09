// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc295.GrizzlynatorBase.subsystems;

import org.usfirst.frc295.GrizzlynatorBase.RobotMap;
import org.usfirst.frc295.GrizzlynatorBase.commands.*;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import com.ctre.CANTalon; 

/**
 *
 */
public class SysShooter extends Subsystem 
{

    private CANTalon         _escLeft;
    private CANTalon         _escRight;

    public SysShooter()
    {
		super();
    	
        // ==========================================================
        // SYS SHOOTER 
        // ==========================================================
    	_escLeft = new CANTalon(RobotMap.TBD);
    	_escLeft.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
    	_escLeft.enableBrakeMode(true);
    	_escLeft.reverseOutput(false);
    	_escLeft.enable();
        LiveWindow.addActuator("SysShooter", "Esc Left", _escLeft);

        _escRight = new CANTalon(RobotMap.TBD);
        _escRight.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
        _escRight.enableBrakeMode(true);
        _escRight.reverseOutput(false);
        _escRight.enable();
        LiveWindow.addActuator("SysShooter", "Esc Right", _escRight);
    }
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

	/**
     *  If the input is within the +- the deadband range, then ignore the input
     */
	private double deadbandAdjust(double dInput) 
    {
    	double dDeadbandRange = 0.025;
		return (Math.abs(dInput) < dDeadbandRange) ? 0 : dInput;
	}


	
	public void Shooter() 
	{
	}	
	

	public void stop() 
	{
		_escLeft.set(0);
		_escRight.set(0);
	}


	public void speedLoadLow() 
	{
		_escLeft.set(-1000);
		_escRight.set(-1000);
	}
	
	public void speedLoadHigh() 
	{
		_escLeft.set(-3000);
		_escRight.set(-3000);
	}
	
	
	public void speedShootLow() 
	{
		_escLeft.set(1000);
		_escRight.set(1000);
	}
	
	public void speedShootHigh() 
	{
		_escLeft.set(3000);
		_escRight.set(3000);
	}
	
	public void setSpeedUsingJoystick(double dSpeed) 
	{
		_escLeft.set(deadbandAdjust(dSpeed));
		_escRight.set(deadbandAdjust(dSpeed));
	}
	

	@Override
	protected void initDefaultCommand() 
	{
        // Set the default command for a subsystem here.
		// DEFAULT FOR THIS SUBSYSTEM IS TO STOP THE SHOOTER
		setDefaultCommand(new CmdShooterDefaultStop());
	}

    /**
	 * The log method puts interesting information to the SmartDashboard.
	 */
	public void logToSmartDashboard() 
    {
	
	}
}
