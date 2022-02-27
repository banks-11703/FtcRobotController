package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
@TeleOp(name = "DriveCode_Player2", group = "Robot 2")
public class Robot_2_DriveCode_Player2 extends Robot_2_DriveCodeCommon {
    @Override
    public void runOpMode() {
        robot.init(hardwareMap);
        telemetry.addData("Status", "Initialized");
        Telemetry();
        robot.cappingServoY.setPosition(0.15);
        waitForStart();
        robot.runtime.reset();
        while (opModeIsActive()) {
            telemetry.update();
            Telemetry();
            if (Override == 0) {
                Player_2_Drive();
            } else if (Override == 1) {
                Player_2_Override();
            }
            Toggles_2P();
            SetServoPosition();
            capping();
            lightSequence();
            sensors();
            autoWarehouse();
            screw();
            intake();
            if (shutdown) {
                screwtoggle = 0;
                intaketoggle = 0;
                robot.Bottom_Intake_Motor.setPower(0);
            }


            telemetry.update();
        }
    }
}




