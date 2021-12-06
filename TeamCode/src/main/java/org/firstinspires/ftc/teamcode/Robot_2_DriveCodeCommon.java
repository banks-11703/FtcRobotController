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
    int ServoMode = 0;
    int barcode = 0;
    final double HHold = 1.0; //
    final double HScore = 0.9; //
    final double HRelease = 0.735; //
    final double LHold = 1; //
    final double LScore = 0.78; //
    final double LRelease = 0.67; //
    final int Intake = 0;
    final int Raised = 145;
    final int Scoring = 2300;
    int wheel_Dia = 4;// inches
    double ticksPerRotation = 384.5;
    double rotations = 0;
    boolean dpad_up_was_pressed = false;
    boolean button_a_was_pressed = false;
    boolean y_was_pressed = false;
    boolean button_x_was_pressed = false;
    boolean button_x_is_pressed;
    boolean button_a_is_pressed;
    boolean dpad_up_is_pressed;
    boolean y_is_pressed;
    boolean screw_reverse;
    boolean Spinner;
    boolean SpinnerReverse;
    boolean override;
    @Override
    public void runOpMode() {
        robot.init(hardwareMap);
    }

    public void Player_1_Drive() {
        forward_reverse = gamepad1.left_stick_y;
        rotate = gamepad1.right_stick_x;
        strafe = gamepad1.left_stick_x;
        screw_reverse = gamepad1.left_bumper;
        button_x_is_pressed = gamepad1.x; //screw on
        Spinner = gamepad1.b;
        SpinnerReverse =gamepad1.dpad_right;
        y_is_pressed = gamepad1.y; // intake
        button_a_is_pressed = gamepad1.a; // score
        dpad_up_is_pressed = gamepad1.dpad_up; // scoring mode
        robot.BackLeftDrive.setPower((+forward_reverse + rotate + strafe));
        robot.FrontLeftDrive.setPower((+forward_reverse + rotate - strafe));
        robot.FrontRightDrive.setPower((+forward_reverse - rotate + strafe));
        robot.BackRightDrive.setPower((+forward_reverse - rotate - strafe));
    }
    public void Player_2_Drive(){
        forward_reverse = gamepad1.left_stick_y;
        rotate = gamepad1.right_stick_x;
        strafe = gamepad1.left_stick_x;
        screw_reverse = gamepad2.left_bumper;
        button_x_is_pressed = gamepad2.x; //screw on
        Spinner = gamepad1.b;
        SpinnerReverse =gamepad1.x;
        y_is_pressed = gamepad2.y; // intake
        button_a_is_pressed = gamepad1.a; // score
        dpad_up_is_pressed = gamepad2.dpad_up; // scoring mode
        override = gamepad2.back && gamepad2.start;
        robot.BackLeftDrive.setPower((+forward_reverse + rotate + strafe));
        robot.FrontLeftDrive.setPower((+forward_reverse + rotate - strafe));
        robot.FrontRightDrive.setPower((+forward_reverse - rotate + strafe));
        robot.BackRightDrive.setPower((+forward_reverse - rotate - strafe));
        if(override){
            Override++;
        }
    }
    public void Player_2_Override(){
        forward_reverse = gamepad2.left_stick_y;
        rotate = gamepad2.right_stick_x;
        strafe = gamepad2.left_stick_x;
        screw_reverse = gamepad2.left_bumper;
        button_x_is_pressed = gamepad2.x; //screw on
        Spinner = gamepad2.b;
        SpinnerReverse =gamepad2.dpad_right;
        y_is_pressed = gamepad2.y; // intake
        button_a_is_pressed = gamepad2.a; // score
        dpad_up_is_pressed = gamepad2.dpad_up; // scoring mode
        robot.BackLeftDrive.setPower((+forward_reverse + rotate + strafe));
        robot.FrontLeftDrive.setPower((+forward_reverse + rotate - strafe));
        robot.FrontRightDrive.setPower((+forward_reverse - rotate + strafe));
        robot.BackRightDrive.setPower((+forward_reverse - rotate - strafe));
    }
    public int IntakeToggle(){
       return intaketoggle % 2;
    }
    public int ScrewToggle(){
        return screwtoggle % 2;
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

    public void Toggles_2P() {

        if (button_a_is_pressed && !button_a_was_pressed) {

            button_a_was_pressed = true;
        } else if (!button_a_is_pressed && button_a_was_pressed) {
            button_a_was_pressed = false;
        }
        if (button_x_is_pressed && !button_x_was_pressed) {
            screwtoggle++;
            button_x_was_pressed = true;
        } else if (!button_x_is_pressed && button_x_was_pressed) {
            button_x_was_pressed = false;
        }
        if (y_is_pressed && !y_was_pressed) {
            y_was_pressed = true;
            intaketoggle++;
        } else if (!y_is_pressed && y_was_pressed) {
            y_was_pressed = false;
        }
        if (dpad_up_is_pressed && !dpad_up_was_pressed) {
            ServoMode++;
            dpad_up_was_pressed = true;
        } else if (!dpad_up_is_pressed && dpad_up_was_pressed) {
            dpad_up_was_pressed = false;
        }
    }

    public void Toggles1P() {
        if (button_a_is_pressed && !button_a_was_pressed) {

            button_a_was_pressed = true;
        } else if (!button_a_is_pressed && button_a_was_pressed) {
            button_a_was_pressed = false;
        }
        if (button_x_is_pressed && !button_x_was_pressed) {
            screwtoggle++;
            button_x_was_pressed = true;
        } else if (!button_x_is_pressed && button_x_was_pressed) {
            button_x_was_pressed = false;
        }
        if (y_is_pressed && !y_was_pressed) {
            y_was_pressed = true;
            intaketoggle++;
        } else if (!y_is_pressed && y_was_pressed) {
            y_was_pressed = false;
        }
        if (dpad_up_is_pressed && !dpad_up_was_pressed) {
            ServoMode++;
            dpad_up_was_pressed = true;
        } else if (!dpad_up_is_pressed && dpad_up_was_pressed) {
            dpad_up_was_pressed = false;
        }
    }
    public void verticalDrive(int inches, double power) {
        robot.FrontLeftDrive.setTargetPosition(distancetoticks(inches));
        robot.FrontRightDrive.setTargetPosition(distancetoticks(inches));
        robot.BackLeftDrive.setTargetPosition(distancetoticks(inches));
        robot.BackRightDrive.setTargetPosition(distancetoticks(inches));
        robot.FrontLeftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.FrontRightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.BackRightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.BackLeftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.FrontLeftDrive.setPower(power);
        robot.FrontRightDrive.setPower(power);
        robot.BackLeftDrive.setPower(power);
        robot.BackRightDrive.setPower(power);
        //noinspection StatementWithEmptyBody
        while (robot.FrontLeftDrive.isBusy() && robot.FrontRightDrive.isBusy()
               && robot.BackLeftDrive.isBusy() && robot.BackRightDrive.isBusy()
               && opModeIsActive());
        robot.FrontLeftDrive.setPower(0);
        robot.FrontRightDrive.setPower(0);
        robot.BackLeftDrive.setPower(0);
        robot.BackRightDrive.setPower(0);
        robot.FrontRightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.FrontLeftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.BackRightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.BackLeftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.FrontLeftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.FrontRightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.BackRightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.BackLeftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }
    public void horizontalDrive(int inches, double power) {
        robot.FrontLeftDrive.setTargetPosition(+distancetoticks(inches));
        robot.FrontRightDrive.setTargetPosition(-distancetoticks(inches));
        robot.BackLeftDrive.setTargetPosition(-distancetoticks(inches));
        robot.BackRightDrive.setTargetPosition(+distancetoticks(inches));

        robot.FrontLeftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.FrontRightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.BackRightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.BackLeftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.FrontLeftDrive.setPower(power);
        robot.FrontRightDrive.setPower(power);
        robot.BackLeftDrive.setPower(power);
        robot.BackRightDrive.setPower(power);
        //noinspection StatementWithEmptyBody
        while (robot.FrontLeftDrive.isBusy() && robot.FrontRightDrive.isBusy() && robot.BackLeftDrive.isBusy() && robot.BackRightDrive.isBusy() && opModeIsActive()) ;
        robot.FrontLeftDrive.setPower(0);
        robot.FrontRightDrive.setPower(0);
        robot.BackLeftDrive.setPower(0);
        robot.BackRightDrive.setPower(0);
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
        robot.FrontLeftDrive.setPower(power);
        robot.FrontRightDrive.setPower(power);
        robot.BackLeftDrive.setPower(power);
        robot.BackRightDrive.setPower(power);
        //noinspection StatementWithEmptyBody
        while (robot.FrontLeftDrive.isBusy() && robot.FrontRightDrive.isBusy() && robot.BackLeftDrive.isBusy() && robot.BackRightDrive.isBusy() && opModeIsActive()) ;
        robot.FrontLeftDrive.setPower(0);
        robot.FrontRightDrive.setPower(0);
        robot.BackLeftDrive.setPower(0);
        robot.BackRightDrive.setPower(0);
        robot.FrontRightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.FrontLeftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.BackRightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.BackLeftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.FrontLeftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.FrontRightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.BackRightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.BackLeftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public int distancetoticks(int distance_in) {
        double doubleticks = distance_in * ((ticksPerRotation * 2) / (wheel_Dia* 3.14)); // 2x is for gear
        int ticksint = (int) Math.round(doubleticks);
        return ticksint;
    }

    public int degreestoticks(int degrees) {
        double Ddoubleticks = (degrees / 360.0 * (10000 / rotations));
        int ticksint = (int) Math.round(Ddoubleticks);
        telemetry.addData("here ticksint", ticksint);
        telemetry.update();
        return ticksint;
    }
    // Intake Raised Scoring

    public void ResetWheelEncoders(){
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

    public void barcodeReader() {
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
        for (int i = 0; i <50000; i++) {
            if (tfod != null) {
                // getUpdatedRecognitions() will return null if no new information is available since
                // the last time that call was made.
                List<Recognition> updatedRecognitions = tfod.getUpdatedRecognitions();
                if (updatedRecognitions != null && updatedRecognitions.size() > 0) {
                    Recognition gameElement = updatedRecognitions.get(0);

                    if (gameElement.getLeft() < 984 && gameElement.getRight() > 984) {
                        barcode = 2;
                    } else if (gameElement.getLeft() < 351 && gameElement.getRight() > 351) {
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
}

