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

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;


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

@TeleOp(name = "DriveCodeCommon", group = "Linear Opmode")
@Disabled
public class DriveCodeCommon extends LinearOpMode {
    TikhHardware robot = new TikhHardware();
    double forward_reverse;
    double rotate;
    double strafe;
    int ServoMode = 0;
    int ArmPosMode = 0;
    final double HHold = 1.0; //
    final double HScore = 0.9; //
    final double HRelease = 0.735; //
    final double LHold = 1; //
    final double LScore = 0.78; //
    final double LRelease = 0.67; //
    final int Intake = 0;
    final int Raised = 145;
    final int Scoring = 2300;
    double distance;
    int wheel_dia = 4;// inches
    double ticksperrotation = 384.5;
    double rotations = 1.875;
    boolean dpad_up_was_pressed = false;
    boolean button_a_was_pressed = false;
    boolean left_stick_was_pressed = false;
    boolean right_stick_was_pressed = false;
    boolean dpad_left_was_pressed = false;
    boolean button_a_is_pressed;
    boolean dpad_up_is_pressed;
    boolean left_stick_pressed;
    boolean right_stick_pressed;
    boolean dpad_left_is_pressed;
    @Override
    public void runOpMode() {
        robot.init(hardwareMap);
    }

    public void Player_1_Drive(){
        forward_reverse = gamepad1.left_stick_y;
        rotate = gamepad1.right_stick_x;
        strafe = gamepad1.left_stick_x;
        robot.BackLeftDrive.setPower((+forward_reverse + rotate + strafe));
        robot.FrontLeftDrive.setPower((+forward_reverse + rotate - strafe));
        robot.FrontRightDrive.setPower((+forward_reverse - rotate + strafe));
        robot.BackRightDrive.setPower((+forward_reverse - rotate - strafe));

    }
    public int ArmPosMode() {
        return ArmPosMode % 3;
    }

    public void HighHold() {
        robot.HighGoal.setPosition(HHold);
        robot.LowGoal.setPosition(LHold);
    }

    public void ScoreTop() {
        robot.HighGoal.setPosition(HScore);
        robot.LowGoal.setPosition(LHold);
    }

    public void HoldMid() {
        robot.HighGoal.setPosition(HRelease);
        robot.LowGoal.setPosition(LHold);
    }

    public void ScoreMid() {
        robot.HighGoal.setPosition(HRelease);
        robot.LowGoal.setPosition(LScore);
    }

    public void ScoreLow() {
        robot.HighGoal.setPosition(HRelease);
        robot.LowGoal.setPosition(LRelease);
    }

    public int ServoMode() {
        return ServoMode % 3;
    }

    public void SetServoPosition() {
        if (gamepad1.a) {
            if (ServoMode() == 0) {
                ScoreTop();
            } else if (ServoMode() == 1) {
                ScoreMid();
            } else {
                ScoreLow();
            }
        } else {
            HighHold();
            if (ServoMode() == 0) {
                robot.greenLED.setState(false);
                robot.redLED.setState(true);
            } else if (ServoMode() == 1) {

                robot.greenLED.setState(false);
                robot.redLED.setState(false);
            } else {
                robot.redLED.setState(false);
                robot.greenLED.setState(true);
            }
        }
    }

    public void ScoringModeTelemetry() {
        if (ServoMode() == 0) {
            telemetry.addData("ScoringMode:", "High");
        } else if (ServoMode() == 1) {
            telemetry.addData("ScoringMode:", "Mid");
        } else {
            telemetry.addData("ScoringMode:", "Low");
        }

    }

    public void ArmPosModeTelemetry() {
        if (ArmPosMode() == 0) {
            telemetry.addData("ArmMode:", "Intake");
        } else if (ArmPosMode() == 1) {
            telemetry.addData("ArmMode:", "Raised");
        } else {
            telemetry.addData("ArmMode:", "Score");
        }

    }
    public void Toggles(){
        dpad_left_is_pressed = gamepad1.dpad_left;
        button_a_is_pressed = gamepad1.a;
        dpad_up_is_pressed = gamepad1.dpad_up;
        left_stick_pressed = gamepad1.left_stick_button;
        right_stick_pressed = gamepad1.right_stick_button;
        if (left_stick_pressed && !left_stick_was_pressed) {
            ArmPosMode--;
            left_stick_was_pressed = true;
        } else if (!left_stick_pressed && left_stick_was_pressed) {
            left_stick_was_pressed = false;
        }
        if (right_stick_pressed && !right_stick_was_pressed) {
            ArmPosMode++;
            right_stick_was_pressed = true;
        } else if (!right_stick_pressed && right_stick_was_pressed) {
            right_stick_was_pressed = false;
        }
        if (button_a_is_pressed && !button_a_was_pressed) {

            button_a_was_pressed = true;
        } else if (!button_a_is_pressed && button_a_was_pressed) {
            button_a_was_pressed = false;
        }
        if (dpad_left_is_pressed && !dpad_left_was_pressed) {
            dpad_left_was_pressed = true;
        } else if (!dpad_left_is_pressed && dpad_left_was_pressed) {
            dpad_left_was_pressed = false;
        }
        if (dpad_up_is_pressed && !dpad_up_was_pressed) {
            ServoMode++;
            dpad_up_was_pressed = true;
        } else if (!dpad_up_is_pressed && dpad_up_was_pressed) {
            dpad_up_was_pressed = false;
        }
    }

}
