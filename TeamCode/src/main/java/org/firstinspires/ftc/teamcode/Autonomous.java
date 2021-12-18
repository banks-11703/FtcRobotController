package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DigitalChannel;

import org.firstinspires.ftc.teamcode.Old.DriveCodeCommon;

@com.qualcomm.robotcore.eventloop.opmode.Autonomous(name = "Autonomous", group = "Robot_2")
//@Disabled
public class Autonomous extends Robot_2_DriveCodeCommon {
    @Override
    public void runOpMode() {
        robot.init(hardwareMap);
        ResetWheelEncoders();
        while(!opModeIsActive()) {
            button_a_is_pressed = gamepad1.a;
            button_b_is_pressed = gamepad1.b;
            button_x_is_pressed = gamepad1.x;
            if (button_a_is_pressed && !button_a_was_pressed) {
                team++;
                button_a_was_pressed = true;
            } else if (!button_a_is_pressed && button_a_was_pressed) {
                button_a_was_pressed = false;
            }
            if (button_x_is_pressed && !button_x_was_pressed) {
                side++;
                button_x_was_pressed = true;
            } else if (!button_x_is_pressed && button_x_was_pressed) {
                button_x_was_pressed = false;
            }
            if (button_b_is_pressed && !button_b_was_pressed) {
                mode++;
                button_b_was_pressed = true;
            } else if (!button_b_is_pressed && button_b_was_pressed) {
                button_b_was_pressed = false;
            }
            if (Team() == 0 && Mode() == 0 && Side() == 0) {
                telemetry.addData("Team", "Red");
                telemetry.addData("Side", "Left");
                telemetry.addData("Mode", "Nothing");
                telemetry.update();

            }
            if (Team() == 0 && Mode() == 1 && Side() == 0) {
                telemetry.addData("Team", "Red");
                telemetry.addData("Side", "Left");
                telemetry.addData("Mode", "Still Nothing");
                telemetry.update();
            }
            if (Team() == 0 && Mode() == 0 && Side() == 1) {
                telemetry.addData("Team", "Red");
                telemetry.addData("Side", "Right");
                telemetry.addData("Mode", "Nothing");
                telemetry.update();
            }
            if (Team() == 0 && Mode() == 1 && Side() == 1) {
                telemetry.addData("Team", "Red");
                telemetry.addData("Side", "Right");
                telemetry.addData("Mode", "Score & Warehouse");
                telemetry.update();
            }

            if (Team() == 1 && Mode() == 0 && Side() == 0) {
                telemetry.addData("Team", "Blue");
                telemetry.addData("Side", "Left");
                telemetry.addData("Mode", "Nothing");
                telemetry.update();
            }

            if (Team() == 1 && Mode() == 0 && Side() == 1) {
                telemetry.addData("Team", "Blue");
                telemetry.addData("Side", "Right");
                telemetry.addData("Mode", "Nothing");
                telemetry.update();
            }
            if (Team() == 1 && Mode() == 1 && Side() == 1) {
                telemetry.addData("Team", "Blue");
                telemetry.addData("Side", "Right");
                telemetry.addData("Mode", "Still Nothing");
                telemetry.update();
                HighHold();

            }
            if (Team() == 1 && Mode() == 1 && Side() == 0) {
                telemetry.addData("Team", "Blue");
                telemetry.addData("Side", "Left");
                telemetry.addData("Mode", "Score & Warehouse");
                telemetry.update();
            } else{
                telemetry.addData("NOTHING","No");
            }
        }
            if (opModeIsActive()){ // ONLY MOVE AT 0.1!!!
                HighHold();
                if (Team() == 0 && Mode() == 0 && Side() == 0) {
                    telemetry.addData("Team", "Red");
                    telemetry.addData("Side", "Left");
                    telemetry.addData("Mode", "Nothing");
                    telemetry.update();

                }
                else if (Team() == 0 && Mode() == 1 && Side() == 0) {
                    telemetry.addData("Team", "Red");
                    telemetry.addData("Side", "Left");
                    telemetry.addData("Mode", "Warehouse");
                    telemetry.update();
                    verticalDrive(25,0.5);
                }
                else if (Team() == 0 && Mode() == 0 && Side() == 1) {
                    telemetry.addData("Team", "Red");
                    telemetry.addData("Side", "Right");
                    telemetry.addData("Mode", "Nothing");
                    telemetry.update();
                }
                else if (Team() == 0 && Mode() == 1 && Side() == 1) {
                    telemetry.addData("Team", "Red");
                    telemetry.addData("Side", "Right");
                    telemetry.addData("Mode", "Score & Warehouse");
                    telemetry.update();
                    barcodeReaderRed();
                    if (barcode == 0){ HoldMid();}
                    if (barcode == 1){ HoldMid();}
                    if (barcode == 2){ HighHold();}
                    robot.Screw_Motor.setPower(-1);
                    verticalDrive(18,0.15);
                    horizontalDrive(-22,0.1);
                    verticalDrive(5,0.1);
                    sleep(500);
                    if (barcode == 0){ ScoreLow();}
                    if (barcode == 1){ ScoreMid();
                    HighHold();
                    sleep(200);
                    ScoreMid();
                    }
                    if (barcode == 2){ ScoreTop();}
                    sleep(1000);
                    HighHold();
                    robot.Screw_Motor.setPower(0);
                    verticalDrive(-7,0.1);
                    horizontalDrive(20,0.1);
                    turn(90,0.1);
                    horizontalDrive(-20,0.2);
                    verticalDrive(-40,0.3);
                    robot.SpinnerMotor.setPower(0.4);
                    sleep(1000);
                }
                else if (Team() == 1 && Mode() == 0 && Side() == 1) {
                    telemetry.addData("Team", "Blue");
                    telemetry.addData("Side", "Right");
                    telemetry.addData("Mode", "Nothing");
                    telemetry.update();

                }
                else if (Team() == 1 && Mode() == 1 && Side() == 1) {
                    telemetry.addData("Team", "Blue");
                    telemetry.addData("Side", "Right");
                    telemetry.addData("Mode", "Warehouse");
                    telemetry.update();
                    verticalDrive(25,0.5);
                }
                else if (Team() == 1 && Mode() == 1 && Side() == 0) {
                    telemetry.addData("Team", "Blue");
                    telemetry.addData("Side", "Left");
                    telemetry.addData("Mode", "Score & Warehouse");
                    telemetry.update();
                    barcodeReaderBlue();
                    if (barcode == 0){ HoldMid();}
                    if (barcode == 1){ HoldMid();}
                    if (barcode == 2){ HighHold();}
                    robot.Screw_Motor.setPower(-1);
                    verticalDrive(20,0.1);
                    horizontalDrive(22,0.1);
                    verticalDrive(4,0.1);
                    if (barcode == 0){ ScoreLow();}
                    if (barcode == 1){ ScoreMid();
                        HighHold();
                        sleep(200);
                        ScoreMid();
                    }
                    if (barcode == 2){ ScoreTop();}
                    sleep(1000);
                    HighHold();
                    robot.Screw_Motor.setPower(0);
                    verticalDrive(-6,0.1);
                    horizontalDrive(-25,0.1);
                    turn(-90,0.1);
                    horizontalDrive(31,0.2);
                    verticalDrive(-40,0.3);
                    robot.SpinnerMotor.setPower(0.4);
                    sleep(1000);
                }
                else if (Team() == 1 && Mode() == 0 && Side() == 0) {
                    telemetry.addData("Team", "Blue");
                    telemetry.addData("Side", "Left");
                    telemetry.addData("Mode", "Nothing");
                    telemetry.update();
                }
                else if (Team() == 0 && Mode() == 2 && Side() == 0) {
                    telemetry.addData("Team", "Red");
                    telemetry.addData("Side", "Left");
                    telemetry.addData("Mode", "Nothing");
                    telemetry.update();
                }
                else{
                    telemetry.addData("NOTHING","No");
                }
                telemetry.update();
        }
    }

}