package org.firstinspires.ftc.teamcode;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DigitalChannel;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;

@TeleOp(name = "DriveCode_Player2", group = "Robot 2")
public class DriveCode_Player2 extends DriveCodeCommon_Teleop {

    @Override
    public void runOpMode() {
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);
        drive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        drive.setPoseEstimate(PoseStorage.currentPose);
        telemetry.addData("Status", "Initialized");
        Telemetry();
        drive.cappingServoY.scaleRange(0.55, 0.85);
        waitForStart();
        drive.runtime.reset();
        drive.redLED.setMode(DigitalChannel.Mode.OUTPUT);
        drive.greenLED.setMode(DigitalChannel.Mode.OUTPUT);
        drive.redLED1.setMode(DigitalChannel.Mode.OUTPUT);
        drive.greenLED1.setMode(DigitalChannel.Mode.OUTPUT);
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

            Toggles_2P();
            SetServoPosition();
            capping();
            sensors();
            screw();
            intake();
            duckSpinner(RobotConstants.initDuckSpeed,RobotConstants.duckrate);
            //showdown is when button a,b,y all pressed
            if (shutdown) {
                screwtoggle = 0;
                drive.Screw_Motor.setPower(0);
                intaketoggle = 0;
                drive.Bottom_Intake_Motor.setPower(0);
            }
            telemetry.update();
        }
    }
}




