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

import android.widget.Spinner;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;


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

@TeleOp(name = "DriveCode_Player1", group = "Linear Opmode")
//@Disabled
public class DriveCode_Player1 extends LinearOpMode {

    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor FrontLeftDrive = null;
    private DcMotor BackLeftDrive = null;
    private DcMotor FrontRightDrive = null;
    private DcMotor BackRightDrive = null;
    private DcMotor PivotMotor;
    private Servo HighGoal;
    private Servo LowGoal;
    private Servo Grabber;
    private DcMotor SpinnerMotor;

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

    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        ScoringModeTelemetry();
        // Initialize the hardware variables. Note that the strings used here as parameters
        // to 'get' must correspond to the names assigned during the robot configuration
        // step (using the FTC Robot Controller app on the phone).
        FrontLeftDrive = hardwareMap.get(DcMotor.class, "fl");
        FrontRightDrive = hardwareMap.get(DcMotor.class, "fr");
        BackLeftDrive = hardwareMap.get(DcMotor.class, "bl");
        BackRightDrive = hardwareMap.get(DcMotor.class, "br");
        PivotMotor = hardwareMap.get(DcMotor.class, "pm");
        HighGoal = hardwareMap.get(Servo.class, "hg");
        LowGoal = hardwareMap.get(Servo.class, "lg");
        Grabber = hardwareMap.get(Servo.class, "grabber");
        SpinnerMotor = hardwareMap.get(DcMotor.class, "sp");
        // Most robots need the motor on one side to be reversed to drive forward
        // Reverse the motor that runs backwards when connected directly to the battery
        FrontLeftDrive.setDirection(DcMotor.Direction.FORWARD);
        FrontRightDrive.setDirection(DcMotor.Direction.REVERSE);
        BackLeftDrive.setDirection(DcMotor.Direction.FORWARD);
        BackRightDrive.setDirection(DcMotor.Direction.REVERSE);


        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        PivotMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        //PivotMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        runtime.reset();
        boolean dpad_up_was_pressed = false;
        boolean button_a_was_pressed = false;
        boolean left_stick_was_pressed = false;
        boolean right_stick_was_pressed = false;

        boolean button_a_is_pressed;
        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
            telemetry.update();
            ScoringModeTelemetry();
            ArmPosModeTelemetry();
            // Setup a variable for each drive wheel to save power level for telemetry
            double forward_reverse;
            double rotate;
            double strafe;
            boolean pivot_up;
            boolean pivot_down;
            boolean dpad_up_is_pressed;
            boolean left_stick_pressed;
            boolean right_stick_pressed;
            boolean button_y_pressed;
            boolean Grabber_toggle;
            boolean Spinner;
            forward_reverse = gamepad1.left_stick_y;
            rotate = gamepad1.right_stick_x;
            strafe = gamepad1.left_stick_x;
            button_a_is_pressed = gamepad1.a;
            pivot_up = gamepad1.left_bumper;
            pivot_down = gamepad1.right_bumper;
            dpad_up_is_pressed = gamepad1.dpad_up;
            left_stick_pressed = gamepad1.left_stick_button;
            right_stick_pressed = gamepad1.right_stick_button;
            button_y_pressed = gamepad1.y;
            Grabber_toggle = gamepad1.x;
            Spinner = gamepad1.b;
            BackLeftDrive.setPower((+forward_reverse + rotate + strafe));
            FrontLeftDrive.setPower((+forward_reverse + rotate - strafe));
            FrontRightDrive.setPower((+forward_reverse - rotate + strafe));
            BackRightDrive.setPower((+forward_reverse - rotate - strafe));

            SetServoPosition();
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
            if (dpad_up_is_pressed && !dpad_up_was_pressed) {
                ServoMode++;
                dpad_up_was_pressed = true;
            } else if (!dpad_up_is_pressed && dpad_up_was_pressed) {
                dpad_up_was_pressed = false;
            }
            if (Spinner){
               SpinnerMotor.setPower(0.3);
            }
            else{
                SpinnerMotor.setPower(0);
            }

            //todo (Friday) Get Motor Encoder working
            if (ArmPosMode() == 0) {
                PivotMotor.setTargetPosition(Intake);
            }
            if (ArmPosMode() == 1) {
                PivotMotor.setTargetPosition(Raised);
            }
            if (ArmPosMode() == 2) {
                PivotMotor.setTargetPosition(Scoring);
            }
            if (button_y_pressed) {
                PivotMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                PivotMotor.setPower(0.5);
            } else if (pivot_up) {
                PivotMotor.setPower(1);
            } else if (pivot_down) {
                PivotMotor.setPower(-1);
            } else {
                PivotMotor.setPower(0);
            }

            if (Grabber_toggle){
                Grabber.setPosition(0.8);
            }
            else{
                Grabber.setPosition(0);
            }



            // Show the elapsed game time and wheel power.
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            //telemetry.addData("Motors", "left (%.2f), right (%.2f)", leftPower, rightPower);
            telemetry.addData("Arm Pos", PivotMotor.getCurrentPosition());
            telemetry.addData("Arm Target Pos", PivotMotor.getTargetPosition());
            telemetry.addData("PosMode",ArmPosMode());
            telemetry.update();
        }

    }
    public int ArmPosMode() {
        return ArmPosMode % 3;
    }
    public void HighHold() {
        HighGoal.setPosition(HHold);
        LowGoal.setPosition(LHold);
    }
    public void ScoreTop() {
        HighGoal.setPosition(HScore);
        LowGoal.setPosition(LHold);
    }
    public void HoldMid() {
        HighGoal.setPosition(HRelease);
        LowGoal.setPosition(LHold);
    }
    public void ScoreMid() {
        HighGoal.setPosition(HRelease);
        LowGoal.setPosition(LScore);
    }
    public void ScoreLow() {
        HighGoal.setPosition(HRelease);
        LowGoal.setPosition(LRelease);
    }
    public int ServoMode(){
        return ServoMode % 3;
    }
    public void SetServoPosition() {
        if(gamepad1.a) {
            if(ServoMode() == 0){
                ScoreTop();
            }
            else if(ServoMode() == 1){
                ScoreMid();
            }
            else {
                ScoreLow();
            }
        }
        else {
            if(ServoMode() == 0){
                HighHold();
            }
            else {
                HoldMid();
            }
        }
    }
    public void ScoringModeTelemetry() {
        if (ServoMode() == 0) {
            telemetry.addData("ScoringMode:", "High");
        }
        else if (ServoMode() == 1){
            telemetry.addData("ScoringMode:", "Mid");
        }
        else{
            telemetry.addData("ScoringMode:", "Low");
        }

    }
    public void ArmPosModeTelemetry() {
        if (ArmPosMode() == 0) {
            telemetry.addData("ArmMode:", "Intake");
        }
        else if (ArmPosMode() == 1){
            telemetry.addData("ArmMode:", "Raised");
        }
        else{
            telemetry.addData("ArmMode:", "Score");
        }

    }

    /* public void toggleFlag(servo) {
         if (flag_raised) {
             servo.setPosition(Hold);
             if (servo.getPosition() == 1.0) {
                 flag_raised = false;
             }
         } else {
             servo.setPosition(Release);
             if (servo.getPosition() == 0.0) {
                 flag_raised = true;
             }*/


}
