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
import org.usfirst.frc295.GrizzlynatorBase.commands.CmdShooterPaddleDefaultRetract;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;


/**
 *   Functionality:
 *   CmdShooterPaddleActivate will run the motor for 1 second
 *   Once the command is completed
 *   The Default Command CmdShootorPaddleDefault will retract the paddle until the retracted switch is activated
 */
public class SysShooterPaddle extends Subsystem 
{

    private VictorSP         _escPaddle;
    private DigitalInput     _switchPaddleRetractDetect;


    public SysShooterPaddle()
    {
		super();

		// ==========================================================
        // SYS SHOOTER PADDLE 
        // ==========================================================
    	_escPaddle = new VictorSP(RobotMap.TBD);
        LiveWindow.addActuator("Sys Shooter Paddle", "Esc Paddle", _escPaddle);
        
        _switchPaddleRetractDetect = new DigitalInput(RobotMap.TBD);
        LiveWindow.addActuator("Sys Shooter Paddle", "Retract Switch", _switchPaddleRetractDetect);
    	
    }
    
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	public boolean isRetracted() 
	{
		return (_switchPaddleRetractDetect.get());
	}

	
	// WARNING: THIS STARTS THE MOTOR FOR THE PADDLE.  SOMETING NEEDS TO stopPaddle() THE MOTOR
	//          THE DEFAULT COMMAND SHOULD RETRACT AND STOP THE MOTOR OF THE PADDLE 
	//          IF NO OTHER COMMAND REQUIRES SysShooterPaddle
	public void startShoot() 
	{
		setSpeed(-1);
	}

	
	// KEEP CALLING THIS UNTIL YOU ARE SURE THAT THE PADDLE HAS BEEN RETRACTED
	// IT'S OK TO KEEP CALLING THIS EVEN WHEN THE PADDLE IS RETRACTED
	public void retractPaddle() 
	{
		if (isRetracted() == true)
		{
			setSpeed(0.0);
		}
		else
		{
			setSpeed(0.3);
		}
			
	}

	
	public void stopPaddle() 
	{
		setSpeed(0.0);
	}
	

	protected void setSpeed(double dSpeed) 
	{
		_escPaddle.set(dSpeed);
	}


	
    public void initDefaultCommand() 
    {
        // Set the default command for a subsystem here.
    	// DEFAULT ACTION FOR THE PADDLE IS TO RETRACT
        setDefaultCommand(new CmdShooterPaddleDefaultRetract());
    }

    /**
	 * The log method puts interesting information to the SmartDashboard.
	 */
	public void logToSmartDashboard() 
    {
	
	}
}
