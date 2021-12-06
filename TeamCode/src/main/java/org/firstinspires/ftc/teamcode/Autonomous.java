package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;

@com.qualcomm.robotcore.eventloop.opmode.Autonomous(name = "Autonomous", group = "Robot_2")
//@Disabled
public class Autonomous extends Robot_2_DriveCodeCommon {
    @Override
    public void runOpMode() {
        robot.init(hardwareMap);
        ResetWheelEncoders();
        if (gamepad1.a) {
            team++;
        }

        if (gamepad1.b) {
            mode++;
        }
        if (gamepad1.x) {
            side++;
        }
        if (Team() == 0 && Mode() == 0 && Side() == 0) {
            telemetry.addData("Team", "Red");
            telemetry.addData("Side", "Left");
            telemetry.addData("Mode", "Nothing");
            waitForStart();
        }
        if (Team() == 1 && Mode() == 0 && Side() == 0) {
            telemetry.addData("Team", "Blue");
            telemetry.addData("Side", "Left");
            telemetry.addData("Mode", "Nothing");
            waitForStart();
        }

        if (Team() == 0 && Mode() == 1 && Side() == 0) {
            telemetry.addData("Team", "Red");
            telemetry.addData("Side", "Right");
            telemetry.addData("Mode", "Nothing");
            waitForStart();
        }
        if (Team() == 0 && Mode() == 0 && Side() == 0) {
            telemetry.addData("Team", "Red");
            telemetry.addData("Side", "Left");
            telemetry.addData("Mode", "Nothing");
            waitForStart();
        }
        if (Team() == 0 && Mode() == 0 && Side() == 0) {
            telemetry.addData("Team", "Red");
            telemetry.addData("Side", "Left");
            telemetry.addData("Mode", "Nothing");
            waitForStart();

        }
    }
}