
package org.firstinspires.ftc.teamcode;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "DriveCode_Player1", group = "Robot 2")
public class DriveCode_Player1 extends DriveCodeCommon_Teleop {

    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        Telemetry();
        waitForStart();
        while (!isStopRequested()) {
            // Make sure to call drive.update() on *every* loop
            // Increasing loop time by utilizing bulk reads and minimizing writes will increase your odometry accuracy
            drive.update();

            // Retrieve your pose
            Pose2d myPose = drive.getPoseEstimate();

            telemetry.addData("x", myPose.getX());
            telemetry.addData("y", myPose.getY());
            telemetry.addData("heading", myPose.getHeading());
            telemetry.update();
            Telemetry();
            telemetry.update();
            Telemetry();
            Player_1_Drive();
            Toggles1P();
            SetServoPosition();
            sensors();
            screw();
            intake();
            drive.cappingServoY.setPosition(0.15);
            drive.cappingServoX.setPosition(0.5);
            telemetry.update();
        }

    }

}




