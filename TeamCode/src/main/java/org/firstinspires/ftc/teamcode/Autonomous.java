package org.firstinspires.ftc.teamcode;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Old.BlueDriveCode_Player1;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;

/*
 * This is an example of a more complex path to really test the tuning.
 */
@com.qualcomm.robotcore.eventloop.opmode.Autonomous(group = "drive")
public class Autonomous extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        DriveCodeCommon_Auto drive = new DriveCodeCommon_Auto(hardwareMap);

        waitForStart();

        if (isStopRequested()) return;
        drive.BlueScoreWarehouse();
//        while (!isStopRequested()) {
//            drive.setWeightedDrivePower(
//                    new Pose2d(
//                            -gamepad1.left_stick_y,
//                            -gamepad1.left_stick_x,
//                            -gamepad1.right_stick_x
//                    )
//            );
//
//            drive.update();
//
//            Pose2d poseEstimate = drive.getPoseEstimate();
//            telemetry.addData("x", poseEstimate.getX());
//            telemetry.addData("y", poseEstimate.getY());
//            telemetry.addData("heading", poseEstimate.getHeading());
//            telemetry.update();
//        }
    }
}
