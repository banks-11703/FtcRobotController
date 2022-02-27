package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name="Detector Test", group="test")
public class DetectorTest extends LinearOpMode {
    @Override
    public void runOpMode() {
        Detector detector = new Detector(hardwareMap);
        telemetry.addData("initializing", "done");
        telemetry.update();
        waitForStart();
        while (opModeIsActive()) {
            if (gamepad1.a) {
                //todo added line 18-22
                detector.loadImage();
                telemetry.addData("a is pressed","1");
                Detector.ElementPosition pos = detector.getElementPosition();
                telemetry.addData("seen objects", pos == Detector.ElementPosition.LEFT ? "left" :
                        (pos == Detector.ElementPosition.RIGHT ? "right" : "none"));
                telemetry.update();
            }
            // Default position is NONE so make sure you click 'A' to get the actual position.
            Detector.ElementPosition pos = detector.getElementPosition();
            telemetry.addData("seen objects", pos == Detector.ElementPosition.LEFT ? "left" :
                    (pos == Detector.ElementPosition.RIGHT ? "right" : "none"));
            telemetry.update();
        }
    }
}
