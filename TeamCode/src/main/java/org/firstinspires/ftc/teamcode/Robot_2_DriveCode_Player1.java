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
            if(robot.intakeSensor.getDistance(DistanceUnit.INCH) >= 3.5){
                cubeInScrewOpening = false;
            }else if(robot.intakeSensor.getDistance(DistanceUnit.INCH) <= 3.5){
                cubeInScrewOpening = true;
                screwtoggle = 0;
                timestamp4 = robot.runtime.time();
            }
            if (timestamp4 >= 2 && cubeInScrewOpening){
                intaketoggle = 0;
            }
            telemetry.update();
        }

    }

}




