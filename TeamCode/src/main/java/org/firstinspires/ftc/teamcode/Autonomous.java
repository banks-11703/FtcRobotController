package org.firstinspires.ftc.teamcode;

@com.qualcomm.robotcore.eventloop.opmode.Autonomous(name = "Autonomous", group = "Robot_2")
//@Disabled
public class Autonomous extends Robot_2_DriveCodeCommon {
    @Override
    public void runOpMode() {
        robot.init(hardwareMap);
        ResetWheelEncoders();
        choosePosition();
        telemetry.addData("Set", "Position");
        telemetry.update();
        initVuforia();
        telemetry.addData("does it work","");
        telemetry.update();
        initTfod();
        telemetry.addData("does it work2","");
        telemetry.update();
        if (tfod != null) {
            tfod.activate();
            tfod.setZoom(1, 16.0 / 9.0);
        }
        telemetry.addData("Unknown", "");
        telemetry.update();
        telemetry.update();

        DuckPosition duckPosition = DuckPosition.UNKNOWN;
        while (!isStarted()) {
            duckPosition = getDuckPosition();
            telemetry.addData("spot", duckPosition);
            telemetry.update();
        }
        telemetry.addData("Done!", duckPosition);
        telemetry.update();

        if (opModeIsActive()) { // ONLY MOVE AT 0.1,0.1!!!
            telemetry.addData("Entered OpMode","");
            telemetry.update();
            HighHold();
            switch (duckPosition) {
                case LEFT:
                    telemetry.addData("barcode", "left");
                    break;
                case MIDDLE:
                    telemetry.addData("barcode", "right");
                    break;
                case RIGHT:
                    telemetry.addData("barcode", "middle");
                    break;
                default:
                    duckPosition = DuckPosition.LEFT;
                    telemetry.addData("it's broken", "");
                    break;
            }
            if (Team() == 0 && Mode() == 0 && Side() == 0) {
                telemetry.addData("Team", "Red");
                telemetry.addData("Side", "Left");
                telemetry.addData("Mode", "Nothing");
                telemetry.update();
            }//Red, Left, Nothing                           ✔
            else if (Team() == 0 && Mode() == 1 && Side() == 0) {
                telemetry.addData("Team", "Red");
                telemetry.addData("Side", "Left");
                telemetry.addData("Mode", "Duck, Score, and Warehouse");
                telemetry.update();

                switch (duckPosition) {
                    case LEFT:
                        verticalDrive(11.5,0.1,0.1);
                        dropIntake();
                        horizontalDrive(-36.5,0.3,0.1);
                        verticalDrive(-5,0.1,0.1);
                        spinDuckBlue();
                        HoldMid();
                        sleep(500);
                        robot.Screw_Motor.setPower(-1);
                        verticalDrive(20,0.1,0.1);
                        horizontalDrive(59,0.1,0.1);
                        verticalDrive(2,0.1,0.1);
                        ScoreLow();
                        sleep(1000);
                        HighHold();
                        robot.Screw_Motor.setPower(0);
                        verticalDrive(-6,0.1,0.1);
                        horizontalDrive(-25,0.1,0.1);
                        turn(-90,0.1,0.1);
                        horizontalDrive(27,0.2,0.1);
                        verticalDrive(-40,0.3,0.1);
                        dropIntake();
                        break;
                    case MIDDLE:
                        verticalDrive(11.5,0.1,0.1);
                        dropIntake();
                        horizontalDrive(-36.5,0.3,0.1);
                        verticalDrive(-5,0.1,0.1);
                        spinDuckBlue();
                        HoldMid();
                        sleep(500);
                        robot.Screw_Motor.setPower(-1);
                        verticalDrive(20,0.1,0.1);
                        horizontalDrive(59,0.1,0.1);
                        verticalDrive(3,0.1,0.1);
                        ScoreMid();
                        HighHold();
                        sleep(200);
                        ScoreMid();
                        sleep(1000);
                        HighHold();
                        robot.Screw_Motor.setPower(0);
                        verticalDrive(-6,0.1,0.1);
                        horizontalDrive(-25,0.1,0.1);
                        turn(-90,0.1,0.1);
                        horizontalDrive(27,0.2,0.1);
                        verticalDrive(-40,0.3,0.1);
                        dropIntake();
                        break;
                    case RIGHT:
                        verticalDrive(11.5,0.1,0.1);
                        dropIntake();
                        horizontalDrive(-36.5,0.3,0.1);
                        verticalDrive(-5,0.1,0.1);
                        spinDuckBlue();
                        HighHold();
                        sleep(500);
                        robot.Screw_Motor.setPower(-1);
                        verticalDrive(20,0.1,0.1);
                        horizontalDrive(59,0.1,0.1);
                        verticalDrive(2,0.1,0.1);
                        ScoreTop();
                        sleep(1000);
                        HighHold();
                        robot.Screw_Motor.setPower(0);
                        verticalDrive(-6,0.1,0.1);
                        horizontalDrive(-25,0.1,0.1);
                        turn(-90,0.1,0.1);
                        horizontalDrive(27,0.2,0.1);
                        verticalDrive(-40,0.3,0.1);
                        dropIntake();
                        break;
                    default:
                        //Todo USE IT OR DELETE
                        duckPosition = DuckPosition.LEFT;
                        telemetry.addData("it's broken", "");
                        verticalDrive(11.5,0.1,0.1);
                        dropIntake();
                        horizontalDrive(-36.5,0.3,0.1);
                        verticalDrive(-5,0.1,0.1);
                        spinDuckBlue();
                        HighHold();
                        robot.Screw_Motor.setPower(-1);
                        verticalDrive(20,0.1,0.1);
                        horizontalDrive(59,0.1,0.1);
                        verticalDrive(2,0.1,0.1);
                        ScoreTop();
                        sleep(1000);
                        HighHold();
                        robot.Screw_Motor.setPower(0);
                        verticalDrive(-6,0.1,0.1);
                        horizontalDrive(-25,0.1,0.1);
                        turn(-90,0.1,0.1);
                        horizontalDrive(27,0.2,0.1);
                        verticalDrive(-40,0.3,0.1);
                        dropIntake();
                        break;
                }

                telemetry.update();
            }//Red, Left, Duck, Score, and Warehouse
            else if (Team() == 0 && Mode() == 2 && Side() == 1) {
                telemetry.addData("Team", "Red");
                telemetry.addData("Side", "Right");
                telemetry.addData("Mode", "Warehouse");
                telemetry.update();
                verticalDrive(45,0.5,0.1);
            }//Red, Right, Warehouse                   ✔
            else if (Team() == 0 && Mode() == 0 && Side() == 1) {
                telemetry.addData("Team", "Red");
                telemetry.addData("Side", "Right");
                telemetry.addData("Mode", "Nothing");
                telemetry.update();
            }//Red, Right, Nothing                     ✔
            else if (Team() == 0 && Mode() == 1 && Side() == 1) {
                telemetry.addData("Team", "Red");
                telemetry.addData("Side", "Right");
                telemetry.addData("Mode", "Score & Warehouse");
                telemetry.update();
                switch (duckPosition) {
                    case LEFT:
                        verticalDrive(-2,0.3,0.1);
                        HighHold();
                        sleep(500);
                        robot.Screw_Motor.setPower(-0.8);
                        verticalDrive(18,0.1,0.15);
                        horizontalDrive(-22,0.1,0.1);
                        verticalDrive(4,0.1,0.1);
                        sleep(500);
                        ScoreTop();
                        sleep(1000);
                        HighHold();
                        robot.Screw_Motor.setPower(0);
                        verticalDrive(-8,0.1,0.1);
                        horizontalDrive(20,0.1,0.1);
                        turn(90,0.1,0.1);
                        horizontalDrive(-20,0.2,0.1);
                        verticalDrive(-40,0.3,0.1);
                        dropIntake();
                        break;
                    case MIDDLE:
                        verticalDrive(-2,0.3,0.1);
                        HoldMid();
                        sleep(500);
                        robot.Screw_Motor.setPower(-0.8);
                        verticalDrive(18,0.1,0.15);
                        horizontalDrive(-22,0.1,0.1);
                        verticalDrive(4,0.1,0.1);
                        sleep(500);
                        ScoreMid();
                        HighHold();
                        sleep(200);
                        ScoreMid();
                        sleep(1000);
                        HighHold();
                        robot.Screw_Motor.setPower(0);
                        verticalDrive(-8,0.1,0.1);
                        horizontalDrive(20,0.1,0.1);
                        turn(90,0.1,0.1);
                        horizontalDrive(-20,0.2,0.1);
                        verticalDrive(-40,0.3,0.1);
                        dropIntake();
                        break;
                    case RIGHT:
                        verticalDrive(-2,0.3,0.1);
                        HoldMid();
                        sleep(500);
                        robot.Screw_Motor.setPower(-0.8);
                        verticalDrive(18,0.1,0.15);
                        horizontalDrive(-22,0.1,0.1);
                        verticalDrive(4,0.1,0.1);
                        sleep(500);
                        ScoreLow();
                        sleep(1000);
                        HighHold();
                        robot.Screw_Motor.setPower(0);
                        verticalDrive(-8,0.1,0.1);
                        horizontalDrive(20,0.1,0.1);
                        turn(90,0.1,0.1);
                        horizontalDrive(-20,0.2,0.1);
                        verticalDrive(-40,0.3,0.1);
                        dropIntake();
                        break;
                    default:
                        telemetry.addData("it's broken", "");
                        verticalDrive(-2,0.3,0.1);
                        HighHold();
                        robot.Screw_Motor.setPower(-0.8);
                        verticalDrive(18,0.1,0.15);
                        horizontalDrive(-22,0.1,0.1);
                        verticalDrive(4,0.1,0.1);
                        sleep(500);
                        ScoreTop();
                        sleep(1000);
                        HighHold();
                        robot.Screw_Motor.setPower(0);
                        verticalDrive(-8,0.1,0.1);
                        horizontalDrive(20,0.1,0.1);
                        turn(90,0.1,0.1);
                        horizontalDrive(-20,0.2,0.1);
                        verticalDrive(-40,0.3,0.1);
                        dropIntake();
                        break;
                }
            }//Red, Right, Score & Warehouse

            else if (Team() == 1 && Mode() == 0 && Side() == 0) {
                telemetry.addData("Team", "Blue");
                telemetry.addData("Side", "Left");
                telemetry.addData("Mode", "Nothing");
                telemetry.update();
            }//Blue, Left, Nothing                     ✔
            else if (Team() == 1 && Mode() == 2 && Side() == 0) {
                telemetry.addData("Team", "Blue");
                telemetry.addData("Side", "Left");
                telemetry.addData("Mode", "Warehouse");
                telemetry.update();
                verticalDrive(45,0.5,0.1);
            }//Blue, Left, Warehouse                   ✔
            else if (Team() == 1 && Mode() == 1 && Side() == 0) {
                telemetry.addData("Team", "Blue");
                telemetry.addData("Side", "Left");
                telemetry.addData("Mode", "Score & Warehouse");
                telemetry.update();

                switch (duckPosition) {
                    case LEFT:
                        HoldMid();
                        sleep(500);
                        robot.Screw_Motor.setPower(-0.8);
                        verticalDrive(20,0.1,0.1);
                        horizontalDrive(22,0.1,0.1);
                        verticalDrive(2,0.1,0.1);
                        ScoreLow();
                        sleep(1000);
                        HighHold();
                        robot.Screw_Motor.setPower(0);
                        verticalDrive(-6,0.1,0.1);
                        horizontalDrive(-25,0.1,0.1);
                        turn(-90,0.1,0.1);
                        horizontalDrive(27,0.2,0.1);
                        verticalDrive(-40,0.3,0.1);
                        dropIntake();
                        break;
                    case MIDDLE:
                        HoldMid();
                        sleep(500);
                        robot.Screw_Motor.setPower(-0.8);
                        verticalDrive(20,0.1,0.1);
                        horizontalDrive(22,0.1,0.1);
                        verticalDrive(3,0.1,0.1);
                        ScoreMid();
                        HighHold();
                        sleep(200);
                        ScoreMid();
                        sleep(1000);
                        HighHold();
                        robot.Screw_Motor.setPower(0);
                        verticalDrive(-6,0.1,0.1);
                        horizontalDrive(-25,0.1,0.1);
                        turn(-90,0.1,0.1);
                        horizontalDrive(27,0.2,0.1);
                        verticalDrive(-40,0.3,0.1);
                        dropIntake();
                        break;
                    case RIGHT:
                        HighHold();
                        sleep(500);
                        robot.Screw_Motor.setPower(-1);
                        verticalDrive(20,0.1,0.1);
                        horizontalDrive(22,0.1,0.1);
                        verticalDrive(2,0.1,0.1);
                        ScoreTop();
                        sleep(1000);
                        HighHold();
                        robot.Screw_Motor.setPower(0);
                        verticalDrive(-6,0.1,0.1);
                        horizontalDrive(-25,0.1,0.1);
                        turn(-90,0.1,0.1);
                        horizontalDrive(27,0.2,0.1);
                        verticalDrive(-40,0.3,0.1);
                        dropIntake();
                        break;
                    default:
                        telemetry.addData("it's broken", "");
                        HighHold();
                        robot.Screw_Motor.setPower(-0.8);
                        verticalDrive(20,0.1,0.1);
                        horizontalDrive(22,0.1,0.1);
                        verticalDrive(2,0.1,0.1);
                        ScoreTop();
                        sleep(1000);
                        HighHold();
                        robot.Screw_Motor.setPower(0);
                        verticalDrive(-6,0.1,0.1);
                        horizontalDrive(-25,0.1,0.1);
                        turn(-90,0.1,0.1);
                        horizontalDrive(27,0.2,0.1);
                        verticalDrive(-40,0.3,0.1);
                        dropIntake();
                        break;
                }



                if (duckPosition == DuckPosition.RIGHT){ ScoreTop();}
                sleep(1000);
                HighHold();
                robot.Screw_Motor.setPower(0);
                verticalDrive(-6,0.1,0.1);
                horizontalDrive(-25,0.1,0.1);
                turn(-90,0.1,0.1);
                horizontalDrive(20,0.2,0.1);
                verticalDrive(-40,0.3,0.1);
                dropIntake();

            }//Blue, Left, Score & Warehouse
            else if (Team() == 1 && Mode() == 0 && Side() == 1) {
                telemetry.addData("Team", "Blue");
                telemetry.addData("Side", "Right");
                telemetry.addData("Mode", "Nothing");
                telemetry.update();
            }//Blue, Right, Nothing                    ✔
            else if (Team() == 1 && Mode() == 1 && Side() == 1) {
                telemetry.addData("Team", "Blue");
                telemetry.addData("Side", "Right");
                telemetry.addData("Mode", "Duck, Score, and Warehouse");
                telemetry.update();

                switch (duckPosition) {
                    case LEFT:
                        verticalDrive(11.5,0.1,0.1);
                        dropIntake();
                        horizontalDrive(36.5,0.3,0.1);
                        verticalDrive(-5,0.1,0.1);
                        turn(45,0.1,0.1);
                        spinDuckRed();
                        HighHold();
                        sleep(500);
                        robot.Screw_Motor.setPower(-1);
                        verticalDrive(30,0.1,0.1);
//                            ScoreTop();
//                            sleep(1000);
//                            HighHold();
//                            robot.Screw_Motor.setPower(0);
//                            verticalDrive(-6,0.1,0.1);
//                            horizontalDrive(25,0.1,0.1);
//                            turn(-90,0.1,0.1);
//                            horizontalDrive(-27,0.2,0.1);
//                            verticalDrive(-40,0.3,0.1);
//                            dropIntake();
                        break;
                    case MIDDLE:
                        verticalDrive(11.5,0.1,0.1);
                        dropIntake();
                        horizontalDrive(36.5,0.3,0.1);
                        verticalDrive(-5,0.1,0.1);
                        turn(45,0.1,0.1);
                        spinDuckRed();
                        HoldMid();
                        sleep(500);
                        robot.Screw_Motor.setPower(-1);
                        verticalDrive(30,0.1,0.1);
//                            ScoreMid();
//                            HighHold();
//                            sleep(200);
//                            ScoreMid();
//                            sleep(1000);
//                            HighHold();
//                            robot.Screw_Motor.setPower(0);
//                            verticalDrive(-6,0.1,0.1);
//                            horizontalDrive(25,0.1,0.1);
//                            turn(-90,0.1,0.1);
//                            horizontalDrive(-27,0.2,0.1);
//                            verticalDrive(-40,0.3,0.1);
//                            dropIntake();
                        break;
                    case RIGHT:
                        robot.Screw_Motor.setPower(0.5);
                        verticalDrive(5,0.1,0.1);
                        verticalDrive(-0.5,0.1,0.1);
                        verticalDrive(0.5,0.1,0.1);
                        turn(90,0.1,0.1);
                        horizontalDrive(-2,0.1,0.1);
                        verticalDrive(-15.5,0.1,0.1);
                        sleep(250);
                        spinDuckBlue();
                        horizontalDrive(43,0.1,0.1);
                        verticalDrive(29,0.1,0.1);
                        ScoreTop();
                        horizontalDrive(-42,0.2,0.1);
                        verticalDrive(75,0.2,0.1);
                        break;
                    default:
                        //Todo USE IT OR DELETE
                        duckPosition = DuckPosition.LEFT;
                        telemetry.addData("it's broken", "");
                        telemetry.update();
                        verticalDrive(11.5,0.1,0.1);
                        dropIntake();
                        horizontalDrive(36.5,0.3,0.1);
                        verticalDrive(-5,0.1,0.1);
                        turn(45,0.1,0.1);
                        spinDuckRed();
                        HighHold();
                        robot.Screw_Motor.setPower(-1);
                        verticalDrive(30,0.1,0.1);
//                            ScoreTop();
//                            sleep(1000);
//                            HighHold();
//                            robot.Screw_Motor.setPower(0);
//                            verticalDrive(-6,0.1,0.1);
//                            horizontalDrive(25,0.1,0.1);
//                            turn(-90,0.1,0.1);
//                            horizontalDrive(-27,0.2,0.1);
//                            verticalDrive(-40,0.3,0.1);
//                            dropIntake();
                        break;
                }
            }//Blue, Right, Duck, Score, and Warehouse

            else {
                telemetry.addData("You did the stupid","Not in Initialization");
            }
            telemetry.update();
        }
    }
}