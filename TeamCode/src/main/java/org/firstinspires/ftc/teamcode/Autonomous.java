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

        if (opModeIsActive()) { // ONLY MOVE AT 0.1!!!
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
                        RedRightDSW();
                        break;
                    case MIDDLE:
                        RedRightDSW();
                        break;
                    case RIGHT:
                        RedRightDSW();
                        break;
                    default:
                        duckPosition = DuckPosition.LEFT;
                        telemetry.addData("it's broken", "");
                        RedRightDSW();
                        break;
                }

                telemetry.update();
            }//Red, Left, Duck, Score, and Warehouse   :)
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
                        RedRightSW();
                        break;
                    case MIDDLE:
                        RedRightSW();
                        break;
                    case RIGHT:
                        RedRightSW();
                        break;
                    default:
                        RedRightSW();
                        break;
                }
            }//Red, Right, Score & Warehouse           :/

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
                        BlueLeftSW();
                        break;
                    case MIDDLE:
                        BlueLeftSW();
                        break;
                    case RIGHT:
                        BlueLeftSW();
                        break;
                    default:
                        BlueLeftSW();
                        break;
                }

            }//Blue, Left, Score & Warehouse           :)
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
                       BlueRightDSW();
                        break;
                    case MIDDLE:
                        BlueRightDSW();
                        break;
                    case RIGHT:
                        BlueRightDSW();
                        break;
                    default:
                        BlueRightDSW();
                        break;
                }
            }//Blue, Right, Duck, Score, and Warehouse

            else {
                telemetry.addData("You did the stupid","Not in Initialization");
            }//telemetry not in init feature
            telemetry.update();
        }
    }
}