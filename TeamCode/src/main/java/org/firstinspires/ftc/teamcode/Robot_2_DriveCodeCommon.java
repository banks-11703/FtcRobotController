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

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;

import java.util.List;


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

@TeleOp(name = "Robot_2_DriveCodeCommon", group = "Linear Opmode")
@Disabled
public class Robot_2_DriveCodeCommon extends LinearOpMode {
    Robot_2_TikhHardware robot = new Robot_2_TikhHardware();
    double forward_reverse;
    double rotate;
    double strafe;
    int Override = 0;
    int intaketoggle = 0;
    int screwtoggle = 0;
    int screwspeedtoggle = 0;
    int ServoMode = 0;
    int barcode = 0;
    int team = 0;// 0 = red 1 = blue
    int side = 0;// 0 = left 1 = right
    int mode = 0;//0 = nothing
    int Duck_Spinner_direction = 0;
    final double HHold = 0.7; //
    final double HScore = 0.175; //
    final double LHold = 0.8; //
    final double LScore = 0.55; //
    final double LRelease = 0.35; //
    double wheel_Dia = 3.93701;// inches
    double ticksPerRotation = 384.5;
    double rotations = 4.325;
    boolean dpad_up_was_pressed = false;
    boolean button_a_was_pressed = false;
    boolean y_was_pressed = false;
    boolean button_x_was_pressed = false;
    boolean Intake_Reverse;
    boolean Teservo;
    boolean button_x_is_pressed;
    boolean button_b_is_pressed;
    boolean button_b_was_pressed = false;
    boolean button_a_is_pressed;
    boolean dpad_up_is_pressed;
    boolean dpad_right_is_pressed;
    boolean dpad_right_was_pressed;
    boolean y_is_pressed;
    boolean screw_reverse;
    boolean Spinner;
    boolean SpinnerReverse;
    boolean override;
    boolean shutdown;
    boolean dpad_down_is_pressed;
    boolean dpad_down_was_pressed;
    @Override
    public void runOpMode() {
        robot.init(hardwareMap);
    }

    public void Player_1_Drive() {
        forward_reverse = gamepad1.left_stick_y;
        rotate = gamepad1.right_stick_x;
        strafe = gamepad1.left_stick_x;
        screw_reverse = gamepad1.left_bumper;
        y_is_pressed = gamepad1.y; //Screw
        Spinner = gamepad1.b;
        dpad_down_is_pressed = gamepad1.dpad_down;
        Teservo = gamepad1.dpad_left; // teservo
        button_x_is_pressed = gamepad1.x; // intake
        button_a_is_pressed = gamepad1.a; // score
        dpad_up_is_pressed = gamepad1.dpad_up; // scoring mode
        Intake_Reverse = gamepad1.right_bumper;
        dpad_right_is_pressed = gamepad1.dpad_right; // N/A
        robot.BackLeftDrive.setPower((+forward_reverse + rotate + strafe) / 1.5);
        robot.FrontLeftDrive.setPower((+forward_reverse + rotate - strafe) / 1.5);
        robot.FrontRightDrive.setPower((+forward_reverse - rotate + strafe) / 1.5);
        robot.BackRightDrive.setPower((+forward_reverse - rotate - strafe) / 1.5);
    }

    public void Player_2_Drive() {
        forward_reverse = gamepad1.left_stick_y;
        rotate = gamepad1.right_stick_x;
        strafe = gamepad1.left_stick_x;
        screw_reverse = gamepad2.left_bumper;
        y_is_pressed = gamepad2.y; //Screw
        Spinner = gamepad2.b;
        dpad_down_is_pressed = gamepad2.dpad_down; // Duck Spinner Direction / Team
        Teservo = gamepad1.dpad_left; // teservo
        button_x_is_pressed = gamepad1.x; // Intake
        button_a_is_pressed = gamepad1.a; // score
        dpad_up_is_pressed = gamepad1.dpad_up; // scoring mode
        Intake_Reverse = gamepad1.right_bumper;
        dpad_right_is_pressed = gamepad2.dpad_right; // N/A
        override = gamepad2.back && gamepad2.start;
        shutdown = gamepad2.a && gamepad2.b && gamepad2.y;
        robot.BackLeftDrive.setPower((+forward_reverse + rotate + strafe) / 1.5);
        robot.FrontLeftDrive.setPower((+forward_reverse + rotate - strafe) / 1.5);
        robot.FrontRightDrive.setPower((+forward_reverse - rotate + strafe) / 1.5);
        robot.BackRightDrive.setPower((+forward_reverse - rotate - strafe) / 1.5);
        if (override) {
            Override++;
        }
    }

    public void Player_2_Override() {
        forward_reverse = gamepad2.left_stick_y;
        rotate = gamepad2.right_stick_x;
        strafe = gamepad2.left_stick_x;
        screw_reverse = gamepad2.left_bumper;
        button_x_is_pressed = gamepad2.x; //screw on
        Spinner = gamepad2.b;
        SpinnerReverse = gamepad2.x;
        y_is_pressed = gamepad2.y; // intake
        dpad_right_is_pressed = gamepad2.dpad_right; // N/A
        button_a_is_pressed = gamepad2.a; // score
        dpad_up_is_pressed = gamepad2.dpad_up; // scoring mode
        Intake_Reverse = gamepad2.right_bumper;
        robot.BackLeftDrive.setPower((+forward_reverse + rotate + strafe) / 1.5);
        robot.FrontLeftDrive.setPower((+forward_reverse + rotate - strafe) / 1.5);
        robot.FrontRightDrive.setPower((+forward_reverse - rotate + strafe) / 1.5);
        robot.BackRightDrive.setPower((+forward_reverse - rotate - strafe) / 1.5);
    }

    public int IntakeToggle() {
        return intaketoggle % 2;
    }

    public int Team() {
        return team % 2;
    }

    public int Side() {
        return side % 2;
    }

    public int Mode() {
        return mode % 3;
    }

    public int ScrewToggle() {
        return screwtoggle % 2;
    }
    public int ScrewSpeedToggle() {
        return screwspeedtoggle % 2;
    }
    public int SpinnerDirection() {
        return Duck_Spinner_direction % 2;
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
        robot.HighGoal.setPosition(HScore);
        robot.LowGoal.setPosition(LHold);
    }

    public void ScoreMid() {
        robot.HighGoal.setPosition(HScore);
        robot.LowGoal.setPosition(LScore);
    }

    public void ScoreLow() {
        robot.HighGoal.setPosition(HScore);
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
        } else if (ServoMode() == 0) {
            HighHold();
        } else {
            HoldMid();
        }
    }

    public void Telemetry() {
        if (ServoMode() == 0) {
            telemetry.addData("ScoringMode:", "High");
        } else if (ServoMode() == 1) {
            telemetry.addData("ScoringMode:", "Mid");
        } else {
            telemetry.addData("ScoringMode:", "Low");
        }
        if (SpinnerDirection() == 0){
            telemetry.addData("Team","Blue");
        } else {
            telemetry.addData("Team","Red");
        }
        telemetry.addData("Target - Current ",java.lang.Math.abs(java.lang.Math.abs(robot.Screw_Motor.getTargetPosition()) - java.lang.Math.abs(robot.Screw_Motor.getCurrentPosition())));
        if (robot.ScrewDetector.isPressed()){
            telemetry.addData("magnet",1) ;
            telemetry.update();
        }
        if (!robot.ScrewDetector.isPressed()){
            telemetry.addData("magnet",0) ;
        }
        telemetry.update();
    }

    public void Toggles_2P() {

        if (button_a_is_pressed && !button_a_was_pressed) {

            button_a_was_pressed = true;
        } else if (!button_a_is_pressed && button_a_was_pressed) {
            button_a_was_pressed = false;
        }
        if (button_x_is_pressed && !button_x_was_pressed) {
            intaketoggle++;
            button_x_was_pressed = true;
        } else if (!button_x_is_pressed && button_x_was_pressed) {
            button_x_was_pressed = false;
        }
        if (y_is_pressed && !y_was_pressed) {
            y_was_pressed = true;
            screwtoggle++;
        } else if (!y_is_pressed && y_was_pressed) {
            y_was_pressed = false;
        }
        if (dpad_right_is_pressed && !dpad_right_was_pressed) {
            dpad_right_was_pressed = true;
        } else if (!dpad_right_is_pressed && dpad_right_was_pressed) {
            dpad_right_was_pressed = false;
        }
        if (dpad_up_is_pressed && !dpad_up_was_pressed) {
            ServoMode++;
            dpad_up_was_pressed = true;
        } else if (!dpad_up_is_pressed && dpad_up_was_pressed) {
            dpad_up_was_pressed = false;
        }
        if (dpad_down_is_pressed && !dpad_down_was_pressed) {
            Duck_Spinner_direction++;
            dpad_down_was_pressed = true;
        } else if (!dpad_down_is_pressed && dpad_down_was_pressed) {
            dpad_down_was_pressed = false;
        }
    }

    public void Toggles1P() {

        if (button_a_is_pressed && !button_a_was_pressed) {

            button_a_was_pressed = true;
        } else if (!button_a_is_pressed && button_a_was_pressed) {
            button_a_was_pressed = false;
        }
        if (button_x_is_pressed && !button_x_was_pressed) {
            intaketoggle++;
            button_x_was_pressed = true;
        } else if (!button_x_is_pressed && button_x_was_pressed) {
            button_x_was_pressed = false;
        }
        if (y_is_pressed && !y_was_pressed) {
            y_was_pressed = true;
            screwtoggle++;
        } else if (!y_is_pressed && y_was_pressed) {
            y_was_pressed = false;
        }
        if (dpad_right_is_pressed && !dpad_right_was_pressed) {
            dpad_right_was_pressed = true;
        } else if (!dpad_right_is_pressed && dpad_right_was_pressed) {
            dpad_right_was_pressed = false;
        }
        if (dpad_up_is_pressed && !dpad_up_was_pressed) {
            ServoMode++;
            dpad_up_was_pressed = true;
        } else if (!dpad_up_is_pressed && dpad_up_was_pressed) {
            dpad_up_was_pressed = false;
        }
        if (dpad_down_is_pressed && !dpad_down_was_pressed) {
            Duck_Spinner_direction++;
            dpad_down_was_pressed = true;
        } else if (!dpad_down_is_pressed && dpad_down_was_pressed) {
            dpad_down_was_pressed = false;
        }
    }
/*
    public void spin(double rotations, double power) {
        robot.Screw_Motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.Screw_Motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.Screw_Motor.setTargetPosition(rotationstoticks(rotations));
        robot.Screw_Motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.Screw_Motor.setPower(power);
        //noinspection StatementWithEmptyBody
        while (robot.Screw_Motor.isBusy()) {
        }
        robot.Screw_Motor.setPower(0);
    }

*/
   public void verticalDrive(double inches, double power) {
        robot.FrontLeftDrive.setTargetPosition(distancetoticks(inches));
        robot.FrontRightDrive.setTargetPosition(distancetoticks(inches));
        robot.BackLeftDrive.setTargetPosition(distancetoticks(inches));
        robot.BackRightDrive.setTargetPosition(distancetoticks(inches));
        robot.FrontLeftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.FrontRightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.BackRightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.BackLeftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
       MotorPower(power);
       Driving(power);
       MotorPower(0);
        robot.FrontRightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.FrontLeftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.BackRightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.BackLeftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.FrontLeftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.FrontRightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.BackRightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.BackLeftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void horizontalDrive(double inches, double power) {
        robot.FrontLeftDrive.setTargetPosition(+distancetoticks(inches));
        robot.FrontRightDrive.setTargetPosition(-distancetoticks(inches));
        robot.BackLeftDrive.setTargetPosition(-distancetoticks(inches));
        robot.BackRightDrive.setTargetPosition(+distancetoticks(inches));

        robot.FrontLeftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.FrontRightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.BackRightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.BackLeftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        MotorPower(power);
        Driving(power);
        MotorPower(0);
        robot.FrontRightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.FrontLeftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.BackRightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.BackLeftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.FrontLeftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.FrontRightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.BackRightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.BackLeftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void turn(int degrees, double power) {
        robot.FrontLeftDrive.setTargetPosition(-degreestoticks(degrees));
        robot.FrontRightDrive.setTargetPosition(degreestoticks(degrees));
        robot.BackLeftDrive.setTargetPosition(-degreestoticks(degrees));
        robot.BackRightDrive.setTargetPosition(degreestoticks(degrees));
        robot.FrontLeftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.FrontRightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.BackRightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.BackLeftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        MotorPower(power);
        Driving(power);
        MotorPower(0);
        robot.FrontRightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.FrontLeftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.BackRightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.BackLeftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.FrontLeftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.FrontRightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.BackRightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.BackLeftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }
/*
    public int rotationstoticks(double rotations) {
        double doubleticks = rotations * ((ticksPerRotation)); // 2x is for gear
        return (int) Math.round(doubleticks);
    }
*/
    public int distancetoticks(double distance_in) {
        double doubleticks = (distance_in * ((ticksPerRotation) / (wheel_Dia * 3.14))); // 2x is for gear
        return (int) Math.round(doubleticks);
    }

    public int degreestoticks(int degrees) {
        double Ddoubleticks = (degrees / 360.0 * (10000 / rotations));
        int ticksint = (int) Math.round(Ddoubleticks);
        telemetry.addData("here ticksint", ticksint);
        telemetry.update();
        return ticksint;
    }
    final int Ramp_Distance = 0;
    public void Driving(double power){
        while (robot.FrontLeftDrive.isBusy() && robot.FrontRightDrive.isBusy() && robot.BackLeftDrive.isBusy() && robot.BackRightDrive.isBusy() && opModeIsActive()){
            if (java.lang.Math.abs(robot.BackLeftDrive.getTargetPosition()) - java.lang.Math.abs(robot.BackLeftDrive.getCurrentPosition()) < Ramp_Distance){
                sleep(200);
                MotorPower(power - 0.05);
                sleep(200);
                MotorPower(power - 0.05);
                sleep(200);
                MotorPower(power - 0.05);
                sleep(200);
                MotorPower(power - 0.05);
            } else if (java.lang.Math.abs(robot.BackLeftDrive.getTargetPosition()) - java.lang.Math.abs(robot.BackLeftDrive.getCurrentPosition()) > Ramp_Distance){
                sleep(200);
                MotorPower(power + 0.05);
                sleep(200);
                MotorPower(power + 0.05);
                sleep(200);
                MotorPower(power + 0.05);
                sleep(200);
                MotorPower(power + 0.05);
            }
        }
    }
    public void MotorPower(double power){
        robot.FrontLeftDrive.setPower(power);
        robot.FrontRightDrive.setPower(power);
        robot.BackLeftDrive.setPower(power);
        robot.BackRightDrive.setPower(power);
    }
    public void ResetWheelEncoders() {
        robot.FrontRightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.FrontLeftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.BackRightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.BackLeftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.FrontLeftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.FrontRightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.BackRightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.BackLeftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public static final String VUFORIA_KEY =
            "AX+OlhD/////AAABmchQ+gluEkQLp3sQrhqiF3KHXxsnEsgLAJDu9DSV2wC7G6+9s0Uu9q6Z4aKcCBw6z78OwtprS93nTxJmhXG56BASKXvkqGnrvWtBboz4/IdpGMdfND1atvPm2D4TuE3PPw5nw2VSrHvUWu86aThoKYJIR0fAgqSIlzgcdZ9KLishl5n5KQLeBJpXCsW1tWvYV1Jkw3AAqxPoG5mR9ORbRTu/VXfvJKI6uQQBoAIziccUNtb7i2IoyjN/Dh4Juk9Y3r+GcXlTIBVygDyUgxyL2E+TL8IYzq2snIhTkZpCebeM5+ULPVZrI7xkAj2D/SwG0r23lsWLE105tDs3xjBOlhF/VfG7UOp+fXKt9xqIMnbu";
    public VuforiaLocalizer vuforia;
    public TFObjectDetector tfod;
    public static final String TFOD_MODEL_ASSET = "FreightFrenzy_DM.tflite";
    public static final String[] LABELS = {
            "Ball",
            "Cube",
            "Duck",
            "Marker"
    };

    public void initVuforia() {
        /*
         * Configure Vuforia by creating a Parameter object, and passing it to the Vuforia engine.
         */
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();

        parameters.vuforiaLicenseKey = VUFORIA_KEY;
        parameters.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;

        //  Instantiate the Vuforia engine
        vuforia = ClassFactory.getInstance().createVuforia(parameters);

        // Loading trackables is not necessary for the TensorFlow Object Detection engine.
    }

    public void initTfod() {
        int tfodMonitorViewId = hardwareMap.appContext.getResources().getIdentifier(
                "tfodMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        TFObjectDetector.Parameters tfodParameters = new TFObjectDetector.Parameters(tfodMonitorViewId);
        tfodParameters.minResultConfidence = 0.8f;
        tfodParameters.isModelTensorFlow2 = true;
        tfodParameters.inputSize = 320;
        tfod = ClassFactory.getInstance().createTFObjectDetector(tfodParameters, vuforia);
        tfod.loadModelFromAsset(TFOD_MODEL_ASSET, LABELS);
    }
    public void dropIntake () {
        robot.SpinnerMotor.setPower(0.4);
        sleep(1000);
        robot.SpinnerMotor.setPower(0);
    }
    public void spinDuckRed () {
        robot.SpinnerMotor.setPower(-0.8);
        verticalDrive(-2,0.05);
        sleep(3000);
        robot.SpinnerMotor.setPower(0);
    }
    public void spinDuckBlue () {
        robot.SpinnerMotor.setPower(0.8);
        sleep(3000);
        robot.SpinnerMotor.setPower(0);
    }
    public void barcodeReaderBlue() {
        initVuforia();
        initTfod();
        if (tfod != null) {
            tfod.activate();

            // The TensorFlow software will scale the input images from the camera to a lower resolution.
            // This can result in lower detection accuracy at longer distances (> 55cm or 22").
            // If your target is at distance greater than 50 cm (20") you can adjust the magnification value
            // to artificially zoom in to the center of image.  For best results, the "aspectRatio" argument
            // should be set to the value of the images used to create the TensorFlow Object Detection model
            // (typically 16/9).
            tfod.setZoom(1, 16.0 / 9.0);
        }
        for (int i = 0; i < 50000; i++) {
            if (tfod != null) {
                // getUpdatedRecognitions() will return null if no new information is available since
                // the last time that call was made.
                List<Recognition> updatedRecognitions = tfod.getUpdatedRecognitions();
                if (updatedRecognitions != null && updatedRecognitions.size() > 0) {
                    Recognition gameElement = updatedRecognitions.get(0);

                    if (gameElement.getLeft() < 1003 && gameElement.getRight() > 1003) {
                        barcode = 2;
                    } else if (gameElement.getLeft() < 445 && gameElement.getRight() > 445) {
                        barcode = 1;
                    }

                    telemetry.addData("# Object Detected ", barcode);
                    telemetry.addData("label", gameElement.getLabel());
                    telemetry.addData("  left,top ", "%.03f , %.03f",
                            gameElement.getLeft(), gameElement.getTop());
                    telemetry.addData("right,bottom", "%.03f , %.03f",
                            gameElement.getRight(), gameElement.getBottom());


                } else {
                    telemetry.addData("# Object Detected", barcode);
                }
                telemetry.update();
                telemetry.addData("count", i);
            }

        }
    }
    public void barcodeReaderRed() {
        initVuforia();
        initTfod();
        if (tfod != null) {
            tfod.activate();

            // The TensorFlow software will scale the input images from the camera to a lower resolution.
            // This can result in lower detection accuracy at longer distances (> 55cm or 22").
            // If your target is at distance greater than 50 cm (20") you can adjust the magnification value
            // to artificially zoom in to the center of image.  For best results, the "aspectRatio" argument
            // should be set to the value of the images used to create the TensorFlow Object Detection model
            // (typically 16/9).
            tfod.setZoom(1, 16.0 / 9.0);
        }
        for (int i = 0; i < 50000; i++) {
            if (tfod != null) {
                // getUpdatedRecognitions() will return null if no new information is available since
                // the last time that call was made.
                List<Recognition> updatedRecognitions = tfod.getUpdatedRecognitions();
                if (updatedRecognitions != null && updatedRecognitions.size() > 0) {
                    Recognition gameElement = updatedRecognitions.get(0);

                    if (gameElement.getLeft() < 1101 && gameElement.getRight() > 1101) {
                        barcode = 1;
                    } else if (gameElement.getLeft() < 475 && gameElement.getRight() > 475) {
                        barcode = 2;
                    }

                    telemetry.addData("# Object Detected ", barcode);
                    telemetry.addData("label", gameElement.getLabel());
                    telemetry.addData("  left,top ", "%.03f , %.03f",
                            gameElement.getLeft(), gameElement.getTop());
                    telemetry.addData("right,bottom", "%.03f , %.03f",
                            gameElement.getRight(), gameElement.getBottom());


                } else {
                    telemetry.addData("# Object Detected", barcode);
                }
                telemetry.update();
                telemetry.addData("count", i);
            }



        }
    }
    public void ScrewRotation(){ // When switch is hit and the screw is on slow reset to intake pos
        if (robot.ScrewDetector.isPressed() && ScrewSpeedToggle() == 1){ // switch is reversed
            screwtoggle = 0;
            robot.Screw_Motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            robot.Screw_Motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.Screw_Motor.setTargetPosition(-100);
            telemetry.update();
            robot.Screw_Motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            telemetry.update();
            screwtoggle = 1;
            telemetry.addData("Target - Current ",java.lang.Math.abs(java.lang.Math.abs(robot.Screw_Motor.getTargetPosition()) - java.lang.Math.abs(robot.Screw_Motor.getCurrentPosition())));
            telemetry.update();
            if(java.lang.Math.abs(robot.Screw_Motor.getTargetPosition() - robot.Screw_Motor.getCurrentPosition()) <= 10  ){
                screwtoggle = 0;
                robot.Screw_Motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                screwspeedtoggle = 0;
            }
        }
    }
}

