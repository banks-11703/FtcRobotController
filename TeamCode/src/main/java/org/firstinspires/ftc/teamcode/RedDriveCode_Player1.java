/* Copyright (c) 2017 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DigitalChannel;


/**
 * This file contains an minimal example of a Linear "OpMode". An OpMode is a 'program' that runs in either
 * the autonomous or the teleop period of an FTC match. The names of OpModes appear on the menu
 * of the FTC Driver Station. When an selection is made from the menu, the corresponding OpMode
 * class is instantiated on the Robot Controller and executed.
 * <p>
 * This particular OpMode just executes a basic Tank Drive Teleop for a two wheeled robot
 * It includes all the skeletal structure that all linear OpModes contain.
 * <p>
 * Use Android Studios to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list
 */

@TeleOp(name = "RedDriveCode_Player1", group = "Robot 1")
//@Disabled
public class RedDriveCode_Player1 extends DriveCodeCommon {

    @Override
    public void runOpMode() {
        robot.init(hardwareMap);
        telemetry.addData("Status", "Initialized");
        ScoringModeTelemetry();

        waitForStart();

        robot.runtime.reset();

        robot.redLED.setMode(DigitalChannel.Mode.OUTPUT);
        robot.greenLED.setMode(DigitalChannel.Mode.OUTPUT);
        while (opModeIsActive()) {
            telemetry.update();
            ScoringModeTelemetry();
            ArmPosModeTelemetry();
            boolean pivot_up;
            boolean pivot_down;
            boolean button_y_pressed;
            boolean Grabber_toggle;
            boolean Spinner;
            boolean dpad_right_is_pressed;

            pivot_up = gamepad1.left_bumper;
            pivot_down = gamepad1.right_bumper;
            button_y_pressed = gamepad1.y;
            Grabber_toggle = gamepad1.x;
            Spinner = gamepad1.b;
            dpad_right_is_pressed = gamepad1.dpad_right;
            Player_1_Drive();
            Toggles1P();
            SetServoPosition();

            if (Spinner) {
                robot.SpinnerMotor.setPower(-0.2);
            } else {
                robot.SpinnerMotor.setPower(0);
            }


            if (IntakeToggle() == 1) {
                robot.Intake_Servo.setPower(-1);
            } else if (dpad_right_is_pressed) {
                robot.Intake_Servo.setPower(1);
            } else {
                robot.Intake_Servo.setPower(0);
            }


            if (ArmPosMode == 0) {
                robot.PivotMotor.setTargetPosition(Intake);
            }
            if (ArmPosMode == 1) {
                robot.PivotMotor.setTargetPosition(Raised);
            }
            if (ArmPosMode == 2) {
                robot.PivotMotor.setTargetPosition(Scoring);
            }
            if (button_y_pressed) {
                robot.PivotMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                robot.PivotMotor.setPower(0.5);
            } else if (pivot_up) {
                robot.PivotMotor.setPower(1);
            } else if (pivot_down) {
                robot.PivotMotor.setPower(-1);
            } else {
                robot.PivotMotor.setPower(0);
            }

            if (Grabber_toggle) {
                robot.Grabber.setPosition(0.8);
            } else {
                robot.Grabber.setPosition(0);
            }

            telemetry.addData("Status", "Run Time: " + robot.runtime.toString());
            telemetry.addData("Arm Pos", robot.PivotMotor.getCurrentPosition());
            telemetry.addData("Arm Target Pos", robot.PivotMotor.getTargetPosition());
            telemetry.addData("PosMode", ArmPosMode);
            telemetry.update();
        }

    }

}




