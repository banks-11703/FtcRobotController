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

@TeleOp(name = "DriveCode", group = "Linear Opmode")
//@Disabled
public class DriveCode extends LinearOpMode {

    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor FrontLeftDrive = null;
    private DcMotor BackLeftDrive = null;
    private DcMotor FrontRightDrive = null;
    private DcMotor BackRightDrive = null;
    private DcMotor PivotMotor;
    private Servo HighGoal;
    private Servo LowGoal;
    private DcMotor TestMotor;
    Servo servo;
    int ServoMode = 0;


    final double HHold = 1.0; //
    final double HScore = 0.9; //
    final double HRelease = 0.735; //
    final double LHold = 1; //
    final double LScore = 0.75; //
    final double LRelease = 0.67; //

// todo ARM POSITION IS 1816 arm is weird figure out how to get encoder working.

    public void encoderTest(int x) {

    }
    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        // TODO: 10/3/2021 Get this working
        telemetry.addData("Mode:", ServoMode);
        // Initialize the hardware variables. Note that the strings used here as parameters
        // to 'get' must correspond to the names assigned during the robot configuration
        // step (using the FTC Robot Controller app on the phone).
        FrontLeftDrive = hardwareMap.get(DcMotor.class, "fl");
        FrontRightDrive = hardwareMap.get(DcMotor.class, "fr");
        BackLeftDrive = hardwareMap.get(DcMotor.class, "bl");
        BackRightDrive = hardwareMap.get(DcMotor.class, "br");
        PivotMotor = hardwareMap.get(DcMotor.class, "pm");
        TestMotor = hardwareMap.get(DcMotor.class, "tm");
        servo = hardwareMap.get(Servo.class, "servo");
        HighGoal = hardwareMap.get(Servo.class, "hg");
        LowGoal = hardwareMap.get(Servo.class, "lg");
        // Most robots need the motor on one side to be reversed to drive forward
        // Reverse the motor that runs backwards when connected directly to the battery
        FrontLeftDrive.setDirection(DcMotor.Direction.FORWARD);
        FrontRightDrive.setDirection(DcMotor.Direction.REVERSE);
        BackLeftDrive.setDirection(DcMotor.Direction.FORWARD);
        BackRightDrive.setDirection(DcMotor.Direction.REVERSE);

        // todo Get better POS for this
        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        runtime.reset();
        boolean dpad_up_was_pressed = false;
        boolean button_a_was_pressed = false;

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
            telemetry.update();
            telemetry.addData("Mode:", ServoMode % 4);
            // Setup a variable for each drive wheel to save power level for telemetry
            double leftPower;
            double rightPower;
            double forward_reverse;
            double rotate;
            double strafe;
            boolean button_a_is_pressed;
            boolean pivot_up;
            boolean pivot_down;
            boolean dpad_up_is_pressed;


            //static final double MAX_POS     =  1.0;     // Maximum rotational position
            //static final double MIN_POS     =  0.0;     // Minimum rotational position
            double position = 1.0;

            // Choose to drive using either Tank Mode, or POV Mode
            // Comment out the method that's not used.  The default below is POV.

            // POV Mode uses left stick to go forward, and right stick to turn.
            // - This uses basic math to combine motions and is easier to drive straight.
           /* double drive = -gamepad1.left_stick_y;
            double turn = gamepad1.right_stick_x;
            leftPower = Range.clip(drive + turn, -1.0, 1.0);
            rightPower = Range.clip(drive - turn, -1.0, 1.0);

            // Tank Mode uses one stick to control each wheel.
            // - This requires no math, but it is hard to drive forward slowly and keep straight.
            // leftPower  = -gamepad1.left_stick_y ;
            // rightPower = -gamepad1.right_stick_y ;
            // Send calculated power to wheels
            FrontLeftDrive.setPower(leftPower);
            FrontRightDrive.setPower(rightPower);
            BackLeftDrive.setPower(leftPower);
            BackRightDrive.setPower(rightPower);*/

            forward_reverse = gamepad1.left_stick_y;
            rotate = gamepad1.right_stick_x;
            strafe = gamepad1.left_stick_x;
            button_a_is_pressed = gamepad1.a;
            pivot_up = gamepad1.left_bumper;
            pivot_down = gamepad1.right_bumper;
            dpad_up_is_pressed = gamepad1.dpad_up;

            BackLeftDrive.setPower((+forward_reverse + rotate + strafe));
            FrontLeftDrive.setPower((+forward_reverse + rotate - strafe));
            FrontRightDrive.setPower((+forward_reverse - rotate + strafe));
            BackRightDrive.setPower((+forward_reverse - rotate - strafe));

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
            //todo (Friday) Get Motor Encoder working
            if (ServoMode % 4 == 0) {
                HighGoal.setPosition(HHold);
                LowGoal.setPosition(LHold);
            } else if (ServoMode % 4 == 1) {
                HighGoal.setPosition(HScore);
                LowGoal.setPosition(LHold);
            } else if (ServoMode % 4 == 2) {
                HighGoal.setPosition(HRelease);
                LowGoal.setPosition(LScore);

            } else if (ServoMode % 4 == 3) {
                HighGoal.setPosition(HRelease);
                LowGoal.setPosition(LRelease);
            }
            if (pivot_up) {
                PivotMotor.setPower(1);
            } else if (pivot_down) {
                PivotMotor.setPower(-1);
            } else {
                PivotMotor.setPower(0);
            }
            // Show the elapsed game time and wheel power.
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            //telemetry.addData("Motors", "left (%.2f), right (%.2f)", leftPower, rightPower);
            telemetry.addData("Arm Pos", PivotMotor.getCurrentPosition());
            telemetry.update();
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
