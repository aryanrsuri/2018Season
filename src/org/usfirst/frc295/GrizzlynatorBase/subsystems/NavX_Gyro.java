package org.usfirst.frc295.GrizzlynatorBase.subsystems;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.GyroBase;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.livewindow.LiveWindowSendable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Use a rate gyro to return the robots heading relative to a starting position.
 * The Gyro class tracks the robots heading based on the starting position. As
 * the robot rotates the new heading is computed by integrating the rate of
 * rotation returned by the sensor. When the class is instantiated, it does a
 * short calibration routine where it samples the gyro while at rest to
 * determine the default offset. This is subtracted from each sample to
 * determine the heading.
 *
 * This class is for the digital ADXRS453 gyro sensor that connects via SPI. A
 * datasheet can be found here:
 * http://www.pdocs.kauailabs.com/navx-mxp/intro/technical-specifications/
 * http://www.kauailabs.com/public_files/navx-mxp/apidocs/java/
 *
 * Note that the onboard gyroscopes are sensitive to temperature changes.
 * Therefore, since the average ambient temperature at the factory (on the
 * island of Kauai in Hawaii) may be different than in your environment, you can
 * optionally choose to re-calibrate the gyroscope by pressing and holding the
 * "CAL" button for at least 10 seconds. When you release the "CAL" button,
 * ensure that the "CAL" Led flashes briefly, and then press the "RESET" button
 * to restart navX-MXP. When navX-MXP is re-started, it will perform the Initial
 * Gyro Calibration - the same process that occurs at our factory.
 *
 * NOTE: It is very important to hold navX-MXP still, and parallel to the
 * earth's surface, during this Initial Gyro Calibration period. You might
 * consider performing this process before using your robot the first time it is
 * used within a new environment (e.g., when you arrive at a FTC competition
 * event).
 */
public class NavX_Gyro extends GyroBase implements Gyro, PIDSource
{
	private AHRS m_spi;


	/**
	 * Constructor.
	 */
	public NavX_Gyro()
	{
		this(SPI.Port.kMXP);
	}


	/**
	 * Constructor.
	 *
	 * @param port
	 *            (the SPI port that the gyro is connected to)
	 */
	public NavX_Gyro(SPI.Port port)
	{
		try
		{
			/* Communicate w/navX-MXP via the MXP SPI Bus. */
			/*
			 * Alternatively: I2C.Port.kMXP, SerialPort.Port.kMXP or
			 * SerialPort.Port.kUSB
			 */
			/*
			 * See
			 * http://navx-mxp.kauailabs.com/guidance/selecting-an-interface/
			 * for details.
			 */

			m_spi = new AHRS(port);
		}
		catch (RuntimeException ex)
		{
			m_spi.free();
			m_spi = null;
			DriverStation.reportError("Error instantiating navX-MXP:  " + ex.getMessage(), true);
		}

		calibrate();

		SmartDashboard.putBoolean("IMU_Connected", m_spi.isConnected());
		SmartDashboard.putBoolean("IMU_IsCalibrating", m_spi.isCalibrating());
		SmartDashboard.putNumber("IMU_Yaw", m_spi.getYaw());
		SmartDashboard.putNumber("IMU_CompassHeading", m_spi.getCompassHeading());

		/*
		 * These functions are compatible w/the WPI Gyro Class, providing a
		 * simple
		 */
		/* path for upgrading from the Kit-of-Parts gyro to the navx-MXP */
		SmartDashboard.putNumber("IMU_TotalYaw", m_spi.getAngle());
		SmartDashboard.putNumber("IMU_YawRateDPS", m_spi.getRate());

		LiveWindow.addSensor("NavX_Gyro", port.value, this);
	}


	/**
	 * This is a blocking calibration call. There are also non-blocking options
	 * available in this class!
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public synchronized void calibrate()
	{
		// AUTO CALIBRATES IF NO MOVEMENT DETECTED FOR 8 SECONDS
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public synchronized void reset()
	{
		// CAN ONLY ADJUST ANGLE by +/- 360
		int iRotation = (int) (m_spi.getAngle() / 360);
		for (int i = 0; i < Math.abs(iRotation); i++)
		{
			if (iRotation > 0)
			{
				m_spi.setAngleAdjustment(-360);
			}
			else
			{
				m_spi.setAngleAdjustment(360);
			}
		}
		m_spi.setAngleAdjustment(-1 * m_spi.getAngle());

		// ONLY RESET's THE YAW
		m_spi.reset();
	}


	/**
	 * Delete (free) the spi port used for the gyro and stop accumulating.
	 */
	@Override
	public void free()
	{
		if (m_spi != null)
		{
			m_spi.free();
			m_spi = null;
		}
	}


	public synchronized double getYaw()
	{
		if (m_spi == null)
		{
			return 0.0;
		}
		return m_spi.getYaw();
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public synchronized double getAngle()
	{
		if (m_spi == null)
		{
			return 0.0;
		}
		return m_spi.getAngle();
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public synchronized double getRate()
	{
		if (m_spi == null)
		{
			return 0.0;
		}
		return m_spi.getRate();
	}
}