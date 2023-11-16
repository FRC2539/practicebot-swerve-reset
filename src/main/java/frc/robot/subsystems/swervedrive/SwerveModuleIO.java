package frc.robot.subsystems.swervedrive;

import edu.wpi.first.math.geometry.Rotation2d;

public interface SwerveModuleIO {
    public void updateInputs(SwerveModuleIOInputs inputs);

    public class SwerveModuleIOInputs {
        double angularVelocity = 0;
        double velocity = 0;
        Rotation2d angularPosition = new Rotation2d();
        double position = 0;

        double driveTemperature = 0;
        double angleTemperature = 0;
        double driveVoltage = 0;
        double angleVoltage = 0;
        double driveCurrent = 0;
        double angleCurrent = 0;
    }

    public void setDesiredVelocityOpenLoop(double velocity);

    public void setDesiredVelocityClosedLoop(double velocity);

    public void setDesiredAngularPosition(double angularPosition);

    public void setDesiredAngularPositionAndVelocity(double angularPosition, double angularVelocity);

    public void disableMotors();
}
