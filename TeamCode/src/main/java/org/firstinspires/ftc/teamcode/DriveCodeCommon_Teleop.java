package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;

import java.util.List;

@TeleOp(name = "DriveCodeCommon_Teleop", group = "Linear Opmode")

@Disabled
public class DriveCodeCommon_Teleop extends LinearOpMode {
    @Config
    public static class RobotConstants {
        public static int duckticks = 2200;
        public static double initDuckSpeed = 0.2;
        public static double duckrate = 0.035;
    }

    SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);
    double forward_reverse;
    double rotate;
    double strafe;
    int Override = 0;
    int intaketoggle = 0;
    int screwtoggle = 0;
    int ServoMode = 0;
    int barcode = 0;
    int team = 0;// 0 = red 1 = blue
    int side = 0;// 0 = left 1 = right
    int mode = 0;//0 = nothing
    int Duck_Spinner_direction = 0;
    int speed = 0;

    double Timestamp = drive.runtime.time();
    double Timestamp2 = 0;
    double timestamp3 = 0;
    double timestamp4 = 0;
    double timestamp5 = 0;
    double DuckMaxPower = 0.8;
    double MaxPower = 1;
    double MinPower = 0.1;
    final double HHold = 0.7; //
    final double HScore = 0.175; //
    final double HHub = 0.015; //
    final double LHold = 0.8; //
    final double LScore = 0.65; //
    final double LRelease = 0.4; //
    double wheel_Dia = 3.93701;// inches
    double ticksPerRotation = 384.5;
    double rotations = 4.325;
    double xPos = 0;
    double yPos = 0;
    boolean dpad_up_was_pressed = false;
    boolean button_a_was_pressed = false;
    boolean y_was_pressed = false;
    boolean button_x_was_pressed = false;
    boolean Intake_Reverse;
    boolean Teservo;
    boolean button_x_is_pressed;
    boolean button_b_is_pressed;
    boolean button_y_is_pressed;
    boolean button_y_was_pressed;
    boolean button_b_was_pressed = false;
    boolean button_a_is_pressed;
    boolean dpad_up_is_pressed;
    boolean dpad_right_is_pressed;
    boolean dpad_right_was_pressed = false;
    boolean y_is_pressed;
    boolean screw_reverse;
    boolean Spinner;
    boolean SpinnerReverse;
    boolean override;
    boolean shutdown;
    boolean dpad_down_is_pressed;
    boolean dpad_down_was_pressed;
    boolean Stopper;
    boolean cubeIntaking;
    boolean cubeInScrewOpening;
    boolean cubeWasInScrewOpening;
    boolean sped;
    boolean screwShutOff;
    double duckSpeed;
    private final Object runningNotifier = new Object();



    @Override
    public void runOpMode() {

        // Set your initial pose to x: 10, y: 10, facing 90 degrees
        drive.setPoseEstimate(new Pose2d(0, 0, Math.toRadians(0)));

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
        dpad_right_is_pressed = gamepad1.dpad_right; // Auto Warehouse
        Pose2d poseEstimate = drive.getPoseEstimate();
        Vector2d input = new Vector2d(
                -gamepad1.left_stick_y,
                -gamepad1.left_stick_x
        ).rotated(-poseEstimate.getHeading());
        drive.setWeightedDrivePower(
                new Pose2d(
                        input.getX(),
                        input.getY(),
                        -gamepad1.right_stick_x
                )
        );
    }

    public void Player_2_Drive() {
        screw_reverse = gamepad1.left_bumper;
        y_is_pressed = gamepad2.y; //Screw
        button_b_is_pressed = gamepad1.b; // Screw Shut off
        dpad_down_is_pressed = gamepad2.dpad_down; // Duck Spinner Direction / Team
        Stopper = gamepad1.dpad_left; //
        button_x_is_pressed = gamepad1.x; // Intake
        button_a_is_pressed = gamepad1.a; // score
        dpad_up_is_pressed = gamepad1.dpad_up; // scoring mode
        Intake_Reverse = gamepad1.right_bumper;
        dpad_right_is_pressed = gamepad1.dpad_right; //
        override = gamepad2.back && gamepad2.start;
        shutdown = gamepad2.a && gamepad2.b && gamepad2.y;
        Pose2d poseEstimate = drive.getPoseEstimate();
        Vector2d input = new Vector2d(
                -gamepad1.left_stick_y,
                -gamepad1.left_stick_x
        ).rotated(-poseEstimate.getHeading());
        drive.setWeightedDrivePower(
                new Pose2d(
                        input.getX(),
                        input.getY(),
                        -gamepad1.right_stick_x
                )
        );
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
        dpad_right_is_pressed = gamepad2.dpad_right; // Auto Warehouse
        button_a_is_pressed = gamepad2.a; // score
        dpad_up_is_pressed = gamepad2.dpad_up; // scoring mode
        Intake_Reverse = gamepad2.right_bumper;
        Pose2d poseEstimate = drive.getPoseEstimate();
        Vector2d input = new Vector2d(
                -gamepad1.left_stick_y,
                -gamepad1.left_stick_x
        ).rotated(-poseEstimate.getHeading());
        drive.setWeightedDrivePower(
                new Pose2d(
                        input.getX(),
                        input.getY(),
                        -gamepad1.right_stick_x
                )
        );
    }

    public int intakeToggle() {
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

    public int SpinnerDirection() {
        return (2 * (Duck_Spinner_direction % 2) - 1);
    }

    public void HighHold() {
        drive.HighGoal.setPosition(HHold);
        drive.LowGoal.setPosition(LHold);
    }

    public void ScoreHub() {
        drive.HighGoal.setPosition(HHub);
        drive.LowGoal.setPosition(LHold);
    }

    public void ScoreTop() {
        drive.HighGoal.setPosition(HScore);
        drive.LowGoal.setPosition(LHold);
    }

    public void HoldMid() {
        drive.HighGoal.setPosition(HScore);
        drive.LowGoal.setPosition(LHold);
    }

    public void ScoreMid() {
        drive.HighGoal.setPosition(HScore);
        drive.LowGoal.setPosition(LScore);
    }

    public void ScoreLow() {
        drive.HighGoal.setPosition(HScore);
        drive.LowGoal.setPosition(LRelease);
    }

    public int ServoMode() {
        return ServoMode % 4;
    }

    public void SetServoPosition() {
        if (gamepad1.a) {
            if (ServoMode() == 0) {
                ScoreHub();
            } else if (ServoMode() == 1) {
                ScoreTop();
            } else if (ServoMode() == 2) {
                ScoreMid();
            } else if (ServoMode() == 3) {
                ScoreLow();
            }
        } else if (ServoMode() == 0 || ServoMode() == 1) {
            HighHold();
        } else {
            HoldMid();
        }
        drive.Stopper_Servo.setPosition(0.5);
    }

    public void capping() {
        if (gamepad2.left_stick_x < -0.1 && ((xPos - drive.cappingServoX.getPosition()) <= 0.02)) {
            drive.cappingServoX.setPosition(xPos + 0.025);
            xPos = drive.cappingServoX.getPosition();
        } else if (gamepad2.left_stick_x > 0.1 && (drive.cappingServoX.getPosition() - xPos) <= 0.02) {
            drive.cappingServoX.setPosition(xPos - 0.025);
            xPos = drive.cappingServoX.getPosition();
        }
        if (gamepad2.left_stick_y < -0.1 && ((yPos - drive.cappingServoY.getPosition()) <= 0.02)) {
            drive.cappingServoY.setPosition(yPos - 0.03);
            yPos = drive.cappingServoY.getPosition();
        } else if (gamepad2.left_stick_y > 0.1 && (drive.cappingServoY.getPosition() - yPos) <= 0.02) {
            drive.cappingServoY.setPosition(yPos + 0.03);
            yPos = drive.cappingServoY.getPosition();
        }
        drive.cappingMotor.setPower(gamepad2.right_trigger - gamepad2.left_trigger);
    }

    public void sensors() {
        if (!drive.intakeDetector.isPressed() && !cubeIntaking && TimeSinceStamp3() >= 3 && intakeToggle() == 1) {
            Timestamp2 = drive.runtime.time();
            intaketoggle = 0;
            cubeIntaking = true;
            drive.redLED.setState(false);
            drive.greenLED.setState(true);
            drive.redLED1.setState(false);
            drive.greenLED1.setState(true);
        }
        if (TimeSinceStamp2() >= 0.55 && ScrewToggle() == 0 && cubeIntaking && (Math.abs(drive.Screw_Motor.getTargetPosition() - drive.Screw_Motor.getCurrentPosition()) <= 10)) {
            intaketoggle = 1;
            cubeIntaking = false;
            timestamp3 = drive.runtime.time();
            drive.redLED.setState(false);
            drive.greenLED.setState(true);
            drive.redLED1.setState(false);
            drive.greenLED1.setState(true);
        }
        if (drive.intakeSensor.getDistance(DistanceUnit.INCH) >= 2.9) {
            cubeInScrewOpening = false;
            drive.redLED.setState(false);
            drive.greenLED.setState(true);
            drive.redLED1.setState(false);
            drive.greenLED1.setState(true);
        } else if (drive.intakeSensor.getDistance(DistanceUnit.INCH) <= 2.9) {
            cubeInScrewOpening = true;
            screwtoggle = 0;
            timestamp4 = drive.runtime.time();
            cubeWasInScrewOpening = true;
            drive.redLED.setState(false);
            drive.greenLED.setState(true);
            drive.redLED1.setState(false);
            drive.greenLED1.setState(true);
        }
        if (timestamp4 >= 2 && !cubeInScrewOpening && cubeWasInScrewOpening) {
            intaketoggle = 0;
            drive.redLED.setState(true);
            drive.greenLED.setState(false);
            drive.redLED1.setState(true);
            drive.greenLED1.setState(false);
        } else {
            drive.redLED.setState(false);
            drive.greenLED.setState(true);
            drive.redLED1.setState(false);
            drive.greenLED1.setState(true);
        }
    }



    public void screw() {
        if (ScrewToggle() == 1) {
            cubeWasInScrewOpening = false;
            drive.Screw_Motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            drive.Screw_Motor.setPower(-0.7);
            intaketoggle = 0;
        } else if (screw_reverse) {
            drive.Screw_Motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            drive.Screw_Motor.setPower(0.2);
        } else if (ScrewToggle() == 0) {
            drive.Screw_Motor.setPower(0);
            if(!screwShutOff) {
                drive.Screw_Motor.setPower(-0.1);
                if (drive.ScrewDetector.isPressed()) {
                    drive.Screw_Motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                    drive.Screw_Motor.setTargetPosition(-145);
                    drive.Screw_Motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                }

            }
        }
    }

    public void duckSpinner(double power, double rate) {
        if (gamepad1.b && intakeToggle() == 0) {
            if (TimeSinceStamp5() >= 0.025 && Math.abs(duckSpeed) <= 1) {
                timestamp5 = drive.runtime.time();
                drive.Bottom_Intake_Motor.setPower(SpinnerDirection() * (duckSpeed += rate));
            }
        } else {
             duckSpeed = RobotConstants.initDuckSpeed;
        }
    }

    public void intake() {
        if (intakeToggle() == 1 && ScrewToggle() == 0) {
            drive.Top_Intake_Motor.setPower(1);
            drive.Bottom_Intake_Motor.setPower(1);
            screwtoggle = 0;
        } else if (cubeIntaking) {
            drive.Top_Intake_Motor.setPower(0.4);
            drive.Bottom_Intake_Motor.setPower(-0.3);
        } else if (Intake_Reverse) {
            drive.Top_Intake_Motor.setPower(-1);
            drive.Bottom_Intake_Motor.setPower(-1);
        } else if (!gamepad1.b) {
            drive.Top_Intake_Motor.setPower(0);
            drive.Bottom_Intake_Motor.setPower(0);
        }
    }

    public boolean spinDuck(int ticks, double power, double rate) {
        drive.Bottom_Intake_Motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        drive.Bottom_Intake_Motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        drive.Bottom_Intake_Motor.setTargetPosition(ticks);
        drive.Bottom_Intake_Motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        boolean completion = DuckRampcelleration(ticks, power, rate);
        drive.Bottom_Intake_Motor.setPower(0);
        return completion;
    }

    public void autoDuck(int ticks, double power, double rate) {
        if (SpinnerDirection() == 0) {
            boolean completion;
            Timestamp = drive.runtime.time();
            completion = spinDuck(ticks, power, rate);
            if (!completion) {
                drive.Bottom_Intake_Motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            }
            if (completion) {
                drive.Bottom_Intake_Motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            }
        } else if (SpinnerDirection() == 1) {
            boolean completion;
            Timestamp = drive.runtime.time();
            completion = spinDuck(-ticks, power, rate);
            if (!completion) {
                drive.Bottom_Intake_Motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                drive.Bottom_Intake_Motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            }
            if (completion) {
                drive.Bottom_Intake_Motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                drive.Bottom_Intake_Motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            }
        }
    }

    public void Telemetry() {
        if (ServoMode() == 0) {
            telemetry.addData("ScoringMode:", "Hub");
        } else if (ServoMode() == 1) {
            telemetry.addData("ScoringMode:", "High");
        } else if (ServoMode() == 2) {
            telemetry.addData("ScoringMode:", "Med");
        } else {
            telemetry.addData("ScoringMode:", "Low");
        }
        if (SpinnerDirection() == -1) {
            telemetry.addData("Team", "Red");
        } else {
            telemetry.addData("Team", "Blue");
        }
        if (cubeInScrewOpening) {
            telemetry.addData("Cube in screw opening", drive.intakeSensor.getDistance(DistanceUnit.INCH));
        }
        if (!cubeInScrewOpening) {
            telemetry.addData("Cube not in screw opening", drive.intakeSensor.getDistance(DistanceUnit.INCH));
        }
        telemetry.addData("Speed", speed);
        telemetry.addData("Screw Pos", drive.Screw_Motor.getCurrentPosition());
        telemetry.addData("Target - Current ", java.lang.Math.abs(java.lang.Math.abs(drive.Screw_Motor.getTargetPosition()) - java.lang.Math.abs(drive.Screw_Motor.getCurrentPosition())));
        telemetry.addData("DuckSpeed",  RobotConstants.initDuckSpeed);
        telemetry.update();
    }

    public void Toggles_2P() {
        if (button_b_is_pressed && !button_b_was_pressed) {
           screwShutOff = true;
            button_b_was_pressed = true;
        } else if (!button_b_is_pressed && button_b_was_pressed) {
            button_b_was_pressed = false;
            screwShutOff = false;
        }
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




    public boolean DuckRampcelleration(double distance, double power, double rate) {

        Timestamp = drive.runtime.time();
        telemetry.addData("time check", TimeSinceStamp());
        telemetry.update();
        while (drive.Bottom_Intake_Motor.isBusy() && opModeIsActive()) {
            capping();
            if (java.lang.Math.abs(drive.Bottom_Intake_Motor.getTargetPosition()) - java.lang.Math.abs(drive.Bottom_Intake_Motor.getCurrentPosition()) > 10 && TimeSinceStamp() >= 0.05 && power < DuckMaxPower) {
                Timestamp = drive.runtime.time();
                telemetry.addData("power", power);
                if (!gamepad1.b) {
                    drive.Bottom_Intake_Motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
                    return false;
                }
                drive.Bottom_Intake_Motor.setPower(power += rate); // power = power + 0.05;
            } else {
                telemetry.addData("bruh", "");
                if (!gamepad1.b) {
                    drive.Bottom_Intake_Motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
                    return false;
                }
            }
            telemetry.update();
            capping();
        }
        return true;
    }


    public double TimeSinceStamp() {
        return drive.runtime.time() - Timestamp;
    }

    public double TimeSinceStamp2() {
        return drive.runtime.time() - Timestamp2;
    }

    public double TimeSinceStamp3() {
        return drive.runtime.time() - timestamp3;
    }

    public double TimeSinceStamp4() {
        return drive.runtime.time() - timestamp4;
    }

    public double TimeSinceStamp5() {
        return drive.runtime.time() - timestamp5;
    }



}

