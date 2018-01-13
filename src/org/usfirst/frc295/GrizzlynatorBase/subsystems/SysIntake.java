package org.usfirst.frc295.GrizzlynatorBase.subsystems;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class SysIntake extends Subsystem {

	//CHANGE CHANNEL PARAMETERS WHEN ELECTRICAL SYSTEM IS FINALIZED
	
	Talon LeftMotor = new Talon(0); 
	Talon RightMotor = new Talon(1);

	public void initDefaultCommand() {

	}

	public void IntakeCube() {

		LeftMotor.set(1);
		RightMotor.set(-1);

	}

	public void DropCube() {

		LeftMotor.set(-1);
		RightMotor.set(1);

	}

	public void MotorSpeedReset() {
		
		LeftMotor.set(0);
		RightMotor.set(0);
		
	}
	
}

