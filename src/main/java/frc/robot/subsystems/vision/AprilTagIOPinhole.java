package frc.robot.subsystems.vision;

import java.util.Optional;

import frc.robot.subsystems.vision.AprilTagIO.AprilTagIOInputs;

public class AprilTagIOPinhole implements AprilTagIO {
    private CameraInfoIO cameraInfoIO;

    public AprilTagIOPinhole(CameraInfoIO cameraInfo) {
        this.cameraInfoIO = cameraInfo;
    }

    public Optional<AprilTagIOInputs> updateInputs() {
        return cameraInfoIO.updateInputs().map(
            inputs -> {
                AprilTagIOInputs myInputs = new AprilTagIOInputs();

                double targetOffsetAngle_Vertical = ty.getDouble(0.0);

                //TODO: finish this junk
                // how many degrees back is your limelight rotated from perfectly vertical?
                double limelightMountAngleDegrees = 25.0; 

                // distance from the center of the Limelight lens to the floor
                double limelightLensHeightInches = 20.0; 

                // distance from the target to the floor
                double goalHeightInches = 60.0; 

                double angleToGoalDegrees = limelightMountAngleDegrees + targetOffsetAngle_Vertical;
                double angleToGoalRadians = angleToGoalDegrees * (3.14159 / 180.0);

                //calculate distance
                double distanceFromLimelightToGoalInches = (goalHeightInches - limelightLensHeightInches) / Math.tan(angleToGoalRadians);

                myInputs.poseEstimate = inputs.poseEstimate3d.toPose2d();
                myInputs.poseEstimate3d = inputs.poseEstimate3d;
                myInputs.targetDistance = inputs.targetDistance;
                myInputs.timestamp = inputs.timestamp;

                return myInputs;
            }
        );
    }
}
