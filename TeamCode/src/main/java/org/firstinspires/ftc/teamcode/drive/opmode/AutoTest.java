package org.firstinspires.ftc.teamcode.drive.opmode;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;

/*
 * This is an example of a more complex path to really test the tuning.
 */
@Autonomous(group = "drive")
@Disabled
public class AutoTest extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);

        waitForStart();

        if (isStopRequested()) return;
        Pose2d startPose = new Pose2d(6.5, 61, Math.toRadians(-90));
        drive.setPoseEstimate(startPose);
            Trajectory traj = drive.trajectoryBuilder(startPose)
                    .splineTo(new Vector2d(0, 0), 0)
                    .build();
// blue starting is 6.5,61
            drive.followTrajectory(traj);

            sleep(2000);

            drive.followTrajectory(
                    drive.trajectoryBuilder(traj.end(), true)
                            .splineTo(new Vector2d(-6, -6), Math.toRadians(180))
                            .build()
            );
    }
}
