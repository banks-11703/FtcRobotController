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


@TeleOp(name = "RedDriveCode_Player1", group = "Robot 2")
//@Disabled
public class Robot_2_RedDriveCode_Player1 extends DriveCodeCommon {

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
            boolean screw_reverse;
            boolean screw_on;
            boolean button_y_pressed;
            boolean Grabber_toggle;
            boolean Spinner;
            boolean dpad_right_is_pressed;

            screw_reverse = gamepad1.left_bumper;
            screw_on = gamepad1.right_bumper;
            button_y_pressed = gamepad1.y;
            Spinner = gamepad1.b;
            dpad_right_is_pressed = gamepad1.dpad_right;
            Player_1_Drive();
            Toggles_Robot_2();
            SetServoPosition();

            if (Spinner) {
                robot.SpinnerMotor.setPower(-0.2);
            } else {
                robot.SpinnerMotor.setPower(0);
            }
            if (ScrewToggle() == 1) {
                robot.Screw_Motor.setPower(1);
            } else if (dpad_left_is_pressed) {
                robot.Intake_Motor.setPower(-1);
            } else {
                robot.Intake_Motor.setPower(0);
            }
            if (IntakeToggle() == 1) {
                robot.Intake_Motor.setPower(-1);
            } else if (dpad_right_is_pressed) {
                robot.Intake_Motor.setPower(1);
            } else {
                robot.Intake_Motor.setPower(0);
            }
            if (screw_on){
                robot.Screw_Motor.setPower(1);
            }
            else if (screw_reverse){
                robot.Screw_Motor.setPower(-1);
            }
            else {
                robot.Screw_Motor.setPower(0);
            }

            telemetry.addData("Status", "Run Time: " + robot.runtime.toString());
            telemetry.update();
        }

    }

}




