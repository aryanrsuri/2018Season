// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.

package org.usfirst.frc295.GrizzlynatorBase;

import org.usfirst.frc295.GrizzlynatorBase.commands.AutonomousCommand;
import org.usfirst.frc295.GrizzlynatorBase.commands.CmdDriveTrainShifterSetHigh;
import org.usfirst.frc295.GrizzlynatorBase.commands.CmdDriveTrainShifterSetLow;

import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI
{
	//// CREATING BUTTONS
	// One type of button is a joystick button which is any button on a
	//// joystick.
	// You create one by telling it which joystick it's on and which button
	// number it is.
	// Joystick stick = new Joystick(port);
	// Button button = new JoystickButton(stick, buttonNumber);

	// There are a few additional built in buttons you can use. Additionally,
	// by subclassing Button you can create custom triggers and bind those to
	// commands the same as any other Button.

	//// TRIGGERING COMMANDS WITH BUTTONS
	// Once you have a button, it's trivial to bind it to a button in one of
	// three ways:

	// Start the command when the button is pressed and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenPressed(new ExampleCommand());

	// Run the command while the button is being held down and interrupt it once
	// the button is released.
	// button.whileHeld(new ExampleCommand());

	// Start the command when the button is released and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenReleased(new ExampleCommand());

	private JoystickDriver _joystickDriver = new JoystickDriver(0, JoystickDriver.JoystickType.XBOX);
	// = new JoystickDriver(0, JoystickDriver.JoystickType.LOGITECH);
	private JoystickOperator _joystickOperator;
	// = new JoystickOperator(1, JoystickOperator.JoystickType.XBOX);
	// = new JoystickOperator(1, JoystickOperator.JoystickType.LOGITECH);


	public OI()
	{
		// =====================================================================
		// SMART DASHBOARD COMMANDS
		// =====================================================================

		// SmartDashboard Button to start the Autonomous Command
		SmartDashboard.putData("Autonomous Command", new AutonomousCommand());

		// =====================================================================
		// DRIVER JOYSTICK BUTTON COMMANDS
		// =====================================================================
		JoystickButton _btnDriverA = _joystickDriver.getDriveTrainShifterSetLowButton();
		_btnDriverA.whenPressed(new CmdDriveTrainShifterSetLow());
		SmartDashboard.putData("CMD Shift Slow", new CmdDriveTrainShifterSetLow());

		JoystickButton _btnDriverY = _joystickDriver.getDriveTrainShifterSetHighButton();
		_btnDriverY.whenPressed(new CmdDriveTrainShifterSetHigh());
		SmartDashboard.putData("CMD Shift Fast", new CmdDriveTrainShifterSetHigh());

		/*
		 * JoystickButton _btnDriverB = new JoystickButton(_joystickDriver, 3);
		 * _btnDriverY.whenPressed(new CmdFlywheelSetSpeed(9000));
		 * SmartDashboard.putData("Cmd Flywheel", new
		 * CmdFlywheelSetSpeed(2500));
		 */
		/*
		 * 
		 * JoystickButton _btnDriverB = new JoystickButton(_joystickDriver, 3);
		 * _btnDriverB.whenPressed(new CmdDriveTrainSetForward());
		 * SmartDashboard.putData("Cmd Drive Forward Arm", new
		 * CmdDriveTrainSetForward());
		 * 
		 * JoystickButton _btnDriverX = new JoystickButton(_joystickDriver, 4);
		 * _btnDriverX.whenPressed(new CmdDriveTrainSetBackward());
		 * SmartDashboard.putData("Cmd Drive Forward Shooter", new
		 * CmdDriveTrainSetBackward());
		 */

		// JoystickButton _btnDriver5 = new JoystickButton(_joystickDriver, 1);
		// _btnDriver5.whenPressed(new CmdFlywheelSetSpeed(0.25));
		// SmartDashboard.putData("Cmd Flywheel Speed 0.25", new
		// CmdFlywheelSetSpeed(0.25));

		// =====================================================================
		// OPERATOR JOYSTICK BUTTON COMMANDS
		// =====================================================================

	}


	public JoystickDriver getJoystickDriver()
	{
		return _joystickDriver;
	}


	public JoystickOperator getJoystickOperator()
	{
		return _joystickOperator;
	}
}
