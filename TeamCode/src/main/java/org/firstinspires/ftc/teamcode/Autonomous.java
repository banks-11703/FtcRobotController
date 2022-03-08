package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;

import java.util.List;

/*
 * This is an example of a more complex path to really test the tuning.
 */
@com.qualcomm.robotcore.eventloop.opmode.Autonomous(group = "drive")
public class Autonomous extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        DriveCodeCommon_Auto drive = new DriveCodeCommon_Auto(hardwareMap);
        boolean button_y_was_pressed = false;
        boolean button_a_was_pressed = false;
        boolean button_x_was_pressed = false;
        boolean button_b_was_pressed = false;
        
        initVuforia();
        telemetry.addData("does it work", "");
        telemetry.update();
        initTfod();
        telemetry.addData("does it work2", "");
        telemetry.update();
        if (tfod != null) {
            tfod.activate();
            tfod.setZoom(1, 16.0 / 9.0);
        }
        telemetry.addData("Unknown", "");
        telemetry.update();
        DuckPosition duckPosition = DuckPosition.UNKNOWN;

        boolean done = false;

        while (!done) {
            boolean button_a_is_pressed = gamepad1.a;
            boolean button_b_is_pressed = gamepad1.b;
            boolean button_x_is_pressed = gamepad1.x;
            boolean button_y_is_pressed = gamepad1.y;

            if (button_y_is_pressed && !button_y_was_pressed) {
                done = true;
                button_y_was_pressed = true;
            } else if (!button_y_is_pressed && button_y_was_pressed) {
                button_y_was_pressed = false;
            }
            if (button_a_is_pressed && !button_a_was_pressed) {
                drive.team++;
                button_a_was_pressed = true;
            } else if (!button_a_is_pressed && button_a_was_pressed) {
                button_a_was_pressed = false;
            }
            if (button_x_is_pressed && !button_x_was_pressed) {
                drive.side++;
                button_x_was_pressed = true;
            } else if (!button_x_is_pressed && button_x_was_pressed) {
                button_x_was_pressed = false;
            }
            if (button_b_is_pressed && !button_b_was_pressed) {
                drive.mode++;
                button_b_was_pressed = true;
            } else if (!button_b_is_pressed && button_b_was_pressed) {
                button_b_was_pressed = false;
            }
            if (drive.Team() == 0 && drive.Mode() == 0 && drive.Side() == 0) {
                telemetry.addData("Team", "Red");
                telemetry.addData("Side", "Left");
                telemetry.addData("Mode", "Nothing");
                telemetry.update();
            } else if (drive.Team() == 0 && drive.Mode() == 2 && drive.Side() == 1) {
                telemetry.addData("Team", "Red");
                telemetry.addData("Side", "Right");
                telemetry.addData("Mode", "Warehouse");
                telemetry.update();
            } else if (drive.Team() == 0 && drive.Mode() == 1 && drive.Side() == 0) {
                telemetry.addData("Team", "Red");
                telemetry.addData("Side", "Left");
                telemetry.addData("Mode", "Duck, Score, and Warehouse");
                telemetry.update();
            } else if (drive.Team() == 0 && drive.Mode() == 0 && drive.Side() == 1) {
                telemetry.addData("Team", "Red");
                telemetry.addData("Side", "Right");
                telemetry.addData("Mode", "Nothing");
                telemetry.update();
            } else if (drive.Team() == 0 && drive.Mode() == 1 && drive.Side() == 1) {
                telemetry.addData("Team", "Red");
                telemetry.addData("Side", "Right");
                telemetry.addData("Mode", "Score & Warehouse");
                telemetry.update();
            } else if (drive.Team() == 1 && drive.Mode() == 0 && drive.Side() == 0) {
                telemetry.addData("Team", "Blue");
                telemetry.addData("Side", "Left");
                telemetry.addData("Mode", "Nothing");
                telemetry.update();
            } else if (drive.Team() == 1 && drive.Mode() == 0 && drive.Side() == 1) {
                telemetry.addData("Team", "Blue");
                telemetry.addData("Side", "Right");
                telemetry.addData("Mode", "Nothing");
                telemetry.update();
            } else if (drive.Team() == 1 && drive.Mode() == 2 && drive.Side() == 0) {
                telemetry.addData("Team", "Blue");
                telemetry.addData("Side", "Left");
                telemetry.addData("Mode", "Warehouse");
                telemetry.update();
            } else if (drive.Team() == 1 && drive.Mode() == 1 && drive.Side() == 1) {
                telemetry.addData("Team", "Blue");
                telemetry.addData("Side", "Right");
                telemetry.addData("Mode", "Duck, Score, and Warehouse");
                telemetry.update();
            } else if (drive.Team() == 1 && drive.Mode() == 1 && drive.Side() == 0) {
                telemetry.addData("Team", "Blue");
                telemetry.addData("Side", "Left");
                telemetry.addData("Mode", "Score & Warehouse");
                telemetry.update();
            } else {
                telemetry.addData("You did the stupid", "Not in Initialization");
                telemetry.update();
            }

        }
        while (!isStarted()) {
            duckPosition = getDuckPosition();
            telemetry.addData("spot", duckPosition);
            telemetry.update();
        }

        waitForStart();

        if (isStopRequested()) return;
        drive.BlueScoreLeftWarehouse();
        telemetry.addData("Entered OpMode", "");
        telemetry.update();
        drive.HighHold();
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
        if (drive.Team() == 0 && drive.Mode() == 0 && drive.Side() == 0) {
            telemetry.addData("Team", "Red");
            telemetry.addData("Side", "Left");
            telemetry.addData("Mode", "Nothing");
            telemetry.update();
        }//Red, Left, Nothing                           ✔
        else if (drive.Team() == 0 && drive.Mode() == 1 && drive.Side() == 0) {
            telemetry.addData("Team", "Red");
            telemetry.addData("Side", "Left");
            telemetry.addData("Mode", "Duck, Score, and Warehouse");
            telemetry.update();

            switch (duckPosition) {
                case LEFT:
                    drive.barcode = 0;
                    break;
                case MIDDLE:
                    drive.barcode = 1;
                    break;
                case RIGHT:
                    drive.barcode = 2;
                    break;
                default:
                    duckPosition = DuckPosition.LEFT;
                    telemetry.addData("it's broken", "");

                    break;
            }

            telemetry.update();
        }//Red, Left, Duck, Score, and Warehouse   :)
        else if (drive.Team() == 0 && drive.Mode() == 2 && drive.Side() == 1) {
            telemetry.addData("Team", "Red");
            telemetry.addData("Side", "Right");
            telemetry.addData("Mode", "Warehouse");
            telemetry.update();
        }//Red, Right, Warehouse                   ✔
        else if (drive.Team() == 0 && drive.Mode() == 0 && drive.Side() == 1) {
            telemetry.addData("Team", "Red");
            telemetry.addData("Side", "Right");
            telemetry.addData("Mode", "Nothing");
            telemetry.update();
        }//Red, Right, Nothing                     ✔
        else if (drive.Team() == 0 && drive.Mode() == 1 && drive.Side() == 1) {
            telemetry.addData("Team", "Red");
            telemetry.addData("Side", "Right");
            telemetry.addData("Mode", "Score & Warehouse");
            telemetry.update();
            switch (duckPosition) {
                case LEFT:
                    drive.barcode = 0;
                    drive.RedScoreRightWarehouse();
                    break;
                case MIDDLE:
                    drive.barcode = 1;
                    drive.RedScoreRightWarehouse();
                    break;
                case RIGHT:
                    drive.barcode = 2;
                    drive.RedScoreRightWarehouse();
                    break;
                default:
                    duckPosition = DuckPosition.LEFT;
                    telemetry.addData("it's broken", "");
                    drive.RedScoreRightWarehouse();
                    break;
            }
        }//Red, Right, Score & Warehouse           :/

        else if (drive.Team() == 1 && drive.Mode() == 0 && drive.Side() == 0) {
            telemetry.addData("Team", "Blue");
            telemetry.addData("Side", "Left");
            telemetry.addData("Mode", "Nothing");
            telemetry.update();
        }//Blue, Left, Nothing                     ✔
        else if (drive.Team() == 1 && drive.Mode() == 2 && drive.Side() == 0) {
            telemetry.addData("Team", "Blue");
            telemetry.addData("Side", "Left");
            telemetry.addData("Mode", "Warehouse");
            telemetry.update();
        }//Blue, Left, Warehouse                   ✔
        else if (drive.Team() == 1 && drive.Mode() == 1 && drive.Side() == 0) {
            telemetry.addData("Team", "Blue");
            telemetry.addData("Side", "Left");
            telemetry.addData("Mode", "Score & Warehouse");
            telemetry.update();

            switch (duckPosition) {
                case LEFT:
                    drive.barcode = 0;
                    drive.BlueScoreLeftWarehouse();
                    break;
                case MIDDLE:
                    drive.barcode = 1;
                    drive.BlueScoreLeftWarehouse();
                    break;
                case RIGHT:
                    drive.barcode = 2;
                    drive.BlueScoreLeftWarehouse();
                    break;
                default:
                    duckPosition = DuckPosition.LEFT;
                    telemetry.addData("it's broken", "");
                    drive.BlueScoreLeftWarehouse();
                    break;
            }

        }//Blue, Left, Score & Warehouse           :)
        else if (drive.Team() == 1 && drive.Mode() == 0 && drive.Side() == 1) {
            telemetry.addData("Team", "Blue");
            telemetry.addData("Side", "Right");
            telemetry.addData("Mode", "Nothing");
            telemetry.update();
        }//Blue, Right, Nothing                    ✔
        else if (drive.Team() == 1 && drive.Mode() == 1 && drive.Side() == 1) {
            telemetry.addData("Team", "Blue");
            telemetry.addData("Side", "Right");
            telemetry.addData("Mode", "Duck, Score, and Warehouse");
            telemetry.update();

            switch (duckPosition) {
                case LEFT:
                    drive.barcode = 0;
                    break;
                case MIDDLE:
                    drive.barcode = 1;
                    break;
                case RIGHT:
                    drive.barcode = 2;
                    break;
                default:
                    duckPosition = DuckPosition.LEFT;
                    telemetry.addData("it's broken", "");

                    break;
            }
        }//Blue, Right, Duck, Score, and Warehouse
        else if (drive.Team() == 1 && drive.Mode() == 0 && drive.Side() == 1) {
            telemetry.addData("Team", "Blue");
            telemetry.addData("Side", "Left");
            telemetry.addData("Mode", "Duck, Score, and Warehouse");
            telemetry.update();

            switch (duckPosition) {
                case LEFT:
                    drive.barcode = 0;
                    break;
                case MIDDLE:
                    drive.barcode = 1;
                    break;
                case RIGHT:
                    drive.barcode = 2;
                    break;
                default:
                    duckPosition = DuckPosition.LEFT;
                    telemetry.addData("it's broken", "");

                    break;
            }
        }//Blue, Left, Duck, Score, and Warehouse
        else {
            telemetry.addData("You did the stupid", "Not in Initialization");
        }//telemetry not in init feature
        telemetry.update();
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
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();
        parameters.vuforiaLicenseKey = VUFORIA_KEY;
        parameters.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;
        vuforia = ClassFactory.getInstance().createVuforia(parameters);
    }
    public void initTfod() {
        int tfodMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("tfodMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        TFObjectDetector.Parameters tfodParameters = new TFObjectDetector.Parameters(tfodMonitorViewId);
        tfodParameters.minResultConfidence = 0.8f;
        tfodParameters.isModelTensorFlow2 = true;
        tfodParameters.inputSize = 320;
        tfod = ClassFactory.getInstance().createTFObjectDetector(tfodParameters, vuforia);
        tfod.loadModelFromAsset(TFOD_MODEL_ASSET, LABELS);
    }
    public DuckPosition waitUntilStart() {
        DuckPosition duckPosition = DuckPosition.UNKNOWN;
        while (!opModeIsActive()) {
            synchronized (runningNotifier) {
                try {
                    duckPosition = getDuckPosition();
                    //duckPosition = DuckPosition.LEFT;
                    runningNotifier.wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return DuckPosition.UNKNOWN;
                }
            }
        }
        return duckPosition;
    }
    /**
     * @return enum of space the duck is in.
     * @description Get position duck is in.
     */
    @SuppressWarnings("JavaDoc")
    public DuckPosition getDuckPosition() {
        // Get list of visible objects
        List<Recognition> updatedRecognitions = tfod.getRecognitions();
        if (updatedRecognitions == null) {
            return DuckPosition.UNKNOWN;
        }
        if (updatedRecognitions.size() > 0) {
            // object seen
            Recognition gameElement = updatedRecognitions.get(0);
            if (gameElement.getLeft() < 300) {

                return DuckPosition.MIDDLE;
            } else {
                // if robot parked on left side, set top
                //otherwise, set bottom
                return DuckPosition.RIGHT;
            }
        } else {
            // object not seen
            return DuckPosition.LEFT;
        }
    }
    private final Object runningNotifier = new Object();
public enum DuckScoring {
    TOP, MIDDLE, BOTTOM
}
public enum DuckPosition {
    LEFT, MIDDLE, RIGHT, UNKNOWN
}
    // TODO: Complete this function
    public DuckScoring convertDuckPosition(int duckPosition, boolean isLeft) {
        return DuckScoring.TOP;
        // Check if isLeft is true
        // if its true,
        // if not, leave be
    }

}
