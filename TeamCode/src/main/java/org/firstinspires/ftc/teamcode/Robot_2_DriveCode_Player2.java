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


@TeleOp(name = "DriveCode_Player2", group = "Robot 2")
//@Disabled
public class Robot_2_DriveCode_Player2 extends Robot_2_DriveCodeCommon {
    @Override
    public void runOpMode() {
        robot.init(hardwareMap);
        telemetry.addData("Status", "Initialized");
        Telemetry();
        waitForStart();
        robot.runtime.reset();
        while (opModeIsActive()) {
            telemetry.update();
            Telemetry();
            if (Override == 0) {
                Player_2_Drive();
            } else if (Override == 1) {
                Player_2_Override();
            }
            Toggles_2P();
            SetServoPosition();
            if (shutdown) {
                screwtoggle = 0;
                intaketoggle = 0;
                robot.Bottom_Intake_Motor.setPower(0);
            }
            if (ScrewToggle() == 1) {
                robot.Screw_Motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                robot.Screw_Motor.setPower(-0.8);
            } else if (screw_reverse) {
                robot.Screw_Motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                robot.Screw_Motor.setPower(0.2);
            } else if (ScrewToggle() == 0) {
                robot.Screw_Motor.setPower(-0.2);
                if (robot.ScrewDetector.isPressed()) {
                    robot.Screw_Motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                    robot.Screw_Motor.setTargetPosition(-145);
                    robot.Screw_Motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

                }
            }
            if (IntakeToggle() == 1) {
                robot.Top_Intake_Motor.setPower(1);
                robot.Bottom_Intake_Motor.setPower(-1);
            } else if (Intake_Reverse) {
                robot.Top_Intake_Motor.setPower(-1);
                robot.Bottom_Intake_Motor.setPower(1);
            } else {
                robot.Top_Intake_Motor.setPower(0);
                robot.Bottom_Intake_Motor.setPower(0);
            }
            if (dpad_right_was_pressed && SpinnerDirection() == 0) {
                boolean completion;
                Timestamp = robot.runtime.time();
                ResetWheelEncoders();
                teleopverticalDrive(24, 0.3, 0.05);
                completion = teleophorizontalDrive(-30, 0.3, 0.05);
                if (!completion) {
                    RunWithoutWheelEncoders();
                }
                if (completion) {
                    teleopverticalDrive(100, 0.5, 0.1);
                    RunWithoutWheelEncoders();
                }

            }
            if (dpad_right_was_pressed && SpinnerDirection() == 1) {
                boolean completion;
                Timestamp = robot.runtime.time();
                ResetWheelEncoders();
                teleopverticalDrive(24, 0.3, 0.05);
                completion = teleophorizontalDrive(30, 0.3, 0.05);
                if (!completion) {
                    RunWithoutWheelEncoders();
                }
                if (completion) {
                    teleopverticalDrive(100, 0.5, 0.1);
                    RunWithoutWheelEncoders();
                }

            }
            telemetry.update();
        }
    }
}




