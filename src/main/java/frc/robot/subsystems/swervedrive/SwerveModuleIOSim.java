package frc.robot.subsystems.swervedrive;

public class SwerveModuleIOSim implements SwerveModuleIO {
    public SwerveModuleIOSim() {};

    public void updateInputs(SwerveModuleIOInputs inputs) {};

    public void setDesiredVelocityOpenLoop(double velocity) {};

    public void setDesiredVelocityClosedLoop(double velocity) {};

    public void setDesiredAngularPosition(double angularPosition) {};

    public void setDesiredAngularPositionAndVelocity(double angularPosition, double angularVelocity) {};

    public void disableDriveMotor() {};

    public void disableAngleMotor() {};
}
