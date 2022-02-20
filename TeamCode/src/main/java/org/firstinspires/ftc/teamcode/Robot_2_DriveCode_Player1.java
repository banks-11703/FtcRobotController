
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;


@TeleOp(name = "DriveCode_Player1", group = "Robot 2")
public class Robot_2_DriveCode_Player1 extends Robot_2_DriveCodeCommon {
    @Override
    public void runOpMode() {
        robot.init(hardwareMap);
        telemetry.addData("Status", "Initialized");
        Telemetry();
        waitForStart();
        while (opModeIsActive()) {
            telemetry.update();
            telemetry.addData("Switch", robot.ScrewDetector.getValue());
            telemetry.addData("Screw Pos", robot.Screw_Motor.getCurrentPosition());
            Telemetry();
            Player_1_Drive();
            Toggles1P();
            SetServoPosition();
            lightSequence();
            if (ScrewToggle() == 1) {
                robot.Screw_Motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                robot.Screw_Motor.setPower(-0.7);
                intaketoggle = 0;
            } else if (screw_reverse) {
                robot.Screw_Motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                robot.Screw_Motor.setPower(0.2);
            } else if (ScrewToggle() == 0) {
                robot.Screw_Motor.setPower(-0.1);
                if (robot.ScrewDetector.isPressed()) {
                    robot.Screw_Motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                    robot.Screw_Motor.setTargetPosition(-145);
                    robot.Screw_Motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                }
            }
            if (intakeToggle() == 1) {
                robot.Top_Intake_Motor.setPower(1);
                robot.Bottom_Intake_Motor.setPower(1);
                screwtoggle = 0;
            } else if (Intake_Reverse) {
                robot.Top_Intake_Motor.setPower(-1);
                robot.Bottom_Intake_Motor.setPower(-1);
            } else {
                robot.Top_Intake_Motor.setPower(0);
                robot.Bottom_Intake_Motor.setPower(0);
            }
            if (!robot.intakeDetector.isPressed() && !cubeIntaking && TimeSinceStamp3() >= 3 && intakeToggle() == 1) {
                Timestamp2 = robot.runtime.time();
                intaketoggle = 0;
                cubeIntaking = true;
            }
            if (TimeSinceStamp2() >= 1 && ScrewToggle() == 0 && cubeIntaking) {
                intaketoggle = 1;
                cubeIntaking = false;
                timestamp3 = robot.runtime.time();
            }
            if (robot.intakeSensor.getDistance(DistanceUnit.INCH) >= 4.5) {
                cubeInScrewOpening = false;
            } else if (robot.intakeSensor.getDistance(DistanceUnit.INCH) <= 4.5) {
                cubeInScrewOpening = true;
                screwtoggle = 0;
                timestamp4 = robot.runtime.time();
                cubeWasInScrewOpening = true;
            }
            if (timestamp4 >= 2 && !cubeInScrewOpening && cubeWasInScrewOpening) {
                intaketoggle = 0;
                cubeWasInScrewOpening = false;
            }
            telemetry.update();
        }

    }

}




